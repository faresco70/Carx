package com.vatsal.android.carx;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OBD2Activity extends Activity {

    String[] mobileArray = {"OBD2 Fault Codes","Veicle Speed","Engine RPM","Engine Load","Fuel Consumption","Fuel Economy","Engine Runtime","Coolant Temp","Mass Air Flow",
    "Throttle Position", "Fuel Pressure","Fuel Level","Air Intake Temp","Timing Advance", "Highest Acceleration"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obd2);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.content_obd2, mobileArray);

        ListView listView = (ListView) findViewById(R.id.Obd2_List);
        listView.setAdapter(adapter);
    }

}