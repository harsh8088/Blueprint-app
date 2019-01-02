package com.example.network;


import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * version V1.
 */

public class ErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // before request
        Request request = chain.request();
Log.d("url",request.url().toString());
        // execute request
        Response response = chain.proceed(request);
        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);

        return response;
    }
}
