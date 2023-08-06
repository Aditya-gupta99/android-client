package com.mifos.mifosxdroid.injection.module

import com.mifos.api.DataManager
import com.mifos.api.datamanager.DataManagerAuth
import com.mifos.api.datamanager.DataManagerCenter
import com.mifos.api.datamanager.DataManagerClient
import com.mifos.api.datamanager.DataManagerDataTable
import com.mifos.api.datamanager.DataManagerGroups
import com.mifos.api.datamanager.DataManagerRunReport
import com.mifos.api.datamanager.DataManagerSearch
import com.mifos.repositories.ActivateRepository
import com.mifos.repositories.ActivateRepositoryImp
import com.mifos.repositories.CenterDetailsRepository
import com.mifos.repositories.CenterDetailsRepositoryImp
import com.mifos.repositories.CenterListRepository
import com.mifos.repositories.CenterListRepositoryImp
import com.mifos.repositories.ClientDetailsRepository
import com.mifos.repositories.ClientDetailsRepositoryImp
import com.mifos.repositories.GroupDetailsRepository
import com.mifos.repositories.GroupDetailsRepositoryImp
import com.mifos.repositories.GroupListRepository
import com.mifos.repositories.GroupListRepositoryImp
import com.mifos.repositories.LoginRepository
import com.mifos.repositories.LoginRepositoryImp
import com.mifos.repositories.PathTrackingRepository
import com.mifos.repositories.PathTrackingRepositoryImp
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
    fun providesLoginRepository(dataManagerAuth: DataManagerAuth): LoginRepository {
        return LoginRepositoryImp(dataManagerAuth)
    }

    @Provides
    fun providesSearchRepository(dataManagerSearch: DataManagerSearch): SearchRepository {
        return SearchRepositoryImp(dataManagerSearch)
    }

    @Provides
    fun providesCenterListRepository(dataManagerCenter: DataManagerCenter): CenterListRepository {
        return CenterListRepositoryImp(dataManagerCenter)
    }

    @Provides
    fun providesPathTrackingRepository(dataTable: DataManagerDataTable): PathTrackingRepository {
        return PathTrackingRepositoryImp(dataTable)
    }

    @Provides
    fun providesGroupListRepository(dataManager: DataManager): GroupListRepository {
        return GroupListRepositoryImp(dataManager)
    }

    @Provides
    fun providesClientDetailsRepository(dataManagerClient: DataManagerClient): ClientDetailsRepository {
        return ClientDetailsRepositoryImp(dataManagerClient)
    }

    @Provides
    fun providesCenterDetailsRepository(
        dataManagerCenter: DataManagerCenter,
        dataManagerRunReport: DataManagerRunReport
    ): CenterDetailsRepository {
        return CenterDetailsRepositoryImp(dataManagerCenter, dataManagerRunReport)
    }

    @Provides
    fun providesGroupDetailsRepository(dataManagerGroups: DataManagerGroups): GroupDetailsRepository {
        return GroupDetailsRepositoryImp(dataManagerGroups)
    }

    @Provides
    fun providesActivateRepository(
        dataManagerClient: DataManagerClient,
        dataManagerCenter: DataManagerCenter,
        dataManagerGroups: DataManagerGroups
    ): ActivateRepository {
        return ActivateRepositoryImp(dataManagerClient, dataManagerCenter, dataManagerGroups)
    }
}