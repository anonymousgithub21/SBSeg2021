package com.tcc2.bke_auth4isp.authentication_client.interactor;

import com.tcc2.bke_auth4isp.authentication_client.AuthenticationClientContracts;

public class AuthenticationClientInteractor implements AuthenticationClientContracts.Interactor {

    AuthenticationClientContracts.Presenter presenter;

    public AuthenticationClientInteractor(AuthenticationClientContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}
