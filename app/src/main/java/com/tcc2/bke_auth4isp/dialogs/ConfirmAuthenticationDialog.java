package com.tcc2.bke_auth4isp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.common.ImageUtilities;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.entity.Technician;

public class ConfirmAuthenticationDialog {

    MaterialDialog confirmAuthenticationDialog;
    ImageView photo_feedback;
    RatingBar rate_technican;
    TextView nameConfirmation;
    TextView certificateTechnician;
    Button buttonConfirm;
    private Activity baseActivity;
    Context mContext;


    public ConfirmAuthenticationDialog(Context mContext, Technician technician) {
        this.baseActivity = baseActivity;
        this.mContext = mContext;
        confirmAuthenticationDialog = new MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_confirm_authentication, false)
                .progressIndeterminateStyle(false)
                .show();

        buttonConfirm = (Button) confirmAuthenticationDialog.findViewById(R.id.buttonConfirm);
        photo_feedback = (ImageView) confirmAuthenticationDialog.findViewById(R.id.photo_feedback);
        rate_technican = (RatingBar) confirmAuthenticationDialog.findViewById(R.id.rate_technican);
        nameConfirmation = (TextView) confirmAuthenticationDialog.findViewById(R.id.nameConfirmation);
        certificateTechnician = (TextView) confirmAuthenticationDialog.findViewById(R.id.certificateTechnician);

        ImageUtilities.downloadWppFast(photo_feedback, mContext, technician.getUrl_photo().concat("?type=large"), 130, 150);
        rate_technican.setRating(technician.getRating());
        nameConfirmation.setText(technician.getName());
        certificateTechnician.setText(technician.getCertified());

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Técnico autenticado.", Toast.LENGTH_SHORT).show();
                confirmAuthenticationDialog.dismiss();
            }
        });
    }

    public ConfirmAuthenticationDialog(Activity activity, Client client) {
        this.baseActivity = baseActivity;
        this.mContext = activity.getBaseContext();
        confirmAuthenticationDialog = new MaterialDialog.Builder(activity)
                .customView(R.layout.dialog_confirm_authentication, false)
                .progressIndeterminateStyle(false)
                .show();

        buttonConfirm = (Button) confirmAuthenticationDialog.findViewById(R.id.buttonConfirm);
        photo_feedback = (ImageView) confirmAuthenticationDialog.findViewById(R.id.photo_feedback);
        rate_technican = (RatingBar) confirmAuthenticationDialog.findViewById(R.id.rate_technican);
        nameConfirmation = (TextView) confirmAuthenticationDialog.findViewById(R.id.nameConfirmation);
        certificateTechnician = (TextView) confirmAuthenticationDialog.findViewById(R.id.certificateTechnician);

        ImageUtilities.downloadWppFast(photo_feedback, mContext, client.getUrl_photo().concat("?type=large"), 130, 150);
        nameConfirmation.setText(client.getName());
        rate_technican.setVisibility(View.GONE);
        certificateTechnician.setVisibility(View.GONE);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Cliente autenticado.", Toast.LENGTH_SHORT).show();
                confirmAuthenticationDialog.dismiss();
            }
        });
    }
}
