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
public class Population {
    private ArrayList<Genotype >genotypes;
    public Population(int populationSize, Genotype prototype)
    {
        genotypes = new ArrayList();
        for (int i = 0; i < populationSize; i++)
        {
            genotypes.add(prototype.cloneRandomized());
        }
    }
    public void evaluate(Evaluator evaluator)
    {
        for(Genotype genotype : genotypes)
        {
            evaluator.evaluate(genotype);
        }
    }
}
