package com.mifos.repositories

import com.mifos.api.datamanager.DataManagerAuth
import com.mifos.objects.user.User
import retrofit2.Response
import rx.Observable
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val dataManagerAuth: DataManagerAuth) : LoginRepository {

    override suspend fun login(username: String, password: String) : Response<User>  {
        return dataManagerAuth.login(username,password)
    }

}