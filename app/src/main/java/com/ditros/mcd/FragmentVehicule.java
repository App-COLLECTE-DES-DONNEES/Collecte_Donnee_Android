package com.ditros.mcd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ditros.mcd.adapter.AdapterListBasic;
import com.ditros.mcd.app.AppConfig;
import com.ditros.mcd.model.dto.DataLoad;
import com.ditros.mcd.model.dto.VehiculeReq;
import com.ditros.mcd.model.object;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FragmentVehicule extends Fragment {

    private RecyclerView recyclerView;
    private AdapterListBasic mAdapter;
    View parent_view;
    private List<DataLoad> dataLoads24 = new ArrayList<>();

    List<VehiculeReq> items;
    ImageView img;
    public  FragmentVehicule() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_list_basic, container, false);
        //parent_view = root.findViewById(android.R.id.content);

        //Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);

        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //getActivity().setTitle("Basic");
        //getActivity().setDisplayHomeAsUpEnabled(true);
        //Tools.setSystemBarColor(getActivity());

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
         items = new ArrayList<>();
         img = root.findViewById(R.id.autokollision);
       //items = GetAccidentData.accidentReq.getVehicules();

        if(GetAccidentData.accidentReq.getVehicules()!=null){
            items = GetAccidentData.accidentReq.getVehicules();
        }
        if(items.size()>0){
            img.setVisibility(View.GONE);
        }else{
            img.setVisibility(View.VISIBLE);
        }
         //mAdapter = new AdapterListBasic(getActivity(), items);
         mAdapter =new AdapterListBasic(getActivity(),items, new AdapterListBasic.OnItemClickListener() {
             @Override
             public void onItemClick(View view, VehiculeReq obj, int position) {

                 Intent intent = new Intent(getActivity(),item_vehicule.class);
                 intent.putExtra("position", position);
                 intent.putExtra("add",false);
                 startActivity(intent);
                 Toast.makeText(getActivity(),"Item " + obj.getChassis() + " clicked", Toast.LENGTH_LONG).show();

                 Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_LONG).show();
             }
         });
          recyclerView.setAdapter(mAdapter);
        //items.addAll(AppConfig.getobjectData(getActivity()));
        //items.addAll(AppConfig.getobjectData(getActivity()));

        //set data and list adapter

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),item_vehicule.class);
                intent.putExtra("position", GetAccidentData.accidentReq.getVehicules()==null?
                        0:GetAccidentData.accidentReq.getVehicules().size());
                intent.putExtra("add",true);
                startActivity(intent);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // on item list clicked

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Write your code here
        if(GetAccidentData.accidentReq.getVehicules()!=null){
            items = GetAccidentData.accidentReq.getVehicules();
        }
        if(items.size()>0){
            img.setVisibility(View.GONE);
        }else{
            img.setVisibility(View.VISIBLE);
        }
       // mAdapter = new AdapterListBasic(getActivity(), items);
        mAdapter =new AdapterListBasic(getActivity(),items, new AdapterListBasic.OnItemClickListener() {
            @Override
            public void onItemClick(View view, VehiculeReq obj, int position) {

                Intent intent = new Intent(getActivity(),item_vehicule.class);
                intent.putExtra("position", position);
                intent.putExtra("add",false);
                intent.putExtra("object",GetAccidentData.accidentReq.getVehicules().get(position));
                startActivity(intent);

                Toast.makeText(getActivity(), "Item Clicked2", Toast.LENGTH_LONG).show();

            }
        });


        // mAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(mAdapter);
        Log.e("onresume3","onresume");
    }

    private void initComponent() {
        

    }

}
