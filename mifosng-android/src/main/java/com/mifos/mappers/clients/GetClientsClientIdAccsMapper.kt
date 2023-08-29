package com.mifos.mappers.clients

import com.mifos.objects.accounts.ClientAccounts
import org.apache.fineract.client.models.GetClientsClientIdAccountsResponse
import org.mifos.core.data.AbstractMapper

object GetClientsClientIdAccsMapper :
    AbstractMapper<GetClientsClientIdAccountsResponse, ClientAccounts>() {

    override fun mapFromEntity(entity: GetClientsClientIdAccountsResponse): ClientAccounts {
        return ClientAccounts().apply {
            savingsAccounts = entity.savingsAccounts?.let { SavingsAccMapper.mapFromEntityList(it) }
                ?: listOf()
            loanAccounts = entity.loanAccounts?.let { LoanAccMapper.mapFromEntityList(it) }
                ?: listOf()
        }
    }

    override fun mapToEntity(domainModel: ClientAccounts): GetClientsClientIdAccountsResponse {
        return GetClientsClientIdAccountsResponse().apply {
            savingsAccounts = SavingsAccMapper.mapToEntityList(domainModel.savingsAccounts)
            loanAccounts = LoanAccMapper.mapToEntityList(domainModel.loanAccounts)
        }
    }
}