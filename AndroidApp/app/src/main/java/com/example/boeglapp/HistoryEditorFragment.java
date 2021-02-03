package com.example.boeglapp;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class HistoryEditorFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_editor, container, false);
    }

    DatePickerDialog from_picker;
    EditText from_date;
    DatePickerDialog to_picker;
    EditText to_date;
    ImageButton save_button;

    TextView new_entry_label;
    //TextView fixed_input;
    TextView unfixed_label;
    Spinner unfixed_input;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final boolean update = getArguments().getBoolean("update");
        final boolean isToolNotSite = getArguments().getBoolean("isToolNotSite");
        final int id = getArguments().getInt("id");
        final String label = getArguments().getString("label");

        new_entry_label = (TextView) view.findViewById(R.id.new_entry_label);
        unfixed_label = (TextView) view.findViewById(R.id.unfixed_label);
        unfixed_input = (Spinner) view.findViewById(R.id.unfixed_input);

        if(isToolNotSite){
            //fixed_label.setText(getString(R.string.tool));
            unfixed_label.setText(getString(R.string.site));

            ArrayList<Site> sites = null;
            try {
                sites = new API().new AsyncGetSites(null).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayAdapter<Site> adapter = new ArrayAdapter<Site>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sites);
            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
            unfixed_input.setAdapter(adapter);
        } else {
            //fixed_label.setText(getString(R.string.site));
            unfixed_label.setText(getString(R.string.tool));

            ArrayList<Tool> tools = null;
            try {
                tools = new API().new AsyncGetTools(null).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayAdapter<Tool> adapter = new ArrayAdapter<Tool>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tools);
            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
            unfixed_input.setAdapter(adapter);
        }
        new_entry_label.append(" " + label);



        to_date = (EditText) view.findViewById(R.id.to_date_input);
        to_date.setInputType(InputType.TYPE_NULL);

        System.out.println(to_date.hasOnClickListeners());

        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                assert getActivity() != null;
                System.out.println(getActivity());
                to_picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                to_date.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            }
                        }, year, month, day);
                to_picker.show();
            }
        });

        from_date = (EditText) view.findViewById(R.id.from_date_input);
        from_date.setInputType(InputType.TYPE_NULL);
        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED");
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                assert getContext() != null;
                from_picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                from_date.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            }
                        }, year, month, day);
                from_picker.show();
            }
        });

        save_button = (ImageButton) view.findViewById(R.id.save_history);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employee = getActivity().getPreferences(Context.MODE_PRIVATE).getString("employee", "");
                if(employee.equals("")){
                    Toast.makeText(getActivity(), "Fehler: Name ist leer, bitte trage einen Namen ein", Toast.LENGTH_SHORT).show();
                    return;
                }

                long to_date_long;
                try {
                    to_date_long = Utils.to_timestamp(to_date.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(getActivity(), "Fehler: Etwas is falsch gelaufen", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }

                long from_date_long;
                try {
                    from_date_long = Utils.to_timestamp(from_date.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(getActivity(), "Fehler: Etwas is falsch gelaufen", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }

                if(to_date_long < from_date_long){
                    Toast.makeText(getActivity(), "Fehler: Bis-Datum muss nach Von-Datum sein", Toast.LENGTH_SHORT).show();
                    return;
                }

                int toolid;
                int siteid;

                if(isToolNotSite){
                    toolid = id;
                    siteid = ((Site)unfixed_input.getSelectedItem()).Id;
                } else {
                    toolid = ((Tool)unfixed_input.getSelectedItem()).Id;
                    siteid = id;
                }

                API.AsyncInsertHistory a = new API().new AsyncInsertHistory(employee, from_date_long, to_date_long, toolid, siteid);
                a.execute();

                //((EditText) view.getRootView().findViewById(R.id.toolName)).onEditorAction(EditorInfo.IME_ACTION_DONE);
                NavHostFragment.findNavController(HistoryEditorFragment.this).navigateUp();
            }
        });

    }


}
