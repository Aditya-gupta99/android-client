package com.mifos.mifosxdroid.injection.module

import com.mifos.api.datamanager.DataManagerAuth
import com.mifos.api.datamanager.DataManagerSearch
import com.mifos.repositories.LoginRepository
import com.mifos.repositories.LoginRepositoryImp
import com.mifos.repositories.SearchRepository
import com.mifos.repositories.SearchRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Aditya Gupta on 06/08/23.
 */

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesLoginRepository(dataManagerAuth: DataManagerAuth) : LoginRepository {
        return LoginRepositoryImp(dataManagerAuth)
    }

    @Provides
    fun providesSearchRepository(dataManagerSearch: DataManagerSearch) : SearchRepository {
        return SearchRepositoryImp(dataManagerSearch)
    }

}