package com.tcc2.bke_auth4isp.panel_client_calls.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.common.ImageUtilities;
import com.tcc2.bke_auth4isp.entity.Call;
import com.tcc2.bke_auth4isp.panel_client_calls.CallsContracts;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdapterCalls extends RecyclerView.Adapter<AdapterCalls.MyViewHolder> {
    private Context mContext;
    private List<Call> myCalls;
    private CallsContracts.Router router;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTechnican;
        public TextView certificationTechnican;
        public TextView datatime;
        public ImageView photoView;
        public Button buttonEvaluation;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            buttonEvaluation = view.findViewById(R.id.buttonEvaluation);
            nameTechnican = view.findViewById(R.id.txtViewNameTechnican);
            certificationTechnican = view.findViewById(R.id.txtViewCertificationTechnican);
            datatime = view.findViewById(R.id.textViewDataTime);
            photoView = view.findViewById(R.id.photoView);
            card = view.findViewById(R.id.cardViewCall);
        }

    }

    public AdapterCalls(Context mContext, ArrayList<Call> myCalls, CallsContracts.Router router) {
        this.mContext = mContext;
        this.router = router;
        this.myCalls = myCalls;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_call, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Call call = myCalls.get(position);
//        YLog.d("AdapterCalls", "onBindViewHolder", "Exibindo call: "+ call.getClient().getName());
//                String nameTechnican = call.getTechnician().getName();
//        boolean certificationTechnican = call.getTechnician().isCertified();
////        String certification;
////        if (certificationTechnican) {
//////            certification = "Certificado por " + call.getIsp().getName();
////        } else {
//////            certification = "Não certificado por " + call.getIsp().getName();
////        }

        holder.buttonEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                YLog.d("AdapterCalls", "buttonEvaluation.setOnClickListener", "Alterando a tela: "+ call.getClient().getName());
                router.gotoTechnicianEvaluation(call);
            }
        });

//        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy às hh:mm:ss");
//        Date datatime;
//        try {
//            datatime = dateFormat.parse(call.getDate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        YLog.d("AdapterCalls", "OnBindView", "Buscando nome do técnico: " + myCalls.get(position).getName_technician());
        holder.nameTechnican.setText(call.getName_technician());
        ImageUtilities.downloadWppFast(holder.photoView, mContext, call.getUrl_photo().concat("?type=large"), 130, 150);
        holder.datatime.setText(call.getDate());
//        holder.certificationTechnican.setText(certification);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCalls.get(position);
            }
        });
    }


    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    @Override
    public int getItemCount() {
        return myCalls.size();
    }

    public void addItem(Call s) {
        myCalls.add(s);
        notifyDataSetChanged();
    }
}