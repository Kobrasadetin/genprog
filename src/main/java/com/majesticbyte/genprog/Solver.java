/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

/**
 * Solver runs a genetic programming batch with the given data on populations of given size and type.
 * 
 * @author mkarjanm
 */
public class Solver{

    private Population population;

    /**
     * TODO: will have arguments
     */
    public Solver() {
        
    }
    
    /**
     * TODO implementation will change
     * @param populationSize
     * @param prototype
     */
    public void initialize(int populationSize, Genotype prototype)
    {
        population = new Population(populationSize, prototype);
    }
    
    /**
     * TODO implementation will change
     * @param batch
     */
    public void runBatch(Batch batch)
    {
        
    }
}
