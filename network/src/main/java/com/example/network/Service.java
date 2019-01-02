package com.example.network;


import com.example.model.login.LoginUser;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

//    public Subscription RegUser(final IRegistrationUseCase callback, RegistrationInput registrationInput) {
//        return networkService.createUser(registrationInput)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .onErrorResumeNext(new Func1<Throwable, Observable<? extends UserRegistration>>() {
//                    @Override
//                    public Observable<? extends UserRegistration> call(Throwable throwable) {
//                        return Observable.error(throwable);
//                    }
//                })
//                .subscribe(new Subscriber<UserRegistration>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onError(new NetworkError(e));
//
//                    }
//
//                    @Override
//                    public void onNext(UserRegistration registration1) {
//                        callback.onSuccess(registration1);
//
//                    }
//                });
//    }
//
//
    public Subscription login(final GetCityListCallback2 callback, HashMap<String, String> hashMap) {
        return networkService.login(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends LoginUser>>() {
                    @Override
                    public Observable<? extends LoginUser> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<LoginUser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(LoginUser registration1) {
                        callback.onSuccess(registration1);

                    }
                });
    }
//
//    public Subscription forgotPassword(final ForgotPasswordCallback callback, HashMap<String, String> networkLogin) {
//        return networkService.forgotPassWord(networkLogin)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ForgorPassword>>() {
//                    @Override
//                    public Observable<? extends ForgorPassword> call(Throwable throwable) {
//                        return Observable.error(throwable);
//                    }
//                })
//                .subscribe(new Subscriber<ForgorPassword>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onError(new NetworkError(e));
//
//                    }
//
//                    @Override
//                    public void onNext(ForgorPassword forgorPassword) {
//                        callback.onSuccess(forgorPassword);
//
//                    }
//                });
//    }
//
//
//    public Subscription getUserInfo(final GetUserInfo callback) {
//        return networkService.getUserInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .onErrorResumeNext(new Func1<Throwable, Observable<? extends GetUserData>>() {
//                    @Override
//                    public Observable<? extends GetUserData> call(Throwable throwable) {
//                        return Observable.error(throwable);
//                    }
//                })
//                .subscribe(new Subscriber<GetUserData>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onError(new NetworkError(e));
//
//                    }
//
//                    @Override
//                    public void onNext(GetUserData getUserData) {
//                        callback.onSuccess(getUserData);
//
//                    }
//                });
//    }


//    public interface IRegistrationUseCase {
//        void onSuccess(UserRegistration userRegistration);
//        void onError(NetworkError networkError);
//    }
//
//
    public interface GetCityListCallback2 {
        void onSuccess(LoginUser cityListResponse);

        void onError(NetworkError networkError);
    }
//
//    public interface ForgotPasswordCallback {
//        void onSuccess(ForgorPassword cityListResponse);
//
//        void onError(NetworkError networkError);
//    }
//
//    public interface GetUserInfo {
//        void onSuccess(GetUserData getUserData);
//
//        void onError(NetworkError networkError);
//    }


}
