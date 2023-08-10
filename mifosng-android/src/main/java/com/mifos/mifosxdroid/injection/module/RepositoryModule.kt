package com.mifos.mifosxdroid.injection.module

import com.mifos.api.DataManager
import com.mifos.api.datamanager.DataManagerAuth
import com.mifos.api.datamanager.DataManagerCenter
import com.mifos.api.datamanager.DataManagerCharge
import com.mifos.api.datamanager.DataManagerClient
import com.mifos.api.datamanager.DataManagerCollectionSheet
import com.mifos.api.datamanager.DataManagerDataTable
import com.mifos.api.datamanager.DataManagerDocument
import com.mifos.api.datamanager.DataManagerGroups
import com.mifos.api.datamanager.DataManagerLoan
import com.mifos.api.datamanager.DataManagerNote
import com.mifos.api.datamanager.DataManagerOffices
import com.mifos.api.datamanager.DataManagerRunReport
import com.mifos.api.datamanager.DataManagerSavings
import com.mifos.api.datamanager.DataManagerSearch
import com.mifos.api.datamanager.DataManagerStaff
import com.mifos.api.datamanager.DataManagerSurveys
import com.mifos.repositories.ActivateRepository
import com.mifos.repositories.ActivateRepositoryImp
import com.mifos.repositories.CenterDetailsRepository
import com.mifos.repositories.CenterDetailsRepositoryImp
import com.mifos.repositories.CenterListRepository
import com.mifos.repositories.CenterListRepositoryImp
import com.mifos.repositories.ClientChargeRepository
import com.mifos.repositories.ClientChargeRepositoryImp
import com.mifos.repositories.ClientDetailsRepository
import com.mifos.repositories.ClientDetailsRepositoryImp
import com.mifos.repositories.ClientIdentifiersRepository
import com.mifos.repositories.ClientIdentifiersRepositoryImp
import com.mifos.repositories.ClientListRepository
import com.mifos.repositories.ClientListRepositoryImp
import com.mifos.repositories.CollectionSheetRepository
import com.mifos.repositories.CollectionSheetRepositoryImp
import com.mifos.repositories.CreateNewCenterRepository
import com.mifos.repositories.CreateNewCenterRepositoryImp
import com.mifos.repositories.CreateNewClientRepository
import com.mifos.repositories.CreateNewClientRepositoryImp
import com.mifos.repositories.CreateNewGroupRepository
import com.mifos.repositories.CreateNewGroupRepositoryImp
import com.mifos.repositories.DataTableDataRepository
import com.mifos.repositories.DataTableDataRepositoryImp
import com.mifos.repositories.DataTableListRepository
import com.mifos.repositories.DataTableListRepositoryImp
import com.mifos.repositories.DataTableRepository
import com.mifos.repositories.DataTableRepositoryImp
import com.mifos.repositories.DocumentListRepository
import com.mifos.repositories.DocumentListRepositoryImp
import com.mifos.repositories.GroupDetailsRepository
import com.mifos.repositories.GroupDetailsRepositoryImp
import com.mifos.repositories.GroupListRepository
import com.mifos.repositories.GroupListRepositoryImp
import com.mifos.repositories.GroupsListRepository
import com.mifos.repositories.GroupsListRepositoryImp
import com.mifos.repositories.IndividualCollectionSheetDetailsRepository
import com.mifos.repositories.IndividualCollectionSheetDetailsRepositoryImp
import com.mifos.repositories.LoanAccountApprovalRepository
import com.mifos.repositories.LoanAccountApprovalRepositoryImp
import com.mifos.repositories.LoanAccountDisbursementRepository
import com.mifos.repositories.LoanAccountDisbursementRepositoryImp
import com.mifos.repositories.LoanAccountRepository
import com.mifos.repositories.LoanAccountRepositoryImp
import com.mifos.repositories.LoanAccountSummaryRepository
import com.mifos.repositories.LoanAccountSummaryRepositoryImp
import com.mifos.repositories.LoanChargeRepository
import com.mifos.repositories.LoanChargeRepositoryImp
import com.mifos.repositories.LoanRepaymentRepository
import com.mifos.repositories.LoanRepaymentRepositoryImp
import com.mifos.repositories.LoginRepository
import com.mifos.repositories.LoginRepositoryImp
import com.mifos.repositories.NewIndividualCollectionSheetRepository
import com.mifos.repositories.NewIndividualCollectionSheetRepositoryImp
import com.mifos.repositories.NoteRepository
import com.mifos.repositories.NoteRepositoryImp
import com.mifos.repositories.PathTrackingRepository
import com.mifos.repositories.PathTrackingRepositoryImp
import com.mifos.repositories.PinPointClientRepository
import com.mifos.repositories.PinPointClientRepositoryImp
import com.mifos.repositories.SavingsAccountRepository
import com.mifos.repositories.SavingsAccountRepositoryImp
import com.mifos.repositories.SavingsAccountSummaryRepository
import com.mifos.repositories.SavingsAccountSummaryRepositoryImp
import com.mifos.repositories.SearchRepository
import com.mifos.repositories.SearchRepositoryImp
import com.mifos.repositories.SignatureRepository
import com.mifos.repositories.SignatureRepositoryImp
import com.mifos.repositories.SurveyListRepository
import com.mifos.repositories.SurveyListRepositoryImp
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
        dataManagerCenter: DataManagerCenter, dataManagerRunReport: DataManagerRunReport
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

    @Provides
    fun providesClientListRepository(dataManagerClient: DataManagerClient): ClientListRepository {
        return ClientListRepositoryImp(dataManagerClient)
    }

    @Provides
    fun providesGroupsListRepository(dataManagerGroups: DataManagerGroups): GroupsListRepository {
        return GroupsListRepositoryImp(dataManagerGroups)
    }

    @Provides
    fun providesClientChargeRepository(dataManagerCharge: DataManagerCharge): ClientChargeRepository {
        return ClientChargeRepositoryImp(dataManagerCharge)
    }

    @Provides
    fun providesLoanAccountSummary(dataManagerLoan: DataManagerLoan): LoanAccountSummaryRepository {
        return LoanAccountSummaryRepositoryImp(dataManagerLoan)
    }

    @Provides
    fun providesSavingsAccountSummaryRepository(dataManagerSavings: DataManagerSavings): SavingsAccountSummaryRepository {
        return SavingsAccountSummaryRepositoryImp(dataManagerSavings)
    }

    @Provides
    fun providesDataTableRepository(dataManagerDataTable: DataManagerDataTable): DataTableRepository {
        return DataTableRepositoryImp(dataManagerDataTable)
    }

    @Provides
    fun providesPinPointClientRepository(dataManagerClient: DataManagerClient): PinPointClientRepository {
        return PinPointClientRepositoryImp(dataManagerClient)
    }

    @Provides
    fun providesDocumentListRepository(dataManagerDocument: DataManagerDocument): DocumentListRepository {
        return DocumentListRepositoryImp(dataManagerDocument)
    }

    @Provides
    fun providesNoteRepository(dataManagerNote: DataManagerNote): NoteRepository {
        return NoteRepositoryImp(dataManagerNote)
    }

    @Provides
    fun providesSavingAccountRepository(dataManagerSavings: DataManagerSavings): SavingsAccountRepository {
        return SavingsAccountRepositoryImp(dataManagerSavings)
    }

    @Provides
    fun providesLoanAccountRepository(dataManagerLoan: DataManagerLoan): LoanAccountRepository {
        return LoanAccountRepositoryImp(dataManagerLoan)
    }

    @Provides
    fun providesSignatureRepository(dataManagerDocument: DataManagerDocument): SignatureRepository {
        return SignatureRepositoryImp(dataManagerDocument)
    }

    @Provides
    fun providesClientIdentifiersRepository(dataManagerClient: DataManagerClient): ClientIdentifiersRepository {
        return ClientIdentifiersRepositoryImp(dataManagerClient)
    }

    @Provides
    fun providesSurveyListRepository(dataManagerSurveys: DataManagerSurveys): SurveyListRepository {
        return SurveyListRepositoryImp(dataManagerSurveys)
    }

    @Provides
    fun providesLoanChargeRepository(dataManager: DataManager): LoanChargeRepository {
        return LoanChargeRepositoryImp(dataManager)
    }

    @Provides
    fun providesLoanAccountApprovalRepository(dataManager: DataManager): LoanAccountApprovalRepository {
        return LoanAccountApprovalRepositoryImp(dataManager)
    }

    @Provides
    fun providesLoanAccountDisbursementRepository(dataManagerLoan: DataManagerLoan): LoanAccountDisbursementRepository {
        return LoanAccountDisbursementRepositoryImp(dataManagerLoan)
    }

    @Provides
    fun providesLoanRepaymentRepository(dataManagerLoan: DataManagerLoan): LoanRepaymentRepository {
        return LoanRepaymentRepositoryImp(dataManagerLoan)
    }

    @Provides
    fun providesCollectionSheetRepository(dataManager: DataManager): CollectionSheetRepository {
        return CollectionSheetRepositoryImp(dataManager)
    }

    @Provides
    fun providesNewIndividualCollectionSheetRepository(
        dataManager: DataManager, dataManagerCollection: DataManagerCollectionSheet
    ): NewIndividualCollectionSheetRepository {
        return NewIndividualCollectionSheetRepositoryImp(dataManager, dataManagerCollection)
    }

    @Provides
    fun providesIndividualCollectionSheetDetailsRepository(dataManagerCollection: DataManagerCollectionSheet): IndividualCollectionSheetDetailsRepository {
        return IndividualCollectionSheetDetailsRepositoryImp(dataManagerCollection)
    }

    @Provides
    fun providesCreateNewCenterRepository(dataManagerCenter: DataManagerCenter): CreateNewCenterRepository {
        return CreateNewCenterRepositoryImp(dataManagerCenter)
    }

    @Provides
    fun providesCreateNewClientRepository(
        dataManagerClient: DataManagerClient,
        dataManagerOffices: DataManagerOffices,
        dataManagerStaff: DataManagerStaff
    ): CreateNewClientRepository {
        return CreateNewClientRepositoryImp(dataManagerClient, dataManagerOffices, dataManagerStaff)
    }

    @Provides
    fun providesCreateNewGroupRepository(
        dataManagerOffices: DataManagerOffices, dataManagerGroups: DataManagerGroups
    ): CreateNewGroupRepository {
        return CreateNewGroupRepositoryImp(dataManagerOffices, dataManagerGroups)
    }

    @Provides
    fun providesDataTableDataRepository(dataManagerDataTable: DataManagerDataTable): DataTableDataRepository {
        return DataTableDataRepositoryImp(dataManagerDataTable)
    }

    @Provides
    fun providesDataTableListRepository(
        dataManagerLoan: DataManagerLoan,
        dataManager: DataManager,
        dataManagerClient: DataManagerClient
    ): DataTableListRepository {
        return DataTableListRepositoryImp(dataManagerLoan, dataManager, dataManagerClient)
    }
}