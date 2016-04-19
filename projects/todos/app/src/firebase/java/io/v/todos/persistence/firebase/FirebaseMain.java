// Copyright 2016 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.todos.persistence.firebase;

import android.content.Context;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;

import java.util.HashMap;
import java.util.Map;

import io.v.todos.Task;
import io.v.todos.TodoList;
import io.v.todos.persistence.ListEventListener;
import io.v.todos.persistence.MainPersistence;

public class FirebaseMain extends FirebasePersistence implements MainPersistence {
    public static final String TODO_LISTS = "snackoos (TodoList)";

    private final Firebase mTodoLists;
    private final ChildEventListener mTodoListsListener;

    private final ListEventListener<TodoList> mListener;

    private final Map<String, ChildEventListener> mTodoListTaskListeners;
    private final Map<String, TodoListTasksListener> mTodoListTrackers;

    public FirebaseMain(Context context, final ListEventListener<TodoList> listener) {
        super(context);

        mTodoLists = getFirebase().child(TODO_LISTS);

        // This handler will forward events to the passed in listener after ensuring that all the
        // data in the TodoList is set and can automatically update.
        mTodoListsListener = mTodoLists.addChildEventListener(
                new ChildEventListenerAdapter<>(TodoList.class, new ListEventListener<TodoList>() {
                    @Override
                    public void onItemAdd(TodoList item) {
                        // Hook up listeners for the # completed and # tasks. Then forward the item.
                        startWatchTodoListTasks(item);
                        mListener.onItemAdd(item);
                    }

                    @Override
                    public void onItemUpdate(TodoList item) {
                        // Retrieve # completed and # tasks. Then forward the item.
                        setTaskCompletion(item);
                        mListener.onItemUpdate(item);
                    }

                    @Override
                    public void onItemDelete(String key) {
                        // Remove listeners for the # completed and # tasks. Then forward the item.
                        stopWatchTodoListTasks(key);
                        mListener.onItemDelete(key);
                    }
                }));

        mListener = listener;
        mTodoListTaskListeners = new HashMap<>();
        mTodoListTrackers = new HashMap<>();
    }

    @Override
    public void addTodoList(TodoList todoList) {
        mTodoLists.push().setValue(todoList);
    }

    @Override
    public void deleteTodoList(String key) {
        mTodoLists.child(key).removeValue();

        // After deleting the list itself, delete all the orphaned tasks!
        Firebase tasksRef = getFirebase().child(FirebaseTodoList.TASKS).child(key);
        tasksRef.removeValue();
    }

    @Override
    public void completeAllTasks(final TodoList todoList) {
        // Update all child tasks for this key to have done = true.
        Firebase tasksRef = getFirebase().child(FirebaseTodoList.TASKS).child(todoList.getKey());
        tasksRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                // Note: This is very easy to make conflicts with. It may be better to avoid doing
                // this in a batch or to split up the Task into components.
                for (Task t : mTodoListTrackers.get(todoList.getKey()).mTasks.values()) {
                    Task tCopy = t.copy();
                    tCopy.setDone(true);
                    mutableData.child(t.getKey()).setValue(tCopy);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
            }
        });

        // Further, update this todo list to set its last updated time.
        mTodoLists.child(todoList.getKey()).setValue(new TodoList(todoList.getName()));
    }

    private void setTaskCompletion(TodoList todoList) {
        TodoListTasksListener tracker = mTodoListTrackers.get(todoList.getKey());
        tracker.swapTodoList(todoList);
    }

    private void startWatchTodoListTasks(final TodoList todoList) {
        final String todoListKey = todoList.getKey();

        Firebase taskRef = getFirebase().child(FirebaseTodoList.TASKS).child(todoListKey);
        TodoListTasksListener tasksListener = new TodoListTasksListener(todoList);
        ChildEventListener l = taskRef.addChildEventListener(
                new ChildEventListenerAdapter<>(Task.class, tasksListener)
        );
        mTodoListTrackers.put(todoListKey, tasksListener);
        mTodoListTaskListeners.put(todoListKey, l);
    }

    private void stopWatchTodoListTasks(String key) {
        mTodoListTrackers.remove(key).disable(); // Disable; we don't want this listener anymore.
        ChildEventListener l = mTodoListTaskListeners.remove(key);
        getFirebase().removeEventListener(l);
    }

    @Override
    public void close() {
        getFirebase().removeEventListener(mTodoListsListener);
        for (ChildEventListener listener : mTodoListTaskListeners.values()) {
            getFirebase().removeEventListener(listener);
        }
    }

    private class TodoListTasksListener implements ListEventListener<Task> {
        TodoList mTodoList; // The list whose numCompleted and numTasks fields will be updated.
        final Map<String, Task> mTasks;
        boolean disabled = false;

        TodoListTasksListener(TodoList todoList) {
            mTodoList = todoList;
            mTasks = new HashMap<>();
        }

        // Prevent this listener from propagating any more updates.
        // Note: It looks like Firebase will continue firing listeners if they have more data, so
        // call this if you absolutely don't need any more events to fire.
        public void disable() {
            disabled = true;
        }

        public void swapTodoList(TodoList otherList) {
            if (disabled) {
                return;
            }
            assert mTodoList.getKey() == otherList.getKey();
            otherList.numCompleted = mTodoList.numCompleted;
            otherList.numTasks = mTodoList.numTasks;
            mTodoList = otherList;
        }

        @Override
        public void onItemAdd(Task item) {
            if (disabled) {
                return;
            }
            mTodoList.numTasks++;
            if (item.getDone()) {
                mTodoList.numCompleted++;
            }
            mTasks.put(item.getKey(), item);

            mListener.onItemUpdate(mTodoList);
        }

        @Override
        public void onItemUpdate(Task item) {
            if (disabled) {
                return;
            }
            Task oldItem = mTasks.get(item.getKey());
            mTasks.put(item.getKey(), item);

            if (oldItem.getDone() != item.getDone()) {
                if (item.getDone()) {
                    mTodoList.numCompleted++;
                } else {
                    mTodoList.numCompleted--;
                }
                mListener.onItemUpdate(mTodoList);
            }
        }

        @Override
        public void onItemDelete(String key) {
            if (disabled) {
                return;
            }
            mTodoList.numTasks--;
            Task t = mTasks.remove(key);
            if (t.getDone()) {
                mTodoList.numCompleted--;
            }

            mListener.onItemUpdate(mTodoList);
        }
    }
}
