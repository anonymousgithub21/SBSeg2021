/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkeauth4ispserver.presenter;

import bkeauth4ispserver.common.SecurityUtilies;
import bkeauth4ispserver.common.TEA;
import bkeauth4ispserver.entity.M3AuthSolicitation;
import bkeauth4ispserver.interactor.BKEAuth4ISPServerInteractor;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anonymous
 */
public class BKEAuth4ISPServerPresenter implements bkeauth4ispserver.BKEAuth4ISPServerContracts.Presenter {

    bkeauth4ispserver.BKEAuth4ISPServerContracts.Interactor interactor;
    bkeauth4ispserver.BKEAuth4ISPServerContracts.View view;
    TEA tea;
    static String otac = "80C7622471B6AD6D23C7354912BDC97559EA72F65DA8528ADE81D3559FC940B3";
    static String IDISP = "INTERNEITH";
    Random nonceIsp;

    public BKEAuth4ISPServerPresenter(bkeauth4ispserver.BKEAuth4ISPServerContracts.View view) {
        this.view = view;
        this.interactor = new BKEAuth4ISPServerInteractor(this);
        this.tea = new TEA(otac);
    }

    @Override
    public void onM2InformationRetrivied(String encryptedM2) {
        String decrypted = tea.decrypt(encryptedM2);
        view.showMessage2(decrypted);
        System.out.println("M2 Decrypted: " + decrypted);
        String[] clientParameters = decrypted.split("="); // Criando um array para separar as informações com um split
        String clientUsername = clientParameters[0];
        String technicianUsername = clientParameters[1];
        String nonceTechnician = clientParameters[2];
        String nonceClient = clientParameters[3];
        String HMACM2 = clientParameters[4];

        String input = clientUsername.concat("=").concat(technicianUsername).concat("=").concat(nonceTechnician).concat("=").concat(nonceClient);
        // System.out.println("M2 Input: " + input);
        try {
            String HMAC = SecurityUtilies.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)
            if (HMACM2.equals(HMAC)) {
                System.out.println("Validação de HMAC: HMAC Output == HMAC Result");
                System.out.println("HMAC Output: " + HMACM2);
                System.out.println("HMAC Result: " + HMAC);
                generateM3(nonceClient, nonceTechnician, technicianUsername, clientUsername);
            } else {
                System.out.println("Erro de autenticação: os HMAC não coicidem.");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BKEAuth4ISPServerPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateM3(String nonceTechnician, String nonceClient, String technicianUsername, String clientUsername) {
        nonceIsp = new Random(); // criando um nonce para o cliente
        long nonccc = nonceIsp.nextLong();
        M3AuthSolicitation m3AuthSolicitation = new M3AuthSolicitation(nonceTechnician, nonceClient, String.valueOf(nonccc), clientUsername, IDISP, otac);
        String encrypted = tea.encrypt(m3AuthSolicitation.getPayload());
        interactor.sendM3Information(encrypted, otac);
        interactor.listenForM5(otac);

        System.out.println("M3 Input: " + m3AuthSolicitation.getPayload());
        System.out.println("M3 Encrypted: " + encrypted);
        String decrypted = tea.decrypt(encrypted);
        System.out.println("M3 Decrypted: " + decrypted);
    }

    @Override
    public void downloadM2Information() {
        interactor.downloadM2Information(otac);
    }

    @Override
    public void onM5InformationRetrivied(String encryptedM5) {
        //System.out.println("encryptedM5" + encryptedM5);
        String decrypted = tea.decrypt(encryptedM5);
        System.out.println("M5 Decrypted: " + decrypted);
        view.showMessage2(decrypted);
        
        String[] clientParameters = decrypted.split("="); // Criando um array para separar as informações com um split
        String nonceIsp = clientParameters[0];
        String HMACM5 = clientParameters[1];

        String input = nonceIsp;

        try {
            String HMAC = SecurityUtilies.hash256(input.concat(otac)); // Gerando HMAC = (input + otac)
            if (HMACM5.equals(HMAC)) {
                System.out.println("Validação de HMAC: HMAC Output == HMAC Result");
                System.out.println("HMAC Output: " + HMACM5);
                System.out.println("HMAC Result: " + HMAC);
                interactor.removeActiveSession(otac);
                //System.out.println("Usuários autenticados.");
            } else {
                System.out.println("Erro de autenticação: os HMAC não coicidem.");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BKEAuth4ISPServerPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
