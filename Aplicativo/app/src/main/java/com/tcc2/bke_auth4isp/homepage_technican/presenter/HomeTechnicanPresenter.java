package com.tcc2.bke_auth4isp.homepage_technican.presenter;


import com.tcc2.bke_auth4isp.homepage_technican.HomeTechnicanContracts;
import com.tcc2.bke_auth4isp.homepage_technican.interactor.HomeTechnicanInteractor;

public class HomeTechnicanPresenter implements HomeTechnicanContracts.Presenter {
    HomeTechnicanContracts.Interactor interactor;
    HomeTechnicanContracts.View view;

    public HomeTechnicanPresenter(HomeTechnicanContracts.View view) {
        this.view = view;
        this.interactor = new HomeTechnicanInteractor(this);
    }



}
