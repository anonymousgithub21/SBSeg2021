package com.tcc2.bke_auth4isp.dialogs;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;

public class RevokeOTACDialog {

    MaterialDialog revokeDialog;
    TextView titulo;
    TextView conteudo;
    Button btnConfirm;

    private Activity baseActivity;

    public RevokeOTACDialog(FragmentActivity activity, String message) {
        this.baseActivity = activity;
        revokeDialog = new MaterialDialog.Builder(baseActivity)
                .customView(R.layout.dialog_revoke_otac, false)
                .progressIndeterminateStyle(false)
                .show();
        btnConfirm = (Button) revokeDialog.findViewById(R.id.btnMaterialDialogOk);
        titulo = (TextView) revokeDialog.findViewById(R.id.title);
        conteudo = (TextView) revokeDialog.findViewById(R.id.content_notification_dialog);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revokeDialog.dismiss();
            }
        });
    }
}
