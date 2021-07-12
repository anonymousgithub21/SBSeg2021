package com.tcc2.bke_auth4isp.homepage_technican.interactor;


import com.tcc2.bke_auth4isp.homepage_technican.HomeTechnicanContracts;

public class HomeTechnicanInteractor implements HomeTechnicanContracts.Interactor {

    private HomeTechnicanContracts.Presenter presenter;

    public HomeTechnicanInteractor(HomeTechnicanContracts.Presenter presenter) {
        this.presenter = presenter;
    }

}
