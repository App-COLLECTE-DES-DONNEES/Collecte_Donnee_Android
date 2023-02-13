package com.ditros.mcd;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class verbalprocess extends AppCompatActivity {

    private LinearLayout linear;
    private Bitmap bitmap;
    EditText datepvt,heurepvt,patrouillet,idacct,nous,assist,avons,constate;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.verbalprocess);

        datepvt = findViewById(R.id.datepvt);

        String idacc = "1"; //getIntent().getStringExtra("idacc");
        //confirm = findViewById(R.id.confirm);
        Log.e("idadd",idacc);


        heurepvt = findViewById(R.id.heurepvt);
        patrouillet = findViewById(R.id.patrouillet);
        idacct = findViewById(R.id.idacct);
        nous = findViewById(R.id.nous);
        assist = findViewById(R.id.assist);
        avons = findViewById(R.id.avons);
        constate = findViewById(R.id.constate);
        linear = findViewById(R.id.linear);


        idacct.setText(idacc);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String heure = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        datepvt.setText(date);
        heurepvt.setText(heure);



      /* confirm.setOnClickListener(view -> {


            Log.e("click","click");

            bitmap = LoadBitmap(linear, linear.getWidth(), linear.getHeight());
            //createPdf();

            datepvt.setText(datepvt.getText().toString());
            heurepvt.setText(heurepvt.getText().toString());
            patrouillet.setText(patrouillet.getText().toString());
            idacct.setText(idacct.getText().toString());
            nous.setText(nous.getText().toString());
            assist.setText(assist.getText().toString());
            avons.setText(avons.getText().toString());
            constate.setText(constate.getText().toString());


            generatePDF();
            //finish();

            //openPdf();

        });*/


}
    private Bitmap LoadBitmap(View v, int width, int height) {

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;

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

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(bitmap, 0, 0, paint);

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

        /*mypageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 2).create();
        myPage = pdfDocument.startPage(mypageInfo);
        canvas = myPage.getCanvas();
        canvas.drawPaint(paint);*/

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(verbalprocess.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }
    
    private void createPdf() {

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;

        int convertHighet = (int) hight, convertWidth = (int) width;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        //paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        // write the document content
        //String targetPdf = "/sdcard/page.pdf";
        String targetPdf = Environment.getExternalStorageDirectory().toString()+ "/page.pdf";

        //String targetPdf = Environment.DIRECTORY_DOCUMENTS;
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }////////////////////

        // close the document
        document.close();
        Toast.makeText(this, "successfully pdf created", Toast.LENGTH_SHORT).show();

        openPdf();

    }

    private void openPdf() {
        //File file = new File("/sdcard/page.pdf");
        //File file = new File(Environment.getExternalStorageDirectory().toString()+ "/page.pdf");
        File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");

        if (file.exists()) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            //Uri uri = Uri.fromFile(file);
            Uri uri = FileProvider.getUriForFile(getApplicationContext(), getPackageName() + ".provider", file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No Application for pdf view", Toast.LENGTH_SHORT).show();
            }
        }

    }

}


