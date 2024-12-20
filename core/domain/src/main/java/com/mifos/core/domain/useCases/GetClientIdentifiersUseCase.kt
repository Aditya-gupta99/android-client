/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */
package com.mifos.core.domain.useCases

import com.mifos.core.common.utils.Resource
import com.mifos.core.data.repository.ClientIdentifiersRepository
import com.mifos.core.objects.noncore.Identifier
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class GetClientIdentifiersUseCase @Inject constructor(private val clientIdentifiersRepository: ClientIdentifiersRepository) {

    suspend operator fun invoke(clientId: Int): Flow<Resource<List<Identifier>>> = callbackFlow {
        try {
            trySend(Resource.Loading())
            clientIdentifiersRepository.getClientIdentifiers(clientId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<List<Identifier>>() {
                    override fun onCompleted() {}

                    override fun onError(exception: Throwable) {
                        trySend(Resource.Error(exception.message.toString()))
                    }

                    override fun onNext(identifiers: List<Identifier>) {
                        trySend(Resource.Success(identifiers))
                    }
                })

            awaitClose { channel.close() }
        } catch (exception: Exception) {
            trySend(Resource.Error(exception.message.toString()))
        }
    }
}
