package com.example.boeglapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class NameDialog {

    static String employee_name;

    public static void checkName(final Activity actvty, boolean edit){
        if(!actvty.getPreferences(Context.MODE_PRIVATE).contains("employee") || edit){
            Context context = new ContextThemeWrapper(actvty, R.style.DialogTheme);
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            builder.setTitle("Name");
            // Set up the input
            LinearLayout container = new LinearLayout(actvty);
            container.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(50, 0, 50, 0);
            final EditText input = new EditText(actvty);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setHint("Bitte Name eingeben");

            if(actvty.getPreferences(Context.MODE_PRIVATE).contains("employee")){
                input.setText(actvty.getPreferences(Context.MODE_PRIVATE).getString("employee", ""));
            }

            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setLayoutParams(lp);
            input.setGravity(android.view.Gravity.TOP|android.view.Gravity.LEFT);
            input.setLines(1);
            input.setMaxLines(1);
            container.addView(input, lp);
            builder.setView(container);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
            });

            if(edit){
                builder.setNegativeButton("Abbruch", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
            }

            final AlertDialog ad = builder.create();
            ad.setCancelable(false);
            ad.setCanceledOnTouchOutside(false);
            ad.show();
            ad.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    employee_name = input.getText().toString();
                    if(employee_name.trim().equals(""))
                    {
                        input.setError( "Name ist erforderlich!" );

                    } else {
                        SharedPreferences sharedPref = actvty.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("employee", employee_name);
                        editor.apply();
                        ((TextView)actvty.findViewById(R.id.employee_toolbar)).setText(actvty.getPreferences(Context.MODE_PRIVATE).getString("employee", ""));
                        ad.dismiss();
                    }
                }
            });
        } else {
            ((TextView)actvty.findViewById(R.id.employee_toolbar)).setText(actvty.getPreferences(Context.MODE_PRIVATE).getString("employee", ""));
        }
    }
}
