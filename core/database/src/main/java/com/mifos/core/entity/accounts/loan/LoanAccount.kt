/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.entity.accounts.loan

import android.os.Parcelable
import com.mifos.core.database.MifosDatabase
import com.mifos.core.model.MifosBaseModel
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.ModelContainer
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import kotlinx.parcelize.Parcelize

@Parcelize
@Table(database = MifosDatabase::class)
@ModelContainer
data class LoanAccount(
    @Column
    var clientId: Long = 0,

    @Column
    var groupId: Long = 0,

    @Column
    var centerId: Long = 0,

    @PrimaryKey
    var id: Int? = null,

    @Column
    var accountNo: String? = null,

    @Column
    var externalId: String? = null,

    @Column
    var productId: Int? = null,

    @Column
    var productName: String? = null,

    @Column
    @ForeignKey(saveForeignKeyModel = true)
    var status: Status? = null,

    @Column
    @ForeignKey(saveForeignKeyModel = true)
    var loanType: LoanType? = null,

    @Column
    var loanCycle: Int? = null,

    @Column
    var inArrears: Boolean? = null,
) : MifosBaseModel(), Parcelable {

    fun isInArrears(): Boolean? {
        return inArrears
    }
}
