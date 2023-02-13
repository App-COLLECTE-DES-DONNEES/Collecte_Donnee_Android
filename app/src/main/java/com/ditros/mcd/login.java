package com.ditros.mcd;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.interfaces.OAuthServerIntface;
import com.ditros.mcd.model.loginObject;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

public class login extends AccountAuthenticatorActivity {
    TextInputEditText username, password;
    Button btn;
    String authTokenType;
    SharedPreferences prefs;
    private AccountManager accountManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn=findViewById(R.id.login);
        prefs= getSharedPreferences(AppConfig.Prefs_infos_login,MODE_PRIVATE);

        accountManager = AccountManager.get(getBaseContext());
        authTokenType = getIntent().getStringExtra(AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS);

        if(authTokenType!=null){
            authTokenType = AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("username",username.getText().toString());

        OAuthServerIntface loginService =
                OAuthServer.createService(OAuthServerIntface.class);

        Call<loginObject> call = loginService.getAccessTokenObservable(username.getText().toString(),
                Objects.requireNonNull(password.getText()).toString(), AppConfig.clientId,AppConfig.clientSecret,"password");

        call.enqueue(new Callback<loginObject >() {
                         @Override
                         public void onResponse(Call<loginObject> call, Response<loginObject> response) {
                             Log.e("sucess",response.toString());
                             Log.e("sucess",call.toString());
                             Log.e("sucess", String.valueOf(response.body()));

                             if (response.isSuccessful()) {
                                 SharedPreferences.Editor editor = getSharedPreferences(AppConfig.Prefs_infos_login,MODE_PRIVATE).edit();

                                 editor.putString("access_token",response.body().getAccess_token());
                                 editor.putString("refresh_token",response.body().getRefresh_token());
                                 editor.putBoolean("logged",true);
                                 editor.apply();
                                 createAccount(getApplicationContext(),username,password,response);

                                 // user object available
                                 Log.e("sucess", String.valueOf(Objects.requireNonNull(response.body()).getAccess_token()));
                             } else {
                                 // error response, no access to resource?
                                 //APIError error = ErrorUtils.parseError(response);

                                 int code = response.code();
                                 if (code == 401) {
                                     //progressBar.setVisibility(View.GONE);
                                     Toast.makeText(login.this, "UserName or  Password Incorrect", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<loginObject> call, Throwable t) {
                             // something went completely south (like no internet connection)
                             Log.d("Error", t.getMessage());
                         }
                     });


            }


        });


        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    private Boolean createAccount(Context mContext, TextInputEditText username, TextInputEditText password, Response<loginObject> response) {

        String refreshToken = response.body().getRefresh_token();
        Log.e("IS_ACCOUNT", refreshToken);

        Bundle result = null;
        Account account = new Account(username.getText().toString(), mContext.getString(R.string.accountype));

        Log.e("IS_ACCOUNT2", refreshToken);
        Bundle userdata = new Bundle();
        Log.e("IS_ACCOUNT", String.valueOf(getIntent().getBooleanExtra(AppConfig.IS_ACCOUNT, false)));
        userdata.putString(AppConfig.KEY_REFRESH_TOKEN, refreshToken);
        result = new Bundle();
        if (getIntent().getBooleanExtra(AppConfig.IS_ACCOUNT, false)) {

            SharedPreferences.Editor editor = prefs.edit();

            String authtoken = response.body().getAccess_token();


            editor.putString("accesstoken", authtoken);
            Log.e("authtoken",refreshToken);

            editor.apply();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authtoken);

            result.putString(AppConfig.PARAM_USER_PASS, password.getText().toString());
            result.putString(AppConfig.KEY_REFRESH_TOKEN, refreshToken);

            Log.e("authtoken",authtoken);
            accountManager.addAccountExplicitly(account, password.getText().toString(), userdata);
            accountManager.setAuthToken(account, authTokenType, authtoken);

            //requete pour recuperer tous les donnees customer


        } else {
            Log.e("cd",password.getText().toString());
            Log.e("cd",accountManager.getPassword(account));

            accountManager.setPassword(account, password.getText().toString());
        }

        final Intent intent = new Intent();
        intent.putExtras(result);

        setAccountAuthenticatorResult(result);
        setResult(RESULT_OK, intent);
        finish();

        return true;

    }
}
