package com.tcc2.bke_auth4isp.panel_client_calls.view;

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

import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.entity.Call;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.panel_client_calls.CallsContracts;
import com.tcc2.bke_auth4isp.panel_client_calls.presenter.CallsPresenter;
import com.tcc2.bke_auth4isp.panel_client_calls.router.CallsRouter;

import java.util.ArrayList;


public class CallsFragment extends Fragment implements CallsContracts.View {

    AdapterCalls adapter;
    RecyclerView recyclerView;
    Context context;
    CallsContracts.Router router;
    CallsContracts.Presenter presenter;
    Person person;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.calls_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewCalls);
        person = (Person) getActivity().getIntent().getSerializableExtra("PERSON");
        context = getContext();
        router = new CallsRouter(this);
        presenter = new CallsPresenter(this);
        presenter.downloadCalls(person.getUsername());
        return root;
    }

    public void onCallsReceveid(ArrayList<Call> callList){
        YLog.d("CallsFragment", "onCallsReceveid", "Chegaram " + callList.size() + " chamados.");
        adapter = new AdapterCalls(getContext(), callList, router);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String localizedMessage) {
        Toast.makeText(context, "Erro: " + localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getmContext() {
        return context;
    }

}