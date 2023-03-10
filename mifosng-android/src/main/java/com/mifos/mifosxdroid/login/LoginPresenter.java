package com.mifos.mifosxdroid.login;

import android.util.Log;

import com.mifos.api.datamanager.DataManagerAuth;
import com.mifos.mifosxdroid.base.BasePresenter;
import com.mifos.utils.MFErrorParser;

import org.apache.fineract.client.models.PostAuthenticationRequest;
import org.apache.fineract.client.models.PostAuthenticationResponse;
import org.mifos.core.apimanager.BaseApiManager;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;

/**
 * Created by Rajan Maurya on 4/6/16.
 */
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManagerAuth dataManagerAuth;
    private Subscription subscription;
    private BaseApiManager baseApiManager;

    @Inject
    public LoginPresenter(DataManagerAuth dataManager) {
        dataManagerAuth = dataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }


    @Override
    public void detachView() {
        if (subscription != null) subscription.unsubscribe();
    }

    public void login(String username, String password,String url,String tenant) {
        getMvpView().showProgressbar(true);

        baseApiManager = org.mifos.core.apimanager.BaseApiManager.Companion.getInstance();
        baseApiManager.createService(username,password,url,tenant,false);


        PostAuthenticationRequest req = new PostAuthenticationRequest();
        req.username(username);
        req.password(password);

        baseApiManager.getAuthApi().authenticate(req, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PostAuthenticationResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e("login","success");
                        getMvpView().showProgressbar(false);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("login",e.toString());
                        getMvpView().showProgressbar(false);
                        String errorMessage;
                        try {
                            if (e instanceof HttpException) {
                                errorMessage = ((HttpException) e).response().errorBody().string();
                                getMvpView().onLoginError( MFErrorParser.parseError(errorMessage)
                                        .getDeveloperMessage());
                            }
                        } catch (Throwable throwable) {
                            RxJavaPlugins.getInstance().getErrorHandler().handleError(throwable);
                        }

                    }
                    @Override
                    public void onNext(PostAuthenticationResponse postAuthenticationResponse) {
                        Log.e("login",postAuthenticationResponse.toString());
                        getMvpView().showProgressbar(false);
                        getMvpView().onLoginSuccessful(postAuthenticationResponse);
                    }
                });

    }
}
