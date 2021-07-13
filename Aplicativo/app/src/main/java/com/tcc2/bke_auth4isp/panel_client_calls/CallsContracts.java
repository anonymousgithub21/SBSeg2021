package com.tcc2.bke_auth4isp.panel_client_calls;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.Call;

import java.util.ArrayList;

public interface CallsContracts {


    interface Router {
        void gotoTechnicianEvaluation(Call call);
    }

    interface Presenter {
        void onCallsReceveid(ArrayList<Call> callList);
        void downloadCalls(String idUser); // @TODO PEGAR ID DA SESSÃO
        void onError(String localizedMessage);
    }

    interface Interactor {
        void downloadCalls(String idUser);
    }

    interface View {
        Context getmContext();
        void onCallsReceveid(ArrayList<Call> callList);

        void onError(String localizedMessage);
    }

}


