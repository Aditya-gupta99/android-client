package com.mifos.repositories

import com.mifos.objects.user.User
import retrofit2.Response
import rx.Observable

interface LoginRepository {

    suspend fun login(username : String, password : String) : Response<User>

}