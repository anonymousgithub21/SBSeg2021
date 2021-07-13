package com.tcc2.bke_auth4isp.panel_client_calls.router;

import android.content.Intent;
import android.os.Bundle;

import com.tcc2.bke_auth4isp.call_evaluation.view.ActivityCallEvaluation;
import com.tcc2.bke_auth4isp.entity.Call;
import com.tcc2.bke_auth4isp.panel_client_calls.CallsContracts;

public class CallsRouter implements CallsContracts.Router{

    CallsContracts.View view;

    public CallsRouter(CallsContracts.View view) {
        this.view = view;
    }

    @Override
    public void gotoTechnicianEvaluation(Call call) {
            Intent i = new Intent(view.getmContext(), ActivityCallEvaluation.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CALL", call);
        i.putExtras(bundle);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getmContext().startActivity(i);
    }
}
