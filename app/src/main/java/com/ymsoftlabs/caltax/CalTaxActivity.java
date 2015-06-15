package com.ymsoftlabs.caltax;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class CalTaxActivity extends ActionBarActivity implements  OnItemSelectedListener {

    int paymentPeriod = 0;
    int employment = 0;
    int civilStatus = 0;
    double salary = 0;

    EditText sssEditTextView;
    EditText phEditTextView;
    EditText pagibigEditTextView;

    EditText salaryEditTextView;
    EditText taxAllowanceTextView;
    EditText otPayEditTextView;
    EditText nightDiffEditTextView;
    EditText holidayEditTextView;

    EditText hmoEditTextView;
    EditText absencesTextView;
    EditText tardinessTextView;
    EditText undertimeEditTextView;
    EditText shieldedEditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_tax);

        // Pay Period
        Spinner payChoices = (Spinner) findViewById(R.id.payChoices);
        ArrayAdapter<CharSequence> payAdapter = ArrayAdapter.createFromResource(this,
                R.array.basicPay_array, android.R.layout.simple_spinner_item);

        payAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payChoices.setAdapter(payAdapter);
        payChoices.setOnItemSelectedListener(this);

        // Employment Type
        Spinner employment = (Spinner) findViewById(R.id.employment);
        ArrayAdapter<CharSequence> employmentAdapter = ArrayAdapter.createFromResource(this,
                R.array.employment_array, android.R.layout.simple_spinner_item);

        employmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employment.setAdapter(employmentAdapter);
        employment.setOnItemSelectedListener(this);

        // Civil Status
        Spinner status = (Spinner) findViewById(R.id.status);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.dependents_array, android.R.layout.simple_spinner_item);

        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);
        status.setOnItemSelectedListener(this);

        sssEditTextView = (EditText) findViewById(R.id.sssContribution);
        phEditTextView = (EditText) findViewById(R.id.philHealth);
        pagibigEditTextView = (EditText) findViewById(R.id.pagibig);

        salaryEditTextView = (EditText)findViewById(R.id.basicSalary);
        taxAllowanceTextView = (EditText)findViewById(R.id.allowance);
        otPayEditTextView = (EditText) findViewById(R.id.overtime);
        nightDiffEditTextView = (EditText) findViewById(R.id.nightDiff);
        holidayEditTextView = (EditText) findViewById(R.id.holiday);

        hmoEditTextView = (EditText)findViewById(R.id.hmo);
        absencesTextView = (EditText)findViewById(R.id.absences);
        tardinessTextView = (EditText) findViewById(R.id.tardiness);
        undertimeEditTextView = (EditText) findViewById(R.id.undertime);
        shieldedEditTextView = (EditText) findViewById(R.id.shielded);

        TextWatcher watch = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        salaryEditTextView.addTextChangedListener(watch);
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
        String result = parent.getItemAtPosition(pos).toString();

        if (result.equalsIgnoreCase("Monthly")) paymentPeriod = 0;
        if (result.equalsIgnoreCase("Semi-Monthly")) paymentPeriod = 1;

        if (result.equalsIgnoreCase("Employed")) employment = 0;
        if (result.equalsIgnoreCase("Self-Employed")) employment = 1;
        if (result.equalsIgnoreCase("Voluntary")) employment = 1;
        if (result.equalsIgnoreCase("OFW")) employment = 1;

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

        if (salary > 0) setContributions(salary, employment, paymentPeriod);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setContributions(double salary, int employmentType, int payPeriod){

        ContributionsManager contributionsManager = new ContributionsManager();
        double sssContribution = contributionsManager.sssContribution(salary, employmentType, payPeriod);
        double philhealthContribution = contributionsManager.philhealthContribution(salary, payPeriod);
        double pagibigContribution = contributionsManager.pagIbigContribution(salary);

        sssEditTextView.setText("" + sssContribution);
        phEditTextView.setText("" + philhealthContribution);
        pagibigEditTextView.setText("" + pagibigContribution);
    }

}
