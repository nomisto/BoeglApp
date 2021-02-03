package com.example.boeglapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_tools:
                        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).popBackStack(R.id.siteFragment, true);
                        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).popBackStack(R.id.ToolsFragment, true);
                        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(R.id.ToolsFragment);
                        return true;
                    case R.id.nav_sites:
                        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).popBackStack(R.id.siteFragment, true);
                        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).popBackStack(R.id.ToolsFragment, true);
                        Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(R.id.siteFragment);
                        return true;
                }
                return false;
            }
        });

        /*
        SharedPreferences asdf = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor qwer = asdf.edit();
        qwer.remove("employee");
        qwer.apply();
         */

        System.out.println(this.getPreferences(Context.MODE_PRIVATE).contains("employee"));
        System.out.println(this.getPreferences(Context.MODE_PRIVATE).getString("employee","HAHA"));

        NameDialog.checkName(this, false);

        ((Button)findViewById(R.id.employee_change_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameDialog.checkName(MainActivity.this, true);
            }
        });



        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    // Altes menü
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_tool) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("update", false);
            Navigation.findNavController(findViewById(R.id.content)).navigate(R.id.action_tools_to_history, bundle);
        }

        return super.onOptionsItemSelected(item);
    }
    */


}