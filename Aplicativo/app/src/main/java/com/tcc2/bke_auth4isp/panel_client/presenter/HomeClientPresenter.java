package com.tcc2.bke_auth4isp.panel_client.presenter;


import com.tcc2.bke_auth4isp.panel_client.HomeClientContracts;
import com.tcc2.bke_auth4isp.panel_client.interactor.HomeClientInteractor;

public class HomeClientPresenter implements HomeClientContracts.Presenter {
    HomeClientContracts.Interactor interactor;
    HomeClientContracts.View view;

    public HomeClientPresenter(HomeClientContracts.View view) {
        this.view = view;
        this.interactor = new HomeClientInteractor(this);
    }
}
