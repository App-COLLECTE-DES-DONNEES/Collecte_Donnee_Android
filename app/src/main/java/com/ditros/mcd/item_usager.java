package com.ditros.mcd;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.adapter.SpinnerAdapter;
import com.ditros.mcd.model.*;
import com.ditros.mcd.model.dto.DataLoad;
import com.ditros.mcd.model.dto.PersonDtoReq;
import com.ditros.mcd.model.dto.VehiculeReq;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.*;

public class item_usager extends AppCompatActivity {

    private EditText numpersonne,numvehiculeoccupant,numvehiculepieton,age,nom,prenom;
    private Spinner vehicles,dispositif,portcasque,typeusageroute,gravitetraumatisme,manoeuvrepieton,persongender,consommationdrogues,statut,typetest,resultest,
    consommationalcool,rangee,placesiege;
    int position = 0;

    private List<PersonRoadType> typeusageroutelist = new ArrayList<>();
    private List<OccupantRestraintSystem> occupantRestraintSystemList = new ArrayList<>();
    private List<WearingHelmet> wearingHelmetList = new ArrayList<>();
    private List<PersonTraumaSeverity> gravitetraumatismelist = new ArrayList<>();
    private List<PersonAction> manoeuvrepietonlist = new ArrayList<>();
    private List<PersonDrugUse> consommationdrogueslist = new ArrayList<>();
    private List<PersonGender> personGenderList = new ArrayList<>();
    private List<AlcoholTestStatus> statutlist = new ArrayList<>();
    private List<PersonAlcoholConsumption> consommationalcoolist = new ArrayList<>();

    private List<AlcoholTestType> typetestlist = new ArrayList<>();
    private List<AlcoholTestResult> resultList = new ArrayList<>();
    private List<SeatingRange> rangeelist = new ArrayList<>();
    private List<SeatingPlace> placesiegelist = new ArrayList<>();

    private List<DataLoad> dataLoads = new ArrayList<>();
    private List<DataLoad> dataLoads1 = new ArrayList<>();
    private List<DataLoad> dataLoads2 = new ArrayList<>();
    private List<DataLoad> dataLoads3 = new ArrayList<>();
    private List<DataLoad> dataLoads4 = new ArrayList<>();
    private List<DataLoad> dataLoads5 = new ArrayList<>();
    private List<DataLoad> dataLoads6 = new ArrayList<>();
    private List<DataLoad> dataLoads7 = new ArrayList<>();
    private List<DataLoad> dataLoads8 = new ArrayList<>();
    private List<DataLoad> dataLoadsd = new ArrayList<>();
    private List<DataLoad> dataLoads9 = new ArrayList<>();
    private List<DataLoad> dataLoads10 = new ArrayList<>();
    private List<DataLoad> dataLoads11 = new ArrayList<>();
    private List<DataLoad> dataLoads12 = new ArrayList<>();
    appDatabase appDatabases; DatePickerDialog picker;
    EditText datedelivrance;
    PersonDtoReq personDtoReq = new PersonDtoReq();
    private SpinnerAdapter spinnerAdapter;
    private List<VehicleModel> vehicleModelList = new ArrayList<>();
    private List<VehicleSpecialFunction> vehicleSpecialFunctionList = new ArrayList<>();
    private List<VehicleType> vehicleTypeList = new ArrayList<>();
    private List<VehicleBrand> vehicleBrandList = new ArrayList<>();
    private Boolean add;
    public item_usager() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("message", "This is my message to be reloaded");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_usager);

        datedelivrance = findViewById(R.id.datedelivrance);
        Intent inte= getIntent();
        position = inte.getIntExtra("position",0);
        add = inte.getBooleanExtra("add",true);

        appDatabases = appDatabase.getInstance(getApplicationContext());
        initSpinner2();
        managevalue();

        personDtoReq = new PersonDtoReq();
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        datedelivrance.setText(((day )<10?"0" + (day):day) + "/" + ((month + 1)<10?"0" + (month+1):month+1) + "/" + year);
        datedelivrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // date picker dialog


                picker = new DatePickerDialog(v.getContext(), AlertDialog.THEME_HOLO_LIGHT,
                        (view, year1, monthOfYear, dayOfMonth) -> datedelivrance.setText(((dayOfMonth )<10?"0" + (dayOfMonth):dayOfMonth) + "/" + ((monthOfYear + 1)<10?"0" + (monthOfYear+1):monthOfYear+1) + "/" + year1), year, month, day);
                picker.show();


            }
        });

        if(!add) {

            Bundle data = getIntent().getExtras();
            PersonDtoReq personDtoReq1 = data.getParcelable("object");

            numpersonne.setText(String.valueOf(personDtoReq1.getPersonAccidentNumber()));
            numvehiculeoccupant.setText(String.valueOf(personDtoReq1.getVehicleAccidentNumber()));
            numvehiculepieton.setText(String.valueOf(personDtoReq1.getVehicleLinkedPedestrian()));
            datedelivrance.setText(String.valueOf(personDtoReq1.getDrivingLicenceYear()));
            nom.setText(String.valueOf(personDtoReq1.getLastName()));
            prenom.setText(String.valueOf(personDtoReq1.getFirstName()));
            List<DataLoad> dataLoads5 = new ArrayList<>();


            for (PersonRoadType personRoadType:typeusageroutelist
            ) {
                dataLoads5.add(new DataLoad(personRoadType.getId(),String.valueOf(personRoadType.getCode()),
                        personRoadType.getValue()));
            }

            typeusageroute.setSelection(getPositions(dataLoads5,personDtoReq1.getRoadType()));
            dataLoads5.clear();

            for (OccupantRestraintSystem occupantRestraintSystem:occupantRestraintSystemList
            ) {
                dataLoads5.add(new DataLoad(occupantRestraintSystem.getId(),String.valueOf(occupantRestraintSystem.getCode()),
                        occupantRestraintSystem.getValue()));
            }

            dispositif.setSelection(getPositions(dataLoads5,personDtoReq1.getRoadType()));
            dataLoads5.clear();

            for (WearingHelmet wearingHelmet:wearingHelmetList
            ) {
                dataLoads5.add(new DataLoad(wearingHelmet.getId(),String.valueOf(wearingHelmet.getCode()),
                        wearingHelmet.getValue()));
            }

            portcasque.setSelection(getPositions(dataLoads5,personDtoReq1.getWearingHelmet()));
            dataLoads5.clear();



            for (PersonTraumaSeverity personTraumaSeverity:gravitetraumatismelist
            ) {
                dataLoads5.add(new DataLoad(personTraumaSeverity.getId(),String.valueOf(personTraumaSeverity.getCode()),
                        personTraumaSeverity.getValue()));
            }

            gravitetraumatisme.setSelection(getPositions(dataLoads5,personDtoReq1.getTraumaSeverity()));
            dataLoads5.clear();

            for (PersonAction personAction:manoeuvrepietonlist
            ) {
                dataLoads5.add(new DataLoad(personAction.getId(),String.valueOf(personAction.getCode()),
                        personAction.getValue()));
            }

            manoeuvrepieton.setSelection(getPositions(dataLoads5,personDtoReq1.getPersonAction()));
            dataLoads5.clear();




            for (PersonDrugUse personDrugUse:consommationdrogueslist
            ) {
                dataLoads5.add(new DataLoad(personDrugUse.getId(),String.valueOf(personDrugUse.getCode()),
                        personDrugUse.getValue()));
            }

            consommationdrogues.setSelection(getPositions(dataLoads5,personDtoReq1.getDrugUse()));
            dataLoads5.clear();

            for (PersonGender personGender:personGenderList
            ) {
                dataLoads5.add(new DataLoad(personGender.getId(),String.valueOf(personGender.getCode()),
                        personGender.getValue()));
            }

            persongender.setSelection(getPositions(dataLoads5,personDtoReq1.getGender()));
            dataLoads5.clear();

            for (AlcoholTestStatus alcoholTestStatus:statutlist
            ) {
                dataLoads5.add(new DataLoad(alcoholTestStatus.getId(),String.valueOf(alcoholTestStatus.getCode()),
                        alcoholTestStatus.getValue()));
            }

            statut.setSelection(getPositions(dataLoads5,personDtoReq1.getTestStatus()));
            dataLoads5.clear();



            for (PersonAlcoholConsumption personAlcoholConsumption:consommationalcoolist
            ) {
                dataLoads5.add(new DataLoad(personAlcoholConsumption.getId(),String.valueOf(personAlcoholConsumption.getCode()),
                        personAlcoholConsumption.getValue()));
            }

            consommationalcool.setSelection(getPositions(dataLoads5,personDtoReq1.getAlcoholConsumption()));
            dataLoads5.clear();

            for (AlcoholTestType alcoholTestType:typetestlist
            ) {
                dataLoads5.add(new DataLoad(alcoholTestType.getId(),String.valueOf(alcoholTestType.getCode()),
                        alcoholTestType.getValue()));
            }

            typetest.setSelection(getPositions(dataLoads5,personDtoReq1.getTestType()));
            dataLoads5.clear();
            for (AlcoholTestResult alcoholTestResult:resultList
            ) {
                dataLoads5.add(new DataLoad(alcoholTestResult.getId(),String.valueOf(alcoholTestResult.getCode()),
                        alcoholTestResult.getValue()));
            }

            resultest.setSelection(getPositions(dataLoads5,personDtoReq1.getTestResult()));
            dataLoads5.clear();


            for (SeatingRange seatingRange:rangeelist
            ) {
                dataLoads5.add(new DataLoad(seatingRange.getId(),String.valueOf(seatingRange.getCode()),
                        seatingRange.getValue()));
            }

            rangee.setSelection(getPositions(dataLoads5,personDtoReq1.getRange()));
            dataLoads5.clear();

            for (SeatingPlace seatingPlace:placesiegelist
            ) {
                dataLoads5.add(new DataLoad(seatingPlace.getId(),String.valueOf(seatingPlace.getCode()),
                        seatingPlace.getValue()));
            }

            placesiege.setSelection(getPositions(dataLoads5,personDtoReq1.getPlace()));
            dataLoads5.clear();

            /*

            for (VehicleModel vehicleModel:vehicleModelList
            ) {
                dataLoads5.add(new DataLoad(vehicleModel.getId(),String.valueOf(vehicleModel.getCode()),
                        vehicleModel.getValue()));
            }

            vehicle.setSelection(getPositions(dataLoads5,personDtoReq1.getDrugUse()));
            dataLoads5.clear();*/


        }

            FloatingActionButton fab = findViewById(R.
                id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildobjectAccident();
                finish();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    int getPositions(List<DataLoad> dataLoads,Long id){

        for(int i=0;i<dataLoads.size();i++){
            if(Objects.equals(dataLoads.get(i).getId(), id)){
                return i;
            }
        }
        return 0;
    }


    private void managevalue(){

        numvehiculeoccupant.setText(String.valueOf(dataLoadsd.get(0).getId()));

    }
    private void initSpinner2(){

        numpersonne = findViewById(R.id.numpersonne);
        numvehiculeoccupant = findViewById(R.id.numvehiculeoccupant);
        numvehiculepieton = findViewById(R.id.numvehiculepieton);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);


        rangee = findViewById(R.id.rangee);
        placesiege = findViewById(R.id.placesiege);


        typeusageroute=findViewById(R.id.typeusageroute);
        gravitetraumatisme=findViewById(R.id.gravitetraumatisme);
        manoeuvrepieton=findViewById(R.id.manoeuvrepieton);
        vehicles=findViewById(R.id.vehicle);
        consommationdrogues=findViewById(R.id.consommationdrogues);
        persongender=findViewById(R.id.persongender);
        statut=findViewById(R.id.statut);
        typetest=findViewById(R.id.typetest);
        resultest=findViewById(R.id.resultattest);
        consommationalcool=findViewById(R.id.consalcool);

        dispositif=findViewById(R.id.dispositif);
        portcasque=findViewById(R.id.portcasque);


        rangeelist = appDatabases.seatingRangeDAO().getAll();
        placesiegelist = appDatabases.seatingPlaceDAO().getAll();

        typeusageroutelist = appDatabases.personRoadTypeDAO().getAll();
        gravitetraumatismelist = appDatabases.personTraumaSeverityDAO().getAll();



        manoeuvrepietonlist = appDatabases.personActionDAO().getAll();
        consommationdrogueslist = appDatabases.personDrugUseDAO().getAll();
        personGenderList = appDatabases.personGenderDAO().getAll();

        statutlist = appDatabases.alcoholTestStatusDAO().getAll();
        typetestlist = appDatabases.alcoholTestTypeDAO().getAll();
        resultList = appDatabases.alcoholTestResultDAO().getAll();

        Log.e("vehicle11", String.valueOf(personGenderList.size()));

        consommationalcoolist = appDatabases.personAlcoholConsumptionDAO().getAll();
        occupantRestraintSystemList = appDatabases.occupantRestraintSystemDAO().getAll();
        wearingHelmetList = appDatabases.wearingHelmetDAO().getAll();


        //Log.e("vehicle", String.valueOf(vehicleModelList.size()));
        //Log.e("vehicle", String.valueOf(vehicleSpecialFunctionList.size()));

        for ( PersonRoadType personRoadType:
                typeusageroutelist ) {
            dataLoads.add(new DataLoad(personRoadType.getId(),String.valueOf(personRoadType.getCode()),personRoadType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads);
        typeusageroute.setAdapter(spinnerAdapter);
         for ( SeatingRange seatingRange:
                        rangeelist ) {
                    dataLoads10.add(new DataLoad(seatingRange.getId(),String.valueOf(seatingRange.getCode()),seatingRange.getValue()));
                }
                spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads10);
                rangee.setAdapter(spinnerAdapter);

        for ( PersonGender personGender:
                personGenderList ) {
            dataLoads12.add(new DataLoad(personGender.getId(),String.valueOf(personGender.getCode()),personGender.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads12);
        persongender.setAdapter(spinnerAdapter);

         for ( SeatingPlace seatingPlace:
                        placesiegelist ) {
                    dataLoads11.add(new DataLoad(seatingPlace.getId(),String.valueOf(seatingPlace.getCode()),seatingPlace.getValue()));
                }
                spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads11);
                placesiege.setAdapter(spinnerAdapter);

        for ( OccupantRestraintSystem occupantRestraintSystem:
                occupantRestraintSystemList ) {
            dataLoads8.add(new DataLoad(occupantRestraintSystem.getId(),String.valueOf(occupantRestraintSystem.getCode()),occupantRestraintSystem.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads8);
        dispositif.setAdapter(spinnerAdapter);

        List<VehiculeReq> items = GetAccidentData.accidentReq.getVehicules();

        for ( VehiculeReq vehiculeReq:
                items ) {
            dataLoadsd.add(new DataLoad(vehiculeReq.getVehicleAccidentNumber(),vehiculeReq.getPlate(),vehiculeReq.getPlate()));
        }

        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoadsd);
        vehicles.setAdapter(spinnerAdapter);

        for ( WearingHelmet wearingHelmet:
                wearingHelmetList ) {
            dataLoads9.add(new DataLoad(wearingHelmet.getId(),String.valueOf(wearingHelmet.getCode()),wearingHelmet.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads9);
        portcasque.setAdapter(spinnerAdapter);

        for ( PersonTraumaSeverity personTraumaSeverity:
                gravitetraumatismelist ) {
            dataLoads1.add(new DataLoad(personTraumaSeverity.getId(),String.valueOf(personTraumaSeverity.getCode()),personTraumaSeverity.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads1);
        gravitetraumatisme.setAdapter(spinnerAdapter);

        for ( PersonAction personAction:
                manoeuvrepietonlist ) {
            dataLoads2.add(new DataLoad(personAction.getId(),String.valueOf(personAction.getCode()),
                    personAction.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads2);
        manoeuvrepieton.setAdapter(spinnerAdapter);

        for ( PersonDrugUse personDrugUse:
                consommationdrogueslist ) {
            dataLoads3.add(new DataLoad(personDrugUse.getId(),String.valueOf(personDrugUse.getCode()),
                    personDrugUse.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads3);
        consommationdrogues.setAdapter(spinnerAdapter);

        for ( AlcoholTestStatus alcoholTestStatus:
                statutlist ) {
            dataLoads4.add(new DataLoad(alcoholTestStatus.getId(),String.valueOf(alcoholTestStatus.getCode()),
                    alcoholTestStatus.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads4);
        statut.setAdapter(spinnerAdapter);

        for ( AlcoholTestType alcoholTestType:
                typetestlist ) {
            dataLoads5.add(new DataLoad(alcoholTestType.getId(),String.valueOf(alcoholTestType.getCode()),
                    alcoholTestType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads5);
        typetest.setAdapter(spinnerAdapter);

        for ( AlcoholTestResult alcoholTestResult:
                resultList ) {
            dataLoads6.add(new DataLoad(alcoholTestResult.getId(),String.valueOf(alcoholTestResult.getCode()),
                    alcoholTestResult.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads6);
        resultest.setAdapter(spinnerAdapter);

        for ( PersonAlcoholConsumption personAlcoholConsumption:
                consommationalcoolist ) {
            dataLoads7.add(new DataLoad(personAlcoholConsumption.getId(),String.valueOf(personAlcoholConsumption.getCode()),
                    personAlcoholConsumption.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getApplicationContext(), dataLoads7);
        consommationalcool.setAdapter(spinnerAdapter);

        typeusageroute=findViewById(R.id.typeusageroute);
        gravitetraumatisme=findViewById(R.id.gravitetraumatisme);
        manoeuvrepieton=findViewById(R.id.manoeuvrepieton);
        consommationdrogues=findViewById(R.id.consommationdrogues);
        statut=findViewById(R.id.statut);
        typetest=findViewById(R.id.typetest);
        resultest=findViewById(R.id.resultattest);
        consommationalcool=findViewById(R.id.consalcool);
        dispositif=findViewById(R.id.dispositif);
        portcasque=findViewById(R.id.portcasque);

        typeusageroute.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setRoadType(clickedItem.getId());
                        Toast.makeText(getApplicationContext(),String.valueOf(clickedItem.getCode().equals("3")),Toast.LENGTH_LONG).show();

                        if(!clickedItem.getCode().equals("3")){

                            numvehiculepieton.setEnabled(false);
                            numvehiculepieton.setBackgroundColor(getResources().getColor(R.color.grey_10));
                            numvehiculepieton.setText("0");

                            numvehiculeoccupant.setEnabled(true);
                            numvehiculeoccupant.setBackgroundColor(getResources().getColor(R.color.white));


                            rangee.setEnabled(true);
                            rangee.setSelection(0);
                            rangee.setBackgroundColor(getResources().getColor(R.color.white));

                            placesiege.setEnabled(true);
                            placesiege.setSelection(0);
                            placesiege.setBackgroundColor(getResources().getColor(R.color.white));

                            dispositif.setEnabled(true);
                            dispositif.setSelection(0);
                            dispositif.setBackgroundColor(getResources().getColor(R.color.white));

                            portcasque.setEnabled(true);
                            portcasque.setSelection(0);
                            portcasque.setBackgroundColor(getResources().getColor(R.color.white));

                            manoeuvrepieton.setEnabled(false);
                            manoeuvrepieton.setSelection(manoeuvrepietonlist.size()-1);
                            manoeuvrepieton.setBackgroundColor(getResources().getColor(R.color.grey_10));

                        }else{

                            numvehiculepieton.setEnabled(true);
                            numvehiculepieton.setBackgroundColor(getResources().getColor(R.color.white));

                            manoeuvrepieton.setEnabled(true);
                            manoeuvrepieton.setSelection(0);
                            manoeuvrepieton.setBackgroundColor(getResources().getColor(R.color.white));

                            numvehiculeoccupant.setEnabled(false);
                            numvehiculeoccupant.setText("0");
                            numvehiculeoccupant.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            rangee.setEnabled(false);
                            rangee.setSelection(rangeelist.size()-1);
                            rangee.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            placesiege.setEnabled(false);
                            placesiege.setSelection(placesiegelist.size()-1);
                            placesiege.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            dispositif.setEnabled(false);
                            dispositif.setSelection(occupantRestraintSystemList.size()-2);
                            dispositif.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            portcasque.setEnabled(false);
                            portcasque.setSelection(wearingHelmetList.size()-1);
                            portcasque.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            //numvehiculeoccupant.setVisibility(View.INVISIBLE);

                        }
                        if(clickedItem.getCode().equals("9")){

                            numvehiculepieton.setEnabled(false);
                            numvehiculepieton.setBackgroundColor(getResources().getColor(R.color.grey_10));
                            numvehiculepieton.setText("0");

                            numvehiculeoccupant.setEnabled(false);
                            numvehiculeoccupant.setBackgroundColor(getResources().getColor(R.color.grey_10));
                            numvehiculeoccupant.setText("0");

                            rangee.setEnabled(false);
                            rangee.setSelection(rangeelist.size()-1);
                            rangee.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            placesiege.setEnabled(false);
                            placesiege.setSelection(placesiegelist.size()-1);
                            placesiege.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            dispositif.setEnabled(false);
                            dispositif.setSelection(occupantRestraintSystemList.size()-2);
                            dispositif.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            portcasque.setEnabled(false);
                            portcasque.setSelection(wearingHelmetList.size()-1);
                            portcasque.setBackgroundColor(getResources().getColor(R.color.grey_10));

                            manoeuvrepieton.setEnabled(false);
                            manoeuvrepieton.setSelection(manoeuvrepietonlist.size()-1);
                            manoeuvrepieton.setBackgroundColor(getResources().getColor(R.color.grey_10));

                        }


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        rangee.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setRange(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        gravitetraumatisme.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setTraumaSeverity(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        placesiege.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setPlace(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        manoeuvrepieton.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setPersonAction(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        consommationdrogues.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                       personDtoReq.setDrugUse(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        statut.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setTestStatus(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        typetest.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setTestType(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        resultest.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setTestResult(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        consommationalcool.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setAlcoholConsumption(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        dispositif.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setOccupantRestraintSystem(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        vehicles.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setVehicleAccidentNumber((clickedItem.getId()));
                        Toast.makeText(getApplicationContext(),
                                String.valueOf(typeusageroutelist.get(typeusageroute.getSelectedItemPosition()).getCode()),Toast.LENGTH_LONG).show();


                        if(!(typeusageroutelist.get(typeusageroute.getSelectedItemPosition()).getCode()==3)){
                            numvehiculeoccupant.setText(String.valueOf(clickedItem.getId()));
                            numvehiculeoccupant.setEnabled(true);
                            numvehiculeoccupant.setBackgroundColor(getResources().getColor(R.color.white));

                        }else{
                            numvehiculeoccupant.setText(0);
                            numvehiculeoccupant.setEnabled(false);
                            numvehiculeoccupant.setBackgroundColor(getResources().getColor(R.color.grey_10));
                        }


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        portcasque.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        personDtoReq.setWearingHelmet(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getApplicationContext(), name + " selected", Toast.LENGTH_SHORT).show();

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
        Log.e("buildobjectusager","onpause");
        //Write your code here
        //buildobjectAccident();
    }

    private void buildobjectAccident() {


        personDtoReq.setPersonAccidentNumber(Integer.parseInt(numpersonne.getText().toString()));
        personDtoReq.setVehicleAccidentNumber(Long.valueOf(numvehiculeoccupant.getText().toString()));
        personDtoReq.setVehicleLinkedPedestrian(Integer.parseInt(numvehiculepieton.getText().toString()));
        personDtoReq.setDrivingLicenceYear(datedelivrance.getText().toString());
        personDtoReq.setFirstName(prenom.getText().toString());
        personDtoReq.setLastName(nom.getText().toString());

        personDtoReq.setCare((0));
        personDtoReq.setId(Long.valueOf(0));
        personDtoReq.setGender(Long.valueOf(1));

        if(add) {
            GetAccidentData.personDtoReq.add(personDtoReq);
        }else{
            GetAccidentData.personDtoReq.set(position,personDtoReq);
        }
        GetAccidentData.accidentReq.setPersons(GetAccidentData.personDtoReq);
        Gson gson = new Gson();
        String json = gson.toJson(GetAccidentData.personDtoReq.get(position));

        Log.e("ve2",json);

    }
}

