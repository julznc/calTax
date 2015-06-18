package com.ymsoftlabs.caltax;

import android.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class ReverseCalcActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    int employmentType = 0;
    int civilStatus = 0;
    double salary = 0;

    EditText targetEditTextView;

    AlertDialog.Builder dialogBuilder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_calc);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.icon_actionbar);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Employment Type
        final Spinner employment = (Spinner) findViewById(R.id.employmentType);
        ArrayAdapter<CharSequence> employmentAdapter = ArrayAdapter.createFromResource(this,
                R.array.rev_employment_array, android.R.layout.simple_spinner_item);

        employmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employment.setAdapter(employmentAdapter);
        employment.setOnItemSelectedListener(this);

        // Civil Status
        Spinner status = (Spinner) findViewById(R.id.civStatus);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.dependents_array, android.R.layout.simple_spinner_item);

        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);
        status.setOnItemSelectedListener(this);

        targetEditTextView = (EditText) findViewById(R.id.targetSalary);

        dialogBuilder = new AlertDialog.Builder(ReverseCalcActivity.this);
        dialogBuilder.setTitle("KalTax");
        dialogBuilder.setPositiveButton("Ok", null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reverse_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String result = parent.getItemAtPosition(pos).toString();

        if (result.equalsIgnoreCase("Private Company")) employmentType = 0;
        if (result.equalsIgnoreCase("Government")) employmentType = 1;

        if (result.equalsIgnoreCase("Single")) civilStatus = 0;
        if (result.equalsIgnoreCase("Married")) civilStatus = 1;
        if (result.equalsIgnoreCase("Single w/1 Dependent")) civilStatus = 2;
        if (result.equalsIgnoreCase("Single w/2 Dependent")) civilStatus = 3;
        if (result.equalsIgnoreCase("Single w/3 Dependent")) civilStatus = 4;
        if (result.equalsIgnoreCase("Single w/4 Dependent")) civilStatus = 5;
        if (result.equalsIgnoreCase("Married w/1 Dependent")) civilStatus = 6;
        if (result.equalsIgnoreCase("Married w/2 Dependent")) civilStatus = 7;
        if (result.equalsIgnoreCase("Married w/3 Dependent")) civilStatus = 8;
        if (result.equalsIgnoreCase("Married w/4 Dependent")) civilStatus = 9;

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void calTaxReverse(View view) {
        double salary = 0;
        double sss = 0;
        double ph = 0;
        double pg = 0;
        double deduction = 0;
        double totalTaxable = 0;
        double tax = 0;
        double step = 1;
        double diff = 1;

        String targetString = targetEditTextView.getText().toString();
        if (!targetString.isEmpty()) salary = Double.parseDouble(targetString);
        if (salary == 0) return;

        double takehome = salary;
        taxBracketing calTax = new taxBracketing();
        ContributionsManager contributionsManager = new ContributionsManager();

        int i = 0;
        while (diff > 0.0001) {

            sss = contributionsManager.sssContribution(takehome, employmentType, 0);
            ph = contributionsManager.philhealthContribution(takehome, 0);
            pg = contributionsManager.pagIbigContribution(takehome);
            deduction = sss + ph + pg;

            totalTaxable = takehome - deduction;
            tax = calTax.calTaxMonthly(totalTaxable, civilStatus);

            diff = salary - (takehome - deduction - tax);
            step = diff / 2;
            takehome += step;
        }

        dialogBuilder.setMessage("Para makapag-uwi ka ng Php " + salary + " kada buwan, kailangan" +
                " mong sumahod ng: \n\nPhp " + takehome + "\n\nGood luck sa salary negotiation.");

        alert = dialogBuilder.create();
        alert.show();
    }
}