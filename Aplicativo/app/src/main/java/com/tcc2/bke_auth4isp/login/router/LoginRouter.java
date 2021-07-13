package com.tcc2.bke_auth4isp.login.router;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.panel_client.view.PanelClientActivity;
import com.tcc2.bke_auth4isp.panel_manager_home.view.ActivityHomeManager;
import com.tcc2.bke_auth4isp.homepage_technican.view.ActivityHomeTechnican;
import com.tcc2.bke_auth4isp.login.LoginContracts;

public class LoginRouter implements LoginContracts.Router {

    Context mContexto;

    public LoginRouter(Context mContexto) {
        this.mContexto = mContexto;
    }

    LoginContracts.View view;

    private Context getContext() {
        return mContexto;
    }

    public void gotoHomeScreenClient(Person person){

        Intent i = new Intent(mContexto, PanelClientActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSON", person);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    public void gotoHomeScreenTechican(Person person){
        Intent i = new Intent(getContext(), ActivityHomeTechnican.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSON", person);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    public void gotoHomeScreenManager(Person person){
        Intent i = new Intent(getContext(), ActivityHomeManager.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSON", person);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    @Override
    public void gotoStartPointActivity() {

    }

}
