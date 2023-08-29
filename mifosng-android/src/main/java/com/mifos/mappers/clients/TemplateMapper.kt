package com.mifos.mappers.clients

import com.mifos.objects.templates.clients.ClientsTemplate
import org.apache.fineract.client.models.GetClientsTemplateResponse
import org.mifos.core.data.AbstractMapper
import java.util.Date

object TemplateMapper: AbstractMapper<GetClientsTemplateResponse, ClientsTemplate>() {
    override fun mapFromEntity(entity: GetClientsTemplateResponse): ClientsTemplate {
        return ClientsTemplate().apply {
            activationDate = arrayOf(
                entity.activationDate!!.year,
                entity.activationDate!!.month,
                entity.activationDate!!.date
            )
            officeId = entity.officeId!!
            officeOptions = entity.officeOptions?.let { OfficeOptionMapper.mapFromEntityList(it) } ?:  listOf()
            staffOptions = entity.staffOptions?.let { StaffOptionMapper.mapFromEntityList(it) } ?:  listOf()
            savingProductOptions = entity.savingProductOptions?.let {
                SavingProductOptionMapper.mapFromEntityList(
                    it
                )
            } ?:  listOf()
            genderOptions = listOf()
            clientTypeOptions = listOf()
            clientClassificationOptions = listOf()
            clientLegalFormOptions = listOf()
            dataTables = entity.datatables?.let { ClientDataTableMapper.mapFromEntityList(it) } ?:  listOf()
        }
    }

    override fun mapToEntity(domainModel: ClientsTemplate): GetClientsTemplateResponse {
        return GetClientsTemplateResponse().apply {
            activationDate = Date().apply {
                year = domainModel.activationDate[0]
                month = domainModel.activationDate[1]
                date = domainModel.activationDate[2]
            }
            officeId = domainModel.officeId
            officeOptions = OfficeOptionMapper.mapToEntityList(domainModel.officeOptions)
        }
    }
}