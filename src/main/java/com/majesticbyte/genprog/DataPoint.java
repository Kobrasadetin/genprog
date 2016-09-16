/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import java.util.ArrayList;

/**
 *
 * @author mkarjanm
 */
public class DataPoint{
    private ArrayList<Double> input;
    private ArrayList<Double> output;

    //returns sum of squred errors
    double getError(DataPoint calculate) {
        double sum = 0.0;
        int i = 0;
        for (Double inputValue : input)
        {
            double error = inputValue-output.get(i);
            error = Math.pow(error, 2);
            sum += error;
            i++;
        }
        return sum;
        
    }
    
}
