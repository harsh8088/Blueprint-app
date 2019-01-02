package com.example.harsh.blueprintapp.mvp.view;


import com.example.model.login.LoginUser;

/**
 * version V1.
 */

public interface LoginView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);
    void getLoginSuccess(LoginUser loginUser);


}
