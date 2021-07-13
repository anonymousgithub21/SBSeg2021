package com.tcc2.bke_auth4isp.panel_client_calls.interactor;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Call;
import com.tcc2.bke_auth4isp.panel_client_calls.CallsContracts;

import java.util.ArrayList;

public class CallsInteractor implements CallsContracts.Interactor {

    CallsContracts.Presenter presenter;

    public CallsInteractor(CallsContracts.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void downloadCalls(String idUser) {
        YLog.d("CallsInteractor", "downloadCalls", "Iniciando download de chamados: " + idUser);
        CommonDatabaseReferences.getCallsReference(idUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                YLog.d("CallsInteractor", "downloadCalls", "Iniciou busca por chamados: " + snapshot.getRef());
                try {
                    ArrayList<Call> callList = new ArrayList<Call>();
                    for (DataSnapshot snapshotObject : snapshot.getChildren()) {
                        callList.add(snapshotObject.getValue(Call.class));
                    }
                    presenter.onCallsReceveid(callList);
                } catch (Exception e){
                    presenter.onError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onError(error.getMessage());
            }
        });
    }
}
