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
public class Solver{

    private Population population;
    public Solver() {
        
    }
    
    public void initialize(int populationSize, Genotype prototype)
    {
        population = new Population(populationSize, prototype);
    }
    
    public void runBatch(Batch batch)
    {
        
    }
}
