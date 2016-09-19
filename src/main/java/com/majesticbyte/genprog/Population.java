/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import java.util.ArrayList;

/**
 * Population is a collection of genotypes,
 * held in it's own class for convenience.
 * @author mkarjanm
 */
public class Population {
    private ArrayList<Genotype >genotypes;

    /**
     * The constructor uses the cloneRandomized method of the given Genotype prototype to create a population of genotypes.
     * 
     * @param populationSize    genotype count
     * @param prototype         The constructor uses the cloneRandomized method of the given Genotype prototype to create a population of genotypes.
     */
    public Population(int populationSize, Genotype prototype)
    {
        genotypes = new ArrayList();
        for (int i = 0; i < populationSize; i++)
        {
            genotypes.add(prototype.cloneRandomized());
        }
    }

    /**
     * Uses an Evaluator to go trough the population and evaluate each Genotype's Phenotype
     * @param evaluator     Evaluator used to go trough the population and evaluate each Genotype's Phenotype
     */
    public void evaluate(Evaluator evaluator, Batch batch)
    {
        for(Genotype genotype : genotypes)
        {
            evaluator.evaluate(genotype, batch);
        }
    }

    @Override
    public String toString() {
        return "Population{" + genotypes + '}';
    }
    
   
}
