/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majesticbyte.genprog;

import com.majesticbyte.util.BinarySet;
import java.util.ArrayList;
import java.util.Random;

/**
 * Population is a collection of genotypes,
 * held in it's own class for convenience.
 * @author mkarjanm
 */
public class Population {
    private ArrayList<Genotype >genotypes;
    private Random rng;

    /**
     * The constructor uses the cloneRandomized method of the given Genotype prototype to create a population of genotypes.
     * 
     * @param populationSize    genotype count
     * @param prototype         The constructor uses the cloneRandomized method of the given Genotype prototype to create a population of genotypes.
     */
    public Population(int populationSize, Genotype prototype, Random rng)
    {
        this.rng = rng;
        genotypes = new ArrayList();
        for (int i = 0; i < populationSize; i++)
        {
            genotypes.add(prototype.cloneRandomized());
        }
    }

    /**
     * Uses an Evaluator to go trough the population and evaluate each Genotype's Phenotype
     * @param evaluator     Evaluator used to go trough the population and evaluate each Genotype's Phenotype
     */
    public void evaluate(Evaluator evaluator, Batch batch)
    {
        for(Genotype genotype : genotypes)
        {
            evaluator.evaluate(genotype, batch);
        }
    }

    @Override
    public String toString() {
        return "Population{" + genotypes + '}';
    }
    
    public void tournament(double killRatio)
    {
        int poolSize = genotypes.size();
        int targetKills = Math.max((int)Math.round((killRatio*poolSize)/poolSize), poolSize-2);     
        int[] substitutes = new int[poolSize];
        int[] killList = new int[targetKills];
        BinarySet killSet = new BinarySet(poolSize);
        fillArray(substitutes);
        int killCount = 0;
        while(killCount<targetKills)
        {
            int alive = poolSize-killCount;
            int firstpick = rng.nextInt(alive);
            int otherpick = rng.nextInt(alive);
            //if we picked the same, just pick the next one - the bias should be meaningless
            if (firstpick == otherpick) otherpick = (firstpick+1)%alive;
            Genotype first = genotypes.get(substitutes[firstpick]);
            Genotype other = genotypes.get(substitutes[otherpick]);
            boolean firstWins = first.getFeebleness()<other.getFeebleness();
            if (firstWins) {
                //add killed  to killList, substitute the last specimen (or second-to-last if we were last)
                int killed = substitutes[otherpick];
                killList[killCount] = killed;
                killSet.add(killed);
                substitutes[otherpick] = otherpick == (alive-1) ? alive-2 : alive-1;
            } else
            {
                int killed = substitutes[firstpick];
                killList[killCount] = killed;
                killSet.add(killed);
                substitutes[firstpick] = firstpick == (alive-1) ? alive-2 : alive-1;
            }
            killCount++;
        }
       //I wonder why I code like this when I'm tired.. -mk
       int alive = poolSize-killCount;
       for(int i = 0; i<poolSize; i++)
       {
           if (killSet.contains(i))
           {
               Genotype parent1 = genotypes.get(substitutes[rng.nextInt(alive)]);
               Genotype parent2 = genotypes.get(substitutes[rng.nextInt(alive)]);
               genotypes.get(i).combine(parent1, parent2);
               genotypes.get(i).setFeebleness(0.0);
           }else
           {
               genotypes.get(i).setFeebleness(0.0);
           }
       }
        
        
    }
    
    
    private void fillArray(int[] array)
    {
        int size = array.length;
        for (int i= 0 ; i<size; i++)
        {
            array[i]=i;
        }
    }
    
    public String printFittest()
    {
        return fittest().toString();
    }
    
    public Genotype fittest()
    {
        double leastFeebleness = Double.MAX_VALUE;
        int fittest = 0;
        for (int i = 0; i<genotypes.size(); i++)
        {
            if (genotypes.get(i).getFeebleness()<leastFeebleness)
            {
                fittest = i;
                leastFeebleness = genotypes.get(i).getFeebleness();
            }
        }
        return genotypes.get(fittest);
    }
   
}
