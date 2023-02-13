package com.ditros.mcd;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.interfaces.OAuthServerIntface;
import com.ditros.mcd.model.*;
import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.utils.Tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAccidentData extends AppCompatActivity {


    /*//Accident
    private String idacc;
    private String dateacc;
    private String heureacc;
    private String munacc;
    private String regacc;
    private String lieuacc;
    private String typeacc;
    private String typeimact;
    private String conditionsmeteorologies;
    private String condluminosite;
    private String graviteacc;

    //route
    private String typeroute;
    private String catfroute;
    private String limitvitesse;
    private String obsroutiers;
    private String etatroute;
    private String carrefour;
    private String contcirculationcarrefour;
    private String virage;
    private String penteroute;

    //vehicule
    private String numvehicule;
    private String typeVehicule;
    private String marqueVehicule;
    private String modeleVehicule;
    private String anneeVehicule;
    private String cylindree;
    private String fonctionVehicule;
    private String manoeuvreVehicule;

    //usager
    private String numpersonne;
    private String numvehiculeoccupant;
    private String numvehiculepieton;
    private String datenaissance;
    private String sexe;
    private String typeusageroute;
    private String placeassise;
    private String placesiege;
    private String rangée;

    private String gravitetraumatisme;
    private String eauipementsecurite;
    private String portcasque;
    private String manoeuvrepieton;
    private String consalcool;
    private String testalcoolemie;
    private String statut;
    private String typetest;
    private String resultattest;
    private String consommationdrogues;
    private String datedelivrance;
    private String age;*/

    public static Boolean acciden = false;
    public static Boolean route = false;
    public static Boolean vehicule = false;
    public static Boolean usager = false;
    public static final AccidentReq accidentReq = new AccidentReq();


    public static List<VehiculeReq> vehiculeReq=new ArrayList<>();
    public static List<PersonDtoReq> personDtoReq =new ArrayList<>();;

    //public static final PersonDtoReq[] personDtoReq = new PersonDtoReq[1];

    private enum State {
        ACCIDENT,
        ROUTE,
        VEHICULE,
        USAGER,
        U,
        U2
        
    }
    private View parent_view;
    State[] array_state = new State[]{State.ACCIDENT, State.ROUTE,
            State.VEHICULE, State.USAGER,State.U,State.U2};

    private View line_first, line_second,line_three;
    private ImageView image_shipping, image_payment, image_confirm,image_confirms;
    private TextView tv_shipping, tv_payment, tv_confirm,tv_confirms;
    private appDatabase appDatabases;
    private int idx_state = 0;
    private int fois ; ProgressDialog progressDialog;
    public Long idacc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getaccidentdata);
        //initToolbar();
        appDatabases = appDatabase.getInstance(getApplicationContext());
        initComponent();

        idacc = getIntent().getLongExtra("idacc",0);
        if(idacc!=0){

            Log.e("tres", String.valueOf(idacc));

            loaddataaccident(idacc);
        }

        progressDialog = new ProgressDialog(GetAccidentData.this);
        fois=0;


        displayFragment(State.ACCIDENT);
    }

    private void loaddataaccident(Long id){

        //showDialog();
        SharedPreferences sharedPreferences= getSharedPreferences(AppConfig.Prefs_infos_login,MODE_PRIVATE);

        OAuthServerIntface loginService =
                OAuthServer.createService1(OAuthServerIntface.class,sharedPreferences.getString("access_token",""));

        Call<ObjectResponse1> call = loginService.listdetailsaccidents(id);
        Log.e("tresy", String.valueOf(id));
        call.enqueue(new Callback<ObjectResponse1>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ObjectResponse1> call, Response<ObjectResponse1> response) {
                Log.e("sucessdy", response.toString());
                Log.e("sucessdy", String.valueOf(response.body().getData()!=null));
                Log.e("sucessdy", String.valueOf(response.body().getData()==null));


                //Log.e("sucessdy", String.valueOf(response.body().getData().getAccidentDate()));
                if(response.body().getData()!=null) {

                    Log.e("sucessdy", String.valueOf(response.body().getData().getId()));
                    Log.e("sucessdy", String.valueOf(response.body().getData().getSpeedLimit()));
                    acciden=true;

                    route=true;

                    accidentReq.setId(response.body().getData().getId());
                    accidentReq.setAccidentDate(response.body().getData().getAccidentDate());
                    accidentReq.setAccidentTime(response.body().getData().getAccidentTime());
                    accidentReq.setMunicipality(0L);
                    accidentReq.setPlace(response.body().getData().getPlace());
                    //accidentReq.setRoad(response.body().getData().getRoad().getId());
                    accidentReq.setRoadType(response.body().getData().getRoadType().getId());
                    accidentReq.setRoadCategory(response.body().getData().getRoadCategory().getId());
                    accidentReq.setSpeedLimit(response.body().getData().getSpeedLimit());
                    accidentReq.setBlock(response.body().getData().getBlock().getId());
                    accidentReq.setRoadState(response.body().getData().getRoadState().getId());
                    accidentReq.setRoadIntersection(response.body().getData().getRoadIntersection().getId());
                    accidentReq.setRoadTrafficControl(response.body().getData().getRoadTrafficControl().getId());
                    accidentReq.setVirage(response.body().getData().getVirage().getId());
                    accidentReq.setRoadSlopSection(response.body().getData().getRoadSlopSection().getId());
                    accidentReq.setAccidentType(response.body().getData().getAccidentType().getId());
                    accidentReq.setImpactType(response.body().getData().getImpactType().getId());
                    accidentReq.setClimaticCondition(response.body().getData().getClimaticCondition().getId());
                    accidentReq.setBrightnessCondition(response.body().getData().getBrightnessCondition().getId());
                    accidentReq.setAccidentSeverity(response.body().getData().getAccidentSeverity().getId());

                    accidentReq.setCity(response.body().getData().getCity().getId());
                    /*vehiculeReq.clear();

                    for(VehicleAccidentResp vehicleAccidentResp:response.body().getData().getVehicules()){

                        VehiculeReq vehiculeReq1 = new VehiculeReq();
                        vehiculeReq1.setVehicleAccidentNumber(vehicleAccidentResp.getVehicleAccidentNumber());
                        vehiculeReq1.setPlate(vehicleAccidentResp.getPlateNumber());
                        vehiculeReq1.setChassis(vehicleAccidentResp.getVehicle().getChassis());
                        vehiculeReq1.setVehicleId(vehicleAccidentResp.getId());
                        vehiculeReq1.setType(vehicleAccidentResp.getVehicle().getType().getId());

                        vehiculeReq1.setBrand(vehicleAccidentResp.getVehicleAccidentNumber());
                        vehiculeReq1.setModel(vehicleAccidentResp.getVehicleAccidentNumber());
                        vehiculeReq1.setFabricationYear(Integer.parseInt(String.valueOf(vehicleAccidentResp.getVehicle().getFabricationYear())));

                        vehiculeReq1.setCylinderCapacity(Integer.parseInt(String.valueOf(vehicleAccidentResp.getVehicleAccidentNumber())));
                        vehiculeReq1.setSpecialFunction(vehicleAccidentResp.getSpecialFunction().getId());
                        vehiculeReq1.setVehicleAction(vehicleAccidentResp.getAction().getId());

                        vehiculeReq.add(vehiculeReq1);
                    }
                    personDtoReq.clear();

                    for(PersonAccidentResp personAccidentResp:response.body().getData().getPersons()){


                        PersonDtoReq personDtoReq1 = new PersonDtoReq();
                        personDtoReq1.setVehicleAccidentNumber(Long.valueOf(personAccidentResp.getVehicleNumber()));
                        personDtoReq1.setLastName(personAccidentResp.getPerson().getLastName());
                        personDtoReq1.setFirstName(personAccidentResp.getPerson().getFirstName());
                        personDtoReq1.setPersonAccidentNumber(personAccidentResp.getPersonNumber());
                        personDtoReq1.setVehicleLinkedPedestrian(personAccidentResp.getVehicleLinkedPedestrian());
                        personDtoReq1.setBirthDate(personAccidentResp.getPerson().getBirthDate());
                        personDtoReq1.setGender(personAccidentResp.getPerson().getGender().getId());
                        personDtoReq1.setRoadType(personAccidentResp.getRoadType().getId());
                        personDtoReq1.setRange(personAccidentResp.getRange().getId());

                        personDtoReq1.setPlace(personAccidentResp.getPlace().getId());
                        personDtoReq1.setId(personAccidentResp.getId());
                        personDtoReq1.setTraumaSeverity(personAccidentResp.getTraumaSeverity().getId());
                        personDtoReq1.setWearingHelmet(personAccidentResp.getWearingHelmet().getId());
                        personDtoReq1.setOccupantRestraintSystem(personAccidentResp.getOccupantRestraintSystem().getId());
                        personDtoReq1.setPersonAction(personAccidentResp.getAction().getId());
                        personDtoReq1.setAlcoholConsumption(personAccidentResp.getAlcoholConsumption().getId());
                        personDtoReq1.setTestStatus(personAccidentResp.getTestStatus().getId());
                        personDtoReq1.setTestType(personAccidentResp.getTestType().getId());
                        personDtoReq1.setTestResult(personAccidentResp.getTestResult().getId());
                        personDtoReq1.setDrugUse(personAccidentResp.getDrugUse().getId());
                        personDtoReq1.setDrivingLicenceYear(personAccidentResp.getDrivingLicenceYear());

                        personDtoReq.add(personDtoReq1);
                    }*/


                }else{
                    acciden=false;
                    route=false;
                }

                dismissDialog();
            }

        @Override
        public void onFailure(Call<ObjectResponse1> call, Throwable t) {

            dismissDialog();
            Log.d("Error", t.getMessage());
        }
    });

    }
    private void initComponent() {
        parent_view = findViewById(android.R.id.content);
        line_first = findViewById(R.id.line_first);
        line_second = findViewById(R.id.line_second);
        line_three = findViewById(R.id.line_three);

        image_shipping = findViewById(R.id.image_shipping);
        image_payment = findViewById(R.id.image_payment);
        image_confirm = findViewById(R.id.image_confirm);
        image_confirms = findViewById(R.id.image_confirms);

        tv_shipping = findViewById(R.id.tv_shipping);
        tv_payment = findViewById(R.id.tv_payment);
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_confirms = findViewById(R.id.tv_confirms);

        image_payment.setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_ATOP);
        image_confirm.setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_ATOP);
        image_confirms.setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_ATOP);

        (findViewById(R.id.lyt_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx_state == array_state.length - 1) return;
                if(array_state[idx_state].name().equalsIgnoreCase(State.VEHICULE.name())){
                    if(GetAccidentData.vehiculeReq.size()==0){
                        snackBarFloating("Vehicule inexistant","Ajouter un vehicule concerne par l'accident");
                        return ;
                    }

                }

                idx_state++;
                displayFragment(array_state[idx_state]);
            }
        });

        (findViewById(R.id.lyt_valid)).setOnClickListener(v -> {
            showDialog();
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    () -> {
                        Gson gson = new Gson();
                        String json = gson.toJson(personDtoReq);

                        SharedPreferences sharedPreferences= getSharedPreferences(AppConfig.Prefs_infos_login,MODE_PRIVATE);


                        Log.e("vehicleaction",gson.toJson(accidentReq));
                        OAuthServerIntface syncService =
                                OAuthServer.createService1(OAuthServerIntface.class,sharedPreferences.getString("access_token",""));

                        Call<AccidentReq> call = syncService.declareaccident(accidentReq);

                        call.enqueue(new Callback<AccidentReq >() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(Call<AccidentReq> call, Response<AccidentReq> response) {

                                Gson gson = new Gson();
                                String json = gson.toJson(response.toString());
                                Log.e("json",json);

                                if (response.isSuccessful()) {
                                    dismissDialog();
                                    Toast.makeText(getApplicationContext(),"Declaration d'accident Reussie!",
                                            Toast.LENGTH_LONG).show();


                                    gson = new Gson();
                                    json = gson.toJson(response.body());
                                    Log.e("json",json);
                                    finish();

                                   // Intent intent = new Intent(getApplicationContext(),verbalprocess.class);
                                    //intent.putExtra("idacc",idacc);
                                    //startActivity(intent);
                                    //finish();

                                    //Intent intent = new Intent(GetAccidentData.this, LoginActivity.class);
                                    //startActivity(intent);

                                }else{
                                    Toast.makeText(getApplicationContext(),"Erreur de Declaration d'accident !",Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<AccidentReq> call, Throwable t) {

                            }

                        });

                    },
                    3000);
        });

        (findViewById(R.id.lyt_previous)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx_state < 1) return;



                idx_state--;
                displayFragment(array_state[idx_state]);
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, android.R.color.black);
    }

    /*private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }*/

    private void displayFragment(State state) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        refreshStepTitle();

        if (state.name().equalsIgnoreCase(State.ACCIDENT.name())) {
            fragment = new FragmentRoute();
            tv_shipping.setTextColor(getResources().getColor(R.color.grey_90));
            image_shipping.clearColorFilter();

        } else if (state.name().equalsIgnoreCase(State.ROUTE.name())) {
            //buildobjectAccident()

            fragment = new FragmentAccident();
            line_first.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            image_shipping.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_payment.clearColorFilter();
            tv_payment.setTextColor(getResources().getColor(R.color.grey_90));

        } else if (state.name().equalsIgnoreCase(State.VEHICULE.name())) {
            fragment = new FragmentVehicule();
            line_second.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            image_payment.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_confirm.clearColorFilter();
            tv_confirm.setTextColor(getResources().getColor(R.color.grey_90));
            (findViewById(R.id.lyt_next)).setVisibility(View.VISIBLE);
            (findViewById(R.id.lyt_valid)).setVisibility(View.GONE);
        }else if (state.name().equalsIgnoreCase(State.USAGER.name())) {

            fragment = new FragmentUsager();
            line_three.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            image_confirm.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_confirms.clearColorFilter();
            tv_confirms.setTextColor(getResources().getColor(R.color.grey_90));

            (findViewById(R.id.lyt_next)).setVisibility(View.GONE);
            (findViewById(R.id.lyt_valid)).setVisibility(View.VISIBLE);
            ///Toast.makeText(getApplicationContext(),"appuie deux fois pour valider",Toast.LENGTH_LONG).show();

        }else if (state.name().equalsIgnoreCase(State.U.name())) {
            Log.e("state", String.valueOf(state.name()));



            //callLifeCycleMethod().callActivityOnPause(MainActivity.this);
            //startActivity(new Intent(GetAccidentData.this, TransparentActivity.class));



            //create object

        }


        if (fragment == null) return;
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmer la declaration d'accident ?");
        builder.setPositiveButton(R.string.NO, (dialogInterface, i) -> {
            Gson gson = new Gson();
            // String json = gson.toJson(accidentReq);

            Log.e("personaction2",gson.toJson(vehiculeReq));
            Log.e("vehicleaction2",gson.toJson(accidentReq));
        });
        builder.setNegativeButton(R.string.YES, null);
        builder.show();
    }

    private void refreshStepTitle() {
        tv_shipping.setTextColor(getResources().getColor(R.color.grey_20));
        tv_payment.setTextColor(getResources().getColor(R.color.grey_20));
        tv_confirm.setTextColor(getResources().getColor(R.color.grey_20));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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

    private void snackBarFloating(String title,String content) {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_custom, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView)custom_view.findViewById(R.id.title)).setText(title);
        ((TextView)custom_view.findViewById(R.id.content)).setText(content);

        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                //Toast.makeText(getApplicationContext(), "UNDO Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }
}

