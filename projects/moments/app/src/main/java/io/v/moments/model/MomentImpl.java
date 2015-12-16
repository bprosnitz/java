// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.moments.model;

import android.graphics.Bitmap;

import org.joda.time.DateTime;

import io.v.moments.lib.Id;
import io.v.moments.ifc.Moment;

/**
 * A photo and ancillary information.
 */
public class MomentImpl implements Moment {
    private static final String NOT_LETTERS_DIGITS = "[^a-zA-Z0-9]";
    protected final BitMapper mBitMapper;
    private final DateTime mCreationTime;
    private final String mAuthor;
    private final String mCaption;
    private final Id mId;
    private final int mOrdinal;
    private boolean mShouldBeAdvertising = false;

    public MomentImpl(
            BitMapper bitMapper, Id id, int ordinal,
            String author, String caption, DateTime dt,
            boolean shouldBeAdvertising) {
        mBitMapper = bitMapper;
        mOrdinal = ordinal;
        mAuthor = author;
        mCaption = caption;
        mCreationTime = dt;
        mId = id;
        mShouldBeAdvertising = shouldBeAdvertising;
    }

    public boolean shouldBeAdvertising() {
        return mShouldBeAdvertising;
    }

    public void setShouldBeAdvertising(boolean value) {
        mShouldBeAdvertising = value;
    }

    public boolean hasPhoto(Kind kind, Style style) {
        return mBitMapper.exists(getOrdinal(), kind, style);
    }

    public int getOrdinal() {
        return mOrdinal;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public Bitmap getPhoto(Kind kind, Style style) {
        return mBitMapper.readBitmap(getOrdinal(), kind, style);
    }

    public void setPhoto(Kind kind, Style style, byte[] data) {
        mBitMapper.writeBytes(getOrdinal(), kind, style, data);
    }

    public String getName() {
        StringBuilder b = new StringBuilder();
        b.append(mAuthor.replaceAll(NOT_LETTERS_DIGITS, ""));
        b.append(mCaption.replaceAll(NOT_LETTERS_DIGITS, ""));
        return b.toString();
    }

    // A string with no blanks, could be used as a file name.
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(getName());
        b.append("_index_");
        b.append(getOrdinal());
        b.append("_id_");
        b.append(getId());
        return b.toString();
    }

    public Id getId() {
        return mId;
    }

    public String getCaption() {
        return mCaption;
    }

    public DateTime getCreationTime() {
        return mCreationTime;
    }
}