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
    public void evaluate(Genotype genotype, Batch batch) {
        for (DataPoint data : batch) {
            double error;
            ProgramResult output = genotype.getPhenotype().calculate(data);
            if (output.isDeadlock()) {
                error = data.maxError();
            } else {
                error = data.getError(output.getOutput());
            }
            genotype.setFeebleness(genotype.getFeebleness() + error);
            System.out.println("evaluation result: "+output.toString());
        }
    }
}
