package com.mifos.repositories

import com.mifos.api.datamanager.DataManagerAuth
import com.mifos.objects.user.User
import rx.Observable
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val dataManagerAuth: DataManagerAuth) : LoginRepository {

    override fun login(username: String, password: String): Observable<User> {
        return dataManagerAuth.login(username,password)
    }

}