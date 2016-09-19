/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Operations.Registry;
import com.majesticbyte.genprog.Operations.Stack;
import java.util.ArrayList;

/**
 *
 * @author mkarjanm
 */
class BasicLGAPhenotype implements Phenotype {

    private ArrayList<OpNode> program;
    private int programSize;
    private static final int JUMP_LIMIT = 256;

    BasicLGAPhenotype(ArrayList<BasicLGAGene> genome, int programSize) {
        this.programSize = programSize;
        this.program = construct(genome);
    }

    @Override
    public ProgramResult calculate(DataPoint input) {
        //TODO DO
        Stack stack = new Stack(8);
        Registry registry = new Registry(8);
        setInputToRegistry(input.getInput(), registry);
        int i = 0;
        int jumpcount = 0;
        int e = program.size();
        for (; i < e; i++) {
            program.get(i).call(stack, registry);
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

    private ArrayList<OpNode> construct(ArrayList<BasicLGAGene> genome) {
        //TODO use strength property?
        ArrayList<OpNode> nodeListing = new ArrayList<OpNode>();
        BasicLGAGene[] programProto = new BasicLGAGene[programSize];
        int e = genome.size();
        for (int i = 0; i < e; i++) {
            BasicLGAGene gene = genome.get(i);
            int position = gene.getProgramPosition();
            if (position < programSize) {
                programProto[position] = gene;
            }
        }
        for (int i = 0; i < programSize; i++) {
            BasicLGAGene gene = programProto[i];
            if (gene != null) {
                nodeListing.add(gene.getOperation());
            }
        }
        return nodeListing;
    }

    @Override
    public String toString() {
        return "BLGAPhenotype{ operations:" + program.size() + ", " + program + '}';
    }

}
