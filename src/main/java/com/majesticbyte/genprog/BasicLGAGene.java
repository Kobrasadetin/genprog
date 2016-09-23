/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.OpNode;
import java.util.Random;

/**
 *
 * @author mkarjanm
 */
public class BasicLGAGene {

    private final OpNode operation;
    private final int programPosition;
    private final int strength;

    public BasicLGAGene(BasicLGAGene gene) {
        this.operation = gene.operation;
        this.programPosition = gene.programPosition;
        this.strength = gene.strength;
    }

    public BasicLGAGene(OpNode operation, int programPosition, int strength) {
        this.operation = operation;
        this.programPosition = programPosition;
        this.strength = strength;
    }

    public OpNode getOperation() {
        return operation;
    }

    public int getProgramPosition() {
        return programPosition;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return "BasicLGAGene{" + "op= " + operation + ", pos= " + programPosition + ", str= " + strength + '}';
    }

    BasicLGAGene mutate(int programSize, Random rng) {
        return new BasicLGAGene( this.operation, rng.nextInt(programSize), rng.nextInt(256));
    }

}
