package com.ditros.mcd.syncs;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OperationCanceledException;
import android.content.*;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.ditros.mcd.R;
import com.ditros.mcd.app.AppConfig;

public class SyncAdapterImpl extends AbstractThreadedSyncAdapter {

    public final static String TAG = SyncAdapterImpl.class.getSimpleName();
    private Context mContext;
    private static final int SYNC_INTERVAL= 60 * 100;
    private static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
    private static ContentResolver mContentResolver;

    public SyncAdapterImpl(Context context) {

        super(context, true);
        mContext = context;
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        try {
            SyncAdapterImpl.performSync(mContext, account, extras, authority, provider, syncResult);
        } catch (OperationCanceledException e) {
        }
    }

    private static void performSync(Context context, Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult)
            throws OperationCanceledException {

        mContentResolver = context.getContentResolver();
        Log.i(TAG, "performSync: " + account.toString());
        //mContentResolver.delete(AppConfig.CONTENT_URI,null,null);


        //request to get data from server to phone date now
        //get list of tracking
        String result="";
        //getTrackingData( result);

        Log.i(TAG, "performSync: " + provider.toString());
        //This is where the magic will happen!
    }

  /*  private static void getTrackingData(String result)  {

        try{
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for(int i=0;i<jsonArray.length();i++){

                JSONObject jsondetails = jsonArray.getJSONObject(i);

                ContentValues contentValues = new ContentValues();
                contentValues.put(AppConfig.TRACKING_REF,jsondetails.getString(""));
                contentValues.put(AppConfig.TRACKING_LOCALISATION,jsondetails.getString(""));
                contentValues.put(AppConfig.TRACKING_DESC,jsondetails.getString(""));
                contentValues.put(AppConfig.TRACKING_DATEDEBUT,jsondetails.getString(""));
                contentValues.put(AppConfig.TRACKING_DATEFIN,jsondetails.getString(""));

                mContentResolver.insert(AppConfig.CONTENT_URI,contentValues);

            }
            Log.i(TAG,"Sync complete ..."+jsonArray.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    public static void configurePeriodicSync(Context context,int syncInterval,int flextime){

        Account account = getSyncAccount(context);
        String authority = context.getResources().getString(R.string.contentAuthority);

        //we can enable inexact timers in our periodic sync
        SyncRequest request = new SyncRequest.Builder().
                syncPeriodic(syncInterval,flextime)
                .setSyncAdapter(account,authority)
                .setExtras(new Bundle()).build();
        ContentResolver.requestSync(request);

    }

    /*
       Helper method to have the sync adapter sync immediately
       the context used to access the account service
      */
    public static void syncImmediately(Context context){
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED,true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL,true);
        ContentResolver.requestSync(getSyncAccount(context),context.getResources().getString(R.string.contentAuthority),bundle);
    }
    /*
     * Helper method to get the fake account to be used with syncadapter or make a new one */
    public static Account getSyncAccount(Context context){
        //Get an instance of the Android account manager
        Log.e("initialize","account");
        AccountManager accountManager  = (AccountManager)context.getSystemService(Context.ACCOUNT_SERVICE);

        //Create the account type and default account
        Account newAccount = new Account("test",context.getResources().getString(R.string.accountype));
        Log.e("initializes",String.valueOf(accountManager.getPassword(newAccount)));
        accountManager.setPassword(newAccount,null);
        Log.e("initializes",String.valueOf(accountManager.getPassword(newAccount)));
        //If the password doesnt exist
        if(null == accountManager.getPassword(newAccount)){
            //add account and account type , no password or user data
            //if successful return the account object otherwise report an error

            if(!accountManager.addAccountExplicitly(newAccount,"",null)){
                return null;
            }

            //if you dont set android:syncable = true in your provider element in the manifest,
            // then call ContentResolver.setissyncable(account,AUTHORITY,1)
            Log.e("initialize","setissyncable");
            //ContentResolver.setIsSyncable(newAccount,AUTHORITY,1);
            Log.e("initialize","onAccountCreated");
            onAccountCreated(newAccount,context);

        }

        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context){

        SyncAdapterImpl.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);
        /*
         * without calling setsyncautomatically our periodic sync will not be enabled*/
        ContentResolver.setSyncAutomatically(newAccount,context.getResources().getString(R.string.contentAuthority),true);
        /*
         * Finally, lets do a sync to get things started
         *
         */
        syncImmediately(context);

    }

    public static  void initializeSyncAdapter(Context context){
        Log.e("initialize","initialize");

        getSyncAccount(context); }


}
