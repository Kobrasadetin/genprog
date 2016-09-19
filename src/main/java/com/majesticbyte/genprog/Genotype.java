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
public interface Genotype {   

    /**
     * Combines two genotypes. Should allow for either parameter to be a reference to self.
     * @param a     first genotype (order does not matter)
     * @param b     second  genotype (order does not matter)
     */
    public void combine(Genotype a, Genotype b);

    /**
     * Returns a phenotype for running. Should avoid rebuilding an already built phenotype.
     * @return      a reference to phenotype
     */
    public Phenotype getPhenotype();

    /**
     * Creates a new genotype with the same input-output scheme.
     * @return      a new genotype
     */
    public Genotype cloneRandomized();

    /**
     * TODO implementation might change
     * Returns the cumulative error of genotype's phenotype evaluations (opposite of 'fitness')
     * @return      cumulative error, larger numbers for poorer performance
     */
    public Double getFeebleness();

    /**
     * TODO implementation might change 
     * Sets the cumulative error of genotype's phenotype evaluations (opposite of 'fitness')
     * @param value     error
     */
    public void setFeebleness(Double value);
}
