/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import java.util.HashMap;

/**
 *
 * @author mkarjanm
 */
class Evaluator {
    private Batch currentBatch;
    protected int evaluationCount = 0;
    public Evaluator()
    {
    }
    public double evaluate(Genotype genotype, Batch batch) {
        double total = 0;
        evaluationCount++;
        for (DataPoint data : batch) {
            double error;
            ProgramResult output = genotype.getPhenotype().calculate(data);
            if (output.isDeadlock()) {
                error = data.getError(output.getOutput());
            } else {
                error = data.getError(output.getOutput());
            }
            total += error;
        }
                
        return total;
    }
    public double evaluate(Genotype genotype) {
        if (currentBatch == null)
        {
            throw new IllegalArgumentException("no batch");
        }
        return evaluate(genotype, currentBatch);
    }

    public void setBatch(Batch currentBatch) {
        this.currentBatch = currentBatch;
    }
    
        public int evcount() {
       return  this.evaluationCount;
    }
    
}
