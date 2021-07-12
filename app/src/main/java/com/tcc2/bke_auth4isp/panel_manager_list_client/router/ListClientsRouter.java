package com.tcc2.bke_auth4isp.panel_manager_list_client.router;

import com.tcc2.bke_auth4isp.panel_manager_list_client.ListClientsContracts;

public class ListClientsRouter implements ListClientsContracts.Router {

    ListClientsContracts.View view;

    public ListClientsRouter (ListClientsContracts.View view) {
        this.view = view;
    }
}
