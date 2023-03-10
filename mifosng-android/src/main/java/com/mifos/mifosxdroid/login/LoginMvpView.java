package com.mifos.mifosxdroid.login;

import com.mifos.mifosxdroid.base.MvpView;

import org.apache.fineract.client.models.PostAuthenticationResponse;

/**
 * Created by Rajan Maurya on 4/6/16.
 */
public interface LoginMvpView extends MvpView {

    void showToastMessage(String message);

    void onLoginSuccessful(PostAuthenticationResponse postAuthenticationResponse);

    void onLoginError(String error);

}
