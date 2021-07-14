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
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.ListTechnicianContracts;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class Add_Technician_Dialog {

    MaterialDialog add_technician_dialog;
    TextView generalDate;
    EditText cpfTechnician;
    TextView rgTechnician;
    TextView infoLoginTechnician;
    RelativeLayout relativeLayout;
    TextView nameTechnician;
    TextView phoneTechnician;
    EditText usernameTechnician;
    EditText passwordTechnician;
    Button btnDialogSave;
    Technician technician;
    User user;
    ListTechnicianContracts.Presenter presenter;
    Context mContext;

    private Activity baseActivity;

    public Add_Technician_Dialog(Context mContext, String message, ListTechnicianContracts.Presenter presenter) {
        this.presenter = presenter;
        this.mContext = mContext;
        this.baseActivity = baseActivity;
        add_technician_dialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.add_technician, false)
                .progressIndeterminateStyle(false)
                .show();

        cpfTechnician = (EditText) add_technician_dialog.findViewById(R.id.cpfTechnician);
        relativeLayout = (RelativeLayout) add_technician_dialog.findViewById(R.id.relativeInfoTechnician);
        generalDate = (TextView) add_technician_dialog.findViewById(R.id.infoTechnician);
        nameTechnician = (TextView) add_technician_dialog.findViewById(R.id.nameTechnician);
        phoneTechnician = (TextView) add_technician_dialog.findViewById(R.id.phoneTechnician);
        rgTechnician = (TextView) add_technician_dialog.findViewById(R.id.rgTechnician);
        infoLoginTechnician = (TextView) add_technician_dialog.findViewById(R.id.infoLoginTechnician);
        usernameTechnician = (EditText) add_technician_dialog.findViewById(R.id.usernameTechnician);
        passwordTechnician = (EditText) add_technician_dialog.findViewById(R.id.passwordTechnician);
        btnDialogSave = (Button) add_technician_dialog.findViewById(R.id.btnDialogSave);
        MaskEditTextChangedListener maskCPF = new MaskEditTextChangedListener("###.###.###-##", cpfTechnician);
        cpfTechnician.addTextChangedListener(maskCPF);

        cpfTechnician.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (cpfTechnician.getText().toString().length() == 14) {
                    String cpf = String.valueOf(cpfTechnician.getText());
                    cpf = cpf.replace(".", "");
                    cpf = cpf.replace("-","");
                    System.out.println("Aqui o CPF: " + cpf);
                    presenter.downloadTechnicianAPI(cpf);
                    usernameTechnician.setText(cpf);
                }
            }
        });


        btnDialogSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = String.valueOf(cpfTechnician.getText());
                cpf = cpf.replace(".", "");
                cpf = cpf.replace("-","");
                String username = usernameTechnician.getText().toString();
                username = username.replace(".", "");
                username = username.replace("-","");
                String password = String.valueOf(passwordTechnician.getText());
                String role = "Technician";

                user = new User(username, password, role);

                presenter.saveNewTechnician(technician, user);
            }
        });

    }

    public void onShowTechnicianInformation(Technician technician) {
        this.technician = technician;

        relativeLayout.setVisibility(View.VISIBLE);
        generalDate.setVisibility(View.VISIBLE);
        infoLoginTechnician.setVisibility(View.VISIBLE);
        usernameTechnician.setVisibility(View.VISIBLE);
        passwordTechnician.setVisibility(View.VISIBLE);
        nameTechnician.setText(technician.getName());
        phoneTechnician.setText(technician.getPhone());
        rgTechnician.setText(String.valueOf(technician.getRg()));
        btnDialogSave.setEnabled(true);
    }

    public void close() {
        this.add_technician_dialog.dismiss();
    }
}

