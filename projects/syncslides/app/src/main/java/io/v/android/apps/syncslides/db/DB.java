// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.android.apps.syncslides.db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io.v.android.apps.syncslides.model.Deck;
import io.v.android.apps.syncslides.model.Listener;
import io.v.android.apps.syncslides.model.Slide;

/**
 * Provides high-level methods for getting and setting the state of SyncSlides.
 * It is an interface instead of a concrete class to make testing easier.
 */
public interface DB {
    class Singleton {
        private static volatile DB instance;
        public static DB get(Context context) {
            DB result = instance;
            if (instance == null) {
                synchronized (Singleton.class) {
                    result = instance;
                    if (result == null) {
                        // Switch between FakeDB and SyncbaseDB by commenting out one.
                        instance = result = new FakeDB(context);
                        //instance = result = new SyncbaseDB(context);
                    }
                }
            }
            return result;
        }
    }

    /**
     * Perform initialization steps.  This method must be called early in the lifetime
     * of the activity.  As part of the initialization, it might send an intent to
     * another activity.
     *
     * @param activity implements onActivityResult() to call into DB.onActivityResult.
     */
    void init(Activity activity);

    /**
     * If init() sent an intent to another Activity, the result must be forwarded
     * from our app's activity to this method.
     *
     * @return true if the requestCode matches an intent sent by this implementation.
     */
    boolean onActivityResult(int requestCode, int resultCode, Intent data);

    /**
     * Provides a list of elements via an API that fits well with RecyclerView.Adapter.
     */
    interface DBList<E> {

        /**
         * Returns the number of items in the list.
         */
        int getItemCount();

        /**
         * Returns the ith item in the list.
         */
        E get(int i);

        /**
         * Sets the listener for changes to the list.  There can only be one listener.
         */
        void setListener(Listener listener);

        /**
         * Indicates that the list is no longer needed and should stop notifying its listener.
         */
        void discard();
    }

    /**
     * Add user to presenter's question queue.
     *
     * @param identity the user's identity name
     */
    void askQuestion(String identity);

    /**
     * Fetch the list of identities asking questions for the given deck.
     *
     * @param deckId   the deck to fetch
     * @param callback runs on the UI thread when the slide data is loaded
     */
    void getQuestionerList(String deckId, QuestionerListener callback);

    /** Listener for changes in the Q&A queue.
     *
     */
    interface QuestionerListener {
        /**
         * This callback is run on the UI thread to detect changes in the number of questions asked.
         *
         * @param questionerList the list of identities in the questions queue
         */
        void onChange(String[] questionerList);
    }

    /**
     * Gets the list of decks visible to the user.
     *
     * @return a list of decks
     */
    DBList<Deck> getDecks();

    /**
     * Given a deck ID, gets the list of slides visible to the user.
     *
     * @return a list of slides
     */
    DBList<Slide> getSlides(String deckId);

    interface SlidesCallback {
        /**
         * This callback is run on the UI thread once the list of slides is loaded from the DB.
         *
         * @param slides the loaded slide data
         */
        void done(Slide[] slides);
    }

    /**
     * Asynchronously fetch the slides for the given deck.
     *
     * @param deckId the deck to fetch
     * @param callback runs on the UI thread when the slide data is loaded
     */
    void getSlides(String deckId, SlidesCallback callback);
}