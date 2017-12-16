package Testing;

import GeneticAlgorithm.GeneticAlgorithmPlayingRandomPlayer;

public class TestGeneticAlgorithm {
    public static void main(String[] args) {
        GeneticAlgorithmPlayingRandomPlayer geneticAlgorithm = new GeneticAlgorithmPlayingRandomPlayer(15, 100,6,40,10,10);
        geneticAlgorithm.start();
        geneticAlgorithm.saveFinalIndividualsToFile("./src/GeneticallyFoundIndividuals/FinalIndividualsAgainstRandomDepth6Gen100.dat");
    }
}

