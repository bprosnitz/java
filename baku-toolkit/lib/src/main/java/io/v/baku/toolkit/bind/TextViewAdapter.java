// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.baku.toolkit.bind;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TextViewAdapter<T> extends AbstractViewAdapter<T> {
    private final LayoutInflater mInflater;
    @LayoutRes
    private final int mResource;
    @IdRes
    private final int mFieldId;

    public TextViewAdapter(final Context context) {
        this(context, android.R.layout.simple_list_item_1, android.R.id.text1);
    }

    public TextViewAdapter(final Context context, final @LayoutRes int resource) {
        this(context, resource, 0);
    }

    public TextViewAdapter(final Context context, final @LayoutRes int resource,
                           final @IdRes int textViewResourceId) {
        this(LayoutInflater.from(context), resource, textViewResourceId);
    }

    /**
     * This is a reimplementation of
     * {@link android.widget.ArrayAdapter#getView(int, View, ViewGroup)}
     */
    @Override
    public View getView(final int position, final T value,
                        View view, final ViewGroup parent) {
        if (view == null) {
            view = mInflater.inflate(mResource, parent, false);
        }
        final TextView text;
        try {
            if (mFieldId == 0) {
                text = (TextView) view;
            } else {
                text = (TextView) view.findViewById(mFieldId);
            }
        } catch (final ClassCastException e) {
            throw new IllegalStateException(
                    "TextViewAdapter requires the resource ID to be a TextView", e);
        }

        text.setText(format(position, value));

        return view;
    }

    /**
     * The default implementation passes through the value unaltered if it is a {@link CharSequence}
     * or stringizes otherwise. We avoid agnostic stringization to preserve any Android formatting
     * that might be present, like with {@link android.text.SpannableString}.
     */
    protected CharSequence format(final int position, final T value) {
        return value instanceof CharSequence ? (CharSequence) value : Objects.toString(value);
    }
}