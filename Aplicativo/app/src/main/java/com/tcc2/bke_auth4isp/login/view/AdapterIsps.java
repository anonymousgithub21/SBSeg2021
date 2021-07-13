package com.tcc2.bke_auth4isp.login.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.entity.ISP;
import com.tcc2.bke_auth4isp.login.LoginContracts;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdapterIsps extends RecyclerView.Adapter<AdapterIsps.MyViewHolder> {
    private Context mContext;
    LoginContracts.Router router;
    private List<ISP> isps;
    LoginContracts.View viewLogin;

    public AdapterIsps(Context mContext, ArrayList<ISP> isps, LoginContracts.View viewLogin) {
        this.viewLogin = viewLogin;
        this.isps = isps;
        this.mContext = mContext;
    }


    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<ISP> getIspName() {
        return isps;
    }


    public void setIspName(List<ISP> ispsNames) {
        this.isps = ispsNames;
    }

    public void addItem(ISP icon) {
        isps.add(icon);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public CardView cardCategorySelection;

        public MyViewHolder(View view) {
            super(view);
            description = view.findViewById(R.id.card_service_selecion_name);
            cardCategorySelection = view.findViewById(R.id.card_service_selecion);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_isp, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ISP isp = isps.get(position);
        holder.description.setText(isp.getName());
        holder.cardCategorySelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                viewLogin.onUpdateSelectedCity(cities.get(position), account);
            }

        });

    }

    /*---------- Faz busca na lista ------- */
    public ArrayList<ISP> gridSearch(String text) {
        ArrayList<ISP> result = new ArrayList<>();
        for (int i = 0; i < isps.size(); i++) {
            if (unAccent(isps.get(i).getName().toLowerCase()).contains(unAccent(text.toLowerCase()))) {
                result.add(isps.get(i));
            }
        }
        setIspName(result);
        return result;
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    @Override
    public int getItemCount() {
        return isps.size();
    }
}