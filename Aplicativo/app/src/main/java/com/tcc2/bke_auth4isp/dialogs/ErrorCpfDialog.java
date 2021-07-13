package com.tcc2.bke_auth4isp.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;


/**
 * Created by Vagner on 20/01/2021.
 */

public class ErrorCpfDialog {

    MaterialDialog chargebackDialog;
    Button btnConfirm;
    Context mContext;

    public ErrorCpfDialog(Context mContext, String message) {
        this.mContext = mContext;
        chargebackDialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_error_cpf, false)
                .progressIndeterminateStyle(false)
                .show();
        YLog.d("LoginErrorDialog","Constructor", message);

        btnConfirm = (Button) chargebackDialog.findViewById(R.id.buttonBackDialogError);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargebackDialog.dismiss();
            }
        });
    }
}
