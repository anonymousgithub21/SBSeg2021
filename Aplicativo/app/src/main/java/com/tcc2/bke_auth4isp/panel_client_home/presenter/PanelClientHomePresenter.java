package com.tcc2.bke_auth4isp.panel_client_home.presenter;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.common.SecurityUtilities;
import com.tcc2.bke_auth4isp.common.AES;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.panel_client_home.ClientHomeContracts;
import com.tcc2.bke_auth4isp.panel_client_home.interactor.PanelClientHomeInteractor;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class PanelClientHomePresenter implements ClientHomeContracts.Presenter {

    ClientHomeContracts.Interactor interactor;
    ClientHomeContracts.View view;
    Context mContext;
    String otac;
    AES AES;
    String technicianUsername;
    String nonceIsp;

    public PanelClientHomePresenter(ClientHomeContracts.View view) {
        this.view = view;
        mContext = view.getContext();
        this.otac = mContext.getString(R.string.BKE_OTAC);
        this.interactor = new PanelClientHomeInteractor(this);
    }


    @Override
    public void newAuthSolicitation(String encrypted, String otac) {
        interactor.newAuthSolicitation(encrypted, otac);
        interactor.listenForM4(otac);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onM4InformationRetrivied(String encryptedM4) {
//        System.out.println("TEA: " + tea);
//        System.out.println("encryptedM4: " + encryptedM4);

        String decrypted = AES.decrypt(encryptedM4, otac);
        view.showMessage4(decrypted);
        System.out.println("M4 Output: " + decrypted);

        String[] technicianParameters = decrypted.split("="); // Criando um array para separar as informações com um split
        String nonceIsp = technicianParameters[0];
        String nonceClient = technicianParameters[1];
        String HMACM4 = technicianParameters[2];

        this.nonceIsp = nonceIsp;
        String input = nonceIsp.concat("=").concat(nonceClient);

        try {
            String HMAC = SecurityUtilities.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)
            if (HMACM4.equals(HMAC)) {
                System.out.println("HMAC Output: " + HMACM4);
                System.out.println("HMAC Result: " + HMAC);
                interactor.downloadTechnicianInformation(technicianUsername);
            } else {
                System.out.println("Erro de autenticação: os HMAC não coicidem.");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }

    @Override
    public void setTechnicianUsername(String technicianUsername) {
        this.technicianUsername = technicianUsername;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onTechnicianInformationReceived(Technician technician) {
        try {
            generateM5(this.nonceIsp, technician);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateM5(String nonceIsp, Technician technician) throws NoSuchAlgorithmException {
        String input = nonceIsp;
        System.out.println("M5 Input: " + input);
        String HMAC = SecurityUtilities.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)
        String encrypted = AES.encrypt(input.concat("=").concat(HMAC), otac);
        System.out.println("M5 Encrypted: " + encrypted);
        System.out.println("M5 Decrypted: " + AES.decrypt(encrypted, otac));

        interactor.sendM5(encrypted, otac);
        view.showConfimationDialog(technician);
    }

}
