package com.tcc2.bke_auth4isp.panel_manager_list_technician.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.common.ImageUtilities;
import com.tcc2.bke_auth4isp.dialogs.Edit_Technician_Dialog;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.ListTechnicianContracts;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdapterListTechnician extends RecyclerView.Adapter<AdapterListTechnician.MyViewHolder>{

    private final ListTechnicianFragment listTechnicianFragment;
    private Context mContext;
    private List<Technician> technicianList;
    private ListTechnicianContracts.Router router;

    public AdapterListTechnician(Context mContext, ArrayList<Technician> technicianList, ListTechnicianContracts.Router router, ListTechnicianFragment listTechnicianFragment) {
        this.mContext = mContext;
        this.router = router;
        this.technicianList = technicianList;
        this.listTechnicianFragment = listTechnicianFragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTechnician;
        public TextView phoneTechnician;
        public ImageView photoViewTechnician;
        public CardView card;
        public TextView txtViewCertificationTechnican;
        public Button edit_technician;
        public Button buttonRevogationTechnician;
        public RatingBar rate_technican;

        public MyViewHolder(View view) {
            super(view);
            nameTechnician = view.findViewById(R.id.txtViewNameTechnican);
            phoneTechnician = view.findViewById(R.id.txtViewPhoneTechnician);
            photoViewTechnician = view.findViewById(R.id.photoViewTechnician);
            rate_technican = view.findViewById(R.id.rate_technican);
            buttonRevogationTechnician = view.findViewById(R.id.buttonRevogationTechnician);
            txtViewCertificationTechnican = view.findViewById(R.id.txtViewCertificationTechnican);
            edit_technician = view.findViewById(R.id.edit_technician);
            card = view.findViewById(R.id.cardViewTechnician);
        }
    }

    @Override
    public AdapterListTechnician.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_technician, parent, false);

        return new MyViewHolder(itemView);
    }

    public void addTechnician(Technician technician) {
        technicianList.add(technician);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListTechnician.MyViewHolder holder, int position) {
        Technician technician = technicianList.get(position);
        holder.nameTechnician.setText(technician.getName());
        holder.rate_technican.setRating(technician.getRating());
        ImageUtilities.downloadWppFast(holder.photoViewTechnician, mContext, technician.getUrl_photo().concat("?type=large"), 130, 150);
        holder.phoneTechnician.setText(technician.getPhone());
        holder.txtViewCertificationTechnican.setText(technician.getCertified());
        holder.edit_technician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit_Technician_Dialog edit_technician_dialog = new Edit_Technician_Dialog(mContext, "Edite as suas informações.", technician, listTechnicianFragment);
            }
        });
        holder.buttonRevogationTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                listTechnicianFragment.revokeOTAC(technician.getUsername());
                Toast.makeText(getmContext(), "Desabilitamos este fluxo porque a autorização foi feita de forma manual. Dessa forma, a revogação inviabilizaria futuros testes.", Toast.LENGTH_LONG);
            }
        });

    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public List<Technician> getTechnicianList() {
        return technicianList;
    }

    public void setTechnicianList(List<Technician> technicianList) {
        this.technicianList = technicianList;
    }

    @Override
    public int getItemCount() {
        return technicianList.size();
    }

    public Context getmContext() {
        return mContext;
    }
}
