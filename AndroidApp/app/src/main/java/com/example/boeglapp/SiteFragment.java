package com.example.boeglapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boeglapp.API.AsyncGetSites;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SiteFragment extends Fragment {

    SiteAdapter tAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.addNewSite_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("update", false);
                NavHostFragment.findNavController(SiteFragment.this)
                        .navigate(R.id.action_siteFragment_to_siteEditorFragment, bundle);
            }
        });

        recyclerView = (RecyclerView) getView().findViewById(R.id.sitelist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        tAdapter = new SiteAdapter();
        recyclerView.setAdapter(tAdapter);

        AsyncGetSites a = new API().new AsyncGetSites(this);
        a.execute();
    }

    public void updateSites(ArrayList<Site> sites){
        tAdapter.setSites(sites);
        tAdapter.notifyDataSetChanged();
    }
}