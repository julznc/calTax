package com.ymsoftlabs.caltax;

import android.util.Log;

import java.lang.reflect.Array;

/**
 * Created by yus on 6/15/2015.
 */
public class ContributionsManager {

    private double sssTable[][] = {
            {36.30, 54.50, 72.70, 90.80, 109.00, 127.20, 145.30, 163.50, 181.70, 199.80, 218.00, 236.20, 254.30, 272.50, 290.70, 308.80, 327.00, 345.20, 363.30, 381.50, 399.70, 417.80, 436.00, 454.20, 472.30, 490.50, 508.70, 526.80, 545.00, 563.20, 581.30},
            {110.00, 165.00, 220.00, 275.00, 330.00, 385.00, 440.00, 495.00, 550.00, 605.00, 660.00, 715.00, 770.00, 825.00, 880.00, 935.00, 990.00, 1045.00, 1100.00, 1155.00, 1210.00, 1265.00, 1320.00, 1375.00, 1430.00, 1485.00, 1540.00, 1595.00, 1650.00, 1705.00, 1760.00}
    };

    public double sssContribution(double salary, int employment, int payPeriod){
        double contribution = 0;
        int index = 0;

        if (employment == 2) return 0;

        if (salary < 1000) return 0;
        if (salary >= 15750) index = 30;
        else index = (int)(((salary - 1000)/500) + 0.5);

        int type = 0;
        if (employment > 0) type = 1;

        contribution = sssTable[type][index];

        if (payPeriod == 0) return contribution;
        else return (contribution/2);
    }

    public double philhealthContribution(double salary, int payPeriod){
        double phcontribution = 0;
        int index = 0;

        if (salary < 100) return 0;

        if (salary < 9000) index = 0;
        if (salary >= 35000 ) index = 27;
        else index = (int)(((salary - 9000)/1000) + 1.0);

        phcontribution = 100 + (12.5 * index);

        if (payPeriod == 0) return phcontribution;
        else return  (phcontribution / 2);
    }

    public double pagIbigContribution(double salary, int payPeriod){
        double pg = 0;

        if (salary > 5000) pg = 100;
        else if (salary < 1500) pg = salary * 0.01;
        else pg = salary * 0.02;

        if (payPeriod == 0) return pg;
        else return (pg/2);
    }

    public double gsisContribution(double salary){
        return salary * 0.09;
    }
}
