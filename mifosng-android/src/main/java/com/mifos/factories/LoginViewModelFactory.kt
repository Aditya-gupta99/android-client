package com.mifos.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mifos.repositories.LoginRepository
import com.mifos.viewmodels.LoginViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject


class LoginViewModelFactory @Inject constructor (private val repository: LoginRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("ModelClass ")
    }

}