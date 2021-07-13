package com.tcc2.bke_auth4isp.login;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.ISP;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.entity.User;

import java.util.ArrayList;

public interface LoginContracts {

    interface Router{
        void gotoHomeScreenClient(Person person);
        void gotoHomeScreenTechican(Person person);
        void gotoHomeScreenManager(Person person);
        void gotoStartPointActivity();
    }

    interface Presenter {
        void requestLogin(String username, String password);

        void authenticateLogin(String password, User user);

        void onLoginSucess(Person person);

        void onIspsRetrivied(ArrayList<ISP> ispList);

        void onLoginError(String message);
    }

    interface Interactor {
        void verifyLogin(String username, String password);

        void downloadProfile(String username, String role);

        void downloadIspList();
    }

    interface  View {
       Context getContext();

        void onIspsRetrivied(ArrayList<ISP> ispsList);

        void onLoginError(String message);
    }

}
