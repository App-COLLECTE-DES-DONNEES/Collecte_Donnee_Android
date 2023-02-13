package com.ditros.mcd;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.*;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.ditros.mcd.palette.PaletteView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class fragmentPalette extends Fragment implements View.OnClickListener, PaletteView.Callback,Handler.Callback {
    private View mUndoView;
    Context _context;
    private View mRedoView;
    private View mPenView;
    //onSomeEventListener someEventListener;
    private View mEraserView;
    private View mClearView;
    private PaletteView mPaletteView;
    private ProgressDialog mSaveProgressDlg;
    private static final int MSG_SAVE_SUCCESS = 1;
    private static final int MSG_SAVE_FAILED = 2;
    private Handler mHandler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_palette, container, false);



        /*LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadCastReceiver,
                new IntentFilter("KEY"));*/
        _context.registerReceiver(br , new IntentFilter("BROADCAST_ACTION"));


        generatepv.imageView  = root.findViewById(R.id.imageview);
        mPaletteView = root.findViewById(R.id.palette);
        mPaletteView.setCallback(this);
        //generatepv.paletteView  = root.findViewById(R.id.palette);


        mUndoView = root.findViewById(R.id.undo);
        mRedoView = root.findViewById(R.id.redo);
        mPenView = root.findViewById(R.id.pen);
        mPenView.setSelected(true);
        mEraserView = root.findViewById(R.id.eraser);
        mClearView = root.findViewById(R.id.clear);

        mUndoView.setOnClickListener(this);
        mRedoView.setOnClickListener(this);
        mPenView.setOnClickListener(this);
        mEraserView.setOnClickListener(this);
        mClearView.setOnClickListener(this);

        mUndoView.setEnabled(false);
        mRedoView.setEnabled(false);

        mHandler = new Handler(this);

        return root;
    }

  /*  private final BroadcastReceiver broadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //receive your data here
            Log.e("receive_data","receive");



        }
    };*/
  @Override
  public void onAttach(@NonNull Context context)
  {
      super.onAttach(context);
      _context = context;
     // someEventListener = (onSomeEventListener) getParentFragment();
  }
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Check", "Inside On Receiver");
            Toast.makeText(getActivity(), "received",
                    Toast.LENGTH_LONG).show();

            //someEventListener.someEvent(mPaletteView.buildBitmap());
        }
    };

    @Override
    public void onDestroyView() {
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadCastReceiver);
        _context.unregisterReceiver(br);
        super.onDestroyView();
    }

    public void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
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

        generatepv.bitmap1 = Bitmap.createScaledBitmap(generatepv.bitmap1, convertWidth, convertHighet, true);

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(generatepv.bitmap1, 0, 0, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(getActivity(), R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("", 209, 100, title);
        canvas.drawText("", 209, 80, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(getActivity(), R.color.purple_200));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("", 396, 560, title);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);


        mypageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 2).create();
        myPage = pdfDocument.startPage(mypageInfo);

        canvas = myPage.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);
            Bitmap bitmap2  = mPaletteView.buildBitmap();
        bitmap2 =  Bitmap.createScaledBitmap(bitmap2, convertWidth, convertHighet, true);
        //bitmap2 = Bitmap.createScaledBitmap(bitmap2, convertWidth, convertHighet, true);
        canvas.drawBitmap(bitmap2, 0, 0, paint);

        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "proces-"+System.currentTimeMillis()+".pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(getActivity(), "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
           // finish();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }


    /*public interface onSomeEventListener {
         void someEvent(Bitmap bitmap);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(MSG_SAVE_FAILED);
        mHandler.removeMessages(MSG_SAVE_SUCCESS);
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.e("onpause1","onpause");
        //Write your code here
       // generatepv.bitmap2 = mPaletteView.buildBitmap();

    }


    private void initSaveProgressDlg(){
        mSaveProgressDlg = new ProgressDialog(getActivity());
        mSaveProgressDlg.setMessage("Enregistrement, veuillez patienter...");
        mSaveProgressDlg.setCancelable(false);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case MSG_SAVE_FAILED:
                mSaveProgressDlg.dismiss();
                Toast.makeText(getActivity(),"Échec de la sauvegarde",Toast.LENGTH_SHORT).show();
                break;
            case MSG_SAVE_SUCCESS:
                mSaveProgressDlg.dismiss();
                Toast.makeText(getActivity(),"La planche à dessin a été sauvegardée",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    private static void scanFile(Context context, String filePath) {

        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(scanIntent);
    }

    private static String saveImage(Bitmap bmp, int quality) {

        if (bmp == null) {
            return null;
        }

        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (appDir == null) {
            return null;
        }


        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void onUndoRedoStatusChanged() {
        mUndoView.setEnabled(mPaletteView.canUndo());
        mRedoView.setEnabled(mPaletteView.canRedo());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.undo:
                mPaletteView.undo();
                break;
            case R.id.redo:
                mPaletteView.redo();
                break;
            case R.id.pen:
                v.setSelected(true);
                mEraserView.setSelected(false);
                mPaletteView.setMode(PaletteView.Mode.DRAW);
                break;
            case R.id.eraser:
                v.setSelected(true);
                mPenView.setSelected(false);
                mPaletteView.setMode(PaletteView.Mode.ERASER);
                break;
            case R.id.clear:
                mPaletteView.clear();
                break;
        }
    }
}
