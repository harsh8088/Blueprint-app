package com.example.network;


import com.example.model.login.LoginUser;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface NetworkService {
//    @PUT("app/v1/auth/register")
//    Observable<UserRegistration> createUser(@Body RegistrationInput registrationInput);
//
    @FormUrlEncoded
    @POST("app/v1/auth/login")
    Observable<LoginUser> login(@FieldMap HashMap<String, String> hashMap);
//
//    @FormUrlEncoded
//    @POST("app/v1/auth/forget-password")
//    Observable<ForgorPassword> forgotPassWord(@FieldMap HashMap<String, String> networkLogin);
//
//    @GET("app/v1/account/user-info")
//    Observable<GetUserData> getUserInfo();
//
//    @GET("app/v1/account/get-all-cities")
//    Observable<GetCities> getAllCities(@Query("stateId") String stateId);


}
