package com.tcc2.bke_auth4isp.panel_manager_list_technician.router;

import com.tcc2.bke_auth4isp.panel_manager_list_technician.ListTechnicianContracts;

public class ListTechnicianRouter implements ListTechnicianContracts.Router {

    ListTechnicianContracts.View view;

    public ListTechnicianRouter (ListTechnicianContracts.View view) {
        this.view = view;
    }
}
