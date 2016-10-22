/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

/**
 *
 * @author mkarjanm
 */
public class EvaluatorPrintout extends Evaluator {

    @Override
    public double evaluate(Genotype genotype, Batch batch) {
        double total = 0;
        Phenotype phenotype = genotype.getPhenotype();
        for (DataPoint data : batch) {
            double error;
            System.out.println("input:"+ data.getInput() + ", expected :"+data.getOutput());
            ProgramResult output = genotype.getPhenotype().calculate(data);
            System.out.println("output:"+ output);
            if (output.isDeadlock()) {
                error = data.maxError();
            } else {
                error = data.getError(output.getOutput());
            }
            total += error;
        }
        return total;
    }

}
