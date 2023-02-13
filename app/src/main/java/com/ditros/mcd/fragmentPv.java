package com.ditros.mcd;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class fragmentPv extends Fragment {

    private LinearLayout linear;
    private Bitmap bitmap;
    EditText datepvt,heurepvt,patrouillet,idacct,nous,assist,constate,circulation;
    Button confirm;String date,heure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.verbalprocess, container, false);

        datepvt = root.findViewById(R.id.datepvt);
        generatepv.linear = root.findViewById(R.id.entete);

        //confirm = root.findViewById(R.id.confirm);
        //Log.e("idadd",idacc);

        heurepvt = root.findViewById(R.id.heurepvt);
        patrouillet = root.findViewById(R.id.patrouillet);
        idacct = root.findViewById(R.id.idacct);
        nous = root.findViewById(R.id.nous);


        assist = root.findViewById(R.id.assist);
        constate = root.findViewById(R.id.avons);
        circulation = root.findViewById(R.id.constate);
        linear = root.findViewById(R.id.linear);
        //idacct.setText(idacc);
         date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
         heure = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        datepvt.setText(date);
        heurepvt.setText(heure);
        idacct.setText(generatepv.idacc);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("onpause","onpause");

        //Write your code here
        buildobjectAccident();

    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }


    private void buildobjectAccident() {

       generatepv.pvreq.setDate(date);
       generatepv.pvreq.setHeure(heure);

       generatepv.pvreq.setPatrouille(patrouillet.getText().toString());
       generatepv.pvreq.setIdacc(generatepv.idacc);
       generatepv.pvreq.setNous(nous.getText().toString());
       generatepv.pvreq.setAssiste(assist.getText().toString());
       generatepv.pvreq.setConstate(constate.getText().toString());
       generatepv.pvreq.setCirculation(circulation.getText().toString());

        generatepv.bitmap1 = LoadBitmap(linear, linear.getWidth(), linear.getHeight());



    }
}
