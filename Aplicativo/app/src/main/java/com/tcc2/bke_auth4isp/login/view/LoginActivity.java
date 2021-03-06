package com.tcc2.bke_auth4isp.login.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.common.CpfMask;
import com.tcc2.bke_auth4isp.dialogs.SelectionIspDialog;
import com.tcc2.bke_auth4isp.entity.ISP;
import com.tcc2.bke_auth4isp.login.LoginContracts;
import com.tcc2.bke_auth4isp.login.presenter.LoginPresenter;
import com.tcc2.bke_auth4isp.login.router.LoginRouter;

import java.text.ParseException;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements LoginContracts.View {

    LoginContracts.Presenter presenter;
    LoginContracts.Router router;
    private Activity activity;
    EditText username;
    EditText password;
    Button buttonConfirmLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        router = new LoginRouter(getContext());
        presenter = new LoginPresenter(this, router);
        activity = this;
        username = findViewById(R.id.username);
        username.addTextChangedListener(CpfMask.insert(username));
        password = findViewById(R.id.password);
        buttonConfirmLogin = findViewById(R.id.buttonLogin);
        buttonConfirmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicou:" + username.getText().toString() + password.getText().toString());
                presenter.requestLogin(username.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onIspsRetrivied(ArrayList<ISP> ispList) {
        new SelectionIspDialog(this, "Onde voc?? est???", "Sua sess??o ser?? vinculada a uma cidade. Assim voc?? ver?? apenas o que est?? acontecendo perto de voc??!", ispList, this);
    }

    @Override
    public void onLoginError(String message) {
        LoginErrorDialog dialogError = new LoginErrorDialog(this, message);
    }
}
