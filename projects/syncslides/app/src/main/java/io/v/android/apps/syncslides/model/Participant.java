// Copyright 2015 The Vanadium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package io.v.android.apps.syncslides.model;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Someone taking part in a presentation.
 *
 * An instance of this corresponds to one instance of a user running syncslides.
 */
public interface Participant {
    // Name of the user participating, intended to be visible to others. This
    // can be a colloquial name as opposed to a 'real' name or email address
    // extracted from a device or blessing.
    String getUserName();

    // Deck the user may be presenting.
    Deck getDeck();

    // Name of a service with participant information.
    String getEndPoint();

    // Initially get or refresh data from the endPoint.
    void refreshData();

    // Serializable form of this for storing, passing via Message, etc.
    Bundle toBundle();

    // For debugging.
    String toString();

    // For storage in Sets.
    boolean equals(Object obj);

    // For storage in Sets.
    int hashCode();
}