package com.tcc2.bke_auth4isp.homepage_technican.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.homepage_technican.HomeTechnicanContracts;
import com.tcc2.bke_auth4isp.homepage_technican.presenter.HomeTechnicanPresenter;
import com.tcc2.bke_auth4isp.homepage_technican.router.HomeTechnicanRouter;

public class ActivityHomeTechnican extends AppCompatActivity implements HomeTechnicanContracts.View {

    HomeTechnicanContracts.Presenter presenter;
    HomeTechnicanContracts.Router router;
    private Activity activity;
    Button buttonGenerateQRCode;
    Button buttonAuthenticationClient;
    Person person;
    TextView technician_name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_technican);
        presenter = new HomeTechnicanPresenter(this);
        router = new HomeTechnicanRouter(getContext());
        activity = this;
        person = (Person) getIntent().getSerializableExtra("PERSON");
        buttonGenerateQRCode = findViewById(R.id.buttonGenerateQRCode);
        buttonAuthenticationClient = findViewById(R.id.buttonAuthenticationTechnican);
        technician_name = findViewById(R.id.technician_name);
        technician_name.setText(person.getName() + "!");

        buttonGenerateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                router.gotoGenerateQRCode(person);
            }
        });
        buttonAuthenticationClient.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(getContext(), "Esse fluxo de autenticação não está disponível." +
                          " O técnico deve gerar o código de autenticação para que o cliente leia.", Toast.LENGTH_LONG).show();
///                  router.gotoReadQRCode();
              }
          }
        );
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
