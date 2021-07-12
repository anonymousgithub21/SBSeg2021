package com.tcc2.bke_auth4isp.homepage_technican.router;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tcc2.bke_auth4isp.QRCode.ActivityReadQRCode;
import com.tcc2.bke_auth4isp.authentication_technician.view.GenerateQRCodeTechnicianActivity;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.homepage_technican.HomeTechnicanContracts;


public class HomeTechnicanRouter implements HomeTechnicanContracts.Router {

    Context mContexto;

    public HomeTechnicanRouter(Context mContexto) {
        this.mContexto = mContexto;
    }

    public void gotoGenerateQRCode(Person person){
        Intent i = new Intent(mContexto, GenerateQRCodeTechnicianActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSON", person);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    public void gotoReadQRCode(){
        Intent i = new Intent(mContexto, ActivityReadQRCode.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    private Context getContext() {
        return mContexto;
    }

}
