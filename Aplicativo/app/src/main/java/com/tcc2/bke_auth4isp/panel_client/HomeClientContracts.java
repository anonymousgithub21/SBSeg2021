package com.tcc2.bke_auth4isp.panel_client;

import android.content.Context;

public interface HomeClientContracts {

    interface Router{
        void gotoGenerateQRCode();
        void gotoReadQRCode();
    }

    interface Presenter {

    }

    interface Interactor {

    }

    interface  View {
        Context getContext();
    }
}
