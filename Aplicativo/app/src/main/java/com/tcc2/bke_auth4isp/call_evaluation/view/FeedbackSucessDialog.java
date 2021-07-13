package com.tcc2.bke_auth4isp.call_evaluation.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;



/**
 * Created by Vagner on 20/01/2021.
 */

public class FeedbackSucessDialog {

    MaterialDialog chargebackDialog;
    TextView text;
    Button btnConfirm;


    private ActivityCallEvaluation baseActivity;


    public FeedbackSucessDialog(final ActivityCallEvaluation baseActivity, String message) {
        this.baseActivity = baseActivity;
        chargebackDialog = new MaterialDialog.Builder(this.baseActivity)
                .customView(R.layout.dialog_feedback_sucess, false)
                .progressIndeterminateStyle(false)
                .show();
        YLog.d("FeedbackSucessDialog","Constructor", message);

        btnConfirm = (Button) chargebackDialog.findViewById(R.id.btnMaterialDialogOk);
        text = (TextView) chargebackDialog.findViewById(R.id.textSucessFeedback);
        text.setText(message);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargebackDialog.dismiss();
                baseActivity.router.gotoStartPointActivity();//gotoStartPointActivity();
                YLog.d("FeedbackSucessDialog","onClick", "Dismiss");

            }
        });
        YLog.d("FeedbackSucessDialog","Constructor", "End");
    }
}
