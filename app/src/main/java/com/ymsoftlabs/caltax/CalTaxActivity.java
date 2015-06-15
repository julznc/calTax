package com.ymsoftlabs.caltax;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


public class CalTaxActivity extends ActionBarActivity implements  OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_tax);

        Spinner payChoices = (Spinner) findViewById(R.id.payChoices);
        ArrayAdapter<CharSequence> payAdapter = ArrayAdapter.createFromResource(this,
                R.array.basicPay_array, android.R.layout.simple_spinner_item);

        payAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payChoices.setAdapter(payAdapter);
        payChoices.setOnItemSelectedListener(this);

        Spinner status = (Spinner) findViewById(R.id.status);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.dependents_array, android.R.layout.simple_spinner_item);

        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);
        status.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cal_tax, menu);
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Object result = parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}
