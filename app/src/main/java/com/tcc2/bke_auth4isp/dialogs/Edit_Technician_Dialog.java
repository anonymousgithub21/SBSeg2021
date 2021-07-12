package com.tcc2.bke_auth4isp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.view.ListTechnicianFragment;

public class Edit_Technician_Dialog {

    private final ListTechnicianFragment listTechnicianFragment;
    MaterialDialog edit_technican_dialog;
    TextView titulo;
    TextView conteudo;
    Button btnConfirm;
    EditText nameTechnician;
    EditText urlPhotoTechnician;
    EditText phoneTechnician;
    EditText certificateTechnician;
    Button btnExitEditTechnician;
    private Activity baseActivity;

    public Edit_Technician_Dialog(Context mContext, String message, Technician technician, ListTechnicianFragment listTechnicianFragment) {
        this.baseActivity = baseActivity;
        this.listTechnicianFragment = listTechnicianFragment;
        edit_technican_dialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_technician_edit, false)
                .progressIndeterminateStyle(false)
                .show();

        btnConfirm = (Button) edit_technican_dialog.findViewById(R.id.btnDialogOk);
        btnExitEditTechnician = (Button) edit_technican_dialog.findViewById(R.id.btnDialogExit);
        nameTechnician = (EditText) edit_technican_dialog.findViewById(R.id.nameTechnician);
        phoneTechnician = (EditText) edit_technican_dialog.findViewById(R.id.phoneTechnician);
        certificateTechnician = (EditText) edit_technican_dialog.findViewById(R.id.certificateTechnician);
        urlPhotoTechnician = (EditText) edit_technican_dialog.findViewById(R.id.urlPhotoTechnician);
        conteudo = (TextView) edit_technican_dialog.findViewById(R.id.textEdit);

        conteudo.setText(message);
        nameTechnician.setText(technician.getName());
        phoneTechnician.setText(technician.getPhone());
        urlPhotoTechnician.setText(technician.getUrl_photo());
        certificateTechnician.setText(technician.getCertified());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                technician.setName(String.valueOf(nameTechnician.getText()));
                technician.setPhone(String.valueOf(phoneTechnician.getText()));
                technician.setUrl_photo(String.valueOf(urlPhotoTechnician.getText()));
                technician.setCertified(String.valueOf(certificateTechnician.getText()));
                listTechnicianFragment.refreshInfoTechnician(technician);
            }
        });

        btnExitEditTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_technican_dialog.dismiss();
            }
        });
    }
}
