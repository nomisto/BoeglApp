package com.example.boeglapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    HistoryAdapter hAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public void updateHistories(ArrayList<History> histories){
        hAdapter.setHistories(histories);
        hAdapter.notifyDataSetChanged();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        final boolean byTool = getArguments().getBoolean("byTool");
        final int id = getArguments().getInt("id");
        final String label = getArguments().getString("label");
        ((TextView) view.findViewById(R.id.label)).setText(label);

        view.findViewById(R.id.addNewHistory_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("update", false);
                bundle.putBoolean("isToolNotSite", byTool);
                bundle.putInt("id", id);
                bundle.putString("label", label);
                NavHostFragment.findNavController(HistoryFragment.this)
                        .navigate(R.id.action_History_to_HistoryEditor, bundle);
            }
        });


        recyclerView = (RecyclerView) getView().findViewById(R.id.historylist);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        hAdapter = new HistoryAdapter();
        recyclerView.setAdapter(hAdapter);

        if(byTool){
            API.AsyncGetHistoryByToolId a = new API().new AsyncGetHistoryByToolId(this, id);
            a.execute();
        } else {
            API.AsyncGetHistoryBySiteId a = new API().new AsyncGetHistoryBySiteId(this, id);
            a.execute();
        }


        recyclerView.scrollToPosition(hAdapter.histories.size());
    }
}