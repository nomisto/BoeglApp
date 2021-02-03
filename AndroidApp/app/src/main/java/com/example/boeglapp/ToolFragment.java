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

import com.example.boeglapp.API.AsyncGetTools;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ToolFragment extends Fragment {

    ToolAdapter tAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tool, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.addNewTool_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("update", false);
                NavHostFragment.findNavController(ToolFragment.this)
                        .navigate(R.id.action_Tools_to_ToolEditor, bundle);
            }
        });

        recyclerView = (RecyclerView) getView().findViewById(R.id.toolslist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        tAdapter = new ToolAdapter();
        recyclerView.setAdapter(tAdapter);

        AsyncGetTools a = new API().new AsyncGetTools(this);
        a.execute();

    }

    public void updateTools(ArrayList<Tool> tools){
        tAdapter.setTools(tools);
        tAdapter.notifyDataSetChanged();
    }
}