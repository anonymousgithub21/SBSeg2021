package com.tcc2.bke_auth4isp.authentication_technician.router;

import android.content.Context;

import com.tcc2.bke_auth4isp.authentication_technician.AuthenticationTechnicianContracts;

public class AuthenticationTechnicianRouter implements AuthenticationTechnicianContracts.Router {

    AuthenticationTechnicianContracts.View view;
    Context mContexto;

    public AuthenticationTechnicianRouter(AuthenticationTechnicianContracts.View view, Context mContexto) {
        this.view = view;
        this.mContexto = mContexto;
    }
}
