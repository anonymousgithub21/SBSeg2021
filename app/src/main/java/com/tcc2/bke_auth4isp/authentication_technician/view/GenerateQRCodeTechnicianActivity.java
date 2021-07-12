package com.tcc2.bke_auth4isp.authentication_technician.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.authentication_technician.AuthenticationTechnicianContracts;
import com.tcc2.bke_auth4isp.authentication_technician.presenter.AuthenticationTechnicianPresenter;
import com.tcc2.bke_auth4isp.dialogs.ConfirmAuthenticationDialog;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.Person;

public class GenerateQRCodeTechnicianActivity extends AppCompatActivity implements AuthenticationTechnicianContracts.View {

    ImageView imgQRCode;
    AuthenticationTechnicianContracts.Presenter presenter;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_qrcode_technician);
        Toolbar toolbar = findViewById(R.id.toolbarMenuTechnician);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        person = (Person) getIntent().getSerializableExtra("PERSON");
        presenter = new AuthenticationTechnicianPresenter(this, getApplicationContext());
        imgQRCode = findViewById(R.id.imgQRCode);
        presenter.downloadQRCodeInformation(person);
    }

    @Override
    public void generateQRCode(String input) {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(input, BarcodeFormat.QR_CODE, 320, 354);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQRCode.setImageBitmap(bitmap);
        } catch (WriterException e){
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorMessage(String localizedMessage) {
        Toast.makeText(this, localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage3(String decrypted) {

    }

    @Override
    public void showConfirmationActivity(Client client) {
        ConfirmAuthenticationDialog confirmAuthenticationDialog = new ConfirmAuthenticationDialog(this, client);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
