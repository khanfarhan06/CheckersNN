package Testing;

import GeneticAlgorithm.GeneticAlgorithm;

public class TestGeneticAlgorithm {
    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(10, 50,4,15,15);
        geneticAlgorithm.start();
    }
}