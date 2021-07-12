package com.tcc2.bke_auth4isp.panel_client_calls.presenter;

import com.tcc2.bke_auth4isp.entity.Call;
import com.tcc2.bke_auth4isp.panel_client_calls.CallsContracts;
import com.tcc2.bke_auth4isp.panel_client_calls.interactor.CallsInteractor;

import java.util.ArrayList;

public class CallsPresenter implements CallsContracts.Presenter {

    CallsContracts.View view;
    CallsContracts.Interactor interactor;

    public CallsPresenter(CallsContracts.View view) {
        this.view = view;
        this.interactor = new CallsInteractor(this);
    }


    @Override
    public void onCallsReceveid(ArrayList<Call> callList) {
        view.onCallsReceveid(callList);
    }

    @Override
    public void downloadCalls(String idUser) {
        interactor.downloadCalls(idUser);
    }

    @Override
    public void onError(String localizedMessage) {
        view.onError(localizedMessage);
    }
}
