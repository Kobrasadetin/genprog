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
public class EvaluatorCorrelation extends Evaluator {
    
    private final static double MAX_ACCEPTED_INPUT = 1E5;
    
    ArrayList<Double> output = new ArrayList();
    ArrayList<Double> expected = new ArrayList();

    @Override
    public double evaluate(Genotype genotype, Batch batch) {
        double total = 0;
        Phenotype phenotype = genotype.getPhenotype();
        output.clear();
        expected.clear();
        for (DataPoint data : batch) {
            double error;
            ProgramResult result = genotype.getPhenotype().calculate(data);
            output.add(clamp(result.getOutput().get(0)));
            expected.add(data.getOutput().get(0));
            /*if (result.isDeadlock()) {
            error = data.maxError();
            } else {
            error = 1.0 - data.correlation(output.getOutput());
            }*/
            
        }
        //return 1 - Math.abs(correlation(output, expected));
         return correlation(output, expected);
    }
    
    private double clamp(double in)
    {
        return Math.min(Math.max(in, -MAX_ACCEPTED_INPUT), MAX_ACCEPTED_INPUT);
    }
    
    protected double correlation(ArrayList<Double> in , ArrayList<Double> expected) {
        double sumxy = 0.0;
        double sumx = 0.0;
        double sumy = 0.0;
        double sumx2 = 0.0;
        double sumy2 = 0.0;
        double mse = 0.0;
        int n = 0;
        for (Double outputValue : expected) {
            double x = outputValue;
            double y = in.get(n);
            if (Double.isNaN(y) || Double.isInfinite(y)) {
                return Double.MAX_VALUE;
            }
            sumx += x;
            sumy += y;
            sumx2 += x * x;
            sumy2 += y * y;
            sumxy += x * y;
            mse += Math.pow(x-y, 2);
            n++;
        }
        double covariance = n * sumxy - sumx * sumy;
        double sdx = Math.sqrt(n * sumx2 - Math.pow(sumx, 2));
        double sdy = Math.sqrt(n * sumy2 - Math.pow(sumy, 2));
        double value =  covariance / (sdx * sdy); //TODO numerical stability
        if (Double.isNaN(value) || Double.isInfinite(value))
                {
                    value = 0;
                }
        return mse;

    }

}
