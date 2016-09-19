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
public interface Phenotype {

    /**
     * Returns a result DataPoint for the input DataPoint, used for evaluation of the phenotype
     * @param input     input DataPoint
     * @return          resulting DataPoint
     */
    public DataPoint calculate(DataPoint input);
}
