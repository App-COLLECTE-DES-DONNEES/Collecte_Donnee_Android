package com.ditros.mcd;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmationAccident extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.NO))
                    .setPositiveButton(getString(R.string.YES), (dialog, which) -> {

                        Log.e("yes","yes");
                    } )
                    .create();
        }


        public static String TAG = "PurchaseConfirmationDialog";
    }


