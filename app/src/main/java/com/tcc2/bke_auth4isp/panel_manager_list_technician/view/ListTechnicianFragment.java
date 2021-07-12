package com.tcc2.bke_auth4isp.panel_manager_list_technician.view;

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
import com.tcc2.bke_auth4isp.dialogs.Add_Technician_Dialog;
import com.tcc2.bke_auth4isp.dialogs.ErrorCpfDialog;
import com.tcc2.bke_auth4isp.dialogs.RevokeOTACDialog;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.ListTechnicianContracts;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.presenter.ListTechnicianPresenter;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.router.ListTechnicianRouter;

import java.util.ArrayList;

public class ListTechnicianFragment extends Fragment implements ListTechnicianContracts.View {

    AdapterListTechnician adapter;
    RecyclerView recyclerView;
    Context context;
    ListTechnicianContracts.Router router;
    ListTechnicianContracts.Presenter presenter;
    FloatingActionButton addTechnician;
    Add_Technician_Dialog add_technician_dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_technician_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewListTechnician);
        context = getContext();
        addTechnician = root.findViewById(R.id.addTechnician);
        router = new ListTechnicianRouter(this);
        presenter = new ListTechnicianPresenter(this);
        presenter.downloadListTechnician();

        addTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YLog.d("ListTechnicianFragment", "setOnClickListener", "Clicando em adicionar técnico: ");
                add_technician_dialog = new Add_Technician_Dialog(getmContext(), "", presenter);
            }
        });

        return root;
    }

    @Override
    public void onTechnicianReceived(ArrayList<Technician> technicianList) {
        YLog.d("ListTechnicianFragment", "onTechnicianReceived", "Chegaram " + technicianList.size() + " técnicos.");
        adapter = new AdapterListTechnician(getContext(), technicianList, router, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateTechnicianSucess(Technician technician) {
        Toast.makeText(context, "Informações salvas!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTechnicianAPIReceived(Technician technician) {
        YLog.d("ListTechnicianFragment", "onTechnicianAPIReceived", "Chegou o técnico com CPF: " + technician.getCpf() + " da API." );
        add_technician_dialog.onShowTechnicianInformation(technician);
    }

    @Override
    public void onTechnicianSaved(Technician technician) {
        add_technician_dialog.close();
        Toast.makeText(context, "O técnico " + technician.getName() + "foi salvo.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucessRevokeOTAC() {
        RevokeOTACDialog revokeOTACDialog = new RevokeOTACDialog(getActivity(),"");
    }


    @Override
    public void onError(String localizedMessage) {
        ErrorCpfDialog errorCpfDialog = new ErrorCpfDialog(context, "");
    }

    @Override
    public Context getmContext() {
        return context;
    }

    public void refreshInfoTechnician(Technician technician) {
        presenter.updateInfoTechnician(technician);
    }

    public void revokeOTAC(String username) {
        presenter.revokeOTAC(username);
    }
}
