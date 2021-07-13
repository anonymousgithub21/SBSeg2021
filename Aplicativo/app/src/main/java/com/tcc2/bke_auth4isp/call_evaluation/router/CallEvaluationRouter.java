package com.tcc2.bke_auth4isp.call_evaluation.router;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tcc2.bke_auth4isp.call_evaluation.CallEvaluationContracts;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.panel_client.view.PanelClientActivity;

public class CallEvaluationRouter implements CallEvaluationContracts.Router {

    CallEvaluationContracts.View view;
    Context mContexto;

    public CallEvaluationRouter(CallEvaluationContracts.View view, Context mContexto) {
        this.view = view;
        this.mContexto = mContexto;
    }

    @Override
    public void gotoHomeScreenClient(Person person){

        Intent i = new Intent(mContexto, PanelClientActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSON", person);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContexto.startActivity(i);
    }

    private Context getContext() {
        return mContexto;
    }

    @Override
    public void gotoStartPointActivity() {

    }
}
