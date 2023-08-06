package com.mifos.repositories

import com.mifos.objects.SearchedEntity
import rx.Observable

interface SearchRepository {

    fun searchResources(
        query: String?,
        resources: String?,
        exactMatch: Boolean?
    ): Observable<List<SearchedEntity>>

}