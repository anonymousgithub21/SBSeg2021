package com.tcc2.bke_auth4isp.panel_client.interactor;

import com.tcc2.bke_auth4isp.panel_client.HomeClientContracts;

public class HomeClientInteractor implements HomeClientContracts.Interactor {

    private HomeClientContracts.Presenter presenter;

    public HomeClientInteractor(HomeClientContracts.Presenter presenter) {
        this.presenter = presenter;
    }

}
