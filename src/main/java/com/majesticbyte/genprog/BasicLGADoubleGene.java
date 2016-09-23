/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.genprog.Operations.DoubleOperation;
import com.majesticbyte.genprog.Operations.OpNode;
import java.util.Random;

/**
 *
 * @author mkarjanm
 */
public class BasicLGADoubleGene extends BasicLGAGene{
    private final BasicLGAGene gene2;
    private final Random rng;

    public BasicLGADoubleGene(BasicLGAGene gene1, BasicLGAGene gene2, Random rng) {
        super(gene1);
        this.rng = rng;
        this.gene2 = gene2;
    }

    @Override
    public OpNode getOperation() {
        /*if (rng.nextInt(256)>gene2.getStrength()) {
            return super.getOperation();
        }*/
        return gene2.getOperation();
    }

    @Override
    public int getProgramPosition() {
        return super.getProgramPosition();
    }

    @Override
    public int getStrength() {
        return super.getStrength()+ gene2.getStrength();
    }

    @Override
    public String toString() {
        return "DoubleGene{" + super.toString() + ", " + gene2.toString() + '}';
    }
    
}
