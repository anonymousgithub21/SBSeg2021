package com.tcc2.bke_auth4isp.homepage_technican;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.Person;

public interface HomeTechnicanContracts {

    interface Router{

        void gotoGenerateQRCode(Person person);

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
