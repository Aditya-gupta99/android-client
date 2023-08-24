package com.mifos.mappers.clients.identifiers

import com.mifos.objects.noncore.IdentifierTemplate
import org.apache.fineract.client.models.GetClientsClientIdIdentifiersResponse
import org.mifos.core.data.AbstractMapper

object IdentifierMapper : AbstractMapper<GetClientsClientIdIdentifiersResponse, IdentifierTemplate>() {

    override fun mapFromEntity(entity: GetClientsClientIdIdentifiersResponse): IdentifierTemplate {
        return IdentifierTemplate()
    }

    override fun mapToEntity(domainModel: IdentifierTemplate): GetClientsClientIdIdentifiersResponse {
        TODO("Not yet implemented")
    }
}