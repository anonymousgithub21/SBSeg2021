package com.tcc2.bke_auth4isp.call_evaluation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.call_evaluation.CallEvaluationContracts;
import com.tcc2.bke_auth4isp.entity.Technician;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class AdapterListEvaluations extends RecyclerView.Adapter<AdapterListEvaluations.MyViewHolder> {

    private Context mContext;
    private List<Technician> technicianList;
    private CallEvaluationContracts.Router router;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameUser;
        public Button buttonEvaluation;
        public TextView certificationTechnican;
        public Date dateAttendance;
        public ImageView photoView;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            nameUser = view.findViewById(R.id.txtViewNameTechnican);
            certificationTechnican = view.findViewById(R.id.txtViewCertificationTechnican);
            photoView = view.findViewById(R.id.photoView);
            card = view.findViewById(R.id.cardViewCall);
        }
    }

    public AdapterListEvaluations(Context mContext, ArrayList<Technician> technicianList, CallEvaluationContracts.Router router) {
        this.mContext = mContext;
        this.router = router;
        this.technicianList = technicianList;
    }

    @Override
    public AdapterListEvaluations.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_call, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    @Override
    public int getItemCount() {
        return technicianList.size();
    }

    private Context getContext() {
        return this.mContext;
    }

    public void addItem(Technician s) {
        technicianList.add(s);
        notifyDataSetChanged();
    }


}
