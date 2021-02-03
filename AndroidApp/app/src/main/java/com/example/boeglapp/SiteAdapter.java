package com.example.boeglapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder> implements OnItemClickListener {

    private ArrayList<Site> sites = new ArrayList<Site>();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int siteid = sites.get(position).Id;
        Bundle bundle = new Bundle();
        bundle.putBoolean("byTool", false);
        bundle.putInt("id", siteid);
        bundle.putString("label", sites.get(position).Name);
        Navigation.findNavController(view).navigate(R.id.action_siteFragment_to_HistoryFragment, bundle);
    }
    public void setSites(ArrayList<Site> sites){
        this.sites = sites;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public SiteAdapter.SiteViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.site_view, parent, false);

        return new SiteViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SiteViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ((Button) holder.view.findViewById(R.id.site_textview)).setText(sites.get(position).Name);
        ((Button) holder.view.findViewById(R.id.site_textview)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClick(null, v, position, 0);
            }
        });
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClick(null, v, position, 0);
            }
        });*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return sites.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class SiteViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView view;
        public SiteViewHolder(CardView v) {
            super(v);
            view = v;
        }
    }
}
