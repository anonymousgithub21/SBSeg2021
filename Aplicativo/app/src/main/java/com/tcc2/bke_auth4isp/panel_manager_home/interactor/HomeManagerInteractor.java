package com.tcc2.bke_auth4isp.panel_manager_home.interactor;

import com.tcc2.bke_auth4isp.panel_manager_home.HomeManagerContracts;

public class HomeManagerInteractor implements HomeManagerContracts.Interactor {

    private HomeManagerContracts.Presenter presenter;

    public HomeManagerInteractor(HomeManagerContracts.Presenter presenter) {
        this.presenter = presenter;
    }

}
