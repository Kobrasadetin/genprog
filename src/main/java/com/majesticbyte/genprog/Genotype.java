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
public interface Genotype {   
    public void combine(Genotype a, Genotype b);
    public Phenotype getPhenotype();
    public Genotype cloneRandomized();
    public Double getFeebleness();
    public void setFeebleness(Double value);
}
