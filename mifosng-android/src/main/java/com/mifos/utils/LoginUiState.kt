package com.mifos.utils

import com.mifos.objects.user.User

sealed class LoginUiState {
    data class ShowProgress(val state: Boolean): LoginUiState()
    data class ShowError(val message: String) : LoginUiState()
    data class ShowLoginSuccessful(val user: User) : LoginUiState()
}
