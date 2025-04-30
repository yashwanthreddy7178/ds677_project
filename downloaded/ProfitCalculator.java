package com.brekol.srm154.div2;

/**
 * Created by brekol on 11.12.16.
 */
public class ProfitCalculator {

    public int percent(String[] items) {

        Double itemCosts = 0.0d;
        Double itemPrices = 0.0d;
        for (String item : items) {
            itemPrices += new Double(item.split(" ")[0]);
            itemCosts += new Double(item.split(" ")[1]);
        }
        final double profit = itemPrices - itemCosts;
        return (int) (profit * 100 / itemPrices);

    }
}
