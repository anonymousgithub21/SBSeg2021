package com.tcc2.bke_auth4isp.panel_manager_list_client;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.User;

import java.util.ArrayList;

public interface ListClientsContracts {

    interface Router {
    }

    interface Presenter {

        void onClientsReceveid(ArrayList<Client> clientList);

        void downloadListClients();

        void onError(String localizedMessage);

        void updateInfoClient(Client client);

        void onClientUpdate(Client client);

        void downloadClientAPI(String cpf);

        void onClientsAPIReceveid(Client client);

        void saveNewClient(Client client, User user);

        void onClientSaved(Client client);

        void revokeOTAC(String username);

        void onSucessRevokeOTAC();
    }

    interface Interactor {
        void downloadClients();

        void updateClient(Client client);

        void downloadClientAPI(String cpf);

        void saveNewClient(Client client);

        void saveNewUser(User user);

        void revokeOTAC(String username);
    }

    interface View {

        void onError(String localizedMessage);

        Context getmContext();

        void onClientsReceived(ArrayList<Client> clientList);

        void onUpdateClientSucess(Client client);

        void onClientAPIReceived(Client client);

        void onClientSaved(Client client);

        void onSucessRevokeOTAC();
    }
}