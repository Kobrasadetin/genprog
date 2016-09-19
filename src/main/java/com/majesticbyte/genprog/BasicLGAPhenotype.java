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
class BasicLGAPhenotype implements Phenotype{

    private ArrayList<OpNode> genome;
    
    BasicLGAPhenotype(ArrayList<OpNode> genome) {
        this.genome = genome;
    }

    @Override
    public DataPoint calculate(DataPoint input) {
        //TODO DO
        Stack stack = new Stack(8);
        Registry registry = new Registry(8);
        int i = 0;
        int e = genome.size();
        for(; i<e; i++)
        {
            genome.get(i).call(stack, registry);
        }      
        return input;
    }
    
}
