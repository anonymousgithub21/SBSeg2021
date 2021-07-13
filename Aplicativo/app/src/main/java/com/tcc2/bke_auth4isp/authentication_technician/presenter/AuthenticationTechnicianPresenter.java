package com.tcc2.bke_auth4isp.authentication_technician.presenter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.authentication_technician.AuthenticationTechnicianContracts;
import com.tcc2.bke_auth4isp.authentication_technician.interactor.AuthenticationTechnicianInteractor;
import com.tcc2.bke_auth4isp.common.SecurityUtilities;
import com.tcc2.bke_auth4isp.common.TEA;
import com.tcc2.bke_auth4isp.dialogs.ErrorHMACDialog;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.Person;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Logger;

public class AuthenticationTechnicianPresenter implements AuthenticationTechnicianContracts.Presenter {

    AuthenticationTechnicianContracts.Interactor interactor;
    AuthenticationTechnicianContracts.View view;
    Context mContext;
    Person person;
    String otac;
    TEA tea;

    public AuthenticationTechnicianPresenter (AuthenticationTechnicianContracts.View view, Context mContext) {
        this.mContext = mContext;
        this.view = view;
        this.interactor = new AuthenticationTechnicianInteractor(this);
        this.otac = mContext.getString(R.string.BKE_OTAC);
        this.tea = new TEA(otac);
    }

    @Override
    public void downloadQRCodeInformation(Person person) {
        interactor.downloadOTAC(person.getUsername());
        this.person = person;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onOTACReceived(String otac) {
        // idTechnician, idISP, nonceTechnician
        Random nonceTechnician = new Random(); // Gerando nonce do técnico
        String input = person.getUsername().concat("=").concat("INTERNEITH").concat("=").
                concat(String.valueOf(nonceTechnician.nextLong())); // Concatenando a mensagem // idTechnician, idISP, nonceTechnician
        System.out.println("input" + input);
        try {
            String HMAC = SecurityUtilities.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)

            String encrypted = new TEA(otac).encrypt(input.concat("=").concat(HMAC)); // Encriptando as informações + HMAC
            view.generateQRCode(encrypted); // Gera QR Code com as informações
            interactor.listenForM3(otac);
            System.out.println("");
            Log.e("Input Log E: ", "" + input);
            YLog.d("AuthenticationTechnicianPresenter", "onOTACReceived", "Input: " + input);
            YLog.d("AuthenticationTechnicianPresenter", "onOTACReceived", "HMAC: " + HMAC);
            YLog.d("AuthenticationTechnicianPresenter", "onOTACReceived", "Encrypted: " + encrypted);
            YLog.d("AuthenticationTechnicianPresenter", "onOTACReceived", "Decrypted: " + new TEA(otac).decrypt(encrypted) );
        } catch (NoSuchAlgorithmException e) {
            view.showErrorMessage(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            Toast.makeText(mContext, "Não é possível gerar o QR Code. Usuário não autenticado. Contate o Gestor do ISP.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            view.showErrorMessage(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onM3InformationRetrivied(String encryptedM3) {
        String decrypted = tea.decrypt(encryptedM3);
        System.out.println("M3 Output: " + decrypted);

        view.showMessage3(decrypted);

        String[] managerParameters = decrypted.split("="); // Criando um array para separar as informações com um split
        String nonceTechnician = managerParameters[0];
        String nonceClient = managerParameters[1];
        String nonceIsp = managerParameters[2];
        String clientUsername = managerParameters[3];
        String ISPId = managerParameters[4];
        String HMACM3 = managerParameters[5];

        String input = nonceTechnician.concat("=").concat(nonceClient).concat("=").concat(nonceIsp).
                concat("=").concat(clientUsername).concat("=").concat(ISPId);

        try {
            String HMAC = SecurityUtilities.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)
            if (HMACM3.equals(HMAC)) {
                System.out.println("HMAC Output: " + HMACM3);
                System.out.println("HMAC Result: " + HMAC);
                generateM4(nonceIsp, nonceClient, clientUsername);
            } else {
                ErrorHMACDialog errorHMACDialog = new ErrorHMACDialog(mContext, "Os HMAC não coicidem.");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
    }

    @Override
    public void onClientInformationReceived(Client client) {
        view.showConfirmationActivity(client);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateM4(String nonceIsp, String nonceClient, String clientUsername) throws NoSuchAlgorithmException {

        String input = nonceIsp.concat("=").concat(nonceClient);
        System.out.println("M4 Input: " + input);
        String HMAC = SecurityUtilities.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)
        String encrypted = tea.encrypt(input.concat("=").concat(HMAC));
        System.out.println("M4 Encrypted: " + encrypted);
        System.out.println("M4 Decrypted: " + new TEA(otac).decrypt(encrypted));

        interactor.sendM4(encrypted, otac);
        interactor.downloadClientInformation(clientUsername);
    }
}
