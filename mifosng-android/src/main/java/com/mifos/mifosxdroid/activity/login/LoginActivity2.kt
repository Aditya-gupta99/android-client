package com.mifos.mifosxdroid.activity.login

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import com.mifos.mifosxdroid.core.MifosBaseActivity

class LoginActivity2 : MifosBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}