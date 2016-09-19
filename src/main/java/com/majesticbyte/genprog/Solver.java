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
    private Random rng = new Random();
    /**
     * TODO: will have arguments
     */
    public Solver() {
        
        //this is an usage example, to be removed later
        LinearOpnodeBuilder builder = new LinearOpnodeBuilder();
        builder .addOperation(Opcode.add).addOperation(Opcode.sub).addOperation(Opcode.mul).addOperation(Opcode.div)
                .r1Range(0, 2).r2Range(1, 3)
                .addOperation(Opcode.jneg).addOperation(Opcode.jpos).addOperation(Opcode.jzer).addOperation(Opcode.jump)
                .r1Range(0, 2).r2Range(0, 31).r2StepSize(8)
                .addOperation(Opcode.push).addOperation(Opcode.pop)
                .r1Range(0, 3).r2Range(0, 0)
                .addOperation(Opcode.set).r1Range(1, 3).r2Range(0, 16384).r2StepSize(512)
                .addOperation(Opcode.mov).r1Range(0, 3).r2Range(0, 3);
        
        initialize(700, new BasicLGA(builder.toArray(), 32, rng));
        
        System.out.println(population);
        
        EvaluatorPrintout evaluationPrinter = new EvaluatorPrintout();
        
        Batch batch = new Batch();
        for(int i = 0; i<4; i++)
        {
            ArrayList<Double> input = new ArrayList<>(Arrays.asList((double)i+1));
            ArrayList<Double> output = new ArrayList<>(Arrays.asList((double)Math.pow(i+1, 2)));
            batch.add(new DataPoint(input, output));
        }
        
        for(int i = 0; i<200; i++)
        {
            population.evaluate(evaluationPrinter, batch);
            population.tournament(0.2);
        }
        
        System.out.println("fittest: \n"+population.printFittest());
    }
    
    /**
     * TODO implementation will change
     * @param populationSize
     * @param prototype
     */
    public void initialize(int populationSize, Genotype prototype)
    {
        population = new Population(populationSize, prototype, rng);
    }
    
    /**
     * TODO implementation will change
     * @param batch
     */
    public void runBatch(Batch batch)
    {
        
    }
}
