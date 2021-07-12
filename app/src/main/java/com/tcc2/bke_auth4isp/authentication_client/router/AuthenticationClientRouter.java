package com.tcc2.bke_auth4isp.authentication_client.router;

import android.content.Context;

import com.tcc2.bke_auth4isp.authentication_client.AuthenticationClientContracts;

public class AuthenticationClientRouter implements AuthenticationClientContracts.Router {

    AuthenticationClientContracts.View view;
    Context mContexto;

    public AuthenticationClientRouter(AuthenticationClientContracts.View view, Context mContexto) {
        this.view = view;
        this.mContexto = mContexto;
    }
}
