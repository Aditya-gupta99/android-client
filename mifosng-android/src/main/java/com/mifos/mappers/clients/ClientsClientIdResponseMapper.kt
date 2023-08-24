package com.mifos.mappers.clients

import com.mifos.objects.client.Client
import com.mifos.objects.client.Status
import org.apache.fineract.client.models.GetClientsClientIdResponse
import org.apache.fineract.client.models.GetClientsClientIdStatus
import org.mifos.core.data.AbstractMapper

object ClientsClientIdResponseMapper : AbstractMapper<GetClientsClientIdResponse, Client>() {

    override fun mapFromEntity(entity: GetClientsClientIdResponse): Client {
        return Client().apply {
            id = entity.id!!
            accountNo = entity.accountNo
            fullname = "${entity.firstname} ${entity.lastname}"
            displayName = entity.displayName
            active = entity.active!!
            officeId = entity.officeId!!
            officeName = entity.officeName
//            activationDate = entity.activationDate?.let { listOf(it) } ?: listOf()     // TODO need to change type to List<int> in sdk
            status = Status().apply {
                id = entity.status?.id!!
                code = entity.status?.code
                value = entity.status?.description
            }
        }
    }

    override fun mapToEntity(domainModel: Client): GetClientsClientIdResponse {
        return GetClientsClientIdResponse().apply {
            id = domainModel.id
            accountNo = domainModel.accountNo
            firstname = domainModel.fullname!!.split(" ")[0]
            lastname = domainModel.fullname!!.split(" ")[1]
            displayName = domainModel.displayName
            officeId = domainModel.officeId
            officeName = domainModel.officeName
            active = domainModel.active
            status = GetClientsClientIdStatus().apply {
                id = domainModel.status?.id
                code = domainModel.status?.code
                description = domainModel.status?.value
            }
//            activationDate
        }
    }

}