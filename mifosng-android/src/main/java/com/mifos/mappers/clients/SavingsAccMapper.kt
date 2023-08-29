package com.mifos.mappers.clients

import com.mifos.objects.accounts.savings.Currency
import com.mifos.objects.accounts.savings.DepositType
import com.mifos.objects.accounts.savings.SavingsAccount
import com.mifos.objects.accounts.savings.Status
import org.apache.fineract.client.models.GetClientsSavingsAccounts
import org.apache.fineract.client.models.GetClientsSavingsAccountsCurrency
import org.apache.fineract.client.models.GetClientsSavingsAccountsStatus
import org.mifos.core.data.AbstractMapper

object SavingsAccMapper : AbstractMapper<GetClientsSavingsAccounts, SavingsAccount>() {

    override fun mapFromEntity(entity: GetClientsSavingsAccounts): SavingsAccount {
        return SavingsAccount().apply {
            id = entity.id
            accountNo = entity.accountNo
            productId = entity.productId
            productName = entity.productName
            depositType = DepositType().apply {
                id = entity.depositType!!.id
                code = entity.depositType!!.code
                value = entity.depositType!!.value
            }
            status = Status().apply {
                id = entity.status?.id
                code = entity.status?.code
                value = entity.status?.value
                submittedAndPendingApproval = entity.status?.submittedAndPendingApproval
                approved = entity.status?.approved
                rejected = entity.status?.rejected
                withdrawnByApplicant = entity.status?.withdrawnByApplicant
                active = entity.status?.active
                closed = entity.status?.closed
            }
            currency = Currency().apply {
                code = entity.currency!!.code
                name = entity.currency!!.name
                nameCode = entity.currency!!.nameCode
                decimalPlaces = entity.currency!!.decimalPlaces
                displaySymbol = entity.currency!!.displaySymbol
                displayLabel = entity.currency!!.displayLabel
            }
        }
    }

    override fun mapToEntity(domainModel: SavingsAccount): GetClientsSavingsAccounts {
        return GetClientsSavingsAccounts().apply {
            id = domainModel.id
            accountNo = domainModel.accountNo
            productId = domainModel.productId
            productName = domainModel.productName
            status = GetClientsSavingsAccountsStatus().apply {
                id = domainModel.status?.id
                code = domainModel.status?.code
                value = domainModel.status?.value
                submittedAndPendingApproval = domainModel.status?.submittedAndPendingApproval
                approved = domainModel.status?.approved
                rejected = domainModel.status?.rejected
                withdrawnByApplicant = domainModel.status?.withdrawnByApplicant
                active = domainModel.status?.active
                closed = domainModel.status?.closed
            }
            currency = GetClientsSavingsAccountsCurrency().apply {
                code = domainModel.currency!!.code
                name = domainModel.currency!!.name
                nameCode = domainModel.currency!!.nameCode
                decimalPlaces = domainModel.currency!!.decimalPlaces
                displaySymbol = domainModel.currency!!.displaySymbol
                displayLabel = domainModel.currency!!.displayLabel
            }
        }
    }
}