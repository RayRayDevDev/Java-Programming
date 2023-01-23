package com.raydev.sandwichshop;

import java.util.ArrayList;
import java.lang.Double;
 public class Prices {

    public static Double prices(int userSelection) {
        ArrayList<Double> prices = new ArrayList<>();
        prices.add(2.00); //Rye; 0
        prices.add(3.00); //Sourdough; 1
        prices.add(4.00); //Ciabatta; 2
        prices.add(1.00); //Wheat; 3
        prices.add(1.00); //White; 4
        prices.add(2.50); //Salami; 5
        prices.add(1.50); //Chicken; 6
        prices.add(2.00); //Turkey; 7
        prices.add(3.00); //Roast Beef; 8
        prices.add(2.75); //Egg Salad; 9
        prices.add(0.25); //Lettuce, Tomato, Onion, Pickle; 10
        prices.add(0.50); //Cheese; 11
        return (prices.get(userSelection));  //Should return the price of the index requested.
    }
}
