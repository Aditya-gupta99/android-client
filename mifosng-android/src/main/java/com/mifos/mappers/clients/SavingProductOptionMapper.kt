package com.mifos.mappers.clients

import com.mifos.objects.templates.clients.SavingProductOptions
import org.apache.fineract.client.models.GetClientsSavingProductOptions
import org.mifos.core.data.AbstractMapper

object SavingProductOptionMapper :
    AbstractMapper<GetClientsSavingProductOptions, SavingProductOptions>() {
    override fun mapFromEntity(entity: GetClientsSavingProductOptions): SavingProductOptions {
        return SavingProductOptions().apply {
            id = entity.id!!
            name = entity.name!!
            withdrawalFeeForTransfers = entity.withdrawalFeeForTransfers!!
            allowOverdraft = entity.allowOverdraft!!
        }
    }

    override fun mapToEntity(domainModel: SavingProductOptions): GetClientsSavingProductOptions {
        return GetClientsSavingProductOptions().apply {
            id = domainModel.id
            name = domainModel.name
            withdrawalFeeForTransfers = domainModel.withdrawalFeeForTransfers
            allowOverdraft = domainModel.allowOverdraft
        }
    }
}