package com.mifos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mifos.objects.user.User
import com.mifos.repositories.LoginRepository
import com.mifos.states.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginUiState = MutableLiveData<LoginUiState>()
    val loginUiState: LiveData<LoginUiState>
        get() = _loginUiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiState.postValue(LoginUiState.ShowProgress(true))

        val response = loginRepository.login(username, password)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<User>) {
        if (response.isSuccessful) {
            _loginUiState.postValue(response.body()?.let { LoginUiState.ShowLoginSuccessful(it) })
        } else {
            _loginUiState.postValue(LoginUiState.ShowError(response.message()))
        }
    }

}