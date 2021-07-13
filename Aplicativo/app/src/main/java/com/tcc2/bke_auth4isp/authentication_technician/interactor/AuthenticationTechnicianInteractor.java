package com.tcc2.bke_auth4isp.authentication_technician.interactor;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.authentication_technician.AuthenticationTechnicianContracts;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Client;

public class AuthenticationTechnicianInteractor implements AuthenticationTechnicianContracts.Interactor {

    AuthenticationTechnicianContracts.Presenter presenter;

    public AuthenticationTechnicianInteractor(AuthenticationTechnicianContracts.Presenter preesnter) {
        this.presenter = preesnter;
    }

    @Override
    public void downloadOTAC(String username) {
        CommonDatabaseReferences.getOTACReference(username).child("OTAC").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String otac = snapshot.getValue(String.class);
                presenter.onOTACReceived(otac);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void listenForM3(String otac) {
        DatabaseReference reference = CommonDatabaseReferences.getM3Reference(otac);
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String encryptedM3 = snapshot.getValue(String.class);
                    System.out.println("M3 Encryptado: " + encryptedM3);
                if (encryptedM3 != null) {
                    presenter.onM3InformationRetrivied(encryptedM3);
                } else {
                    System.out.println("Aguardando por M3.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void sendM4(String encrypted, String otac) {
        CommonDatabaseReferences.getM4Reference(otac).setValue(encrypted);
    }

    @Override
    public void downloadClientInformation(String clientUsername) {
        CommonDatabaseReferences.getClientReference(clientUsername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Client client = snapshot.getValue(Client.class);
                System.out.println(client.getCpf() + "CPF");
                presenter.onClientInformationReceived(client);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
