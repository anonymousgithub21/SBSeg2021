package com.tcc2.bke_auth4isp.login.presenter;

import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.ISP;
import com.tcc2.bke_auth4isp.entity.Manager;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.entity.User;
import com.tcc2.bke_auth4isp.login.LoginContracts;
import com.tcc2.bke_auth4isp.login.interactor.LoginInteractor;

import java.util.ArrayList;

public class LoginPresenter implements LoginContracts.Presenter {

    LoginContracts.Interactor interactor;
    LoginContracts.View view;
    LoginContracts.Router router;

    public LoginPresenter(LoginContracts.View view, LoginContracts.Router router) {
        this.view = view;
        this.interactor = new LoginInteractor(this);
        this.router = router;

    }

    @Override
    public void requestLogin(String username, String password) {
        System.out.println("Cliclou Presenter");
        interactor.verifyLogin(username, password);
    }

    @Override
    public void authenticateLogin(String password, User user) {
        if (user.getPassword().equals(password)) {
            interactor.downloadProfile(user.getUsername(), user.getRole());
        } else {
            view.onLoginError("A senha está incorreta.");
        }
    }

    @Override
    public void onLoginSucess(Person person) {
        YLog.d("LoginPresenter", "onLoginSucess", "Usuário autenticado com sucesso: " + person.getUsername() + "");

        if (person instanceof Technician) {
            router.gotoHomeScreenTechican(person);
        } else if (person instanceof Manager) {
            router.gotoHomeScreenManager(person);
        } else if (person instanceof Client) {
            router.gotoHomeScreenClient(person);
        } else {
            view.onLoginError("Papel do usuário indefinido.");
        }
    }

    @Override
    public void onIspsRetrivied(ArrayList<ISP> ispList) {
        view.onIspsRetrivied(ispList);
    }

    @Override
    public void onLoginError(String message) {
        YLog.d("LoginPresenter", "onLoginError", message);
        view.onLoginError(message);
    }
}
