package com.tcc2.bke_auth4isp.panel_client_home;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.Technician;

public interface ClientHomeContracts {

    interface Router{
        void gotoGenerateQRCode();
    }

    interface Presenter {

        void newAuthSolicitation(String encrypted, String otac);

        void onM4InformationRetrivied(String encryptedM4);

        void setTechnicianUsername(String technicianUsername);

        void onTechnicianInformationReceived(Technician technician);
    }

    interface Interactor {

        void newAuthSolicitation(String encrypted, String otac);

        void listenForM4(String otac);

        void sendM5(String encrypted, String otac);

        void downloadTechnicianInformation(String technicianUsername);
    }

    interface  View {
        Context getContext();

        void showMessage4(String decrypted);

        void showConfimationDialog(Technician technician);
    }
}
