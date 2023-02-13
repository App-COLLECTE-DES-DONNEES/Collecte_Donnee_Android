package com.ditros.mcd;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.model.dto.PvReq;
import com.ditros.mcd.palette.PaletteView;
import com.ditros.mcd.utils.Tools;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.*;

public class generatepv extends AppCompatActivity /*implements fragmentPalette.onSomeEventListener */{

    String signaturepad;
    onSomeEventListener2 onSomeEventListener2;

    /*@Override
    public void someEvent(Bitmap bitmap) {
        Log.e("Check1", "Inside On Receiver");
        bitmap2 = bitmap;
    }*/

    private enum State {
        PV,
        DESSIN,
        SIGNATURE

    }

    public static LinearLayout linear;
    public static Bitmap bitmap1;
    public static Bitmap bitmap2;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    
    public static final PvReq pvreq = new PvReq();
    public static String idacc = null;
    State[] array_state = new State[]{State.PV, State.DESSIN};

    private View line_first, line_second,line_three;

    private ImageView image_shipping, image_payment, image_confirm,image_confirms;
    private TextView tv_shipping, tv_payment, tv_confirm,tv_confirms;
    private appDatabase appDatabases;
    private int idx_state = 0;
    public static ImageView imageView;
    public static PaletteView paletteView;

    FragmentManager fragmentManager;

    private int fois ; ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatepv);

        //initToolbar();
        appDatabases = appDatabase.getInstance(getApplicationContext());
        //initComponent();
        progressDialog = new ProgressDialog(this);
        fois=0;
        idacc = getIntent().getStringExtra("idacc");


        displayFragment(State.PV);

        (findViewById(R.id.lyt_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx_state == array_state.length - 1) return;
                idx_state++;
                displayFragment(array_state[idx_state]);
            }
        });

        (findViewById(R.id.lyt_signature)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        (findViewById(R.id.lyt_previous)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx_state < 1) return;
                idx_state--;
                displayFragment(array_state[idx_state]);
            }
        });

        (findViewById(R.id.lyt_valid)).setOnClickListener(v -> {

            showDialog();

           // generatepv.linear.setVisibility(View.VISIBLE);
            Intent broadcast = new Intent();
            broadcast.setAction("BROADCAST_ACTION");
           // broadcast.addCategory(Intent.CATEGORY_DEFAULT);
            sendBroadcast(broadcast);
           // fragmentPalette fragment = (fragmentPalette) getSupportFragmentManager().findFragmentById(R.id.example_fragment);
            fragmentPalette fragment = (fragmentPalette) fragmentManager
                    .findFragmentByTag("fragmentag");

            fragment.generatePDF();


            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    () -> {

                        //generatePDF();


                    },

                    7000);
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    () -> {

                        dismissDialog();
                    },

                    4000);


        });

    }

    private void refreshStepTitle() {
        line_first = findViewById(R.id.line_first);
        line_second = findViewById(R.id.line_second);

        image_shipping = findViewById(R.id.image_shipping);
        image_payment = findViewById(R.id.image_payment);
        image_confirm = findViewById(R.id.image_confirm);

        tv_shipping = findViewById(R.id.tv_shipping);
        tv_payment = findViewById(R.id.tv_payment);
        tv_confirm = findViewById(R.id.tv_confirm);

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

    public interface onSomeEventListener2 {
        public void someEvent(String s);
    }

    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

        PdfDocument.PageInfo mypageInfo;PdfDocument.Page myPage;
        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.

        mypageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        myPage = pdfDocument.startPage(mypageInfo);


        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();


        canvas.drawPaint(paint);

        bitmap1 = Bitmap.createScaledBitmap(bitmap1, convertWidth, convertHighet, true);

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(bitmap1, 0, 0, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("", 209, 100, title);
        canvas.drawText("", 209, 80, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("", 396, 560, title);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);


        /*mypageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 2).create();
        myPage = pdfDocument.startPage(mypageInfo);

        canvas = myPage.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);
        //bitmap2  = generatepv.paletteView.buildBitmap();
        bitmap2 =  Bitmap.createScaledBitmap(bitmap2, convertWidth, convertHighet, true);
        //bitmap2 = Bitmap.createScaledBitmap(bitmap2, convertWidth, convertHighet, true);
        canvas.drawBitmap(bitmap2, 0, 0, paint);

        pdfDocument.finishPage(myPage);*/

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "proces-"+System.currentTimeMillis()+".pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(generatepv.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }
    private void displayFragment(State state) {

         fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        refreshStepTitle();

        if (state.name().equalsIgnoreCase(State.PV.name())) {
            fragment = new fragmentPv();

            tv_shipping.setTextColor(getResources().getColor(R.color.grey_90));
            image_shipping.clearColorFilter();

            (findViewById(R.id.lyt_signature)).setVisibility(View.GONE);
            (findViewById(R.id.lyt_next)).setVisibility(View.VISIBLE);
            (findViewById(R.id.lyt_valid)).setVisibility(View.GONE);

        } else if (state.name().equalsIgnoreCase(State.DESSIN.name())) {
            //buildobjectAccident();


            fragment = new fragmentPalette();

            line_first.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
           /* image_confirm.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_payment.clearColorFilter();
            tv_payment.setTextColor(getResources().getColor(R.color.grey_90));*/

            image_shipping.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_confirm.clearColorFilter();
            tv_confirm.setTextColor(getResources().getColor(R.color.grey_90));
            (findViewById(R.id.lyt_next)).setVisibility(View.GONE);
            (findViewById(R.id.lyt_signature)).setVisibility(View.VISIBLE);


            //afficher interface signature

        } /*else if (state.name().equalsIgnoreCase(State.SIGNATURE.name())) {

            //afficher interface de
            fragment = new generatepv();
            line_second.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            image_payment.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_confirm.clearColorFilter();
            tv_confirm.setTextColor(getResources().getColor(R.color.grey_90));
            (findViewById(R.id.lyt_next)).setVisibility(View.GONE);
            (findViewById(R.id.lyt_valid)).setVisibility(View.VISIBLE);
        }*/


        if (fragment == null) return;
        fragmentTransaction.replace(R.id.frame_content, fragment,"fragmentag");
        fragmentTransaction.commit();
    }

    private void showCustomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.activity_drawing);
        dialog.setCancelable(true);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        SignaturePad mSignaturePad = dialog.findViewById(R.id.signature_pad);

        Button mClearButton = dialog.findViewById(R.id.clear_button);
        Button mSaveButton = dialog.findViewById(R.id.save_button);

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(generatepv.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });


        mSaveButton.setOnClickListener(view -> {
            Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
            if (addJpgSignatureToGallery(signatureBitmap)) {
                Toast.makeText(generatepv.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(generatepv.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
            }
            /*if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                Toast.makeText(generatepv.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(generatepv.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
            }*/
        });


        dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.bt_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //coller signature quelque part
                (findViewById(R.id.lyt_valid)).setVisibility(View.VISIBLE);
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                imageView.setImageBitmap(signatureBitmap);
                /*
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(generatepv.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(generatepv.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }*/
               /* if (addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(generatepv.this, "SVG Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(generatepv.this, "Unable to store the SVG signature", Toast.LENGTH_SHORT).show();
                }*/

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            Log.e("absolutepath",photo.getAbsolutePath());
            Log.e("absolutepath",photo.getName());
            Log.e("absolutepath",photo.getCanonicalPath());
            signaturepad = photo.getAbsolutePath();

            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        generatepv.this.sendBroadcast(mediaScanIntent);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    
    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void showDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.setMessage("Wait Please");
            progressDialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(generatepv.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    
    public void dismissDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
