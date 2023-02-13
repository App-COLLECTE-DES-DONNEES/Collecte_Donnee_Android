package com.ditros.mcd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ditros.mcd.adapter.AdapterListBasic;
import com.ditros.mcd.adapter.AdapterListBasic2;
import com.ditros.mcd.model.dto.PersonDtoReq;
import com.ditros.mcd.model.dto.VehiculeReq;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FragmentUsager extends Fragment {
    private AdapterListBasic2 mAdapter;
    List<PersonDtoReq> items;
    ImageView img;
    private RecyclerView recyclerView;
    public FragmentUsager() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_list_basic, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        items = new ArrayList<>();
        img =  root.findViewById(R.id.autokollision1);

        if(GetAccidentData.accidentReq.getPersons()!=null){
            items = GetAccidentData.accidentReq.getPersons();
        }
        if(items.size()>0){
            img.setVisibility(View.GONE);
        }else{
            img.setVisibility(View.VISIBLE);
        }
        //  mAdapter = new AdapterListBasic2(getActivity(), items);


        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),item_usager.class);
                intent.putExtra("position", GetAccidentData.accidentReq.getPersons()==null?
                        0:GetAccidentData.accidentReq.getPersons().size());
                intent.putExtra("add",true);
                startActivity(intent);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        //Write your code here
        if(GetAccidentData.accidentReq.getPersons()!=null){
            items = GetAccidentData.accidentReq.getPersons();
        }
        if(items.size()>0){
            img.setVisibility(View.GONE);
        }else{
            img.setVisibility(View.VISIBLE);
        }

        mAdapter = new AdapterListBasic2(getActivity(),items, (view, obj, position) -> {

            Intent intent = new Intent(getActivity(),item_usager.class);
            intent.putExtra("position", position);
            intent.putExtra("add", false);

            intent.putExtra("object",GetAccidentData.accidentReq.getPersons().get(position));

            startActivity(intent);

            Toast.makeText(getActivity(), "Item Clicked", Toast.LENGTH_LONG).show();

        });
        // mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        //Log.e("onresume3","onresume");
    }


}
