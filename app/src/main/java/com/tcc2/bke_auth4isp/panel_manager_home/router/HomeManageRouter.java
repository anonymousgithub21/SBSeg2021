package com.tcc2.bke_auth4isp.panel_manager_home.router;

import android.content.Context;
import android.content.Intent;

import com.tcc2.bke_auth4isp.QRCode.ActivityReadQRCode;
import com.tcc2.bke_auth4isp.panel_manager_home.HomeManagerContracts;
import com.tcc2.bke_auth4isp.panel_manager_list.ListUserActivity;

public class HomeManageRouter implements HomeManagerContracts.Router {

    Context mContexto;

    public HomeManageRouter(Context mContexto) {
        this.mContexto = mContexto;
    }

    public void gotoReadQRCode() {
        Intent i = new Intent(mContexto, ActivityReadQRCode.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    public void gotoListUsers(){
        Intent i = new Intent(mContexto, ListUserActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    private Context getContext() {
        return mContexto;
    }

}
