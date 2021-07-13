package com.tcc2.bke_auth4isp.call_evaluation;

import android.content.Context;

import com.tcc2.bke_auth4isp.entity.Feedback;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.entity.Technician;

public interface CallEvaluationContracts {

    interface Router{

        void gotoHomeScreenClient(Person person);

        void gotoStartPointActivity();
    }

    interface Presenter {

        void saveFeedback(Feedback feedback);

        void onError(String localizedMessage);

        void onFeedbackSaved(String message);

        void fetchTechnicianInformation(String username_technician);

        void onTechnicianReceived(Technician technician);

        void onTechnicianReceivedError(String localizedMessage);
    }

    interface Interactor {

        void saveNewFeedback(Feedback feedback);

        void downloadTechnicianInformation(String username_technician);
    }

    interface  View {

        void onFeedbackCreated(String message);

        Context getContext();

        void ShowErrorMessage(String error);

        void showTechnicanInformation(Technician technician);

        void showTechnicanRetrivingError(String localizedMessage);
    }

}
