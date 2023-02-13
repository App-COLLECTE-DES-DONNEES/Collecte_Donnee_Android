package com.ditros.mcd;

import android.text.TextUtils;
import android.util.Log;
import com.ditros.mcd.Authenticator.AuthenticationInterceptor;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.interfaces.OAuthServerIntface;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class OAuthServer {

    private static final String siteURL = "https://www.googleapis.com/";
    private static String code = AppConfig.Authcode;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private  static   OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("http://141.95.103.210:8090")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit.Builder builder2 =
            new Retrofit.Builder()
                    .baseUrl("http://141.95.103.210:8099/api/v1/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    private static Retrofit retrofit2 = builder2.build();

    public static OAuthServerIntface oAuthServerIntface = null;

    public static OAuthServerIntface getoAuthServerIntface() {


        if (oAuthServerIntface == null) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(siteURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            oAuthServerIntface = retrofit.create(OAuthServerIntface.class);


        }

        return oAuthServerIntface;

    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, String clientId, String clientSecret) {
        Log.e("username", String.valueOf(TextUtils.isEmpty(clientId)));
        if (!TextUtils.isEmpty(clientId)
                && !TextUtils.isEmpty(clientSecret)) {
            String authToken = Credentials.basic(clientId, clientSecret);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }

        }

        return retrofit.create(serviceClass);
    }

    public static <S> S createService1(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder2.client(httpClient.build());
                retrofit2 = builder2.build();
            }

        }

        return retrofit2.create(serviceClass);
    }
}