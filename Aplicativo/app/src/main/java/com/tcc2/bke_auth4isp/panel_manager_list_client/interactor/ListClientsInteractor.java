package com.tcc2.bke_auth4isp.panel_manager_list_client.interactor;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.panel_manager_list_client.ListClientsContracts;

import java.util.ArrayList;

public class ListClientsInteractor implements ListClientsContracts.Interactor {

    ListClientsContracts.Presenter presenter;

    public ListClientsInteractor(ListClientsContracts.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void downloadClients() {
        YLog.d("ListClientsInteractor", "downloadClients", "Iniciando download de clientes: ");
        CommonDatabaseReferences.getClientReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        ArrayList<Client> clientList = new ArrayList<Client>();
                        for (DataSnapshot snapshotObject : snapshot.getChildren()) {
                            clientList.add(snapshotObject.getValue(Client.class));
                        }
                        presenter.onClientsReceveid(clientList);
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

    @Override
    public void updateClient(Client client) {
        CommonDatabaseReferences.getClientReference(client.getUsername()).setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                presenter.onClientUpdate(client);
            }
        });
    }

    @Override
    public void downloadClientAPI(String cpf) {
        YLog.d("ListClientsInteractor", "downloadClientAPI", "Iniciando download de novos clientes: ");
        CommonDatabaseReferences.getClientAPIReference(cpf).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Client client = snapshot.getValue(Client.class);
                    presenter.onClientsAPIReceveid(client);
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

    @Override
    public void saveNewClient(Client client) {
            YLog.d("ListClientsInteractor", "saveNewClient", "Salvando novo cliente: " + client.getUsername());
            DatabaseReference reference = CommonDatabaseReferences.getClientReference(client.getUsername());
        System.out.println(reference.getRef() + "Referência");
            Task<Void> task = reference.setValue(client);

            task.addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    presenter.onClientSaved(client);
                }
            });

            task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    presenter.onError(e.getMessage());
                }
            });
    }

    @Override
    public void saveNewUser(User user) {
        YLog.d("CallsInteractor", "downloadCalls", "Salvando novo usuário: " + user.getUsername());
        Task<Void> reference = CommonDatabaseReferences.getLoginReference(user.getUsername()).setValue(user);

        reference.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.onError(e.getMessage());
            }
        });
    }

    public void revokeOTAC(String username) {
        CommonDatabaseReferences.getOTACReference(username).child("OTAC").setValue("REVOGADO").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                presenter.onSucessRevokeOTAC();
            }
        });
    }

}
