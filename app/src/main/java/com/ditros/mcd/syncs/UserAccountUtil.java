package com.ditros.mcd.syncs;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.ditros.mcd.R;
import com.ditros.mcd.model.User;

public class UserAccountUtil {

    public static String TAG = UserAccountUtil.class.getSimpleName();
    // Use this method to get an instance of Account.
    public static Account getAccount(Context context) {
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "GET_ACCOUNTS not present.");
        }

        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType(context.getResources().getString(R.string.accountype));
        Log.d(TAG, accounts[0].name);

        if (accounts.length > 0) {
            return accounts[0];
           // return accounts[accounts.length - 1];
        } else {
            return null;
        }

    }

}
