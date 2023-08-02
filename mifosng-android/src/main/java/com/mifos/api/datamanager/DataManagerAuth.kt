package com.mifos.api.datamanager

import com.mifos.api.BaseApiManager
import com.mifos.api.mappers.UserMapper
import com.mifos.api.model.LoginData
import com.mifos.objects.user.User
import com.mifos.utils.PrefManager
import org.apache.fineract.client.models.PostAuthenticationRequest
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Rajan Maurya on 19/02/17.
 */
@Singleton
class DataManagerAuth @Inject constructor(private val baseApiManager: BaseApiManager, private val sdkBaseApiManager : org.mifos.core.apimanager.BaseApiManager) {
    /**
     * @param username Username
     * @param password Password
     * @return Basic OAuth
     */
    fun login(username: String, password: String): Observable<User> {

        sdkBaseApiManager.createService(username,password, PrefManager.instanceUrl,PrefManager.tenant,false)
        val body = PostAuthenticationRequest()
        body.username = username
        body.password = password
//        val loginData = LoginData(username,password)
//        return baseApiManager.authApi.authenticate(loginData)
        return sdkBaseApiManager.getAuthApi().authenticate(body,true).map(UserMapper::mapFromEntity)
    }
}