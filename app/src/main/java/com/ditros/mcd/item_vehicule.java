package com.ditros.mcd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.adapter.SpinnerAdapter;
import com.ditros.mcd.model.*;
import com.ditros.mcd.model.dto.DataLoad;
import com.ditros.mcd.model.dto.VehiculeReq;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class item_vehicule extends AppCompatActivity
{

    private Spinner modelspinners,vehicletypespinner,functionvehiclespinner,manoeuvrevehicule,marques;
    private List<DataLoad> dataLoads = new ArrayList<>();
    private List<DataLoad> dataLoads1 = new ArrayList<>();
    private List<DataLoad> dataLoads2 = new ArrayList<>();
    private List<DataLoad> dataLoads3 = new ArrayList<>();

    private List<DataLoad> dataLoads4 = new ArrayList<>();
    appDatabase appDatabases;

    private SpinnerAdapter spinnerAdapter;
    private List<VehicleModel> vehicleModelList = new ArrayList<>();
    private List<VehicleSpecialFunction> vehicleSpecialFunctionList = new ArrayList<>();
    private List<VehicleType> vehicleTypeList = new ArrayList<>();
    private List<VehicleBrand> vehicleBrandList = new ArrayList<>();
    private List<VehicleAction> vehicleActionList = new ArrayList<>();
    EditText idacc,annee,cylindre,plate;
    VehiculeReq vehiculeReq;
    int positions = 0;Boolean add;
    public item_vehicule() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vehicule);

        Intent inte= getIntent();
        positions = inte.getIntExtra("position",0);
        add = inte.getBooleanExtra("add",true);
        appDatabases = appDatabase.getInstance(getApplicationContext());
        initSpinner2();
        vehiculeReq = new VehiculeReq();

        if(!add){

            Bundle data = getIntent().getExtras();
            VehiculeReq vehiculeReq1= data.getParcelable("object");
            idacc.setText(String.valueOf(vehiculeReq1.getVehicleAccidentNumber()));
            annee.setText(String.valueOf(vehiculeReq1.getFabricationYear()));
            plate.setText(String.valueOf(vehiculeReq1.getPlate()));
            cylindre.setText(String.valueOf(vehiculeReq1.getCylinderCapacity()));
            List<DataLoad> dataLoads5 = new ArrayList<>();

            for (VehicleModel vehiculeModel:vehicleModelList
                 ) {
                dataLoads5.add(new DataLoad(vehiculeModel.getId(),String.valueOf(vehiculeModel.getCode()),
                        vehiculeModel.getValue()));
            }

             modelspinners.setSelection(getPositions(dataLoads5,vehiculeReq1.getModel()));
            dataLoads5.clear();
            for (VehicleType vehicleType:vehicleTypeList
            ) {
                dataLoads5.add(new DataLoad(vehicleType.getId(),String.valueOf(vehicleType.getCode()),
                        vehicleType.getValue()));
            }
            vehicletypespinner.setSelection(getPositions(dataLoads5,vehiculeReq1.getType()));

            dataLoads5.clear();
            for (VehicleSpecialFunction vehicleSpecialFunction:vehicleSpecialFunctionList
            ) {
                dataLoads5.add(new DataLoad(vehicleSpecialFunction.getId(),String.valueOf(vehicleSpecialFunction.getCode()),
                        vehicleSpecialFunction.getValue()));
            }

            functionvehiclespinner.setSelection(getPositions(dataLoads5,vehiculeReq1.getSpecialFunction()));

            dataLoads5.clear();
            for (VehicleAction vehicleAction:vehicleActionList
            ) {
                dataLoads5.add(new DataLoad(vehicleAction.getId(),String.valueOf(vehicleAction.getCode()),
                        vehicleAction.getValue()));
            }

            manoeuvrevehicule.setSelection(getPositions(dataLoads5,vehiculeReq1.getVehicleAction()));

            dataLoads5.clear();
            for (VehicleBrand vehicleBrand:vehicleBrandList
            ) {
                dataLoads5.add(new DataLoad(vehicleBrand.getId(),String.valueOf(vehicleBrand.getCode()),
                        vehicleBrand.getValue()));
            }

            marques.setSelection(getPositions(dataLoads5,vehiculeReq1.getBrand()));
            //une methode qui va recuperer la liste et l id et retourner la position
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(TextUtils.isEmpty(idacc.getText())){
                    idacc.setError( "Numero vehicule est obligatoire!" );
                    return;
                }
                if(TextUtils.isEmpty(plate.getText())){
                    plate.setError( "l'immatriculation est obligatoire!" );
                    return;
                }
                if(TextUtils.isEmpty(annee.getText())){
                    annee.setText("0");
                }

                if(TextUtils.isEmpty(cylindre.getText())){
                    cylindre.setText("0");

                }
                buildobjectAccident(add);
                finish();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }



    /* @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View root = inflater.inflate(R.layout.fragment_vehicule, container, false);
         appDatabases = appDatabase.getInstance(this);
         initSpinner2(root);
         vehiculeReq = new VehiculeReq();
         return root;

     }*/

    int getPositions(List<DataLoad> dataLoads,Long id){

        for(int i=0;i<dataLoads.size();i++){
            if(Objects.equals(dataLoads.get(i).getId(), id)){
                return i;
            }
        }
        return 0;
    }

    private void initSpinner2(){


        idacc = findViewById(R.id.idacc);
        annee = findViewById(R.id.annee);
        plate = findViewById(R.id.plate);
        cylindre = findViewById(R.id.cylindre);

        // GetAccidentData.vehiculeReq = new VehiculeReq[2];

        modelspinners = findViewById(R.id.modelspinner);
        vehicletypespinner = findViewById(R.id.vehicletypespinner);
        functionvehiclespinner = findViewById(R.id.functionvehiclespinner);
        manoeuvrevehicule = findViewById(R.id.manoeuvrevehicule);
        marques  = findViewById(R.id.marquespinner);

        vehicleTypeList = appDatabases.vehicleTypeDAO().getAll();
        vehicleModelList = appDatabases.vehicleModelDAO().getAll();
        vehicleSpecialFunctionList = appDatabases.vehicleSpecialFunctionDAO().getAll();
        vehicleActionList = appDatabases.vehicleActionDAO().getAll();
        vehicleBrandList = appDatabases.vehiculeBrandDAO().getAll();

        Log.e("vehicle", String.valueOf(vehicleBrandList.size()));
        Log.e("vehicle", String.valueOf(vehicleBrandList.get(0).getValue()));

        for ( VehicleModel vehicleModel:
                vehicleModelList ) {
            dataLoads.add(new DataLoad(vehicleModel.getId(),String.valueOf(vehicleModel.getValue()),vehicleModel.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(this, dataLoads);
        modelspinners.setAdapter(spinnerAdapter);

        for ( VehicleBrand vehicleBrand:
                vehicleBrandList ) {
            dataLoads4.add(new DataLoad(vehicleBrand.getId(),String.valueOf(vehicleBrand.getValue()),vehicleBrand.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(this, dataLoads4);
        marques.setAdapter(spinnerAdapter);

        for ( VehicleAction vehicleAction:
                vehicleActionList ) {
            dataLoads3.add(new DataLoad(vehicleAction.getId(),String.valueOf(vehicleAction.getCode()),vehicleAction.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(this, dataLoads3);
        manoeuvrevehicule.setAdapter(spinnerAdapter);

        for ( VehicleType vehicleType:
                vehicleTypeList ) {
            dataLoads1.add(new DataLoad(vehicleType.getId(),String.valueOf(vehicleType.getCode()),vehicleType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(this, dataLoads1);
        vehicletypespinner.setAdapter(spinnerAdapter);

        for ( VehicleSpecialFunction vehicleSpecialFunction:
                vehicleSpecialFunctionList ) {
            dataLoads2.add(new DataLoad(vehicleSpecialFunction.getId(),String.valueOf(vehicleSpecialFunction.getCode()),vehicleSpecialFunction.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(this, dataLoads2);
        functionvehiclespinner.setAdapter(spinnerAdapter);


        modelspinners.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        vehiculeReq.setModel(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(this, name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        vehicletypespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        vehiculeReq.setType(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(this, name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        functionvehiclespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        vehiculeReq.setSpecialFunction(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(this, name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        marques.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        Log.e("marques", String.valueOf(clickedItem.getId()));

                        vehiculeReq.setBrand(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(this, name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        manoeuvrevehicule.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        vehiculeReq.setVehicleAction(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(this, name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onpause","onpause");
        //Write your code here
        //buildobjectAccident();
    }

    private void loadobjectVehicule(){

    }
    private void buildobjectAccident(Boolean add) {


            vehiculeReq.setVehicleAccidentNumber(Long.valueOf(idacc.getText().toString()));
            vehiculeReq.setFabricationYear(Integer.parseInt(annee.getText().toString()));
            vehiculeReq.setPlate((plate.getText().toString()));
            vehiculeReq.setCylinderCapacity(Integer.parseInt(cylindre.getText().toString()));
            vehiculeReq.setBrand(Long.valueOf(1));
            vehiculeReq.setVehicleId(Long.valueOf(0));

            //  GetAccidentData.vehiculeReq= new VehiculeReq[2];
        if(add) {
            GetAccidentData.vehiculeReq.add(vehiculeReq);

        }else{
            GetAccidentData.vehiculeReq.set(positions,vehiculeReq);
        }
        GetAccidentData.accidentReq.setVehicules(GetAccidentData.vehiculeReq);

    }
}