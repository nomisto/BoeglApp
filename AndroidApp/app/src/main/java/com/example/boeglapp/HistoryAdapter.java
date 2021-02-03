package com.example.boeglapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder > {

    ArrayList<History> histories = new ArrayList<History>();

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_view, parent, false);

        return new HistoryAdapter.HistoryViewHolder(v);
    }

    public void setHistories(ArrayList<History> histories){
        this.histories = histories;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder  holder, int position) {
        History h = histories.get(position);
        ((TextView) holder.view.findViewById(R.id.employee_value)).setText(h.Employee);
        ((TextView) holder.view.findViewById(R.id.to_value)).setText(h.get_formatted_to_string());
        ((TextView) holder.view.findViewById(R.id.from_value)).setText(h.get_formatted_from_string());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView view;
        public HistoryViewHolder(CardView v) {
            super(v);
            view = v;
        }
    }
}
