package com.mifos.mappers.clients

import com.mifos.objects.accounts.loan.LoanAccount
import com.mifos.objects.accounts.loan.LoanType
import com.mifos.objects.accounts.loan.Status
import org.apache.fineract.client.models.GetClientsLoanAccounts
import org.apache.fineract.client.models.GetClientsLoanAccountsStatus
import org.apache.fineract.client.models.GetClientsLoanAccountsType
import org.mifos.core.data.AbstractMapper

object LoanAccMapper : AbstractMapper<GetClientsLoanAccounts, LoanAccount>() {

    override fun mapFromEntity(entity: GetClientsLoanAccounts): LoanAccount {
        return LoanAccount().apply {
            id = entity.id
            accountNo = entity.accountNo
            externalId = entity.externalId.toString()
            productId = entity.productId
            productName = entity.productName
            status = Status().apply {
                id = entity.status?.id
                code = entity.status?.code
                value = entity.status?.description
                pendingApproval = entity.status?.pendingApproval
                waitingForDisbursal = entity.status?.waitingForDisbursal
                active = entity.status?.active
                closedObligationsMet = entity.status?.closedObligationsMet
                closedWrittenOff = entity.status?.closedWrittenOff
                closedRescheduled = entity.status?.closedRescheduled
                closed = entity.status?.closed
                overpaid = entity.status?.overpaid
            }
            loanType = LoanType().apply {
                id = entity.loanType?.id
                code = entity.loanType?.code
                value = entity.loanType?.description
            }
            loanCycle = entity.loanCycle
        }
    }

    override fun mapToEntity(domainModel: LoanAccount): GetClientsLoanAccounts {
        return GetClientsLoanAccounts().apply {
            id = domainModel.id
            accountNo = domainModel.accountNo
            externalId = domainModel.externalId?.toInt()
            productId = domainModel.productId
            productName = domainModel.productName
            status = GetClientsLoanAccountsStatus().apply {
                id = domainModel.status?.id
                code = domainModel.status?.code
                description = domainModel.status?.value
                pendingApproval = domainModel.status?.pendingApproval
                waitingForDisbursal = domainModel.status?.waitingForDisbursal
                active = domainModel.status?.active
                closedObligationsMet = domainModel.status?.closedObligationsMet
                closedWrittenOff = domainModel.status?.closedWrittenOff
                closedRescheduled = domainModel.status?.closedRescheduled
                closed = domainModel.status?.closed
                overpaid = domainModel.status?.overpaid
            }
            loanType = GetClientsLoanAccountsType().apply {
                id = domainModel.loanType?.id
                code = domainModel.loanType?.code
                description = domainModel.loanType?.value
            }
            loanCycle = domainModel.loanCycle
        }
    }
}