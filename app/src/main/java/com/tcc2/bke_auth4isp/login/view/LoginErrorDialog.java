package com.tcc2.bke_auth4isp.login.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;

public class LoginErrorDialog {

    MaterialDialog chargebackDialog;
    TextView text;
    Button btnConfirm;


    private LoginActivity baseActivity;


    public LoginErrorDialog(final LoginActivity baseActivity, String message) {
        this.baseActivity = baseActivity;
        chargebackDialog = new MaterialDialog.Builder(this.baseActivity)
                .customView(R.layout.dialog_error_login, false)
                .progressIndeterminateStyle(false)
                .show();
        YLog.d("LoginErrorDialog","Constructor", message);

        btnConfirm = (Button) chargebackDialog.findViewById(R.id.buttonBackDialogError);
        text = (TextView) chargebackDialog.findViewById(R.id.textErrorLogin);
        text.setText(message);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargebackDialog.dismiss();
                baseActivity.router.gotoStartPointActivity();//gotoStartPointActivity();
            }
        });
    }
}
