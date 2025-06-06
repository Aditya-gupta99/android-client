/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.objects.template.loan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rajan Maurya on 16/07/16.
 */
@Parcelize
data class ChargeOptions(
    var id: Int? = null,

    var name: String? = null,

    var active: Boolean? = null,

    var penalty: Boolean? = null,

    var currency: Currency? = null,

    var amount: Double? = null,

    var chargeTimeType: ChargeTimeType? = null,

    var chargeAppliesTo: ChargeAppliesTo? = null,

    var chargeCalculationType: ChargeCalculationType? = null,

    var chargePaymentMode: ChargePaymentMode? = null,

    var taxGroup: TaxGroup? = null,
) : Parcelable
