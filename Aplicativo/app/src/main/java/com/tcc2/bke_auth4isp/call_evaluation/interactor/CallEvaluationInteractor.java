package com.tcc2.bke_auth4isp.call_evaluation.interactor;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.call_evaluation.CallEvaluationContracts;
import com.tcc2.bke_auth4isp.common.CommonDatabaseReferences;
import com.tcc2.bke_auth4isp.entity.Feedback;
import com.tcc2.bke_auth4isp.entity.Technician;

public class CallEvaluationInteractor implements CallEvaluationContracts.Interactor {

    private CallEvaluationContracts.Presenter presenter;

    public CallEvaluationInteractor(CallEvaluationContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void saveNewFeedback(final Feedback feedback) {
        YLog.d("CallsInteractor", "downloadCalls", "Iniciando download de chamados: " + feedback);
        DatabaseReference databaseReference = CommonDatabaseReferences.getFeedbacksReference(feedback.getUsername_client());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                YLog.d("CallsInteractor", "downloadCalls", "Iniciou busca por chamados: " + snapshot.getRef());
                try {
                    databaseReference.child(String.valueOf(snapshot.getChildrenCount())).setValue(feedback);
                    updateTechnicianRate(feedback);
                    presenter.onFeedbackSaved("Recebemos o seu feedback!");
                } catch (Exception e) {
                    presenter.onError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                presenter.onError(error.getMessage());
            }
        });
    }

    private void updateTechnicianRate(Feedback feedback) {
        CommonDatabaseReferences.getTechnicianReference(feedback.getUsername_techinician()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    YLog.d("updateTechnicianRate", "downloadTechnicianInformation", "Referência: " + snapshot.getRef());
                    YLog.d("updateTechnicianRate", "downloadTechnicianInformation", "Fazendo download das informações do técnico no Firebase.");
                    Technician technician = snapshot.getValue(Technician.class);
                    float myRate = feedback.getRating();
                    float currentRate = technician.getRating();
                    float weightMyRate;
                    float weightCurrentRate;
                    if (technician.getCountFeedbacks() > 0) {
                        weightMyRate = Float.valueOf((Float.valueOf(1) / Float.valueOf(technician.getCountFeedbacks())));
                        YLog.d("CallEvaluationInteractor", "updateTechnicianRate", "weightMyRate: "+ "1 / " + technician.getCountFeedbacks() + " = " + weightMyRate);

                        weightCurrentRate = 1 - weightMyRate;
                    } else {
                        weightMyRate = 1;
                        weightCurrentRate = 0;
                    }

                    float newRating = (myRate * weightMyRate) + (currentRate * weightCurrentRate);
                    YLog.d("CallEvaluationInteractor", "updateTechnicianRate", "weightCurrentRate: " + weightCurrentRate);
                    YLog.d("CallEvaluationInteractor", "updateTechnicianRate", "currentRate." + currentRate);
                    YLog.d("CallEvaluationInteractor", "updateTechnicianRate", "newRating." + newRating);
                    YLog.d("CallEvaluationInteractor", "updateTechnicianRate", "countFeedbacks." + technician.getCountFeedbacks());


                    technician.setRating(newRating);
                    technician.setCountFeedbacks(technician.getCountFeedbacks() + 1);
                    snapshot.getRef().setValue(technician);
                } catch (Exception e) {
                    Log.d("ERROR RETRIVED: ", e.getLocalizedMessage());
                    presenter.onTechnicianReceivedError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("RETRIVED CATEGORY: ", error.getMessage());
            }
        });
    }

    @Override
    public void downloadTechnicianInformation(String username_technician) {
        CommonDatabaseReferences.getProfileReference(username_technician, "Technician").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    YLog.d("CallEvaluationInteractor", "downloadTechnicianInformation", "Fazendo download das informações do técnico no Firebase.");
                    Technician technician = snapshot.getValue(Technician.class);
                        presenter.onTechnicianReceived(technician);
                } catch (Exception e) {
                    Log.d("ERROR RETRIVED: ", e.getLocalizedMessage());
                    presenter.onTechnicianReceivedError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("RETRIVED CATEGORY: ", error.getMessage());
            }
        });

    }

}
