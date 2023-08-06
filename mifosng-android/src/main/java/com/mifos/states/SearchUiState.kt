package com.mifos.states

import com.mifos.objects.SearchedEntity

sealed class SearchUiState {

    data class ShowProgress(val state: Boolean) : SearchUiState()

    data class ShowError(val message: String) : SearchUiState()

    data class ShowSearchedResources(val searchedEntities: List<SearchedEntity>) : SearchUiState()

    data class ShowNoResultFound(val state : Boolean) : SearchUiState()

}