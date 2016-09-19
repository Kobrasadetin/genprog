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

    public DataPoint(ArrayList<Double> input, ArrayList<Double> output) {
        this.input = input;
        this.output = output;
    }

    //returns sum of squred errors
    double getError(ArrayList<Double> compare) {       
        double sum = 0.0;
        int i = 0;
        for (Double outputValue : output)
        {
            double error = outputValue-compare.get(i);
            error = Math.min(Math.pow(error, 2), maxError());
            sum += error;
            i++;
        }
        if (Double.isNaN(sum)) sum = maxError();
        return sum;
        
    }
    
    //returned when program doesn't halt
    double maxError()
    {
        return 100000.0;
    }

    public ArrayList<Double> getInput() {
        return input;
    }

    public ArrayList<Double> getOutput() {
        return output;
    }
    
    
}
