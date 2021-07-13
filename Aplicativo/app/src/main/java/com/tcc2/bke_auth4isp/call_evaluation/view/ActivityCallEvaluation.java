package com.tcc2.bke_auth4isp.call_evaluation.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.call_evaluation.CallEvaluationContracts;
import com.tcc2.bke_auth4isp.call_evaluation.presenter.CallEvaluationPresenter;
import com.tcc2.bke_auth4isp.call_evaluation.router.CallEvaluationRouter;
import com.tcc2.bke_auth4isp.common.ImageUtilities;
import com.tcc2.bke_auth4isp.entity.Call;
import com.tcc2.bke_auth4isp.entity.Feedback;
import com.tcc2.bke_auth4isp.entity.Technician;

public class ActivityCallEvaluation extends AppCompatActivity implements CallEvaluationContracts.View {

    CallEvaluationContracts.Presenter presenter;
    private Button buttonAvaiableTechnican;
    private TextView technican_name;
    private EditText comments;
    private RatingBar rate_technican_bar;
    CallEvaluationContracts.Router router;
    ImageView imageView;
    Call call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_feedback);
        presenter = new CallEvaluationPresenter(this, getContext());
        router = new CallEvaluationRouter(this, getContext());
        comments = findViewById(R.id.comments);
        imageView = findViewById(R.id.photo_feedback);
        technican_name = findViewById(R.id.technican_name);
        rate_technican_bar = (RatingBar) findViewById(R.id.rate_technican);
        call = (Call) getIntent().getSerializableExtra("CALL");
        technican_name.setText(call.getName_technician());
        ImageUtilities.downloadWppFast(imageView, getApplicationContext(), call.getUrl_photo().concat("?type=large"), 130, 150);

        try {
            YLog.d("ActivityCallEvaluation", "onCreate", "Carregando informações do técnico.");
            presenter.fetchTechnicianInformation(call.getUsername_technician());
        } catch (Exception e) {
            Log.d("ERROR RETRIVED: ", e.getLocalizedMessage());
        }

        try {
            buttonAvaiableTechnican = findViewById(R.id.buttonAvaiableTechnican);
            buttonAvaiableTechnican.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float rating = rate_technican_bar.getRating();
                    String commentsText = String.valueOf(comments.getText());
                    String technicanName = call.getName_technician();
                    String usernameClient = call.getUsername_client();
                    String usernameTechnican = call.getUsername_technician();

                    presenter.saveFeedback(new Feedback(technicanName, usernameTechnican, usernameClient, commentsText, rating));
                    buttonAvaiableTechnican.setEnabled(false);
                    YLog.d("ActivityCallEvaluation", "onClick", String.valueOf(rating));
                }
            });
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    @Override
    public void onFeedbackCreated(String message) {
        YLog.d("FeedbackSucessDialog", "onFeedbackCreated", message);
        FeedbackSucessDialog dialogSucess = new FeedbackSucessDialog(this, message);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void ShowErrorMessage(String error) {
        Toast.makeText(this, "Erro ao avaliar profissional: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTechnicanInformation(Technician technician) {
//        ImageUtilities.downloadWppFast(imageView, getApplicationContext(), technician.getUrl_photo().concat("?type=large"), 130, 150);
    }

    @Override
    public void showTechnicanRetrivingError(String localizedMessage) {
        Toast.makeText(this, "Existe um erro no cadastro: " + localizedMessage, Toast.LENGTH_SHORT).show();
    }

}
