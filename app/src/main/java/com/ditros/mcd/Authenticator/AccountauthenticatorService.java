package com.ditros.mcd.Authenticator;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;


import com.ditros.mcd.OAuthServer;
import com.ditros.mcd.R;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.interfaces.OAuthServerIntface;
import com.ditros.mcd.login;
import com.ditros.mcd.model.loginObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountauthenticatorService extends Service {

    private static final String TAG = AccountauthenticatorService.class.getSimpleName();
    private static AccountAuthenticatorImpl sAccountAuthenticator = null;

    public AccountauthenticatorService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {

        IBinder ret = null;
        if (intent.getAction().equals(AccountManager.ACTION_AUTHENTICATOR_INTENT))
            ret = getAuthenticator().getIBinder();
        return ret;

    }

    private AccountAuthenticatorImpl getAuthenticator() {
        if (sAccountAuthenticator == null)
            sAccountAuthenticator = new AccountAuthenticatorImpl(this);
        return sAccountAuthenticator;
    }

    private static class AccountAuthenticatorImpl extends AbstractAccountAuthenticator {

        private Context mContext;
        public AccountAuthenticatorImpl(Context context) {
            super(context);
            mContext = context;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#addAccount(android.
         * accounts.AccountAuthenticatorResponse, java.lang.String,
         * java.lang.String, java.lang.String[], android.os.Bundle)
         */

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options)
                throws NetworkErrorException {

            Bundle result = new Bundle();
            Intent i = new Intent(mContext, login.class);
            i.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

            i.putExtra(mContext.getResources().getString(R.string.accountype), accountType);

            // This key can be anything too. It's just a way of identifying the token's type (used when there are multiple permissions)
            i.putExtra(AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS, authTokenType);

            // This key can be anything too. Used for your reference. Can skip it too.
            i.putExtra(AppConfig.IS_ACCOUNT, true);

            result.putParcelable(AccountManager.KEY_INTENT, i);
            if (options != null) {
                result.putAll(options);
            }
            return result;


        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#confirmCredentials(
         * android.accounts.AccountAuthenticatorResponse,
         * android.accounts.Account, android.os.Bundle)
         */

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) {
            // TODO Auto-generated method stub
            Log.i(TAG, "confirmCredentials");
            return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#editProperties(android
         * .accounts.AccountAuthenticatorResponse, java.lang.String)
         */
        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            // TODO Auto-generated method stub
            Log.i(TAG, "editProperties");
            return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#getAuthToken(android
         * .accounts.AccountAuthenticatorResponse, android.accounts.Account,
         * java.lang.String, android.os.Bundle)
         * his method allows your SyncAdapter, and really your whole app,
         * to acquire the auth_token for making network calls.
         */
        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account,
                                   String authTokenType, Bundle options) throws NetworkErrorException {

            // TODO Auto-generated method stub
            Log.i(TAG, "getAuthToken");

            if (!authTokenType.equals(AppConfig.AUTHTOKEN_TYPE_READ_ONLY) && !authTokenType.equals(AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS)) {
                final Bundle result = new Bundle();
                result.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType");
                return result;
            }



            final AccountManager manager = AccountManager.get(mContext.getApplicationContext());

            final String[] authToken = {manager.peekAuthToken(account, authTokenType)};

            if (TextUtils.isEmpty(authToken[0])) {

                final String password = manager.getPassword(account);
                //final String refreshToken = manager.getUserData(account, AppConfig.KEY_REFRESH_TOKEN);
                if (password != null) {
                    Log.d("Discounty", TAG + " > re-authenticating with existing password and refresh token");
                    OAuthServerIntface refreshService =
                            OAuthServer.createService(OAuthServerIntface.class);

                    Call<loginObject> calls = refreshService.refreshToken(password, AppConfig.clientId, AppConfig.clientSecret);

                    calls.enqueue(new Callback<loginObject>() {
                        @Override
                        public void onResponse(Call<loginObject> call, Response<loginObject> response) {
                            Log.e("sucess", response.toString());
                            Log.e("sucess", call.toString());
                            Log.e("sucess", String.valueOf(response.body()));


                            authToken[0] = response.body().getAccess_token();
                            Log.e("accesstoken", authToken[0]);
                            manager.setUserData(account, AppConfig.KEY_REFRESH_TOKEN, response.body().getRefresh_token());


                        }

                        @Override
                        public void onFailure(Call<loginObject> call, Throwable t) {
                            // something went completely south (like no internet connection)
                            Log.d("Error", t.getMessage());
                        }
                    });

                }

                //manager.invalidateAuthToken(account.type, authToken[0]);

                if (!TextUtils.isEmpty(authToken[0])) {

                    final Bundle result = new Bundle();
                    result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                    result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                    result.putString(AccountManager.KEY_AUTHTOKEN, authToken[0]);
                    //manager.setPassword(account, accessToken.refreshToken);
                    return result;

                }}

                // If you reach here, person needs to login again. or sign up

                // If we get here, then we couldn't access the user's password - so we
                // need to re-prompt them for their credentials. We do that by creating
                // an intent to display our AuthenticatorActivity which is the AccountsActivity in my case.
                final Intent intent = new Intent(mContext, login.class);
                intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
                intent.putExtra(mContext.getResources().getString(R.string.accountype), account.type);
                intent.putExtra("full_access", authTokenType);

                Bundle retBundle = new Bundle();
                retBundle.putParcelable(AccountManager.KEY_INTENT, intent);
                return retBundle;


        }



        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#getAuthTokenLabel(java
         * .lang.String)
         */
        @Override
        public String getAuthTokenLabel(String authTokenType) {
            // TODO Auto-generated method stub
            Log.i(TAG, "getAuthTokenLabel");
            if (AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS.equals(authTokenType)) {
                return AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS;
            } else if (AppConfig.AUTHTOKEN_TYPE_READ_ONLY.equals(authTokenType)) {
                return AppConfig.AUTHTOKE_TYPE_READ_ONLY_LABEL;
            } else {
                return authTokenType + " (Label)";
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#hasFeatures(android
         * .accounts.AccountAuthenticatorResponse, android.accounts.Account,
         * java.lang.String[])
         */
        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
            // TODO Auto-generated method stub
            Log.i(TAG, "hasFeatures: " + features);
            final Bundle result = new Bundle();
            result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
            return result;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * android.accounts.AbstractAccountAuthenticator#updateCredentials(android
         * .accounts.AccountAuthenticatorResponse, android.accounts.Account,
         * java.lang.String, android.os.Bundle)
         */
        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) {
            // TODO Auto-generated method stub
            Log.i(TAG, "updateCredentials");
            return null;
        }
    }

}