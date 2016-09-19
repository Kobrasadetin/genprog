/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.BasicOperation.Opcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
        
        //this is an usage example, to be removed later
        LinearOpnodeBuilder builder = new LinearOpnodeBuilder();
        builder .addOperation(Opcode.add).addOperation(Opcode.sub).addOperation(Opcode.mul).addOperation(Opcode.div)
                .r1Range(0, 2).r2Range(1, 3)
                //.addOperation(Opcode.jneg).addOperation(Opcode.jpos).addOperation(Opcode.jzer).addOperation(Opcode.jump)
                //.r1Range(0, 2).r2Range(0, 31).r2StepSize(8)
                .addOperation(Opcode.push).addOperation(Opcode.pop)
                .r1Range(0, 3).r2Range(0, 0)
                .addOperation(Opcode.set).r1Range(1, 3).r2Range(0, 16384).r2StepSize(512)
                .addOperation(Opcode.mov).r1Range(0, 3).r2Range(0, 3);
        
        initialize(200, new BasicLGA(builder.toArray(), 32, new Random()));
        
        System.out.println(population);
        
        EvaluatorPrintout evaluationPrinter = new EvaluatorPrintout();
        
        Batch batch = new Batch();
        ArrayList<Double> input = new ArrayList<>(Arrays.asList(1.0));
        ArrayList<Double> output = new ArrayList<>(Arrays.asList(2.0));
        batch.add(new DataPoint(input, output));
        
        population.evaluate(evaluationPrinter, batch);
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
