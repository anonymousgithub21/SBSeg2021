package com.tcc2.bke_auth4isp.panel_client_home.interactor;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.panel_client_home.ClientHomeContracts;

public class PanelClientHomeInteractor implements ClientHomeContracts.Interactor {
    private ClientHomeContracts.Presenter presenter;

    public PanelClientHomeInteractor(ClientHomeContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void newAuthSolicitation(String encrypted, String otac) {
        CommonDatabaseReferences.getM2Reference(otac).setValue(encrypted);
    }

    @Override
    public void listenForM4(String otac) {
        CommonDatabaseReferences.getM4Reference(otac).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String encryptedM4 = snapshot.getValue(String.class);
                System.out.println("M4 Encryptado: " + encryptedM4);
                if (encryptedM4 != null) {
                presenter.onM4InformationRetrivied(encryptedM4);
                } else {
                    System.out.println("Aguardando M4.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendM5(String encrypted, String otac) {
        CommonDatabaseReferences.getM5Reference(otac).setValue(encrypted);
    }

    @Override
    public void downloadTechnicianInformation(String technicianUsername) {
        CommonDatabaseReferences.getTechnicianReference(technicianUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Technician technician = snapshot.getValue(Technician.class);
                presenter.onTechnicianInformationReceived(technician);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
