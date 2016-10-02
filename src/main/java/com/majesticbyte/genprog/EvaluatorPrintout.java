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
        if (knownFeebleness.containsKey(phenotype)) {
            return knownFeebleness.get(phenotype);
        }
        for (DataPoint data : batch) {
            double error;
            ProgramResult output = genotype.getPhenotype().calculate(data);
            if (output.isDeadlock()) {
                error = data.maxError();
            } else {
                error = data.getError(output.getOutput());
            }
            total += error;
        }
        knownFeebleness.put(phenotype, total);
        System.out.println(this.evaluationCount);
        return total;
    }

}
