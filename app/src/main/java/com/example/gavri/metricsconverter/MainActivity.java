package com.example.gavri.metricsconverter;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner spinnerLeft, spinnerRight;
    String currentUnit = "";
    Drawable d;
    AnimatedVectorDrawable a;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.animationImage);

        addSpinnerListeners();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

        EditText number = (EditText)findViewById(R.id.editText14);
        number.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                final EditText editText14 = (EditText)findViewById(R.id.editText14);
                if (actionId == EditorInfo.IME_ACTION_DONE && (!editText14.getText().toString().equals("") && !editText14.getText().toString().equals("."))) {
                    calculate(currentUnit);
                    return true;
                }
                return false;
            }
        });



        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        final EditText editText14 = (EditText)findViewById(R.id.editText14);
        spinnerLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                System.out.println("Spinner 14: " + editText14.getText().toString());
                if((!editText14.getText().toString().equals("") && !editText14.getText().toString().equals("."))) {
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

                if((!editText14.getText().toString().equals("") && !editText14.getText().toString().equals("."))) {
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
        setUpAnimation();
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
        TextView answer = (TextView)findViewById(R.id.answer);
        double leftNumber = Double.parseDouble(leftText.getText().toString());
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
                rightNumber = seconds / (3600 * 24 * 7 * 365.2422);
                break;
            case "Milliseconds":
                rightNumber = seconds * 1000.0;
                break;
            case "Nanoseconds":
                rightNumber = seconds * 1000000000.0;
        }

        answer.setText(rightNumber + "", TextView.BufferType.EDITABLE);

    }
    public void calculateSpeed(){
        EditText leftText = (EditText)findViewById(R.id.editText14);
        TextView answer = (TextView)findViewById(R.id.answer);
        double leftNumber = Double.parseDouble(leftText.getText().toString());
        double rightNumber = 0.0;

        double mph = 0;

        switch(spinnerLeft.getSelectedItem().toString()){
            case "Miles per Hour":
                mph = leftNumber;
                break;
            case "Kilometers per Hour":
                mph = leftNumber * 0.62137119223733;
                break;
            case "Feet per Second":
                mph = leftNumber * 0.68181818181818;
                break;
            case "Meters per Second":
                mph = leftNumber * 2.23694;
                break;
            case "Knots":
                mph = leftNumber * 1.15077945;
                break;
            case "Mach":
                mph = leftNumber * 761.20705;
                break;
            case "Speed of Light":
                mph = leftNumber * 670616629.3843951;
                break;
        }

        switch(spinnerRight.getSelectedItem().toString()){
            case "Miles per Hour":
                rightNumber = mph;
                break;
            case "Kilometers per Hour":
                rightNumber = mph / 0.62137119223733;
                break;
            case "Feet per Second":
                rightNumber = mph / 0.68181818181818;
                break;
            case "Meters per Second":
                rightNumber = mph / 2.23694;
                break;
            case "Knots":
                rightNumber = mph / 1.15077945;
                break;
            case "Mach":
                rightNumber = mph / 761.20705;
                break;
            case "Speed of Light":
                rightNumber = mph / 670616629.3843951;
                break;
        }

        answer.setText(rightNumber + "", TextView.BufferType.EDITABLE);
    }
    public void calculateMass(){
        EditText leftText = (EditText)findViewById(R.id.editText14);
        TextView answer = (TextView)findViewById(R.id.answer);
        double leftNumber = Double.parseDouble(leftText.getText().toString());
        double rightNumber = 0.0;

        double grams = 0;

        switch(spinnerLeft.getSelectedItem().toString()){
            case "Grams":
                grams = leftNumber;
                break;
            case "Kilograms":
                grams = leftNumber * 1000.0;
                break;
            case "Ounces":
                grams = leftNumber * 28.349523;
                break;
            case "Pounds":
                grams = leftNumber * 453.59233;
                break;
            case "Tons":
                grams = leftNumber * 907184.66;
                break;
            case "Earth Mass":
                grams = leftNumber * 5.9722 * Math.pow(10.0, 27.0);
                break;
            case "Atomic Mass":
                grams = leftNumber * 1.66054 * Math.pow(10.0, -24.0);
                break;
            case "Planck Mass":
                grams = leftNumber * 2.17645 * Math.pow(10.0, -5.0);
                break;
            case "Solar Mass":
                grams = leftNumber * 1.9855 * Math.pow(10.0, 33.0);
                break;
        }

        switch(spinnerRight.getSelectedItem().toString()){
            case "Grams":
                rightNumber = grams;
                break;
            case "Kilograms":
                rightNumber = grams / 1000.0;
                break;
            case "Ounces":
                rightNumber = grams / 28.349523;
                break;
            case "Pounds":
                rightNumber = grams / 453.59233;
                break;
            case "Tons":
                rightNumber = grams / 907184.66;
                break;
            case "Earth Mass":
                rightNumber = grams / 5.9722 / Math.pow(10.0, 27.0);
                break;
            case "Atomic Mass":
                rightNumber = grams / 1.66054 / Math.pow(10.0, -24.0);
                break;
            case "Planck Mass":
                rightNumber = grams / 2.17645 / Math.pow(10.0, -5.0);
                break;
            case "Solar Mass":
                rightNumber = grams / 1.9855 / Math.pow(10.0, 33.0);
                break;
        }
        answer.setText(rightNumber + "", TextView.BufferType.EDITABLE);
    }
    public void calculateCooking(){
        EditText leftText = (EditText)findViewById(R.id.editText14);
        TextView answer = (TextView)findViewById(R.id.answer);
        double leftNumber = Double.parseDouble(leftText.getText().toString());
        double rightNumber = 0.0;

        double teaspoons = 0;

        switch(spinnerLeft.getSelectedItem().toString()){
            case "Teaspoons":
                teaspoons = leftNumber;
                break;
            case "Tablespoons":
                teaspoons = leftNumber * 3.0;
                break;
            case "Cups":
                teaspoons = leftNumber * 48.0;
                break;
            case "Pints":
                teaspoons = leftNumber * 96.0;
                break;
            case "Quarts":
                teaspoons = leftNumber * 192.0;
                break;
            case "Gallons":
                teaspoons = leftNumber * 768.0;
                break;
        }

        switch(spinnerRight.getSelectedItem().toString()){
            case "Teaspoons":
                rightNumber = teaspoons;
                break;
            case "Tablespoons":
                rightNumber = teaspoons * 3.0;
                break;
            case "Cups":
                rightNumber = teaspoons * 48.0;
                break;
            case "Pints":
                rightNumber = teaspoons * 96.0;
                break;
            case "Quarts":
                rightNumber = teaspoons * 192.0;
                break;
            case "Gallons":
                rightNumber = teaspoons * 768.0;
                break;
        }

        answer.setText(rightNumber + "", TextView.BufferType.EDITABLE);
    }
    public void calculateData(){
        EditText leftText = (EditText)findViewById(R.id.editText14);
        TextView answer = (TextView)findViewById(R.id.answer);
        double leftNumber = Double.parseDouble(leftText.getText().toString());
        double rightNumber = 0.0;

        double bits = 0;

        switch(spinnerLeft.getSelectedItem().toString()){
            case "Bits":
                bits = leftNumber;
                break;
            case "Kilobits":
                bits = leftNumber * 1000.0;
                break;
            case "Megabits":
                bits = leftNumber * 1000000.0;
                break;
            case "Gigabits":
                bits = leftNumber * 1000000000.0;
                break;
            case "Bytes":
                bits = leftNumber * 8.0;
                break;
            case "Megabytes":
                bits = leftNumber * 8000000.0;
                break;
            case "Kilobytes":
                bits = leftNumber * 8000.0;
                break;
            case "Gigabytes":
                bits = leftNumber *  8000000000.0;
                break;
            case "Terabytes":
                bits = leftNumber * 8000000000000.0;
                break;
        }

        switch(spinnerRight.getSelectedItem().toString()){
            case "Bits":
                rightNumber = bits;
                break;
            case "Kilobits":
                rightNumber = bits / 1000.0;
                break;
            case "Megabits":
                rightNumber = bits / 1000000.0;
                break;
            case "Gigabits":
                rightNumber = bits / 1000000000.0;
                break;
            case "Bytes":
                rightNumber = bits / 8.0;
                break;
            case "Megabytes":
                rightNumber = bits / 8000000.0;
                break;
            case "Kilobytes":
                rightNumber = bits / 8000.0;
                break;
            case "Gigabytes":
                rightNumber = bits /  8000000000.0;
                break;
            case "Terabytes":
                rightNumber = bits / 8000000000000.0;
                break;
        }

        answer.setText(rightNumber + "", TextView.BufferType.EDITABLE);
    }
    public void calculateDistance(){
        EditText leftText = (EditText)findViewById(R.id.editText14);
        TextView answer = (TextView)findViewById(R.id.answer);
        double leftNumber = Double.parseDouble(leftText.getText().toString());
        double rightNumber = 0.0;

        double inches = 0;

        switch(spinnerLeft.getSelectedItem().toString()){
            case "Inches":
                inches = leftNumber;
                break;
            case "Feet":
                inches = leftNumber * 12.0;
                break;
            case "Yards":
                inches = leftNumber * 36.0;
                break;
            case "Miles":
                inches = leftNumber * 63360.0;
                break;
            case "Nanometers":
                inches = leftNumber * 9370079 * Math.pow(10.0, -8.0);
                break;
            case "Millimeters":
                inches = leftNumber * 0.039370078740157;
                break;
            case "Centimeters":
                inches = leftNumber * 0.39370078740157;
                break;
            case "Meters":
                inches = leftNumber *  39.370078740157 ;
                break;
            case "Kilometers":
                inches = leftNumber * 39379.96;
                break;
        }

        switch(spinnerRight.getSelectedItem().toString()){
            case "Inches":
                rightNumber = inches;
                break;
            case "Feet":
                rightNumber = inches / 12.0;
                break;
            case "Yards":
                rightNumber = inches / 36.0;
                break;
            case "Miles":
                rightNumber = inches / 63360.0;
                break;
            case "Nanometers":
                rightNumber = inches / (9370079 * Math.pow(10.0, -8.0));
                break;
            case "Millimeters":
                rightNumber = inches / 0.039370078740157;
                break;
            case "Centimeters":
                rightNumber = inches / 0.39370078740157;
                break;
            case "Meters":
                rightNumber = inches /  39.370078740157 ;
                break;
            case "Kilometers":
                rightNumber = inches / 39379.96;
                break;
        }

        answer.setText(rightNumber + "", TextView.BufferType.EDITABLE);
    }

    public void setUpAnimation(){
        System.out.println("Setting Up animation");
        d = imageView.getDrawable();
        if (d instanceof AnimatedVectorDrawable) {
            a = (AnimatedVectorDrawable) d;
            EditText editText14 = (EditText)findViewById(R.id.editText14);
            TextView text = (TextView)findViewById(R.id.answer);
            startAnimation(a, currentUnit, editText14.getText().toString(), text.getText().toString());
        }
        else{
            System.out.println("Not Drawing");
        }

    }

    public void startAnimation(AnimatedVectorDrawable animation, String unit, String editText, String answer){
        switch(unit){
            case "time":
                a.start();
                break;
            case "speed":
                break;
            case "mass":
                break;
            case "cooking":
                break;
            case "data":
                break;
            case "distance":
                break;
            default:
                break;
        }
    }

}
