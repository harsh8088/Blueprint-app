package com.example.harsh.blueprintapp.mvp.presenter;


import com.example.harsh.blueprintapp.mvp.view.LoginView;
import com.example.model.login.LoginUser;
import com.example.network.NetworkError;
import com.example.network.Service;

import java.util.HashMap;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * version V1.
 */

public class LoginPresenter {
    private final Service service;
    private final LoginView view;
    private CompositeSubscription subscriptions;

    public LoginPresenter(Service service, LoginView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void login(HashMap<String, String> hashMap) {
        view.showWait();

        Subscription subscription = service.login(new Service.GetCityListCallback2() {
            @Override
            public void onSuccess(LoginUser loginUser) {
                view.removeWait();

                view.getLoginSuccess(loginUser);

            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        }, hashMap);

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
