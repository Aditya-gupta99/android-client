package com.mifos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mifos.App
import com.mifos.mifosxdroid.R
import com.mifos.objects.SearchedEntity
import com.mifos.repositories.SearchRepository
import com.mifos.states.SearchUiState
import com.mifos.utils.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    private val _searchUiState = MutableLiveData<SearchUiState>()

    val searchUiState : LiveData<SearchUiState>
        get() = _searchUiState

    fun searchResources(query: String?, resources: String?, exactMatch: Boolean?) {
//        val context = App.context
//        if (context != null && !Network.isOnline(context)) {
//            mvpView?.showProgressbar(false)
//            mvpView?.showMessage(R.string.no_internet_connection)
//            return
//        }
        _searchUiState.value = SearchUiState.ShowProgress(true)
        repository.searchResources(query, resources, exactMatch)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<List<SearchedEntity>>() {
                override fun onCompleted() {}
                override fun onError(e: Throwable) {
                    _searchUiState.value = SearchUiState.ShowError(e.message.toString())
                }

                override fun onNext(searchedEntities: List<SearchedEntity>) {
                    if (searchedEntities.isEmpty()) {
                        _searchUiState.value = SearchUiState.ShowNoResultFound(true)
                    } else {
                        _searchUiState.value = SearchUiState.ShowSearchedResources(searchedEntities)
                    }
                }
            })
    }
}