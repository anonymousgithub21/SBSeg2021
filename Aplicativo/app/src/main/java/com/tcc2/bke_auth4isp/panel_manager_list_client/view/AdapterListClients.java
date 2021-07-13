package com.tcc2.bke_auth4isp.panel_manager_list_client.view;

import android.app.Activity;
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
import com.tcc2.bke_auth4isp.common.ImageUtilities;
import com.tcc2.bke_auth4isp.dialogs.Edit_Client_Dialog;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.panel_manager_list_client.ListClientsContracts;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdapterListClients extends RecyclerView.Adapter<AdapterListClients.MyViewHolder>{

    private final ListClientFragment listClientFragment;
    private Context mContext;
    private List<Client> clientList;
    private ListClientsContracts.Router router;
    private Activity activity;

    public AdapterListClients(Context mContext, ArrayList<Client> clientList, ListClientsContracts.Router router, ListClientFragment listClientFragment) {
        this.mContext = mContext;
        this.router = router;
        this.clientList = clientList;
        this.listClientFragment = listClientFragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nameClient;
        public TextView phoneClient;
        public ImageView photoViewClient;
        public Button buttonEditClient;
        public CardView card;
        public Button buttonRevogation;


        public MyViewHolder(View view) {
            super(view);
            nameClient = view.findViewById(R.id.txtViewNameClient);
            phoneClient = view.findViewById(R.id.txtViewPhoneClient);
            photoViewClient = view.findViewById(R.id.photoViewClient);
            buttonEditClient = view.findViewById(R.id.buttonEditClient);
            buttonRevogation = view.findViewById(R.id.buttonRevogation);
            card = view.findViewById(R.id.cardViewClient);
        }
    }

    @Override
    public AdapterListClients.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_client, parent, false);

        return new MyViewHolder(itemView);
    }

    public void addClient(Client client) {
        clientList.add(client);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.nameClient.setText(client.getName());
        holder.phoneClient.setText(client.getPhone());
        ImageUtilities.downloadWppFast(holder.photoViewClient, mContext, client.getUrl_photo().concat("?type=large"), 130, 150);

        holder.buttonEditClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Edit_Client_Dialog edit_client_dialog = new Edit_Client_Dialog(mContext, "Edite as informações do cliente.", client, listClientFragment);
                }
            });
        holder.buttonRevogation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listClientFragment.revokeOTAC(client.getUsername());
            }
        });
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public Context getmContext() {
        return mContext;
    }




}
