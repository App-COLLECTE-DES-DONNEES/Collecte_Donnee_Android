package com.ditros.mcd;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import androidx.fragment.app.Fragment;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.adapter.AdapterListBasic;
import com.ditros.mcd.adapter.SimpleArrayListAdapter;
import com.ditros.mcd.adapter.SimpleListAdapter;
import com.ditros.mcd.adapter.SpinnerAdapter;
import com.ditros.mcd.model.*;
import com.ditros.mcd.model.dto.DataLoad;
import com.ditros.mcd.model.dto.VehiculeReq;
import com.skydoves.powerspinner.PowerSpinnerView;
import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

import java.lang.reflect.Array;
import java.util.*;

public class FragmentAccident extends Fragment implements OnItemSelectedListener{

    Spinner munispinner,
            typeaccspinner,
            accidentbrightspinner,
            accidentclimaticspinner,
            accidentimpactspinner,
            accseverityspinner,cityspinner;
    EditText places;
    SpinnerAdapter spinnerAdapter;
    private List<Municipality> munilist = new ArrayList<>();
    private List<AccidentBrightnessCondition> accidentBrightnessConditionList = new ArrayList<>();

    private List<AccidentImpactType> accidentImpactTypes = new ArrayList<>();
    private List<AccidentSeverity> accidentSeverityList = new ArrayList<>();
    private List<City> citylist = new ArrayList<>();
    private List<AccidentType> accidentTypeList = new ArrayList<>();

    private List<OccupantRestraintSystem> occupantRestraintSystemList = new ArrayList<>();
    private List<PersonAction> personActions = new ArrayList<>();
    private List<PersonGender> personGenders = new ArrayList<>();
    private List<PersonRoadType> personRoadTypeList = new ArrayList<>();
    private List<PersonTraumaSeverity> personTraumaSeverityList = new ArrayList<>();

    private List<WearingHelmet> wearingHelmets = new ArrayList<>();

    private List<DataLoad> dataLoads = new ArrayList<>();
    private List<DataLoad> dataLoads1 = new ArrayList<>();
    private List<Road> roadList = new ArrayList<>();
    private List<DataLoad> dataLoads2 = new ArrayList<>();
    private List<AccidentClimaticCondition> accidentClimaticConditionList = new ArrayList<>();

    private List<DataLoad> dataLoads3 = new ArrayList<>();
    private List<DataLoad> dataLoads4 = new ArrayList<>();

    private List<DataLoad> dataLoads5 = new ArrayList<>();
    private List<DataLoad> dataLoads6 = new ArrayList<>();
    private List<DataLoad> dataLoads7 = new ArrayList<>();
    private List<DataLoad> dataLoads8 = new ArrayList<>();

    private List<DataLoad> dataLoads9 = new ArrayList<>();
    private List<DataLoad> dataLoads10 = new ArrayList<>();
    private List<DataLoad> dataLoads11 = new ArrayList<>();
    private List<DataLoad> dataLoads12 = new ArrayList<>();

    private List<DataLoad> dataLoads13 = new ArrayList<>();
    private List<DataLoad> dataLoads14 = new ArrayList<>();
    private List<DataLoad> dataLoads15 = new ArrayList<>();
    private List<DataLoad> dataLoads16 = new ArrayList<>();

    private List<DataLoad> dataLoads17 = new ArrayList<>();
    private List<DataLoad> dataLoads18 = new ArrayList<>();
    private List<DataLoad> dataLoads19 = new ArrayList<>();
    private List<DataLoad> dataLoads20 = new ArrayList<>();

    private List<DataLoad> dataLoads21= new ArrayList<>();
    private List<DataLoad> dataLoads22 = new ArrayList<>();
    private List<DataLoad> dataLoads23 = new ArrayList<>();
    private List<DataLoad> dataLoads24 = new ArrayList<>();
    private SearchableSpinner spinnerdate;
    private SearchableSpinner spinnerdate1;
    private SearchableSpinner spinnerdate2;
    private SearchableSpinner spinnerdate3;
    EditText numacc;
    DataLoad dataLoad;
    private final ArrayList<DataLoad> mStrings = new ArrayList<>();
    private SimpleArrayListAdapter simpleArrayListAdapter;
    appDatabase appDatabases;
    private SimpleListAdapter simpleListAdapter;
    EditText dateacc,heureacc;
    DatePickerDialog picker;
    TimePickerDialog pickers;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    EditText eText;
    public FragmentAccident() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_accident, container, false);
        appDatabases = appDatabase.getInstance(getActivity());
        dateacc = root.findViewById(R.id.dateacc);
        heureacc = root.findViewById(R.id.heureacc);
        cityspinner = root.findViewById(R.id.cityspinner);
        places = root.findViewById(R.id.place);

        dateacc.setInputType(InputType.TYPE_NULL);
        heureacc.setInputType(InputType.TYPE_NULL);
        Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        dateacc.setText(((day )<10?"0" + (day):day) + "/" + ((month + 1)<10?"0" + (month+1):month+1) + "/" + year);
        dateacc.setOnClickListener(v -> {

            // date picker dialog


            picker = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,
                    (view, year1, monthOfYear, dayOfMonth) -> dateacc.setText(((dayOfMonth )<10?"0" + (dayOfMonth):dayOfMonth) + "/" + ((monthOfYear + 1)<10?"0" + (monthOfYear+1):monthOfYear+1) + "/" + year1), year, month, day);
            picker.show();


        });

        cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        heureacc.setText(
                ((hour)<10?"0" + (hour):hour +":" + ((minutes)<10?"0" + (minutes):minutes)));
        heureacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // date picker dialog

                pickers = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        heureacc.setText(
                                ((selectedHour)<10?"0" + (selectedHour):selectedHour +":" + ((selectedMinute)<10?"0" + (selectedMinute):selectedMinute)));
                    }
                }, hour, minutes, false);
                pickers.show();

            }
        });
        // initSpinner();
        initSpinner2(root);
        return root;

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("onresume", String.valueOf(GetAccidentData.route));

        if (GetAccidentData.acciden) {
            List<DataLoad> dataLoad = new ArrayList<>();


            //numacc.setText(String.valueOf(GetAccidentData.accidentReq.getId()));

            dateacc.setText(String.valueOf(GetAccidentData.accidentReq.getAccidentDate()));
            heureacc.setText(String.valueOf(GetAccidentData.accidentReq.getAccidentTime()));


            places.setText(String.valueOf(GetAccidentData.accidentReq.getPlace()));

            for (AccidentType accidentType:accidentTypeList
            ) {
                dataLoad.add(new DataLoad(accidentType.getId(),String.valueOf(accidentType.getCode()),
                        accidentType.getValue()));
            }
            typeaccspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getAccidentType()));
            dataLoad.clear();

            for (Municipality municipality:munilist
            ) {
                dataLoad.add(new DataLoad(municipality.getId(),String.valueOf(municipality.getCode()),
                        municipality.getName()));
            }
            munispinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getMunicipality()));
            dataLoad.clear();

            for (AccidentBrightnessCondition accidentBrightnessCondition:accidentBrightnessConditionList
            ) {
                dataLoad.add(new DataLoad(accidentBrightnessCondition.getId(),String.valueOf(accidentBrightnessCondition.getCode()),
                        accidentBrightnessCondition.getValue()));
            }
            accidentbrightspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getBrightnessCondition()));
            dataLoad.clear();

            for (AccidentClimaticCondition accidentClimaticCondition:accidentClimaticConditionList
            ) {
                dataLoad.add(new DataLoad(accidentClimaticCondition.getId(),String.valueOf(accidentClimaticCondition.getCode()),
                        accidentClimaticCondition.getValue()));
            }
            accidentclimaticspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getClimaticCondition()));
            dataLoad.clear();

            for (AccidentImpactType accidentImpactType:accidentImpactTypes
            ) {
                dataLoad.add(new DataLoad(accidentImpactType.getId(),String.valueOf(accidentImpactType.getCode()),
                        accidentImpactType.getValue()));
            }
            accidentimpactspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getImpactType()));
            dataLoad.clear();

            for (AccidentSeverity accidentSeverity:accidentSeverityList
            ) {
                dataLoad.add(new DataLoad(accidentSeverity.getId(),String.valueOf(accidentSeverity.getCode()),
                        accidentSeverity.getValue()));
            }
            accseverityspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getAccidentSeverity()));
            dataLoad.clear();

            for (City city:citylist
            ) {
                dataLoad.add(new DataLoad(city.getId(),String.valueOf(city.getName()),
                        city.getName()));
            }
            cityspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getCity()));
            dataLoad.clear();



        }
    }


    int getPositions(List<DataLoad> dataLoads,Long id){

        for(int i=0;i<dataLoads.size();i++){
            if(Objects.equals(dataLoads.get(i).getId(), id)){
                return i;
            }
        }
        return 0;
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.e("onpause","onpause");
        //Write your code here
        buildobjectAccident();
    }

    private void buildobjectAccident() {

        GetAccidentData.acciden = true;
        final int min = 1;
        final int max = 999999999;
        final int random = new Random().nextInt((max - min) + 1) + min;

        //GetAccidentData.accidentReq.setId(Long.valueOf(random));
        GetAccidentData.accidentReq.setAccidentDate(dateacc.getText().toString());

        GetAccidentData.accidentReq.setAccidentTime(heureacc.getText().toString());
        GetAccidentData.accidentReq.setPlace(places.getText().toString());

    }


    private void initSpinner2(View root) {
        //numacc = root.findViewById(R.id.numaccident);
        dateacc = root.findViewById(R.id.dateacc);
        heureacc = root.findViewById(R.id.heureacc);
        heureacc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });

        dateacc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });

        munispinner = root.findViewById(R.id.munispinner);
        accidentbrightspinner = root.findViewById(R.id.accidentbrightspinner);
        accidentclimaticspinner = root.findViewById(R.id.accidentclimaticspinner);
        accidentimpactspinner = root.findViewById(R.id.accidentimpactspinner);

        typeaccspinner = root.findViewById(R.id.typespinneracc);
        accseverityspinner = root.findViewById(R.id.gravityspinner);

        munilist = appDatabases.municipalityDAO().getAll();
        accidentBrightnessConditionList = appDatabases.accidentBrightnessConditionDAO().getAll();
        accidentClimaticConditionList = appDatabases.accidentClimaticConditionDAO().getAll();

        accidentImpactTypes = appDatabases.accidentImpactTypeDAO().getAll();
        accidentSeverityList = appDatabases.accidentSeverityDAO().getAll();
        accidentTypeList = appDatabases.accidentTypeDAO().getAll();
        citylist = appDatabases.cityDAO().getAll();
        occupantRestraintSystemList = appDatabases.occupantRestraintSystemDAO().getAll();
        personActions = appDatabases.personActionDAO().getAll();
        personGenders = appDatabases.personGenderDAO().getAll();
        personRoadTypeList = appDatabases.personRoadTypeDAO().getAll();
        personTraumaSeverityList = appDatabases.personTraumaSeverityDAO().getAll();



        wearingHelmets = appDatabases.wearingHelmetDAO().getAll();

        for ( Municipality muni:
           munilist ) {
            dataLoads.add(new DataLoad(muni.getId(),muni.getCode(),muni.getName()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads);
        munispinner.setAdapter(spinnerAdapter);

        //dataLoads.clear();

        for ( AccidentType accidentType:
                accidentTypeList ) {
                    dataLoads1.add(new DataLoad(accidentType.getId(),accidentType.getCode(),accidentType.getValue()));
                }
                spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads1);
        typeaccspinner.setAdapter(spinnerAdapter);



        for ( AccidentBrightnessCondition accidentBrightnessCondition:
                accidentBrightnessConditionList ) {
            dataLoads2.add(new DataLoad(accidentBrightnessCondition.getId(),String.valueOf(accidentBrightnessCondition.getCode()),accidentBrightnessCondition.getValue()));
        }

        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads2);
        accidentbrightspinner.setAdapter(spinnerAdapter);

        for ( AccidentClimaticCondition accidentClimaticCondition:
                accidentClimaticConditionList ) {
            dataLoads3.add(new DataLoad(accidentClimaticCondition.getId(),String.valueOf(accidentClimaticCondition.getCode()),accidentClimaticCondition.getValue()));
        }

        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads3);
        accidentclimaticspinner.setAdapter(spinnerAdapter);

        for ( AccidentImpactType accidentImpactType:
                accidentImpactTypes ) {
            dataLoads4.add(new DataLoad(accidentImpactType.getId(),String.valueOf(accidentImpactType.getCode()),accidentImpactType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads4);
        accidentimpactspinner.setAdapter(spinnerAdapter);

        for ( AccidentSeverity accidentSeverity:
                accidentSeverityList ) {
            dataLoads5.add(new DataLoad(accidentSeverity.getId(),String.valueOf(accidentSeverity.getCode()),accidentSeverity.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads5);
        accseverityspinner.setAdapter(spinnerAdapter);

        for ( AccidentType accidentType:
                accidentTypeList ) {
            dataLoads6.add(new DataLoad(accidentType.getId(),String.valueOf(accidentType.getCode()),accidentType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads6);
        typeaccspinner.setAdapter(spinnerAdapter);

        for ( City city:
                citylist ) {
            dataLoads7.add(new DataLoad(city.getId(),city.getName(),city.getName()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads7);
        cityspinner.setAdapter(spinnerAdapter);

        /*for ( OccupantRestraintSystem occupantRestraintSystem:
                occupantRestraintSystemList ) {
            dataLoads7.add(new DataLoad(occupantRestraintSystem.getId(),String.valueOf(occupantRestraintSystem.getCode()),occupantRestraintSystem.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads7);
        occupantspinner.setAdapter(spinnerAdapter);

        for ( PersonAction personAction:
                personActions ) {
            dataLoads8.add(new DataLoad(personAction.getId(),String.valueOf(personAction.getCode()),personAction.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads8);
        personactionspinner.setAdapter(spinnerAdapter);

        for ( PersonGender personGender:
                personGenders ) {
            dataLoads9.add(new DataLoad(personGender.getId(),String.valueOf(personGender.getCode()),personGender.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads9);
        persongenderspinner.setAdapter(spinnerAdapter);

        for ( PersonRoadType personRoadType:
                personRoadTypeList ) {
            dataLoads10.add(new DataLoad(personRoadType.getId(),String.valueOf(personRoadType.getCode()),personRoadType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads10);
        personroadtypespinner.setAdapter(spinnerAdapter);

        for ( PersonTraumaSeverity personTraumaSeverity:
                personTraumaSeverityList ) {
            dataLoads11.add(new DataLoad(personTraumaSeverity.getId(),String.valueOf(personTraumaSeverity.getName()),personTraumaSeverity.getDescription()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads11);
        persontraumaspinner.setAdapter(spinnerAdapter);



        for ( Road road:
                roadList ) {
            dataLoads13.add(new DataLoad(road.getId(),String.valueOf(road.getName()),""));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads13);
        roadspinner.setAdapter(spinnerAdapter);







        for ( WearingHelmet wearingHelmet:
                wearingHelmets ) {
            dataLoads24.add(new DataLoad(wearingHelmet.getId(),String.valueOf(wearingHelmet.getCode()),wearingHelmet.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads24);
        wearingspinner.setAdapter(spinnerAdapter);*/


        munispinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setMunicipality(clickedItem.getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        cityspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setCity(clickedItem.getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        accidentimpactspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setImpactType(clickedItem.getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });


    typeaccspinner.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent,
                View view, int position, long id)
        {

            DataLoad clickedItem = (DataLoad)
                    parent.getItemAtPosition(position);



            GetAccidentData.accidentReq.setAccidentType(clickedItem.getId());


            // It returns the clicked item.
        //    Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
        }
    });

        munispinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        GetAccidentData.accidentReq.setMunicipality(Long.valueOf(clickedItem.getId()));



                        // It returns the clicked item.
                        //    Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        accidentclimaticspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);

                        GetAccidentData.accidentReq.setClimaticCondition(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        accidentbrightspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);


                        GetAccidentData.accidentReq.setBrightnessCondition(clickedItem.getId());

                        // It returns the clicked item.
                        //    Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
        accseverityspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);



                        GetAccidentData.accidentReq.setAccidentSeverity(clickedItem.getId());


                        // It returns the clicked item.
                        //    Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });


    }





    @Override
    public void onItemSelected(View view, int position, long id) {

    }

    @Override
    public void onNothingSelected() {

    }



    private void initSpinner() {

        simpleArrayListAdapter = new SimpleArrayListAdapter(getActivity(),mStrings);
        simpleListAdapter = new SimpleListAdapter(getActivity(),mStrings);
        spinnerdate.setAdapter(simpleArrayListAdapter);
        spinnerdate.setOnItemSelectedListener(mOnItemSelectedListener);
        spinnerdate.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinnerdate1.hideEdit();
                spinnerdate2.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });


        spinnerdate1.setAdapter(simpleListAdapter);
        spinnerdate1.setOnItemSelectedListener(mOnItemSelectedListener);

        spinnerdate1.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinnerdate.hideEdit();
                spinnerdate2.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });

        spinnerdate2.setAdapter(simpleListAdapter);
        spinnerdate2.setOnItemSelectedListener(mOnItemSelectedListener);

        spinnerdate2.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinnerdate.hideEdit();
                spinnerdate1.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });

        spinnerdate3.setAdapter(simpleListAdapter);
        spinnerdate3.setOnItemSelectedListener(mOnItemSelectedListener);

        spinnerdate3.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                spinnerdate.hideEdit();
                spinnerdate3.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });
    }

    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            dataLoad = mStrings.get(position-1);
            Toast.makeText(getActivity(), "Item on position " + position + " : " + simpleListAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            Toast.makeText(getActivity(), "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };


}



