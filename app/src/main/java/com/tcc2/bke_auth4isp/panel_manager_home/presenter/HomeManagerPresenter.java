package com.tcc2.bke_auth4isp.panel_manager_home.presenter;

import com.tcc2.bke_auth4isp.panel_manager_home.HomeManagerContracts;
import com.tcc2.bke_auth4isp.panel_manager_home.interactor.HomeManagerInteractor;

public class HomeManagerPresenter implements HomeManagerContracts.Presenter {
    HomeManagerContracts.Interactor interactor;
    HomeManagerContracts.View view;

    public HomeManagerPresenter(HomeManagerContracts.View view) {
        this.view = view;
        this.interactor = new HomeManagerInteractor(this);
    }
}
