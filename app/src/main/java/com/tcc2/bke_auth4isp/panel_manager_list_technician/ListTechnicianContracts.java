package com.tcc2.bke_auth4isp.panel_manager_list_technician;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.entity.User;

import java.util.ArrayList;

public interface ListTechnicianContracts {

    interface Router {
    }

    interface Presenter {

        void onTechnicianReceveid(ArrayList<Technician> technicianList);

        void downloadListTechnician();

        void onError(String localizedMessage);

        void onTechnicianUpdate(Technician technician);

        void updateInfoTechnician(Technician technician);

        void downloadTechnicianAPI(String cpf);

        void onTechnicianAPIReceveid(Technician technician);

        void saveNewTechnician(Technician technician, User user);

        void onTechnicianSaved(Technician technician);
        
        void revokeOTAC(String username);

        void onSucessRevokeOTAC();
    }

    interface Interactor {
        void downloadTechnicians();

        void updateTechnician(Technician technician);

        void downloadTechnicianAPI(String cpf);

        void saveNewTechnician(Technician technician);

        void saveNewUser(User user);

        void revokeOTAC(String username);
    }

    interface View {

        void onError(String localizedMessage);

        Context getmContext();

        void onTechnicianReceived(ArrayList<Technician> technicianList);

        void onUpdateTechnicianSucess(Technician technician);

        void onTechnicianAPIReceived(Technician technician);

        void onTechnicianSaved(Technician technician);

        void onSucessRevokeOTAC();
    }
}