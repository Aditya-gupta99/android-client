package com.mifos.repositories

import com.mifos.api.datamanager.DataManagerAuth
import com.mifos.objects.user.User
import rx.Observable
import javax.inject.Inject

class LoginRepository @Inject constructor(private val dataManagerAuth: DataManagerAuth) {
    fun login(username: String, password: String): Observable<User> {
        return dataManagerAuth.login(username, password)
    }
}