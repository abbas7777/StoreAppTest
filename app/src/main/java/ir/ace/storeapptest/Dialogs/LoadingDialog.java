package ir.ace.storeapptest.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import ir.ace.storeapptest.R;

public class LoadingDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(),R.layout.dialog_loding,null);
        AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
        dialog.setView(view);
        return dialog.create();
    }
}
