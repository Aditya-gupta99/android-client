/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.feature.offline.syncCenterPayloads

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mifos.core.common.utils.FileUtils.LOG_TAG
import com.mifos.core.common.utils.Resource
import com.mifos.core.datastore.PrefManager
import com.mifos.core.domain.useCases.AllDatabaseCenterPayloadUseCase
import com.mifos.core.domain.useCases.CreateCenterUseCase
import com.mifos.core.domain.useCases.DeleteAndUpdateCenterPayloadsUseCase
import com.mifos.core.domain.useCases.UpdateCenterPayloadUseCase
import com.mifos.core.entity.center.CenterPayload
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditya Gupta on 16/08/23.
 */
@HiltViewModel
class SyncCenterPayloadsViewModel @Inject constructor(
    private val deleteAndUpdateCenterPayloadsUseCase: DeleteAndUpdateCenterPayloadsUseCase,
    private val updateCenterPayloadUseCase: UpdateCenterPayloadUseCase,
    private val createCenterUseCase: CreateCenterUseCase,
    private val allDatabaseCenterPayloadUseCase: AllDatabaseCenterPayloadUseCase,
    private val prefManager: PrefManager,
) : ViewModel() {

    private val _syncCenterPayloadsUiState = MutableStateFlow<SyncCenterPayloadsUiState>(
        SyncCenterPayloadsUiState.ShowProgressbar,
    )
    val syncCenterPayloadsUiState: StateFlow<SyncCenterPayloadsUiState> = _syncCenterPayloadsUiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private var mCenterPayloads: MutableList<CenterPayload> = mutableListOf()
    private var centerSyncIndex = 0

    fun getUserStatus(): Boolean {
        return prefManager.userStatus
    }

    fun refreshCenterPayloads() {
        _isRefreshing.value = true
        loadDatabaseCenterPayload()
        _isRefreshing.value = false
    }

    fun loadDatabaseCenterPayload() = viewModelScope.launch(Dispatchers.IO) {
        allDatabaseCenterPayloadUseCase().collect { result ->
            when (result) {
                is Resource.Error ->
                    _syncCenterPayloadsUiState.value =
                        SyncCenterPayloadsUiState.ShowError(result.message.toString())

                is Resource.Loading -> Unit

                is Resource.Success -> {
                    mCenterPayloads = result.data?.toMutableList() ?: mutableListOf()
                    _syncCenterPayloadsUiState.value =
                        SyncCenterPayloadsUiState.ShowCenters(mCenterPayloads)
                }
            }
        }
    }

    private fun syncCenterPayload(centerPayload: CenterPayload?) {
        if (centerPayload != null) {
            viewModelScope.launch(Dispatchers.IO) {
                createCenterUseCase(centerPayload).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _syncCenterPayloadsUiState.value =
                                SyncCenterPayloadsUiState.ShowError(result.message.toString())
                            updateCenterPayload(centerPayload)
                        }

                        is Resource.Loading ->
                            _syncCenterPayloadsUiState.value =
                                SyncCenterPayloadsUiState.ShowProgressbar

                        is Resource.Success -> {
                            deleteAndUpdateCenterPayload(
                                mCenterPayloads[centerSyncIndex].id,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun deleteAndUpdateCenterPayload(id: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            deleteAndUpdateCenterPayloadsUseCase(id).collect { result ->
                when (result) {
                    is Resource.Error ->
                        _syncCenterPayloadsUiState.value =
                            SyncCenterPayloadsUiState.ShowError(result.message.toString())

                    is Resource.Loading ->
                        _syncCenterPayloadsUiState.value =
                            SyncCenterPayloadsUiState.ShowProgressbar

                    is Resource.Success -> {
                        centerSyncIndex = 0
                        mCenterPayloads = result.data?.toMutableList() ?: mutableListOf()
                        _syncCenterPayloadsUiState.value =
                            SyncCenterPayloadsUiState.ShowCenters(mCenterPayloads)
                        if (mCenterPayloads.isNotEmpty()) {
                            syncCenterPayload()
                        }
                    }
                }
            }
        }

    private fun updateCenterPayload(centerPayload: CenterPayload?) {
        deleteAndUpdateCenterPayload(
            mCenterPayloads[centerSyncIndex].id,
        )
        if (centerPayload != null) {
            viewModelScope.launch(Dispatchers.IO) {
                updateCenterPayloadUseCase(centerPayload).collect { result ->
                    when (result) {
                        is Resource.Error ->
                            _syncCenterPayloadsUiState.value =
                                SyncCenterPayloadsUiState.ShowError(result.message.toString())

                        is Resource.Loading -> Unit

                        is Resource.Success -> {
                            mCenterPayloads[centerSyncIndex] = result.data ?: CenterPayload()
                            _syncCenterPayloadsUiState.value =
                                SyncCenterPayloadsUiState.ShowCenters(mCenterPayloads)
                            centerSyncIndex += 1
                            if (mCenterPayloads.size != centerSyncIndex) {
                                syncCenterPayload()
                            }
                        }
                    }
                }
            }
        }
    }

    fun syncCenterPayload() {
        for (i in mCenterPayloads.indices) {
            if (mCenterPayloads[i].errorMessage == null) {
                syncCenterPayload(mCenterPayloads[i])
                centerSyncIndex = i
                break
            } else {
                mCenterPayloads[i].errorMessage?.let {
                    Log.d(
                        LOG_TAG,
                        it,
                    )
                }
            }
        }
    }
}
