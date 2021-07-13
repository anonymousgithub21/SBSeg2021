package com.tcc2.bke_auth4isp.dialogs;

import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.entity.ISP;
import com.tcc2.bke_auth4isp.login.LoginContracts;
import com.tcc2.bke_auth4isp.login.view.AdapterIsps;
import com.tcc2.bke_auth4isp.login.view.LoginActivity;

import java.util.ArrayList;


/**
 * Created by Silvio on 02/09/2017.
 */

public class SelectionIspDialog {

    private LoginContracts.View viewLogin;
    private GoogleSignInAccount account;
    private MaterialDialog dialog;
    private TextView titleTextView;
    private TextView messageTextView;
    private RelativeLayout headerSuccess;
    private RelativeLayout headerError;
    private EditText textEndereco;

    private LoginActivity baseActivity;


    public SelectionIspDialog(LoginActivity baseActivity, String title, String message, ArrayList<ISP> isps, LoginContracts.View viewLogin) {
        this.viewLogin = viewLogin;
        this.baseActivity = baseActivity;
        dialog = new MaterialDialog.Builder(baseActivity)
                .customView(R.layout.input_dialog_isp, false)
                .progressIndeterminateStyle(false)
                .show();

        titleTextView = (TextView) dialog.findViewById(R.id.title);
        messageTextView = (TextView) dialog.findViewById(R.id.message);
        headerSuccess = (RelativeLayout) dialog.findViewById(R.id.headerNotificationSuccessLayout);
        headerError = (RelativeLayout) dialog.findViewById(R.id.headerNotificationErrorLayout);
        titleTextView.setText(title);
        messageTextView.setText(message);


        RecyclerView cityListReciclewView = (RecyclerView) dialog.findViewById(R.id.recyclerViewCities);
        AdapterIsps adapter = new AdapterIsps(baseActivity.getContext(), isps, viewLogin);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(baseActivity.getApplicationContext(), 1);
        cityListReciclewView.setLayoutManager(mLayoutManager);
        cityListReciclewView.setItemAnimator(new DefaultItemAnimator());
        cityListReciclewView.setAdapter(adapter);

        //dialog.dismiss();

    }
}
