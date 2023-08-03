package com.mifos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mifos.objects.user.User
import com.mifos.repositories.LoginRepository
import com.mifos.utils.LoginUiState
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.plugins.RxJavaPlugins
import rx.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel(private val loginRepository: LoginRepository) :
    ViewModel() {

//    private var subscription: Subscription? = null

    private val _loginUiState = MutableLiveData<LoginUiState>()
    val loginUiState: LiveData<LoginUiState>
        get() = _loginUiState
    fun login(username: String, password: String) {
        _loginUiState.value = LoginUiState.ShowProgress(true)
        loginRepository.login(username,password)
            .observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : Subscriber<User>() {
                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    val errorMessage: String
                    try {
                        if (e is HttpException) {
                            errorMessage = e.response().errorBody().string()
                            _loginUiState.value = LoginUiState.ShowError(errorMessage)
                        }
                    } catch (throwable: Throwable) {
                        RxJavaPlugins.getInstance().errorHandler.handleError(throwable)
                    }
                }

                override fun onNext(user: User) {
                    _loginUiState.value = LoginUiState.ShowLoginSuccessful(user)
                }
            })
    }
}