package com.tcc2.bke_auth4isp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.panel_manager_list_client.view.ListClientFragment;

public class Edit_Client_Dialog {

    private final ListClientFragment listClientFragment;
    MaterialDialog edit_client_dialog;
    TextView titulo;
    TextView conteudo;

    Button btnConfirm;
    EditText nameClient;
    EditText phoneClient;
    EditText urlPhotoClient;
    Button btnExitEditClient;

    private Activity baseActivity;

    public Edit_Client_Dialog(Context mContext, String message, Client client, ListClientFragment listClientFragment) {
        this.baseActivity = baseActivity;
        this.listClientFragment = listClientFragment;
        edit_client_dialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_client_edit, false)
                .progressIndeterminateStyle(false)
                .show();

        nameClient = (EditText) edit_client_dialog.findViewById(R.id.nameClient);
        phoneClient = (EditText) edit_client_dialog.findViewById(R.id.phoneClient);
        btnConfirm = (Button) edit_client_dialog.findViewById(R.id.btnDialogOk);
        urlPhotoClient = (EditText) edit_client_dialog.findViewById(R.id.urlPhotoClient);
        btnExitEditClient = (Button) edit_client_dialog.findViewById(R.id.btnDialogExit);
        titulo = (TextView) edit_client_dialog.findViewById(R.id.title);
        conteudo = (TextView) edit_client_dialog.findViewById(R.id.textEdit);

        conteudo.setText(message);
        nameClient.setText(client.getName());
        urlPhotoClient.setText(client.getUrl_photo());
        phoneClient.setText(client.getPhone());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               client.setName(String.valueOf(nameClient.getText()));
               client.setPhone(String.valueOf(phoneClient.getText()));
               client.setUrl_photo(String.valueOf(urlPhotoClient.getText()));
               listClientFragment.refreshInfoClient(client);
            }
        });

        btnExitEditClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_client_dialog.dismiss();
            }
        });
    }
}

