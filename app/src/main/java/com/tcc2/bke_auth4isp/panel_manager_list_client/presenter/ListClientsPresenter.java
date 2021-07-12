package com.tcc2.bke_auth4isp.panel_manager_list_client.presenter;

import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.panel_manager_list_client.ListClientsContracts;
import com.tcc2.bke_auth4isp.panel_manager_list_client.interactor.ListClientsInteractor;

import java.util.ArrayList;

public class ListClientsPresenter implements ListClientsContracts.Presenter {

    ListClientsContracts.View view;
    ListClientsContracts.Interactor interactor;

    public ListClientsPresenter(ListClientsContracts.View view) {
        this.view = view;
        this.interactor = new ListClientsInteractor(this);
    }

    @Override
    public void onClientsReceveid(ArrayList<Client> clientList) {
        view.onClientsReceived(clientList);
    }

    @Override
    public void downloadListClients() {
        interactor.downloadClients();
    }

    @Override
    public void onError(String localizedMessage) {
        view.onError(localizedMessage);
    }

    @Override
    public void updateInfoClient(Client client) {
        interactor.updateClient(client);
    }

    @Override
    public void onClientUpdate(Client client) {
        view.onUpdateClientSucess(client);
    }

    @Override
    public void downloadClientAPI(String cpf){
        interactor.downloadClientAPI(cpf);
    }

    @Override
    public void onClientsAPIReceveid(Client client) {
        view.onClientAPIReceived(client);
    }

    @Override
    public void saveNewClient(Client client, User user) {
        interactor.saveNewClient(client);
        interactor.saveNewUser(user);
    }

    @Override
    public void onClientSaved(Client client) {
        view.onClientSaved(client);
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
