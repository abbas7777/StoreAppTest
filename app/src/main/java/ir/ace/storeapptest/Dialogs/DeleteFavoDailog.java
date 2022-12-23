package ir.ace.storeapptest.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.ace.storeapptest.R;

public class DeleteFavoDailog extends BottomSheetDialogFragment {

    View view;
    TextView txtYes, txtNo;
    OnDeleteDialogFavorClick onDeleteDialogFavorClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialog_favorlist, container, false);

        setUpViews();

        return view;
    }

    public void setOnDeleteDialogFavorClick(OnDeleteDialogFavorClick onDeleteDialogFavorClick) {
        this.onDeleteDialogFavorClick = onDeleteDialogFavorClick;
    }

    private void setUpViews() {
        txtYes = view.findViewById(R.id.txt_dialogFavor_yes);
        txtNo = view.findViewById(R.id.txt_dialogFavor_no);

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteDialogFavorClick.onClick("Y");

            }
        });

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDeleteDialogFavorClick.onClick("N");

            }
        });
    }

    public interface OnDeleteDialogFavorClick {

        void onClick(String detre);
    }
}
