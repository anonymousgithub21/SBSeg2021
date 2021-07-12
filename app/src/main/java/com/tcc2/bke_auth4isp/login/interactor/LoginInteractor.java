package com.tcc2.bke_auth4isp.login.interactor;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.ISP;
import com.tcc2.bke_auth4isp.entity.Manager;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.login.LoginContracts;

import java.util.ArrayList;

public class LoginInteractor implements LoginContracts.Interactor {

    private LoginContracts.Presenter presenter;

    public LoginInteractor(LoginContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void verifyLogin(String username, String password) {
        CommonDatabaseReferences.getLoginReference(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                YLog.d("LoginInteractor", "onDataChange", "Iniciou verificação de login: " + snapshot.getRef());
                try {
                    User user = snapshot.getValue(User.class);
                    YLog.d("LoginInteractor", "onDataChange", "Iniciou try verificação do login" + user.getUsername());
                    presenter.authenticateLogin(password, user);
                } catch (NullPointerException e){
                    presenter.onLoginError("Username não encontrado.");
                } catch (Exception e){
                    presenter.onLoginError(e.getMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onLoginError(error.getMessage());
            }
        });
    }

    @Override
    public void downloadProfile(String username, String role) {
        CommonDatabaseReferences.getProfileReference(username, role).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                YLog.d("LoginInteractor", "onDataChange", "Iniciou verificação de login: " + snapshot.getRef());
                if (role.equals("Technician")) {
                    try {
                        Technician technician = snapshot.getValue(Technician.class);
                        YLog.d("LoginInteractor", "onDataChange", "Iniciou try verificação do login" + technician.getUsername());
                        presenter.onLoginSucess(technician);
                    } catch (Exception e){
                        presenter.onLoginError(e.getLocalizedMessage());
                    }
                } else if (role.equals("Manager")) {
                    try {
                        Manager manager = snapshot.getValue(Manager.class);
                        YLog.d("LoginInteractor", "onDataChange", "Iniciou try verificação do login" + manager.getUsername());
                        presenter.onLoginSucess(manager);
                    } catch (Exception e){
                        presenter.onLoginError(e.getLocalizedMessage());
                    }
                } else if (role.equals("Client")){
                    try {
                        Client client = snapshot.getValue(Client.class);
                        YLog.d("LoginInteractor", "onDataChange", "Iniciou try verificação do login" + client.getUsername());
                        presenter.onLoginSucess(client);
                    } catch (Exception e){
                        presenter.onLoginError(e.getLocalizedMessage());
                    }
                } else {
                    presenter.onLoginError("Papel indefinido: " + role);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onLoginError(error.getMessage());
            }
        });
    }

    @Override
    public void downloadIspList() {

        CommonDatabaseReferences.getIspsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ISP> ispList = new ArrayList<>();
                for (DataSnapshot isp : dataSnapshot.getChildren()) {
                    ISP ispValue = isp.getValue((ISP.class));
                    ispList.add(ispValue);
                    YLog.d("LoginInteractor","downloadCityList", "ispValue: "+ispValue );
                }
                presenter.onIspsRetrivied(ispList);
            }
            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
