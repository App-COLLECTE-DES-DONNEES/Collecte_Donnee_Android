package com.ditros.mcd;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.adapter.SpinnerAdapter;
import com.ditros.mcd.model.*;
import com.ditros.mcd.model.dto.DataLoad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentRoute extends Fragment {
    Spinner roadtypespinner, roadcategoryspinner,roadstatespinner,viragespinner, roadintersectionspinner,
            roadblockspinner,roadslopspinner,roadtrafficspinner;

    SpinnerAdapter spinnerAdapter;
    EditText limitVitess;
    private List<RoadBlock> roadBlockList = new ArrayList<>();
    private List<Virage> virageList = new ArrayList<>();
    private List<RoadCategory> roadCategoryList = new ArrayList<>();
    private List<RoadSlopSection> roadSlopSectionList = new ArrayList<>();
    private List<RoadIntersection> roadIntersectionList = new ArrayList<>();
    private List<RoadState> roadStateList = new ArrayList<>();
    private List<RoadTrafficControl> roadTrafficControlList = new ArrayList<>();
    private List<RoadType> roadTypeList = new ArrayList<>();
    private List<DataLoad> dataLoads = new ArrayList<>();
    private List<DataLoad> dataLoads1 = new ArrayList<>();
    private List<DataLoad> dataLoads2 = new ArrayList<>();
    private List<DataLoad> dataLoads3 = new ArrayList<>();
    private List<DataLoad> dataLoads4 = new ArrayList<>();
    private List<DataLoad> dataLoads5 = new ArrayList<>();
    private List<DataLoad> dataLoads6 = new ArrayList<>();
    private List<DataLoad> dataLoads7 = new ArrayList<>();
    private List<DataLoad> dataLoads8 = new ArrayList<>();
    appDatabase appDatabases;
    public FragmentRoute() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        Log.e("onsaveinstance","onsaveinstance");
        outState.putString("message", "This is my message to be reloaded");
        super.onSaveInstanceState(outState);
        Log.e("onsaveinstance","onsaveinstance");
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if( savedInstanceState != null ) {
            Toast.makeText(this, savedInstanceState .getString("message"), Toast.LENGTH_LONG).show();
        }*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_route, container, false);
        appDatabases = appDatabase.getInstance(getActivity());
        initSpinner2(root);
        return root;

    }
    private void initSpinner2(View root){
        limitVitess = root.findViewById(R.id.limitvitesse);

        roadtypespinner = root.findViewById(R.id.roadtypespinner);
        roadcategoryspinner = root.findViewById(R.id.catfspinner);
        roadstatespinner = root.findViewById(R.id.etatroutespinner);
        viragespinner = root.findViewById(R.id.viragespinner);
        roadintersectionspinner = root.findViewById(R.id.carrefourspinner);
        roadblockspinner = root.findViewById(R.id.obstroutespinner);
        roadslopspinner = root.findViewById(R.id.roadslopspinner);
        roadtrafficspinner = root.findViewById(R.id.roadtrafficspinner);



        roadBlockList = appDatabases.roadBlockDAO().getAll();

        roadCategoryList = appDatabases.roadCategoryDAO().getAll();
        roadTrafficControlList = appDatabases.roadTrafficControlDAO().getAll();
        roadSlopSectionList = appDatabases.roadSlopSectionDAO().getAll();
        roadIntersectionList = appDatabases.roadIntersectionDAO().getAll();
        roadTypeList = appDatabases.roadTypeDAO().getAll();
        virageList = appDatabases.virageDAO().getAll();
        roadStateList = appDatabases.roadStateDAO().getAll();

        for ( RoadType roadType:
                roadTypeList ) {
            dataLoads.add(new DataLoad(roadType.getId(),String.valueOf(roadType.getCode()),roadType.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads);
        roadtypespinner.setAdapter(spinnerAdapter);

        for ( RoadTrafficControl roadTrafficControl:
                roadTrafficControlList ) {
            dataLoads1.add(new DataLoad(roadTrafficControl.getId(),String.valueOf(roadTrafficControl.getCode()),roadTrafficControl.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads1);
        roadtrafficspinner.setAdapter(spinnerAdapter);

        for ( RoadBlock roadBlock:
                roadBlockList ) {
            dataLoads2.add(new DataLoad(roadBlock.getId(),String.valueOf(roadBlock.getCode()),roadBlock.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads2);
        roadblockspinner.setAdapter(spinnerAdapter);

        for ( RoadCategory roadCategory:
                roadCategoryList ) {
            dataLoads3.add(new DataLoad(roadCategory.getId(),String.valueOf(roadCategory.getName()),roadCategory.getName()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads3);
        roadcategoryspinner.setAdapter(spinnerAdapter);

        for ( RoadIntersection roadIntersection:
                roadIntersectionList ) {
            dataLoads4.add(new DataLoad(roadIntersection.getId(),String.valueOf(roadIntersection.getCode()),roadIntersection.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads4);
        roadintersectionspinner.setAdapter(spinnerAdapter);

        for ( RoadSlopSection roadSlopSection:
                roadSlopSectionList ) {
            dataLoads6.add(new DataLoad(roadSlopSection.getId(),String.valueOf(roadSlopSection.getCode()),roadSlopSection.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads6);
        roadslopspinner.setAdapter(spinnerAdapter);

        for ( RoadState roadState:
                roadStateList ) {
            dataLoads7.add(new DataLoad(roadState.getId(),String.valueOf(roadState.getCode()),roadState.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads7);
        roadstatespinner.setAdapter(spinnerAdapter);

        for ( Virage virage:
                virageList ) {
            dataLoads8.add(new DataLoad(virage.getId(),String.valueOf(virage.getCode()),virage.getValue()));
        }
        spinnerAdapter = new SpinnerAdapter(getActivity(), dataLoads8);
        viragespinner.setAdapter(spinnerAdapter);

        roadtypespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setRoadType(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        roadstatespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setRoadState(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        roadslopspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setRoadSlopSection(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        roadtrafficspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setRoadTrafficControl(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        roadcategoryspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setRoadCategory(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        roadblockspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setBlock(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });

        roadintersectionspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setRoadIntersection(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

                });
        viragespinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        DataLoad clickedItem = (DataLoad)
                                parent.getItemAtPosition(position);
                        GetAccidentData.accidentReq.setVirage(clickedItem.getId());
                        // It returns the clicked item.
                        //Toast.makeText(getActivity(), name + " selected", Toast.LENGTH_SHORT).show();
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
        buildobjectAccident();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onresume", String.valueOf(GetAccidentData.route));
        if(GetAccidentData.route){
            List<DataLoad> dataLoad = new ArrayList<>();

            for (RoadType roadType:roadTypeList
            ) {
                dataLoad.add(new DataLoad(roadType.getId(),String.valueOf(roadType.getCode()),
                        roadType.getValue()));
            }
            roadtypespinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getRoadType()));
            dataLoad.clear();

            for (RoadCategory roadCategory:roadCategoryList
            ) {
                dataLoad.add(new DataLoad(roadCategory.getId(),String.valueOf(roadCategory.getCode()),
                        roadCategory.getValue()));
            }
            roadcategoryspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getRoadCategory()));
            dataLoad.clear();

            for (RoadState roadState:roadStateList
            ) {
                dataLoad.add(new DataLoad(roadState.getId(),String.valueOf(roadState.getCode()),
                        roadState.getValue()));
            }
            roadstatespinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getRoadState()));
            dataLoad.clear();

            for (Virage virage:virageList
            ) {
                dataLoad.add(new DataLoad(virage.getId(),String.valueOf(virage.getCode()),
                        virage.getValue()));
            }
            viragespinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getVirage()));
            dataLoad.clear();

            for (RoadIntersection roadIntersection:roadIntersectionList
            ) {
                dataLoad.add(new DataLoad(roadIntersection.getId(),String.valueOf(roadIntersection.getCode()),
                        roadIntersection.getValue()));
            }
            roadintersectionspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getRoadIntersection()));
            dataLoad.clear();

            for (RoadBlock roadBlock:roadBlockList
            ) {
                dataLoad.add(new DataLoad(roadBlock.getId(),String.valueOf(roadBlock.getCode()),
                        roadBlock.getValue()));
            }
            roadblockspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getBlock()));
            dataLoad.clear();

            for (RoadSlopSection roadSlopSection:roadSlopSectionList
            ) {
                dataLoad.add(new DataLoad(roadSlopSection.getId(),String.valueOf(roadSlopSection.getCode()),
                        roadSlopSection.getValue()));
            }
            roadslopspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getRoadSlopSection()));
            dataLoad.clear();

            for (RoadTrafficControl roadTrafficControl:roadTrafficControlList
            ) {
                dataLoad.add(new DataLoad(roadTrafficControl.getId(),String.valueOf(roadTrafficControl.getCode()),
                        roadTrafficControl.getValue()));
            }
            roadtrafficspinner.setSelection(getPositions(dataLoad,GetAccidentData.accidentReq.getRoadTrafficControl()));
            dataLoad.clear();

            limitVitess.setText(String.valueOf(GetAccidentData.accidentReq.getSpeedLimit()));

            Log.e("sucessdi", String.valueOf(GetAccidentData.accidentReq.getSpeedLimit()));



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


    private void buildobjectAccident() {

        GetAccidentData.route = true;
        GetAccidentData.accidentReq.setRoad(1L);
        GetAccidentData.accidentReq.setSpeedLimit(Integer.parseInt(limitVitess.getText().toString()));



    }


}
