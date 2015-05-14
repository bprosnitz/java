// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.v23;

import io.v.v23.verror.VException;

import java.io.EOFException;

/**
 * A stream of values of the provided type.
 * <p>
 * The {@link java.util.Iterator} returned by the {@link java.lang.Iterable#iterator iterator}
 * method returns values produced by calling {@link #readValue readValue} until an
 * {@link EOFException} is encountered. You should be aware that the iterator
 * <p><ul>
 *     <li>does not support {@link java.util.Iterator#remove remove}</li>
 *     <li>will throw {@link RuntimeException} if the underlying call to
 *         {@link #readValue readValue} throws {@link VException}.
 *         The {@link RuntimeException#getCause cause} of the {@link RuntimeException}
 *         will be the {@link VException}.</li>
 * </ul>
 */
public interface InputChannel<T> extends Iterable<T> {
    /**
     * Returns true iff the next value can be read without blocking.
     */
    boolean available();

    /**
     * Reads the next value from the channel, blocking if the value is unavailable.
     *
     * @return                 the next value from the channel.
     * @throws EOFException    if the graceful EOF is reached.
     * @throws VException      if a read error is encountered.
     */
    T readValue() throws EOFException, VException;
}