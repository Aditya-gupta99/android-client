package com.mifos.mifosxdroid.passcode;

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.mifos.mifosxdroid.HomeActivity
import com.mifos.mifosxdroid.R
import com.mifos.mifosxdroid.SplashScreenActivity
import com.mifos.mifosxdroid.core.util.Toaster
import com.mifos.mifosxdroid.online.DashboardActivity
import com.mifos.mobile.passcode.MifosPassCodeActivity
import com.mifos.mobile.passcode.utils.EncryptionUtil
import com.mifos.mobile.passcode.utils.PasscodePreferencesHelper
import com.mifos.utils.Constants

class PassCodeActivity : MifosPassCodeActivity() {
    private var currPassCode: String? = null
    private var isToUpdatePassCode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            currPassCode = it.getStringExtra(Constants.CURR_PASSWORD)
            isToUpdatePassCode = it.getBooleanExtra(Constants.IS_TO_UPDATE_PASS_CODE, false)
        }
        if(findViewById<AppCompatButton>(R.id.btn_save).text.equals("Login with biometric")){
            BiometricAuthentication(this).authenticateWithBiometrics()
        }
        if(BiometricAuthentication(this).getBiometricCapabilities()==BiometricCapability.HAS_BIOMETRIC_AUTH){
            val btn=findViewById<AppCompatButton>(R.id.btn_save)
            if (btn.visibility==View.GONE){
                btn.visibility=View.VISIBLE
                btn.text="Login with biometric"
                btn.setOnClickListener {
                    BiometricAuthentication(this).authenticateWithBiometrics()
                }
            }
        }

    }

    override fun showToaster(view: View?, msg: Int) {
        Toaster.show(view, msg, Toaster.SHORT)
    }
    override fun startLoginActivity() {
        startActivity(Intent(this, SplashScreenActivity::class.java))
        finish()
    }
    override fun getLogo(): Int {
        return R.drawable.mifos_logo
    }
    override fun getEncryptionType(): Int {
        return EncryptionUtil.FINERACT_CN
    }
    override fun startNextActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isToUpdatePassCode && !currPassCode.isNullOrEmpty()) {
            PasscodePreferencesHelper(this).apply {
                savePassCode(currPassCode)
            }
        }
        finish()
    }

    override fun onResume() {
        if(findViewById<AppCompatButton>(R.id.btn_save).text.equals("Login with biometric")){
            BiometricAuthentication(this).authenticateWithBiometrics()
        }
        super.onResume()
    }
}
