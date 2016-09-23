/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.BasicOperation;
import com.majesticbyte.genprog.Operations.DoubleOperation;
import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Operations.Registry;
import com.majesticbyte.genprog.Operations.Stack;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mkarjanm
 */
class BasicLGAPhenotype implements Phenotype {

    private OpNode[] program;
    private int programSize;
    private static final int JUMP_LIMIT = 64;
    private final Random rng;

    BasicLGAPhenotype(ArrayList<BasicLGAGene> genome, int programSize, Random rng) {
        this.rng = rng;
        this.programSize = programSize;
        this.program = construct(genome);
    }

    @Override
    public ProgramResult calculate(DataPoint input) {
        //TODO DO
        Stack stack = new Stack(16);
        Registry registry = new Registry(8);
        //setInputToRegistry(input.getInput(), registry);
        setInputToStack(input.getInput(), stack);
        int i = 0;
        int jumpcount = 0;
        int e = program.length;
        for (; i < e; i++) {
            program[i].call(stack, registry);
            if(registry.getPC()!=i){
                jumpcount++;
                i = registry.getPC();
            }
            if (jumpcount>JUMP_LIMIT)
            {
                return new ProgramResult(null, true);
            }
            registry.incrementPC();
        }
        ArrayList<Double> result = new ArrayList<Double>();
        result.add(registry.get(0));
        return new ProgramResult(result, false);
    }
    
    private void setInputToRegistry(ArrayList<Double> inputs, Registry registry)
    {
        for (int i = 0; i < inputs.size(); i++) {
            registry.set(i, inputs.get(i));
        }
    }
    
    private void setInputToStack(ArrayList<Double> input, Stack stack) {
        for (int i = 0; i < input.size(); i++) {
            stack.push(input.get(i));
        }
    }

    private OpNode[] construct(ArrayList<BasicLGAGene> genome) {
        //TODO use strength property?
        OpNode[] nodeListing = new OpNode[programSize];
        BasicLGAGene[] programProto = new BasicLGAGene[programSize];
        int e = genome.size();
        for (int i = 0; i < e; i++) {
            BasicLGAGene gene = genome.get(i);
            int position = gene.getProgramPosition();
            if (position < programSize) {
                if (programProto[position] == null) programProto[position] = gene;
                else{
                    programProto[position] = new BasicLGADoubleGene(programProto[position],gene, rng) ;
                }
            }
        }
        for (int i = 0; i < programSize; i++) {
            BasicLGAGene gene = programProto[i];
            if (gene != null) {
                nodeListing[i] = (gene.getOperation());
            }else
            {
                nodeListing[i] = BasicOperation.NoOperation();
            }
        }
        return nodeListing;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int size = program.length;
        for (int i = 0; i < size; i++)
        {
            s.append(program[i].toString()).append(", ");
        }
        return "BLGAPhenotype{ operations:" + program.length + ", " + s.toString() + '}';
    }

}
