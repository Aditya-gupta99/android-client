/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.objects.organisations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rajan Maurya on 15/07/16.
 */
@Parcelize
data class DaysInYearType(
    var id: Int? = null,

    var code: String? = null,

    var value: String? = null,
) : Parcelable
