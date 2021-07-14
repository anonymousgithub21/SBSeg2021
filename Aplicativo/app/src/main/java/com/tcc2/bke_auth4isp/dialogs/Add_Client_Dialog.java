package com.tcc2.bke_auth4isp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.panel_manager_list_client.ListClientsContracts;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class Add_Client_Dialog {

    MaterialDialog add_client_dialog;
    TextView generalDate;
    EditText cpfClient;
    TextView rgClient;
    TextView infoLoginClient;
    RelativeLayout relativeLayout;
    TextView nameClient;
    TextView phoneClient;
    EditText usernameClient;
    EditText passwordClient;
    Button btnDialogSave;
    Client client;
    User user;
    ListClientsContracts.Presenter presenter;
    Context mContext;

    private Activity baseActivity;

    public Add_Client_Dialog(Context mContext, String message, ListClientsContracts.Presenter presenter) {
        this.presenter = presenter;
        this.mContext = mContext;
        this.baseActivity = baseActivity;
        add_client_dialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.add_client, false)
                .progressIndeterminateStyle(false)
                .show();

        cpfClient = (EditText) add_client_dialog.findViewById(R.id.cpfClient);
        relativeLayout = (RelativeLayout) add_client_dialog.findViewById(R.id.relativeInfoClient);
        generalDate = (TextView) add_client_dialog.findViewById(R.id.infoClient);
        nameClient = (TextView) add_client_dialog.findViewById(R.id.nameClient);
        phoneClient = (TextView) add_client_dialog.findViewById(R.id.phoneClient);
        rgClient = (TextView) add_client_dialog.findViewById(R.id.rgClient);
        infoLoginClient = (TextView) add_client_dialog.findViewById(R.id.infoLoginClient);
        usernameClient = (EditText) add_client_dialog.findViewById(R.id.usernameClient);
        passwordClient = (EditText) add_client_dialog.findViewById(R.id.passwordClient);
        btnDialogSave = (Button) add_client_dialog.findViewById(R.id.btnDialogSave);
        MaskEditTextChangedListener maskCPF = new MaskEditTextChangedListener("###.###.###-##", cpfClient);
        cpfClient.addTextChangedListener(maskCPF);

        cpfClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (cpfClient.getText().toString().length() == 14) {
                    String cpf = String.valueOf(cpfClient.getText());
                    cpf = cpf.replace(".", "");
                    cpf = cpf.replace("-","");
                    presenter.downloadClientAPI(cpf);
                    System.out.println(cpf + "cpf");
                    usernameClient.setText(cpf);
                    System.out.println(usernameClient.getText() + "username get text");
                }
            }
        });


        btnDialogSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = String.valueOf(cpfClient.getText());
                cpf = cpf.replace(".", "");
                cpf = cpf.replace("-","");
                String username = usernameClient.getText().toString();
                username = username.replace(".", "");
                username = username.replace("-","");
                String password = String.valueOf(passwordClient.getText());
                String role = "Client";

                user = new User(username, password, role);
                System.out.println(usernameClient.getText() + "username get text");
                System.out.println(client.getUsername() + "username client");
                presenter.saveNewClient(client, user);

            }
        });
    }

    public void onShowClientInformation(Client client) {
        this.client = client;
        relativeLayout.setVisibility(View.VISIBLE);
        generalDate.setVisibility(View.VISIBLE);
        infoLoginClient.setVisibility(View.VISIBLE);
        usernameClient.setVisibility(View.VISIBLE);
        System.out.println(usernameClient.getText() + "username show cliente information");
        passwordClient.setVisibility(View.VISIBLE);
        nameClient.setText(client.getName());
        phoneClient.setText(client.getPhone());
        rgClient.setText(String.valueOf(client.getRg()));
        btnDialogSave.setEnabled(true);
        System.out.println(client.getUsername() + "username onShowClientInformation");

    }

    public void close() {
        this.add_client_dialog.dismiss();
    }
}

