package com.tcc2.bke_auth4isp.authentication_client.presenter;

import android.content.Context;

import com.tcc2.bke_auth4isp.authentication_client.AuthenticationClientContracts;
import com.tcc2.bke_auth4isp.authentication_client.interactor.AuthenticationClientInteractor;

public class AuthenticationClientPresenter implements AuthenticationClientContracts.Presenter {

    AuthenticationClientContracts.Interactor interactor;
    AuthenticationClientContracts.View view;
    Context mContext;

    public AuthenticationClientPresenter (AuthenticationClientContracts.View view, Context mContext) {
        this.mContext = mContext;
        this.view = view;
        this.interactor = new AuthenticationClientInteractor(this);
    }
}
