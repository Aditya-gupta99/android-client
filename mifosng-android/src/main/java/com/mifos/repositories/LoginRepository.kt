package com.mifos.repositories

import com.mifos.objects.user.User
import rx.Observable

interface LoginRepository {

    fun login(username : String , password : String) : Observable<User>

}