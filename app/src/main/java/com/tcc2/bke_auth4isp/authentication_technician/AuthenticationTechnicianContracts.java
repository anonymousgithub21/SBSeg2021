package com.tcc2.bke_auth4isp.authentication_technician;

import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.Person;

public interface AuthenticationTechnicianContracts {

    interface Router{}

    interface View{
        void generateQRCode(String input);

        void showErrorMessage(String localizedMessage);

        void showMessage3(String decrypted);

        void showConfirmationActivity(Client clientUsername);
    }

    interface Presenter{
        void downloadQRCodeInformation(Person person);

        void onOTACReceived(String otac);

        void onM3InformationRetrivied(String encryptedM4);

        void onClientInformationReceived(Client client);
    }

    interface Interactor{
        void downloadOTAC(String username);

        void listenForM3(String otac);

        void sendM4(String encrypted, String otac);

        void downloadClientInformation(String clientUsername);
    }
}
