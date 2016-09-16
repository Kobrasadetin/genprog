/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.OpNode;
import java.util.ArrayList;
import java.util.Random;

/**
 * Basic Linear Genetic Algorithm Genotype
 *
 * @author mkarjanm
 */
public class BasicLGA implements Genotype {

    private ArrayList<OpNode> genome;
    private int size;
    private double feebleness;
    private Random rng;

    BasicLGA(ArrayList<OpNode> operations, int size, Random rng) {
        this.size = size;
        this.rng = rng;
        genome = new ArrayList();
        int operationsSize = operations.size();
        for (int i = 0; i < size; i++) {
            genome.add(operations.get(rng.nextInt(operationsSize)));
        }
    }

    @Override
    public void combine(Genotype a, Genotype b) {
        if (a.getClass().equals(BasicLGA.class) && b.getClass().equals(BasicLGA.class)) {
            BasicLGA mom1, mom2;
            if ((rng.nextInt() & 1) == 1) {
                mom1 = (BasicLGA) a;
                mom2 = (BasicLGA) b;
            } else {
                mom1 = (BasicLGA) b;
                mom2 = (BasicLGA) a;
            }
            int newSize = mom1.size;
            int sizeEnd = mom1.size;
            if (sizeEnd>=newSize) sizeEnd = 0;
            for (int i = 0; i < sizeEnd; i++) {
                genome.add(operations.get(rng.nextInt(operationsSize)));
            }
            for (int i = 0; i < sizeEnd; i++) {
                genome.add(operations.get(rng.nextInt(operationsSize)));
            }
        } else // not compatible genotypes
        {
            throw new IllegalArgumentException("BasicLGA combine operation: incompatible Genotype");
        }
    }

    @Override
    public Phenotype getPhenotype() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Genotype clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Genotype cloneRandomized() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getFeebleness() {
        return feebleness;
    }

    @Override
    public void setFeebleness(Double value) {
        this.feebleness = value;
    }

}
