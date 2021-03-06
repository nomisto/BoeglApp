package com.example.boeglapp;

import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

public class ToolEditorFragment extends Fragment {

    boolean update = false;
    Integer toolid = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        assert getArguments() != null;
        update = getArguments().getBoolean("update");
        if(update){
            toolid = getArguments().getInt("toolid");
        }

        System.out.println("HIassdf");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tool_editor, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) view.getRootView().findViewById(R.id.toolName)).getText().toString();
                if(name.equals("")){
                    Toast.makeText(getActivity(), "Name darf nicht leer sein!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(update){
                    API.AsyncUpdateTool a = new API().new AsyncUpdateTool(toolid, name);
                    a.execute();
                } else {
                    API.AsyncInsertTool a = new API().new AsyncInsertTool(name);
                    a.execute();
                }
                ((EditText) view.getRootView().findViewById(R.id.toolName)).onEditorAction(EditorInfo.IME_ACTION_DONE);
                NavHostFragment.findNavController(ToolEditorFragment.this).navigateUp();
            }
        });
    }
}
