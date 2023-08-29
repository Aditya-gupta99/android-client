package com.mifos.mappers.clients

import com.mifos.objects.noncore.ColumnHeader
import com.mifos.objects.noncore.ColumnValue
import com.mifos.objects.noncore.DataTable
import org.apache.fineract.client.models.GetClientsColumnHeaderData
import org.apache.fineract.client.models.GetClientsDataTables
import org.mifos.core.data.AbstractMapper

object ClientDataTableMapper : AbstractMapper<GetClientsDataTables, DataTable>() {
    override fun mapFromEntity(entity: GetClientsDataTables): DataTable {
        return DataTable().apply {
            applicationTableName = entity.applicationTableName
            registeredTableName = entity.registeredTableName
            columnHeaderData = entity.columnHeaderData!!.map {
                ColumnHeader().apply {
                    dataTableColumnName = it.columnName
                    columnType = it.columnType
                    columnLength = it.columnLength
                    columnDisplayType = it.columnDisplayType
                    columnNullable = it.isColumnNullable
                    columnPrimaryKey = it.isColumnPrimaryKey
                    columnValues = it.columnValues?.map { cv ->
                        ColumnValue().apply {
                            value = cv
                        }
                    }!!
                }
            }
        }
    }

    override fun mapToEntity(domainModel: DataTable): GetClientsDataTables {
        return GetClientsDataTables().apply {
            applicationTableName = domainModel.applicationTableName
            registeredTableName = domainModel.registeredTableName
            columnHeaderData = domainModel.columnHeaderData.map {
                GetClientsColumnHeaderData().apply {
                    columnName = it.dataTableColumnName
                    columnType = it.columnType
                    columnLength = it.columnLength
                    columnDisplayType = it.columnDisplayType
                    isColumnNullable = it.columnNullable
                    isColumnPrimaryKey = it.columnPrimaryKey
                }
            }
        }
    }
}