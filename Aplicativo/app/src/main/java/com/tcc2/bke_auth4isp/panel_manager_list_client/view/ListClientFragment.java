package com.tcc2.bke_auth4isp.panel_manager_list_client.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.dialogs.Add_Client_Dialog;
import com.tcc2.bke_auth4isp.dialogs.ErrorCpfDialog;
import com.tcc2.bke_auth4isp.dialogs.RevokeOTACDialog;
import com.tcc2.bke_auth4isp.entity.Client;
import com.tcc2.bke_auth4isp.panel_manager_list_client.ListClientsContracts;
import com.tcc2.bke_auth4isp.panel_manager_list_client.presenter.ListClientsPresenter;
import com.tcc2.bke_auth4isp.panel_manager_list_client.router.ListClientsRouter;

import java.util.ArrayList;

public class ListClientFragment extends Fragment implements ListClientsContracts.View {

    AdapterListClients adapter;
    RecyclerView recyclerView;
    Context context;
    ListClientsContracts.Router router;
    ListClientsContracts.Presenter presenter;
    FloatingActionButton addClient;
    Add_Client_Dialog add_client_dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_client_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewListClient);
        context = getContext();
        addClient = root.findViewById(R.id.addClient);
        router = new ListClientsRouter(this);
        presenter = new ListClientsPresenter(this);
        presenter.downloadListClients();

        addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YLog.d("ListClientFragment", "setOnClickListener", "Clicando em adicionar técnico: ");
                add_client_dialog = new Add_Client_Dialog(getmContext(), "", presenter);
            }
        });
        return root;
    }

    public void onClientsReceived(ArrayList<Client> clientList){
        YLog.d("ListClientFragment", "onClientsReceived", "Chegaram " + clientList.size() + " clientes.");
        adapter = new AdapterListClients(getContext(), clientList, router, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateClientSucess(Client client) {
        YLog.d("ListClientFragment", "onUpdateClientSucess", "Cliente " + client.getName() +  " atualizado.");
        Toast.makeText(context, "Informações salvas!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClientAPIReceived(Client client) {
        YLog.d("ListClientFragment", "onClientAPIReceived", "Chegou o cliente com CPF: " + client.getCpf() + " da API." );
        add_client_dialog.onShowClientInformation(client);
    }

    @Override
    public void onClientSaved(Client client) {
        add_client_dialog.close();
        Toast.makeText(context, "O cliente " + client.getName() + "foi salvo.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucessRevokeOTAC() {
        RevokeOTACDialog revokeOTACDialog = new RevokeOTACDialog(getActivity(), "");
    }

    @Override
    public void onError(String localizedMessage) {
        ErrorCpfDialog errorCpfDialog = new ErrorCpfDialog(context, "");
    }

    @Override
    public Context getmContext() {
        return context;
    }

    public void refreshInfoClient(Client client) {
        presenter.updateInfoClient(client);
    }

    public void revokeOTAC(String username) {
        presenter.revokeOTAC(username);
    }
}
