package com.tcc2.bke_auth4isp.panel_manager_list_technician.presenter;

import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.ListTechnicianContracts;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.interactor.ListTechnicianInteractor;

import java.util.ArrayList;

public class ListTechnicianPresenter implements ListTechnicianContracts.Presenter {

    ListTechnicianContracts.View view;
    ListTechnicianContracts.Interactor interactor;

    public ListTechnicianPresenter (ListTechnicianContracts.View view) {
        this.view = view;
        this.interactor = new ListTechnicianInteractor(this);
    }

    @Override
    public void onTechnicianReceveid(ArrayList<Technician> technicianList) {
        view.onTechnicianReceived(technicianList);
    }

    @Override
    public void downloadListTechnician() {
        interactor.downloadTechnicians();
    }

    @Override
    public void onError(String localizedMessage) {
        view.onError(localizedMessage);
    }

    @Override
    public void onTechnicianUpdate(Technician technician) {
        view.onUpdateTechnicianSucess(technician);
    }

    @Override
    public void updateInfoTechnician(Technician technician) {
        interactor.updateTechnician(technician);
    }

    @Override
    public void downloadTechnicianAPI(String cpf) {
        interactor.downloadTechnicianAPI(cpf);
    }

    @Override
    public void onTechnicianAPIReceveid(Technician technician) {
        view.onTechnicianAPIReceived(technician);
    }

    @Override
    public void saveNewTechnician(Technician technician, User user) {
        interactor.saveNewTechnician(technician);
        interactor.saveNewUser(user);
    }

    @Override
    public void onTechnicianSaved(Technician technician) {
        view.onTechnicianSaved(technician);
    }

    @Override
    public void revokeOTAC(String username) {
        interactor.revokeOTAC(username);
    }

    @Override
    public void onSucessRevokeOTAC() {
        view.onSucessRevokeOTAC();
    }
}
