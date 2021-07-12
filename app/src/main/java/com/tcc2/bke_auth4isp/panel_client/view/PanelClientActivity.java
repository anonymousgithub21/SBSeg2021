package com.tcc2.bke_auth4isp.panel_client.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.panel_client.HomeClientContracts;
import com.tcc2.bke_auth4isp.panel_client.presenter.HomeClientPresenter;
import com.tcc2.bke_auth4isp.panel_client.router.HomeClientRouter;
import com.tcc2.bke_auth4isp.panel_client_calls.view.CallsFragment;
import com.tcc2.bke_auth4isp.panel_client_home.view.HomeFragment;

import java.security.NoSuchAlgorithmException;

public class PanelClientActivity extends AppCompatActivity implements HomeClientContracts.View {

    HomeClientContracts.Presenter presenter;
    HomeClientContracts.Router router;
    BottomNavigationView bottomNavigationView;
    private Activity activity;
    Button buttonGenerateQRCode;
    Button buttonAuthenticationTechnican;
    Person person;
    private HomeFragment homeFragment;
    private Fragment selectedFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        person = (Person) getIntent().getSerializableExtra("PERSON");
        presenter = new HomeClientPresenter(this);
        router = new HomeClientRouter(getContext());
        activity = this;
        buttonGenerateQRCode = findViewById(R.id.buttonGenerateQRCode);
        buttonAuthenticationTechnican = findViewById(R.id.buttonAuthenticationTechnican);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        YLog.d("ActivityReadQRCode", "onActivityResult", "Retorno da leitura de QR Code");
        YLog.d("ActivityReadQRCode", "onActivityResult", "Testando fragment = " + homeFragment);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
//                alert(result.getContents());
                String resultStr = result.getContents();
                YLog.d("ActivityReadQRCode", "onActivityResult", "HomeFragment result QR Code:" + result.getContents());
                try {
                    homeFragment.onQRCodeRead(resultStr);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                YLog.d("ActivityReadQRCode", "onActivityResult", "Resultado do QR Code:" + result.getContents());
            } else {
//                alert("Scan Cancelado");
                YLog.d("ActivityReadQRCode", "onActivityResult", "Scan Cancelado.");
                onBackPressed();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//    private void alert(String msg) {
//        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            YLog.d("PanelClientActivity", "onNavigationItemSelected", "Clicando no item Chamados "+ item.getItemId());

            selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_attendance:
                    selectedFragment = new CallsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();

            return true;
        }
    };

    public void setHomeFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public Person getPerson() {
        return this.person;
    }
}
