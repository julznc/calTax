package com.ymsoftlabs.caltax;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class CalTaxActivity extends ActionBarActivity implements  OnItemSelectedListener {

    int paymentPeriod = 0;
    int employmentType = 0;
    int civilStatus = 0;
    double salary = 0;
    double taxableAllowance = 0;
    double totalIncome = 0;

    double sssCont = 0;
    double phCont = 0;
    double pgCont = 0;
    double gsisCont = 0;

    EditText sssEditTextView;
    EditText gsisEditTextView;
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

    AlertDialog.Builder dialogBuilder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_tax);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.icon_actionbar);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Pay Period
        Spinner payChoices = (Spinner) findViewById(R.id.payChoices);
        ArrayAdapter<CharSequence> payAdapter = ArrayAdapter.createFromResource(this,
                R.array.basicPay_array, android.R.layout.simple_spinner_item);

        payAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payChoices.setAdapter(payAdapter);
        payChoices.setOnItemSelectedListener(this);

        // Employment Type
        final Spinner employment = (Spinner) findViewById(R.id.employment);
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
        gsisEditTextView = (EditText) findViewById(R.id.gsisContribution);
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
                String input = salaryEditTextView.getText().toString();
                if (input != null && !input.isEmpty()){
                    salary = Double.parseDouble(input);
                } else {
                    salary = 0;
                }

                String allowance = taxAllowanceTextView.getText().toString();
                if (allowance != null && !allowance.isEmpty()){
                    taxableAllowance = Double.parseDouble(allowance);
                } else {
                    taxableAllowance = 0;
                }

                totalIncome = salary + taxableAllowance;
                setContributions(totalIncome, employmentType, paymentPeriod);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        salaryEditTextView.addTextChangedListener(watch);
        taxAllowanceTextView.addTextChangedListener(watch);
        shieldedEditTextView.addTextChangedListener(watch);

        dialogBuilder = new AlertDialog.Builder(CalTaxActivity.this);
        dialogBuilder.setTitle("KalTax");
        dialogBuilder.setPositiveButton("Ok", null);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
        if (id == R.id.action_about) {
            dialogBuilder.setMessage("Ang kalTax ay isang simpleng application na maaari " +
                            "mong gamitin upang malaman kung magkano ang halagang maaaring nababawas sa inyong sweldo. " +
                            "Ito ay maaari mong gamitin kahit walang free public wifi dahil hindi nito kailangan ng internet connection. " +
                            "\n\nPaunawa: Ang mga numerong nakasaad sa kalTax ay base sa tables na inilabas ng mga government agencies at ng BIR. " +
                            "Maaaring ang mga numerong nakasaad ay hindi ang eksaktong halaga ng iyong buwis. Gayunman, sapat na itong pagtataya ng " +
                            "kung magkano ang iyong naiaambag sa kaban ng bayan at sa allowance ng mga congressman.");

            alert = dialogBuilder.create();
            alert.show();
            return true;
        }

        if (id == R.id.action_mode){
            Intent intent = new Intent(this, ReverseCalcActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String result = parent.getItemAtPosition(pos).toString();

        if (result.equalsIgnoreCase("Monthly")) paymentPeriod = 0;
        if (result.equalsIgnoreCase("Semi-Monthly")) paymentPeriod = 1;

        if (result.equalsIgnoreCase("Private Company")) employmentType = 0;
        if (result.equalsIgnoreCase("Government")) employmentType = 2;
        if (result.equalsIgnoreCase("Self-Employed")) employmentType = 1;
        if (result.equalsIgnoreCase("Voluntary")) employmentType = 1;
        if (result.equalsIgnoreCase("OFW")) employmentType = 1;

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

        if (totalIncome > 0) setContributions(totalIncome, employmentType, paymentPeriod);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setContributions(double salary, int employment, int payPeriod){

        ContributionsManager contributionsManager = new ContributionsManager();
        sssCont = contributionsManager.sssContribution(salary, employment, payPeriod);
        phCont = contributionsManager.philhealthContribution(salary, payPeriod);
        pgCont = contributionsManager.pagIbigContribution(salary, payPeriod);
        gsisCont = contributionsManager.gsisContribution(salary);

        if (employment == 2) {
            gsisEditTextView.setText(String.format("%.02f", gsisCont));
            sssEditTextView.setText("");
        } else {
            sssEditTextView.setText(String.format("%.02f", sssCont));
            gsisEditTextView.setText("");
        }
        phEditTextView.setText(String.format("%.02f", phCont));
        pagibigEditTextView.setText(String.format("%.02f", pgCont));
    }

    public void calTax(View view){
        double totalIncome;
        double deductions;

        if (salary == 0) return;

        double allowance = 0;
        String taxableAllowance = taxAllowanceTextView.getText().toString();
        if (!taxableAllowance.isEmpty()) allowance = Double.parseDouble(taxableAllowance);

        double otpay = 0;
        String ot = otPayEditTextView.getText().toString();
        if (!ot.isEmpty()) otpay = Double.parseDouble(ot);

        double nd = 0;
        String ndiff = nightDiffEditTextView.getText().toString();
        if (!ndiff.isEmpty()) nd = Double.parseDouble(ndiff);

        double hd = 0;
        String hdpay = holidayEditTextView.getText().toString();
        if (!hdpay.isEmpty()) hd = Double.parseDouble(hdpay);

        totalIncome = salary + allowance + otpay + nd + hd;

        double ss = 0;
        String sss = sssEditTextView.getText().toString();
        if (!sss.isEmpty()) ss = Double.parseDouble(sss);

        double ph = 0;
        String phl = phEditTextView.getText().toString();
        if (!phl.isEmpty()) ph = Double.parseDouble(phl);

        double pg = 0;
        String pagibig = pagibigEditTextView.getText().toString();
        if (!pagibig.isEmpty()) pg = Double.parseDouble(pagibig);

        double gsis = 0;
        String gsisString = gsisEditTextView.getText().toString();
        if (!gsisString.isEmpty()) gsis = Double.parseDouble(gsisString);

        double hmo = 0;
        String hmoPay = hmoEditTextView.getText().toString();
        if (!hmoPay.isEmpty()) hmo = Double.parseDouble(hmoPay);

        double ab = 0;
        String absences = absencesTextView.getText().toString();
        if (!absences.isEmpty()) ab = Double.parseDouble(absences);

        double tad = 0;
        String tardy = tardinessTextView.getText().toString();
        if (!tardy.isEmpty()) tad = Double.parseDouble(tardy);

        double ut = 0;
        String undT = undertimeEditTextView.getText().toString();
        if(!undT.isEmpty()) ut = Double.parseDouble(undT);

        double sh = 0;
        String shielded = shieldedEditTextView.getText().toString();
        if (!shielded.isEmpty()) sh = Double.parseDouble(shielded);

        deductions = ss + gsis + ph + pg + hmo + ab + tad + ut;

        double totalTaxable = totalIncome - deductions;
        taxBracketing calTax = new taxBracketing();

        double tax = 0;
        if (paymentPeriod == 0)
            tax = calTax.calTaxMonthly(totalTaxable, civilStatus);
        else
            tax = calTax.calTaxSemiMonthly(totalTaxable, civilStatus);

        double takehome = totalTaxable + sh - tax;
        String strTax = "Ang naKalTax sa sweldo mo na sana ay di sa corrupt na politiko mapunta ay:<br/><br/>"
                + "<b>Php " + String.format("%.02f", tax) + "</b><br/>";
        String strTakehome = "<br/>Kaya ang matitira na lang sa sahod mo ay:<br/><br/>"
        String strTakehome = "<br/>Kaya ang matitira na lang sa sahod mo ay:<br/><br/>"
                + "<b>Php " + String.format("%.02f", takehome) + "</b><br/>";

        Spanned strResult = Html.fromHtml(strTax + strTakehome);

        dialogBuilder.setMessage(strResult);

        alert = dialogBuilder.create();
        alert.show();
    }
}
