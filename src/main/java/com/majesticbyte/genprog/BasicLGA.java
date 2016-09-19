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

    private ArrayList<BasicLGAGene> genome;
    private ArrayList<? extends OpNode> possibleOperations;
    private int size;
    private double feebleness;
    private Random rng;
    private BasicLGAPhenotype myPhenotype;

    BasicLGA(Random rng) {
        this.possibleOperations = null;
        this.size = 0;
        this.rng = rng;
        genome = new ArrayList();
    }

    BasicLGA(ArrayList<? extends OpNode> operations, int size, Random rng) {
        this.possibleOperations = operations;
        this.size = size;
        this.rng = rng;
        genome = new ArrayList();
        int operationsSize = operations.size();
        for (int i = 0; i < size; i++) {
            genome.add(new BasicLGAGene(operations.get(rng.nextInt(operationsSize)), rng.nextInt(size),  rng.nextInt(256)));
        }
    }

    /**
     * Combines two genotypes. Allows for either parameter to be a reference to
     * self.
     *
     * @param a first genotype (order does not matter)
     * @param b second genotype (order does not matter)
     */
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
            int maxSplize = Math.min(newSize, mom2.size);
            int spliceSize = rng.nextInt(maxSplize);
            int spliceStart = rng.nextInt(maxSplize - spliceSize);
            int spliceEnd = spliceSize + spliceStart;
            ArrayList<BasicLGAGene> newGenome = new ArrayList<>(newSize);
            for (int i = 0; i < spliceStart; i++) {
                newGenome.add(mom1.genome.get(i));
            }
            for (int i = spliceStart; i < spliceEnd; i++) {
                newGenome.add(mom2.genome.get(i));
            }
            for (int i = spliceEnd; i < newSize; i++) {
                newGenome.add(mom1.genome.get(i));
            }
            this.genome = newGenome;
            this.myPhenotype = null;

        } else // not compatible genotypes
        {
            throw new IllegalArgumentException("BasicLGA combine operation: incompatible Genotype");
        }
    }

    /**
     * Returns a BasicLGAPhenotype for running. Should avoid rebuilding an
     * already built phenotype.
     *
     * @return a reference to BasicLGAPhenotype
     */
    @Override
    public Phenotype getPhenotype() {
        if (this.myPhenotype == null) {
            myPhenotype = new BasicLGAPhenotype(genome, size);
        }
        return myPhenotype;
    }

    /**
     * Creates a new genotype with the same input-output scheme.
     *
     * @return a new genotype
     */
    @Override
    public Genotype cloneRandomized() {
        BasicLGA newGenotype = new BasicLGA(possibleOperations, this.size, rng);
        return newGenotype;
    }

    /**
     * TODO implementation might change Returns the cumulative error of
     * genotype's phenotype evaluations (opposite of 'fitness')
     *
     * @return cumulative error, larger numbers for poorer performance
     */
    @Override
    public Double getFeebleness() {
        return feebleness;
    }

    /**
     * TODO implementation might change Sets the cumulative error of genotype's
     * phenotype evaluations (opposite of 'fitness')
     *
     * @param value error
     */
    @Override
    public void setFeebleness(Double value) {
        this.feebleness = value;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        String nl = System.getProperty("line.separator");
        s.append(nl).append("BasicLGA:").append(nl)
                    .append("genotype:  ").append(genome.toString()).append(nl)
                    .append("phenotype: ").append(this.getPhenotype().toString());
        return s.toString();
    }

}
