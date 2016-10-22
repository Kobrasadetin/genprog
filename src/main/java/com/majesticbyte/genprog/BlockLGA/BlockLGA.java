/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.BlockLGA;

import com.majesticbyte.genprog.BasicLGA.*;
import com.majesticbyte.genprog.Genotype;
import com.majesticbyte.genprog.Operations.OpNode;
import com.majesticbyte.genprog.Phenotype;
import java.util.ArrayList;
import java.util.Random;

/**
 * Basic Linear Genetic Algorithm Genotype
 *
 * @author mkarjanm
 */
public class BlockLGA implements Genotype {

    private ArrayList<BlockLGAGene> genome;
    private ArrayList<? extends OpNode> possibleOperations;
    private int genomeSize;
    private int programSize;
    private double feebleness;
    private Random rng;
    private Phenotype myPhenotype;

    public BlockLGA(Random rng) {
        this.possibleOperations = null;
        this.genomeSize = 0;
        this.rng = rng;
        genome = new ArrayList();
    }

    public BlockLGA(ArrayList<? extends OpNode> operations, int genomeSize, int programSize, Random rng) {
        this.possibleOperations = operations;
        this.genomeSize = genomeSize;
        this.programSize = programSize;
        this.rng = rng;
        genome = new ArrayList();
        for (int i = 0; i < genomeSize; i++) {
            genome.add(newGeneFromPossible(operations, rng, programSize));
        }
    }

    private static BlockLGAGene newGeneFromPossible(ArrayList<? extends OpNode> operations, Random rng1, int programSize1) {
        return new BlockLGAGene(operations.get(rng1.nextInt(operations.size())), rng1.nextInt(programSize1), rng1.nextInt(256));
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
        if (a.getClass().equals(BlockLGA.class) && b.getClass().equals(BlockLGA.class)) {
            BlockLGA mom1;
            BlockLGA mom2;
            if ((rng.nextInt() & 1) == 1) {
                mom1 = (BlockLGA) a;
                mom2 = (BlockLGA) b;
            } else {
                mom1 = (BlockLGA) b;
                mom2 = (BlockLGA) a;
            }
            int newSize = mom1.genomeSize;
            int maxSplize = Math.min(newSize, mom2.genomeSize);
            int spliceSize = rng.nextInt(maxSplize);
            int spliceStart = rng.nextInt(maxSplize - spliceSize);
            int spliceEnd = spliceSize + spliceStart;
            int offset = rng.nextInt(3) == 0 ? rng.nextInt(maxSplize) : 0;
            ArrayList<BlockLGAGene> newGenome = new ArrayList<>(newSize);
            for (int i = 0; i < spliceStart; i++) {
                newGenome.add(mutate((BlockLGAGene)mom1.genome.get(i), 16));
            }
            for (int i = spliceStart; i < spliceEnd; i++) {
                newGenome.add(mutate(mom2.genome.get((i + offset) % mom2.genomeSize), 16));
            }
            for (int i = spliceEnd; i < newSize; i++) {
                newGenome.add(mutate(mom1.genome.get(i), 16));
            }
            this.genome = newGenome;
            this.myPhenotype = null;

        } else // not compatible genotypes
        {
            throw new IllegalArgumentException("BasicLGA combine operation: incompatible Genotype");
        }
    }

    private BlockLGAGene mutate(BlockLGAGene gene, int onceInEvery) {
        if (rng.nextInt(onceInEvery) == 0) {
            if (rng.nextInt(2) == 0) {
                return gene.mutate(programSize, rng);
            } else {
                return newGeneFromPossible(possibleOperations, rng, programSize);
            }
            //return newGeneFromPossible(possibleOperations, rng, programSize);
        }
        return gene;
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
            myPhenotype = new BlockLGAPhenotype(genome, programSize, rng);
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
        BlockLGA newGenotype = new BlockLGA(possibleOperations, this.genomeSize, this.programSize, rng);
        return newGenotype;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        String nl = System.getProperty("line.separator");
        s.append(nl).append("BasicLGA:").append(nl)
                .append("genotype:  ").append(genome.toString()).append(nl)
                .append("phenotype: ").append(this.getPhenotype().toString()).append(nl);
        return s.toString();
    }

}
