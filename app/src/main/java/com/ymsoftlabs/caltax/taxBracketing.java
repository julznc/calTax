package com.ymsoftlabs.caltax;

import android.util.Log;

/**
 * Created by yus on 6/15/2015.
 */
public class taxBracketing {

    public double calTaxMonthly(double taxable, int civStatus) {
        double baseValue = 0;
        double excess = 0;
        double taxPercent = 0;

        if (civStatus == 0 || civStatus == 1) {
            if (taxable < 4167) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 4167 && taxable < 5000) {
                baseValue = 0;
                taxPercent = 5;
                excess = 4167;
            } else if (taxable >= 5000 && taxable < 6667) {
                baseValue = 41.67;
                taxPercent = 10;
                excess = 5000;
            } else if (taxable >= 6667 && taxable < 10000) {
                baseValue = 208.33;
                taxPercent = 15;
                excess = 6667;
            } else if (taxable >= 10000 && taxable < 15833) {
                baseValue = 708.33;
                taxPercent = 20;
                excess = 10000;
            } else if (taxable >= 15833 && taxable < 25000) {
                baseValue = 1875.00;
                taxPercent = 25;
                excess = 15833;
            } else if (taxable >= 25000 && taxable < 45833) {
                baseValue = 4166.67;
                taxPercent = 30;
                excess = 25000;
            } else {
                baseValue = 10416.67;
                taxPercent = 32;
                excess = 45833;
            }

        } else if (civStatus == 2 || civStatus == 6) {
            if (taxable < 6250) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 6250 && taxable < 7083) {
                baseValue = 0;
                taxPercent = 5;
                excess = 6250;
            } else if (taxable >= 7083 && taxable < 8750) {
                baseValue = 41.67;
                taxPercent = 10;
                excess = 7083;
            } else if (taxable >= 8750 && taxable < 12083) {
                baseValue = 208.33;
                taxPercent = 15;
                excess = 8750;
            } else if (taxable >= 12083 && taxable < 17917) {
                baseValue = 708.33;
                taxPercent = 20;
                excess = 12083;
            } else if (taxable >= 17917 && taxable < 27083) {
                baseValue = 1875.00;
                taxPercent = 25;
                excess = 17917;
            } else if (taxable >= 27083 && taxable < 47917) {
                baseValue = 4166.67;
                taxPercent = 30;
                excess = 27083;
            } else {
                baseValue = 10416.67;
                taxPercent = 32;
                excess = 47917;
            }

        } else if (civStatus == 3 || civStatus == 7) {
            if (taxable < 8333) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 8333 && taxable < 9167) {
                baseValue = 0;
                taxPercent = 5;
                excess = 8333;
            } else if (taxable >= 9167 && taxable < 10833) {
                baseValue = 41.67;
                taxPercent = 10;
                excess = 9167;
            } else if (taxable >= 10833 && taxable < 14167) {
                baseValue = 208.33;
                taxPercent = 15;
                excess = 10833;
            } else if (taxable >= 14167 && taxable < 20000) {
                baseValue = 708.33;
                taxPercent = 20;
                excess = 14167;
            } else if (taxable >= 20000 && taxable < 29167) {
                baseValue = 1875.00;
                taxPercent = 25;
                excess = 20000;
            } else if (taxable >= 29167 && taxable < 50000) {
                baseValue = 4166.67;
                taxPercent = 30;
                excess = 29167;
            } else {
                baseValue = 10416.67;
                taxPercent = 32;
                excess = 50000;
            }

        } else if (civStatus == 4 || civStatus == 8) {
            if (taxable < 10417) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 10417 && taxable < 11250) {
                baseValue = 0;
                taxPercent = 5;
                excess = 10417;
            } else if (taxable >= 11250 && taxable < 12917) {
                baseValue = 41.67;
                taxPercent = 10;
                excess = 11250;
            } else if (taxable >= 12917 && taxable < 16250) {
                baseValue = 208.33;
                taxPercent = 15;
                excess = 12917;
            } else if (taxable >= 16250 && taxable < 22083) {
                baseValue = 708.33;
                taxPercent = 20;
                excess = 16250;
            } else if (taxable >= 22083 && taxable < 31250) {
                baseValue = 1875.00;
                taxPercent = 25;
                excess = 22083;
            } else if (taxable >= 31250 && taxable < 52083) {
                baseValue = 4166.67;
                taxPercent = 30;
                excess = 31250;
            } else {
                baseValue = 10416.67;
                taxPercent = 32;
                excess = 52083;
            }

        } else if (civStatus == 5 || civStatus == 9) {
            if (taxable < 12500) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 12500 && taxable < 13333) {
                baseValue = 0;
                taxPercent = 5;
                excess = 12500;
            } else if (taxable >= 13333 && taxable < 15000) {
                baseValue = 41.67;
                taxPercent = 10;
                excess = 13333;
            } else if (taxable >= 15000 && taxable < 18333) {
                baseValue = 208.33;
                taxPercent = 15;
                excess = 15000;
            } else if (taxable >= 18333 && taxable < 24167) {
                baseValue = 708.33;
                taxPercent = 20;
                excess = 18333;
            } else if (taxable >= 24167 && taxable < 33333) {
                baseValue = 1875.00;
                taxPercent = 25;
                excess = 24167;
            } else if (taxable >= 33333 && taxable < 54167) {
                baseValue = 4166.67;
                taxPercent = 30;
                excess = 33333;
            } else {
                baseValue = 10416.67;
                taxPercent = 32;
                excess = 54167;
            }
        }

        double tax = baseValue + ((taxable - excess) * (taxPercent/100));
        return tax;
    }

    public double calTaxSemiMonthly(double taxable, int civStatus) {
        double baseValue = 0;
        double excess = 0;
        double taxPercent = 0;

        if (civStatus == 0 || civStatus == 1) {
            if (taxable < 2083) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 2083 && taxable < 2500) {
                baseValue = 0;
                taxPercent = 5;
                excess = 2083;
            } else if (taxable >= 2500 && taxable < 3333) {
                baseValue = 20.83;
                taxPercent = 10;
                excess = 2500;
            } else if (taxable >= 3333 && taxable < 5000) {
                baseValue = 104.17;
                taxPercent = 15;
                excess = 3333;
            } else if (taxable >= 5000 && taxable < 7917) {
                baseValue = 354.17;
                taxPercent = 20;
                excess = 5000;
            } else if (taxable >= 7917 && taxable < 12500) {
                baseValue = 937.50;
                taxPercent = 25;
                excess = 7917;
            } else if (taxable >= 12500 && taxable < 22917) {
                baseValue = 2083.33;
                taxPercent = 30;
                excess = 12500;
            } else {
                baseValue = 5208.33;
                taxPercent = 32;
                excess = 22917;
            }

        } else if (civStatus == 2 || civStatus == 6) {
            if (taxable < 3125) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 3125 && taxable < 3542) {
                baseValue = 0;
                taxPercent = 5;
                excess = 3125;
            } else if (taxable >= 3542 && taxable < 4375) {
                baseValue = 20.83;
                taxPercent = 10;
                excess = 3542;
            } else if (taxable >= 4375 && taxable < 6042) {
                baseValue = 104.17;
                taxPercent = 15;
                excess = 4375;
            } else if (taxable >= 6042 && taxable < 8958) {
                baseValue = 354.17;
                taxPercent = 20;
                excess = 6042;
            } else if (taxable >= 8958 && taxable < 13542) {
                baseValue = 937.50;
                taxPercent = 25;
                excess = 8958;
            } else if (taxable >= 13542 && taxable < 23958) {
                baseValue = 2083.33;
                taxPercent = 30;
                excess = 13542;
            } else {
                baseValue = 5208.33;
                taxPercent = 32;
                excess = 23958;
            }

        } else if (civStatus == 3 || civStatus == 7) {
            if (taxable < 4167) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 4167 && taxable < 4583) {
                baseValue = 0;
                taxPercent = 5;
                excess = 4167;
            } else if (taxable >= 4583 && taxable < 5417) {
                baseValue = 20.83;
                taxPercent = 10;
                excess = 4583;
            } else if (taxable >= 5417 && taxable < 7083) {
                baseValue = 104.17;
                taxPercent = 15;
                excess = 5417;
            } else if (taxable >= 7083 && taxable < 10000) {
                baseValue = 354.17;
                taxPercent = 20;
                excess = 7083;
            } else if (taxable >= 10000 && taxable < 14583) {
                baseValue = 937.50;
                taxPercent = 25;
                excess = 10000;
            } else if (taxable >= 14583 && taxable < 25000) {
                baseValue = 2083.33;
                taxPercent = 30;
                excess = 14583;
            } else {
                baseValue = 5208.33;
                taxPercent = 32;
                excess = 25000;
            }

        } else if (civStatus == 4 || civStatus == 8) {
            if (taxable < 5208) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 5208 && taxable < 5625) {
                baseValue = 0;
                taxPercent = 5;
                excess = 5208;
            } else if (taxable >= 5625 && taxable < 6458) {
                baseValue = 20.83;
                taxPercent = 10;
                excess = 5625;
            } else if (taxable >= 6458 && taxable < 8125) {
                baseValue = 104.17;
                taxPercent = 15;
                excess = 6458;
            } else if (taxable >= 8125 && taxable < 11042) {
                baseValue = 354.17;
                taxPercent = 20;
                excess = 8125;
            } else if (taxable >= 11042 && taxable < 15625) {
                baseValue = 937.50;
                taxPercent = 25;
                excess = 11042;
            } else if (taxable >= 15625 && taxable < 26042) {
                baseValue = 2083.33;
                taxPercent = 30;
                excess = 15625;
            } else {
                baseValue = 5208.33;
                taxPercent = 32;
                excess = 26042;
            }

        } else if (civStatus == 5 || civStatus == 9) {
            if (taxable < 6250) {
                baseValue = 0;
                taxPercent = 0;
                excess = 1;
            } else if (taxable >= 6250 && taxable < 6667) {
                baseValue = 0;
                taxPercent = 5;
                excess = 6250;
            } else if (taxable >= 6667 && taxable < 7500) {
                baseValue = 20.83;
                taxPercent = 10;
                excess = 6667;
            } else if (taxable >= 7500 && taxable < 9167) {
                baseValue = 104.17;
                taxPercent = 15;
                excess = 7500;
            } else if (taxable >= 9167 && taxable < 12083) {
                baseValue = 354.17;
                taxPercent = 20;
                excess = 9167;
            } else if (taxable >= 12083 && taxable < 16667) {
                baseValue = 937.50;
                taxPercent = 25;
                excess = 12083;
            } else if (taxable >= 16667 && taxable < 27083) {
                baseValue = 2083.33;
                taxPercent = 30;
                excess = 16667;
            } else {
                baseValue = 5208.33;
                taxPercent = 32;
                excess = 27083;
            }
        }

        double tax = baseValue + ((taxable - excess) * (taxPercent/100));
        return tax;
    }
}
