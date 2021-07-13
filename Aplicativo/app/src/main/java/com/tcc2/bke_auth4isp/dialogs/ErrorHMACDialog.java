package com.tcc2.bke_auth4isp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;


/**
 * Created by Vagner on 21/01/2021.
 */

public class ErrorHMACDialog {

    MaterialDialog chargebackDialog;
    TextView text;
    Button btnConfirm;
    Context mContext;

    private Activity baseActivity;


    public ErrorHMACDialog(FragmentActivity activity, String message) {
        this.baseActivity = activity;
        chargebackDialog = new MaterialDialog.Builder(this.baseActivity)
                .customView(R.layout.dialog_error_hmac, false)
                .progressIndeterminateStyle(false)
                .show();

        btnConfirm = (Button) chargebackDialog.findViewById(R.id.buttonBackDialogError);
        text = (TextView) chargebackDialog.findViewById(R.id.textError);
        text.setText(message);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargebackDialog.dismiss();
            }
        });
    }

    public ErrorHMACDialog(Context mContext, String message) {
        this.mContext = mContext;
        chargebackDialog = new MaterialDialog.Builder(this.mContext)
                .customView(R.layout.dialog_error_hmac, false)
                .progressIndeterminateStyle(false)
                .show();

        btnConfirm = (Button) chargebackDialog.findViewById(R.id.buttonBackDialogError);
        text = (TextView) chargebackDialog.findViewById(R.id.textError);
        text.setText(message);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargebackDialog.dismiss();
            }
        });
    }
}
