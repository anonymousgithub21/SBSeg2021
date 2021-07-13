package com.tcc2.bke_auth4isp.panel_manager_home;

import android.content.Context;

public interface HomeManagerContracts {

    interface Router{

        void gotoReadQRCode();
        void gotoListUsers();
    }

    interface Presenter {

    }

    interface Interactor {

    }

    interface  View {
        Context getContext();
    }
}
