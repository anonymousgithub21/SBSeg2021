package com.tcc2.bke_auth4isp.panel_manager_list;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.panel_manager_list_client.view.ListClientFragment;
import com.tcc2.bke_auth4isp.panel_manager_list_technician.view.ListTechnicianFragment;

public class ListUserActivity extends AppCompatActivity {
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        fm = getSupportFragmentManager();
        TabLayout usersStatusTabs = findViewById(R.id.usersTabs);

        usersStatusTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("Clientes")) {
                    Fragment fragment = new ListClientFragment();
                    fm.beginTransaction().replace(R.id.nav_host_user, fragment).commit();
                } else if (tab.getText().equals("Técnicos")) {
                    Fragment fragment = new ListTechnicianFragment();
                    fm.beginTransaction().replace(R.id.nav_host_user, fragment).commit();
                } else {
                    hideEmptyImage();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                    adapter.filterByStatus("");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getText().equals("Cliente")) {
                    Fragment fragment = new ListClientFragment();
                    fm.beginTransaction().replace(R.id.nav_host_user, fragment).commit();
                } else if (tab.getText().equals("Técnico")) {
                    Fragment fragment = new ListTechnicianFragment();
                    fm.beginTransaction().replace(R.id.nav_host_user, fragment).commit();
                } else {
                    hideEmptyImage();
                }
            }
        });
    }

    public void hideEmptyImage() {
        ImageView empty = findViewById(R.id.emptyScheduleList);
        empty.setVisibility(View.INVISIBLE);
    }

    public void showEmptyImage() {
        ImageView empty = findViewById(R.id.emptyScheduleList);
        empty.setVisibility(View.VISIBLE);
    }

}