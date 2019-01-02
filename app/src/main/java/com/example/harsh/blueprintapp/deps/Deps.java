package com.example.harsh.blueprintapp.deps;


import com.example.harsh.blueprintapp.MainActivity;
import com.example.harsh.blueprintapp.mvp.ui.LoginActivity;
import com.example.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(MainActivity activity);
    void inject(LoginActivity activity);


}
