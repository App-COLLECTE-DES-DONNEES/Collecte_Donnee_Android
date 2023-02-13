package com.ditros.mcd.interfaces;

import com.ditros.mcd.model.*;
import com.ditros.mcd.model.dto.AccidentReq;
import com.ditros.mcd.model.dto.ObjectSync;
import retrofit2.Call;
import retrofit2.http.*;

public interface OAuthServerIntface {

    // @Headers("Accept: application/json")

    /**
     * The call to request a token
     */

    @POST("oauth2/v4/token")
    @FormUrlEncoded
    Call<OAuthToken> getAccessToken(
            @Field("code") String code,
            @Field("client_id") String client_id,
            @Field("redirect_uri") String redirect_uri,
            @Field("grant_type") String grant_type
    );

    @FormUrlEncoded
    @POST("/auth/realms/mtckeycloak/protocol/openid-connect/token")
    Call<loginObject> getAccessTokenObservable(
            @Field("username") String email,
            @Field("password") String password,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type);


    @FormUrlEncoded
    @POST("/auth/realms/mtckeycloak/protocol/openid-connect/token?grant_type=refresh_token")
    Call<loginObject> refreshToken(
            @Field("refresh_token") String refresh_token,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret);
    @POST("/login")
    Call<User> basicLogin();

    @POST("accidents")
    Call<AccidentReq> declareaccident(
            @Body AccidentReq accidentReq
    );

    @GET("accidents?page=0&size=10")
    Call<ObjectResponse> listaccidents();

    @GET("accidents/{id}")
    Call<ObjectResponse1> listdetailsaccidents(@Path("id") Long id);

    @GET("accidents/oms-data")
    Call<ObjectSync> listdataaccident();


}


  /*  @FormUrlEncoded
    @POST("/oauth/token")
    Call<AccessToken> getRefreshAccessToken(
            @Field("refresh_token") String refreshToken,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("redirect_uri") String redirectUri*/

    /* final SharedPreferences prefs = this.getSharedPreferences(
                        BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

                APIClient client = ServiceGenerator.createService(APIClient.class);
                Call<AccessToken> call = client.getNewAccessToken(code, API_OAUTH_CLIENTID,
                        API_OAUTH_CLIENTSECRET, API_OAUTH_REDIRECT,
                        "authorization_code");
                call.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        int statusCode = response.code();
                        if(statusCode == 200) {
                            AccessToken token = response.body();
                            prefs.edit().putBoolean("oauth.loggedin", true).apply();
                            prefs.edit().putString("oauth.accesstoken", token.getAccessToken()).apply();
                            prefs.edit().putString("oauth.refreshtoken", token.getRefreshToken()).apply();
                            prefs.edit().putString("oauth.tokentype", token.getTokenType()).apply();

                            // TODO Show the user they are logged in
                        } else {
                            // TODO Handle errors on a failed response
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        // TODO Handle failure
                    }
                });*/




