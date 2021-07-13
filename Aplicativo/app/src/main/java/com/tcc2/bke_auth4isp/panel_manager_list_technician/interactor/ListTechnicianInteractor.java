package com.tcc2.bke_auth4isp.panel_manager_list_technician.interactor;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.ListTechnicianContracts;

import java.util.ArrayList;

public class ListTechnicianInteractor implements ListTechnicianContracts.Interactor {

    ListTechnicianContracts.Presenter presenter;

    public ListTechnicianInteractor (ListTechnicianContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void downloadTechnicians() {
        YLog.d("ListTechnicianInteractor", "downloadTechnicians", "Iniciando download de técnicos: ");
        CommonDatabaseReferences.getTechnicianListReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    ArrayList<Technician> technicianList = new ArrayList<Technician>();
                    for (DataSnapshot snapshotObject : snapshot.getChildren()) {
                        technicianList.add(snapshotObject.getValue(Technician.class));
                    }
                    presenter.onTechnicianReceveid(technicianList);
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
    public void updateTechnician(Technician technician) {
        CommonDatabaseReferences.getTechnicianReference(technician.getUsername()).setValue(technician).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                presenter.onTechnicianUpdate(technician);
            }
        });
    }

    @Override
    public void downloadTechnicianAPI(String cpf) {
        CommonDatabaseReferences.getTechnicianAPIReference(cpf).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Technician technician = snapshot.getValue(Technician.class);
                    presenter.onTechnicianAPIReceveid(technician);
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
    public void saveNewTechnician(Technician technician) {
        YLog.d("ListTechnicianInteractor", "saveNewTechnician", "Salvando novo técnico: " + technician.getName());
        Task<Void> reference = CommonDatabaseReferences.getTechnicianReference(technician.getUsername()).setValue(technician);

        reference.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                presenter.onTechnicianSaved(technician);
            }
        });

        reference.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.onError(e.getMessage());
            }
        });
    }

    @Override
    public void saveNewUser(User user) {
        Task<Void> reference = CommonDatabaseReferences.getLoginReference(user.getUsername()).setValue(user);

        reference.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                presenter.onError(e.getMessage());
            }
        });
    }

    @Override
    public void revokeOTAC(String username) {
        CommonDatabaseReferences.getOTACReference(username).child("OTAC").setValue("REVOGADO").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                presenter.onSucessRevokeOTAC();
            }
        });
    }
}
