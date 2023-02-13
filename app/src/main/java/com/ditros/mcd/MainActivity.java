package com.ditros.mcd;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ditros.mcd.adapter.AdapterListAccident;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.helper.DrawView;
import com.ditros.mcd.interfaces.OAuthServerIntface;
import com.ditros.mcd.model.dto.Content;
import com.ditros.mcd.model.ObjectResponse;
import com.ditros.mcd.syncs.SyncAdapterImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ditros.mcd.syncs.SyncAdapter.syncAllAccountsPeriodically;

public class MainActivity extends AppCompatActivity implements AdapterListAccident.OnItemClickListener {

    private AccountManager accountManager;
    private View parent_view;

    private RecyclerView recyclerView;ProgressDialog progressDialog;
    private AdapterListAccident mAdapter;
    List<Content> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);
        recyclerView = findViewById(R.id.recyclerView);

        progressDialog = new ProgressDialog(this);
        try {
            syncAllAccountsPeriodically(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        items = new ArrayList<>();
        mAdapter = new AdapterListAccident(MainActivity.this, items,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        try {
            loaditems(items);
        } catch (OperationCanceledException | AuthenticatorException | IOException e) {
            throw new RuntimeException(e);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SyncAdapterImpl.initializeSyncAdapter(MainActivity.this);


        accountManager = AccountManager.get(this);
        Log.e("rrr", String.valueOf(accountManager.getAccountsByType(getResources().getString(R.string.accountype)).length));

        if(accountManager.getAccountsByType(getResources().getString(R.string.accountype)).length==0){

            addNewAccount(accountManager);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(getApplicationContext(), com.ditros.mcd.palette.MainActivity.class);
                //startActivity(intent);

                 Intent intent = new Intent(getApplicationContext(),GetAccidentData.class);
                 startActivity(intent);

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }


    private void loaditems(List<Content> items) throws OperationCanceledException,
            AuthenticatorException, IOException {

        showDialog();

        /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
        items.clear();
        mAdapter.notifyDataSetChanged();
        SharedPreferences sharedPreferences= getSharedPreferences(AppConfig.Prefs_infos_login,MODE_PRIVATE);


       // Account[] accounts = accountManager.getAccountsByType(getResources().getString(R.string.accountype));

        //String authToken = accountManager.blockingGetAuthToken(accounts[0], AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS, true);
        //Log.e("sucesse",authToken);
        OAuthServerIntface loginService =
                OAuthServer.createService1(OAuthServerIntface.class,sharedPreferences.getString("access_token",""));

        Call<ObjectResponse> call = loginService.listaccidents();

        call.enqueue(new Callback<ObjectResponse >() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                Log.e("sucessd",response.toString());
                dismissDialog();


                if (response.isSuccessful()) {

                   // Log.e("sucessd", String.valueOf(response.body().getData().getContent()[0].getId()));
                        items.addAll(Arrays.asList(response.body().getData().getContent()));
                    //Log.e("sucessd", String.valueOf(response.body().getData().getAccidentResp()[0].getId()));
                    mAdapter.notifyDataSetChanged();
                    
                }else{
                    Log.e("error",response.toString());
                    Toast.makeText(getApplicationContext(),"erreur lors de l'affichage de la liste des accidents",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {

                dismissDialog();
                Log.d("Error", t.getMessage());
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            loaditems(items);
        } catch (OperationCanceledException | AuthenticatorException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.setMessage("Wait Please");
            progressDialog.show();
        }
    }
    public void dismissDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    private void performLogout() {

        try {
            Account[] accounts = accountManager.getAccountsByType(getResources().getString(R.string.accountype));
            Log.d("PERFORM LOGOUT", String.valueOf(accounts.length));
            if (accounts.length != 0) {
                Log.d("ACCOUNT", accounts[0].toString());
                Log.d("ACCOUNT TOKEN TYPE", accounts[0].type);
                Log.d("ACCOUNT TOKEN NAME", accounts[0].name);

                /*accountManager.clearPassword(accounts[0]);
                accountManager.invalidateAuthToken(AppConfig.ACCOUNT_TYPE,
                        accountManager.getAuthToken(accounts[0], AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS, null, true,
                                accountManagerFuture -> {
                                    try {
                                        Log.d("invalidateAuthToken", accountManagerFuture.getResult().toString());
                                    } catch (android.accounts.OperationCanceledException | AuthenticatorException | IOException e) {
                                        e.printStackTrace();
                                    }
                                }, null).toString());

                if (Build.VERSION.SDK_INT < 22) { // use deprecated method
                    accountManager.removeAccount(accounts[0], null, null);
                } else {
                    accountManager.removeAccountExplicitly(accounts[0]);
                }

                //                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //                intent.putExtra(LoginActivity.ARG_ACCOUNT_TYPE, AppConfig.ACCOUNT_TYPE);
                //                startActivity(intent);
                //                finish();

                Intent intent = getIntent();
                finish();
                startActivity(intent);*/

                SharedPreferences.Editor editor = getSharedPreferences(AppConfig.Prefs_infos_login, MODE_PRIVATE).edit();
                editor.putBoolean("logged", false);
                editor.putString("accesstoken", null);
                editor.putString("refresh_token", null);
                editor.apply();

                AccountManager accountManager = AccountManager.get(getBaseContext());
                accounts = accountManager.getAccounts();
                if(accounts.length>0){
                    accountManager.removeAccount(accounts[0],null,null);
                }


                Intent in = new Intent(MainActivity.this,login.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(in);
                //return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            //ContentResolver.requestSync();
            return true;
        }*/

        switch (item.getItemId()) {
            case R.id.logout:
                performLogout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addNewAccount(AccountManager manager) {

        Log.e("addnewaccount","addnewaccount");

        //Account[] accounts = accountManager.getAccountsByType(context.getResources().getString(R.string.accountype));
        manager.addAccount(getResources().getString(R.string.accountype), AppConfig.AUTHTOKEN_TYPE_FULL_ACCESS, null,
                null, this,
                accountManagerFuture -> {
                    try {
                        accountManagerFuture.getResult();
                    } catch (android.accounts.OperationCanceledException | IOException | AuthenticatorException e) {
                        e.printStackTrace();
                        //Log.e("addnewaccount",String.valueOf(e.printStackTrace()));
                        //MainActivity.this.finish();
                    }
                }, null);
        Log.e("addnewaccount","addnewaccount");
        //Intent in = new Intent(MainActivity.this,login.class);
        // in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(in);
    }


    @Override
    public void onItemClick(View view, Content obj, int position) {

            Log.e("helloall","helloall");
            Intent intent = new Intent(MainActivity.this,GetAccidentData.class);
            intent.putExtra("idacc",obj.getId());
            startActivity(intent);

    }

}