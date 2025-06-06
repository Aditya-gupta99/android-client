/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.objects.collectionsheets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Tarun on 31-07-17.
 */
@Parcelize
data class SavingsProduct(
    var isAllowOverdraft: Boolean = false,

    var depositAccountType: String? = null,

    var isEnforceMinRequiredBalance: Boolean = false,

    var id: Int = 0,

    var name: String? = null,

    var isWithHoldTax: Boolean = false,

    var isWithdrawalFeeForTransfers: Boolean = false,
) : Parcelable
