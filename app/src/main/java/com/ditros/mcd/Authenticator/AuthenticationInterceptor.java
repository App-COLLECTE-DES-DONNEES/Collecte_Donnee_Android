package com.ditros.mcd.Authenticator;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", "Bearer "+authToken)
                .header("lang","fr");

        Request request = builder.build();
        return chain.proceed(request);
    }



























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































}