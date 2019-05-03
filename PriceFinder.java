package pricewatcher.base;

import java.text.DecimalFormat;

/*
 * Name:Javier Soon
 * ID: 80436654
 * CS 3331
 * 
 */
public class PriceFinder {

    public double priceFinder1() {
        double price = 0.00;
        double min = 19.00;
        double max = 35.00;

        price = (double) min + (Math.random() * (max - min));

        DecimalFormat df = new DecimalFormat("0.00");

        return Double.parseDouble(df.format(price));
    }

    public double priceFinder2() {
        double price = 0.00;
        double min = 15.00;
        double max = 50.00;

        price = (double) min + (Math.random() * (max - min));

        DecimalFormat df = new DecimalFormat("0.00");

        return Double.parseDouble(df.format(price));
    }

    public double priceFinder3() {
        double price = 0.00;
        double min = 40.00;
        double max = 65.00;

        price = (double) min + (Math.random() * (max - min));

        DecimalFormat df = new DecimalFormat("0.00");

        return Double.parseDouble(df.format(price));
    }
}
