package com.ditros.mcd.syncs;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.content.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.OAuthServer;
import com.ditros.mcd.R;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.interfaces.OAuthServerIntface;

import com.ditros.mcd.model.dto.ObjectSync;
import com.facebook.stetho.json.ObjectMapper;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public  class SyncAdapter extends AbstractThreadedSyncAdapter {

    private appDatabase appDatabases;
    private static final int SYNC_INTERVAL= 10 * 100;
    private AccountManager mAccountManager;
    Context mContext;
    private AccountManagerFuture<Bundle> myFuture = null;
    private static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;


    static String tag_string_reqs = "req_get_sync";

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
        mAccountManager = AccountManager.get(context);
        appDatabases = appDatabase.getInstance(mContext);

    }


    @Override
    public void onPerformSync(Account account, Bundle extras,
                              String authority, ContentProviderClient provider,
                              SyncResult syncResult) {


        //Log.e("hello", String.valueOf(new Date("dd-mm-yyyy HH:mm")));

        SharedPreferences sharedPreferences= getContext().getSharedPreferences(AppConfig.Prefs_infos_login,MODE_PRIVATE);
        //faire la requete de synchronisation
        Log.e("hello",sharedPreferences.getString("access_token",""));
        //String authToken = mAccountManager.blockingGetAuthToken(account, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, true);

        OAuthServerIntface syncService =
                OAuthServer.createService1(OAuthServerIntface.class,sharedPreferences.getString("access_token",""));

        Call<ObjectSync> call = syncService.listdataaccident();

        call.enqueue(new Callback<ObjectSync>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ObjectSync> call, Response<ObjectSync> response) {
               
                if (response.isSuccessful()) {

                    Gson gson = new Gson();
                     String json = gson.toJson(response.body().getData());

                    Log.e("sucessde", String.valueOf(json));

                    //insert all table
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {

                            appDatabases.alcoholTestStatusDAO().insertListAlcoholTestStatus(response.body().
                                    getData().getAlcoholTestStatusResp());

                            appDatabases.alcoholTestTypeDAO().insertListAlcoholTestType(response.body().
                                    getData().getAlcoholTestTypeResp());

                            appDatabases.alcoholTestResultDAO().insertListAlcoholTestResult(response.body().
                                    getData().getAlcoholTestResultResp());

                            appDatabases.cityDAO().insertListCitySeverity(response.body().
                                    getData().getCityResp());

                            appDatabases.accidentBrightnessConditionDAO().insertListAccidentBrightnessCondition(
                                    response.body().getData().getBrightnessConditionResp());
                            appDatabases.accidentClimaticConditionDAO().insertListAccidentClimaticCondition(
                                    response.body().getData().getClimaticConditionResp());

                            appDatabases.accidentImpactTypeDAO().insertListAccidentImpactType(
                                    response.body().getData().getImpactTypeResp());

                            appDatabases.accidentSeverityDAO().insertListAccidentSeverity(
                                    response.body().getData().getAccidentSeverityResp());

                            appDatabases.accidentTypeDAO().insertListAccidentTypeDAO(
                                    response.body().getData().getAccidentTypeResp());

                            appDatabases.municipalityDAO().insertListMunicipality(
                                    response.body().getData().getMunicipalityResp());

                            appDatabases.occupantRestraintSystemDAO().insertListOccupantRestraintSystem(
                                    response.body().getData().getOccupantRestraintSystemResp());

                            appDatabases.personActionDAO().insertListPersonAction(
                                    response.body().getData().getActionResp());

                            appDatabases.personGenderDAO().insertListPersonGenderDAO(
                                    response.body().getData().getGenderResp());


                            appDatabases.personRoadTypeDAO().insertListPersonRoadType(
                                    response.body().getData().getPersonRoadTypeResp());
                            appDatabases.personTraumaSeverityDAO().insertListPersonTraumaSeverityDAO(
                                    response.body().getData().getTraumaSeverityResp());

                            appDatabases.roadBlockDAO().insertListRoadBlockDAO(
                                    response.body().getData().getBlockResp());

                            appDatabases.roadCategoryDAO().insertListRoadCategoryDAO(
                                    response.body().getData().getRoadCategoryResp());

                            appDatabases.roadDAO().insertListRoadDAO(
                                    response.body().getData().getRoadResp());

                            appDatabases.personDrugUseDAO().insertListPersonDrugUse(response.body().getData()
                                    .getPersonDrugUseResp());

                            appDatabases.personAlcoholConsumptionDAO().insertListPersonAlcoholConsumption(response.body().getData()
                                    .getAlcoholConsumptionResp());

                            appDatabases.vehicleSpecialFunctionDAO().insertListVehicleSpecialFunctionDAO(response.body().getData()
                                    .getSpecialFunctionResp());

                            appDatabases.roadIntersectionDAO().insertListRoadIntersectionDAO(
                                    response.body().getData().getRoadIntersectionResp());

                            appDatabases.seatingRangeDAO().insertListSeatingRangeDAO(
                                    response.body().getData().getSeatingRangeResp());

                            appDatabases.seatingPlaceDAO().insertListSeatingPlaceDAO(
                                    response.body().getData().getSeatingPlaceResp());

                            appDatabases.roadSlopSectionDAO().insertListRoadSlopSectionDAO(
                                    response.body().getData().getRoadSlopSectionResp());

                            appDatabases.roadStateDAO().insertListRoadStateDAO(
                                    response.body().getData().getRoadStateResp());

                            appDatabases.roadTrafficControlDAO().insertListRoadTrafficControlDAO(
                                    response.body().getData().getControlResp());

                            appDatabases.roadTypeDAO().insertListRoadTypeDAO(
                                    response.body().getData().getRoadTypeResp());

                            appDatabases.vehicleModelDAO().insertListVehicleModelDAO(
                                    response.body().getData().getVehicleModelResp());

                            appDatabases.vehicleActionDAO().insertListVehicleActionDAO(
                                    response.body().getData().getVehicleActionResp());

                            appDatabases.vehicleTypeDAO().insertListVehicleTypeDAO(
                                    response.body().getData().getVehicleTypeResp());

                            appDatabases.vehiculeBrandDAO().insertListVehicleBrandDAO(
                                    response.body().getData().getBrandResp());

                            appDatabases.virageDAO().insertListVirageDAO(
                                    response.body().getData().getVirageResp());

                            appDatabases.wearingHelmetDAO().insertListWearingHelmetDAO(
                                    response.body().getData().getWearingHelmetResp());

                            return null;
                        }
                    }.execute();


                }else{

                    Log.e("error",response.toString());

                }



            }

            @Override
            public void onFailure(Call<ObjectSync> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });





    }

    public static void configurePeriodicSync(Account account,Context context,int syncInterval,int flextime){

        //Account account = getSyncAccount(context);
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

        Account account = UserAccountUtil.getAccount(context);
        Log.e("account",account.name);
        Log.e("account",account.type);

        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */

        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED,true);

        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL,true);
        ContentResolver.requestSync(account,context.getResources().getString(R.string.contentAuthority),bundle);


    }


    public static void syncAllAccountsPeriodically(Context context) throws Exception {

        Account account = UserAccountUtil.getAccount(context);
        SyncAdapter.configurePeriodicSync(account,context, SYNC_INTERVAL, SYNC_FLEXTIME);

        //SyncAdapterImpl.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);
        /*
         * without calling setsyncautomatically our periodic sync will not be enabled*/
        ContentResolver.setSyncAutomatically(account,context.getResources().getString(R.string.contentAuthority),true);

        ContentResolver.addPeriodicSync(account,
                context.getResources().getString(R.string.contentAuthority), null, SYNC_INTERVAL);

    }


}