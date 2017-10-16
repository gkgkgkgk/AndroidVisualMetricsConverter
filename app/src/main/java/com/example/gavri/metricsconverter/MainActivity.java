package com.example.gavri.metricsconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spinnerLeft, spinnerRight;

    String currentUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addSpinnerListeners();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_speed) {
            addSpinnerItems("speed");
            currentUnit = "speed";
        } else if (id == R.id.nav_cooking) {
            addSpinnerItems("cooking");
            currentUnit = "cooking";
        } else if (id == R.id.nav_data) {
            addSpinnerItems("data");
            currentUnit = "data";
        } else if (id == R.id.nav_distance) {
            addSpinnerItems("distance");
            currentUnit = "distance";
        } else if (id == R.id.nav_mass) {
            addSpinnerItems("mass");
            currentUnit = "mass";
        } else if (id == R.id.nav_time) {
            addSpinnerItems("time");
            currentUnit = "time";
        } else if (id == R.id.nav_cooking) {
            addSpinnerItems("speed");
        } else if (id == R.id.nav_data) {
            addSpinnerItems("data");
        } else if (id == R.id.nav_distance) {
            addSpinnerItems("distance");
        } else if (id == R.id.nav_mass) {
            addSpinnerItems("mass");
        } else if (id == R.id.nav_time) {
            addSpinnerItems("time");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addSpinnerItems(String unit) {

        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, this.getResources().getStringArray(getResources().getIdentifier(unit, "array", this.getPackageName())));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLeft.setAdapter(dataAdapter);
        spinnerRight.setAdapter(dataAdapter);
    }

    public void addSpinnerListeners() {

        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        final EditText editText15 = (EditText)findViewById(R.id.editText15);
        final EditText editText14 = (EditText)findViewById(R.id.editText14);
        spinnerLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                System.out.println("Spinner 14: " + editText14.getText().toString());
                if((!editText14.getText().toString().equals("") && !editText14.getText().toString().equals(".")) || (!editText15.getText().toString().equals("") && !editText15.getText().toString().equals("."))) {
                    System.out.println("Calculating " + currentUnit);
                    calculate(currentUnit);
                }
                else{
                    System.out.println("Not Calculating");
                }
            }
            public void onNothingSelected(AdapterView<?> parent){}
        });
        spinnerRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                if((!editText14.getText().toString().equals("") && !editText14.getText().toString().equals(".")) || (!editText15.getText().toString().equals("") && !editText15.getText().toString().equals("."))) {
                    System.out.println("Calculating " + currentUnit);
                    calculate(currentUnit);
                }
                else{
                    System.out.println("Not Calculating");
                }
            }
            public void onNothingSelected(AdapterView<?> parent){}
        });
    }

    public void calculate(String unit){
        switch(unit){
            case "time":
                calculateTime();
                break;
            case "speed":
                calculateSpeed();
                break;
            case "mass":
                calculateMass();
                break;
            case "cooking":
                calculateCooking();
                break;
            case "data":
                calculateData();
                break;
            case "distance":
                calculateDistance();
                break;
        }
    }

    public void calculateTime(){
        EditText leftText = (EditText)findViewById(R.id.editText14);
        EditText rightText = (EditText)findViewById(R.id.editText15);

        double leftNumber = Integer.parseInt(leftText.getText().toString());
        double rightNumber = 0.0;

        double seconds = 0;

        switch(spinnerLeft.getSelectedItem().toString()){
            case "Seconds":
                seconds = leftNumber;
                break;
            case "Minutes":
                seconds = leftNumber * 60;
                break;
            case "Hours":
                seconds = leftNumber * 3600;
                break;
            case "Days":
                seconds = leftNumber * 3600 * 24;
                break;
            case "Weeks":
                seconds = leftNumber * 3600 * 24 * 7;
                break;
            case "Years":
                seconds = leftNumber * 3600 * 24 * 7 * 365.2422;
                break;
            case "Milliseconds":
                seconds = leftNumber / 1000.0;
                break;
            case "Nanoseconds":
                seconds = leftNumber / 1000000000.0;
        }

        switch(spinnerRight.getSelectedItem().toString()){
            case "Seconds":
                rightNumber = seconds;
                break;
            case "Minutes":
                rightNumber = seconds / 60.0;
                break;
            case "Hours":
                rightNumber = seconds / (3600.0);
                break;
            case "Days":
                rightNumber = seconds / (3600 * 24);
                break;
            case "Weeks":
                rightNumber = seconds / (3600 * 24 * 7);
                break;
            case "Years":
                rightNumber = leftNumber / (3600 * 24 * 7 * 365.2422);
                break;
            case "Milliseconds":
                rightNumber = leftNumber * 1000.0;
                break;
            case "Nanoseconds":
                rightNumber = leftNumber * 1000000000.0;
        }

        rightText.setText(rightNumber +"", TextView.BufferType.EDITABLE);

    }
    public void calculateSpeed(){

    }
    public void calculateMass(){

    }
    public void calculateCooking(){

    }
    public void calculateData(){

    }
    public void calculateDistance(){

    }
}
