package com.example.harsh.blueprintapp.mvp.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harsh.blueprintapp.R;
import com.example.harsh.blueprintapp.base.BaseActivity;
import com.example.harsh.blueprintapp.deps.DaggerDeps;
import com.example.harsh.blueprintapp.deps.Deps;
import com.example.harsh.blueprintapp.mvp.presenter.LoginPresenter;
import com.example.harsh.blueprintapp.mvp.view.LoginView;
import com.example.harsh.blueprintapp.session.SessionManager;
import com.example.model.login.LoginUser;
import com.example.network.Constants;
import com.example.network.NetworkModule;
import com.example.network.Service;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity implements LoginView {
    EditText mEmailText;
    EditText mPasswordText;

    @Inject
    public Service service;
    LoginPresenter presenter;


    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_login);
        mEmailText = findViewById(R.id.et_email);
        mPasswordText = findViewById(R.id.et_pass);
        dependencyInjection();
        // Session Manager
        session = new SessionManager(getApplicationContext());

    }

    public void onLoginClick(View view) {
        if (!TextUtils.isEmpty(mEmailText.getText()) && !TextUtils.isEmpty(mPasswordText.getText())) {
            if (isEmailValid(mEmailText.getText().toString())) {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("email", mEmailText.getText().toString());
                hashMap.put("password", mPasswordText.getText().toString());
                hashMap.put("fcmid", "222222");
                presenter = new LoginPresenter(service, this);
                presenter.login(hashMap);
            } else {
                Toast.makeText(this, "This is not a valid email address", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Entries cant be null", Toast.LENGTH_SHORT).show();
        }

    }


    public void forgotPassword(View view) {

    }


    public void gotohome(View view) {

  /*      Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);*/
        if (!TextUtils.isEmpty(mEmailText.getText()) && !TextUtils.isEmpty(mPasswordText.getText())) {
            if (isEmailValid(mEmailText.getText().toString())) {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("email", mEmailText.getText().toString());
                hashMap.put("password", mPasswordText.getText().toString());
                hashMap.put("fcmid", "222222");


                LoginPresenter presenter = new LoginPresenter(service, this);
                presenter.login(hashMap);
            } else {
                Toast.makeText(LoginActivity.this, "This is not a valid email address", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Entries cant be null", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }

    public void dependencyInjection() {
        File cacheFile = new File(getCacheDir(), "responses");
        Deps deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
        deps.inject(this);

    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(this, "Username or Password is wrong", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void getLoginSuccess(LoginUser loginUser) {
        if (loginUser.getChef().getStatus()) {
            Constants.token = loginUser.getChef().getData().getToken();
            Log.d("token", Constants.token);
            createLoginSession(Constants.token);
            Toast.makeText(this, loginUser.getChef().getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, loginUser.getChef().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }


    public void createLoginSession(String token) {
        session.createLoginSession(token);
    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
