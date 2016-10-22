/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog.BlockLGA;

import com.majesticbyte.genprog.BasicLGA.*;
import com.majesticbyte.genprog.Operations.BasicOperation;
import com.majesticbyte.genprog.Operations.OpNode;
import java.util.Random;

/**
 *
 * @author mkarjanm
 */
public class BlockLGAGene extends BasicLGAGene{

    public BlockLGAGene(BlockLGAGene gene) {
        super(gene.operation, gene.programPosition,  gene.strength);
    }

    public BlockLGAGene(OpNode operation, int programPosition, int strength) {
         super(operation, programPosition,  strength);
    }

    @Override
    public String toString() {
        return "BlockLGAGene{" + "op= " + operation + ", pos= " + programPosition + ", str= " + strength + '}';
    }

    BlockLGAGene mutate(int programSize, Random rng) {
        BasicOperation mutation = ((BasicOperation)this.operation).mutation(rng.nextInt(4096));
        if (mutation!=null) return new BlockLGAGene(mutation, programPosition, strength);
        return new BlockLGAGene( this.operation, rng.nextInt(programSize), rng.nextInt(256));
    }

}
