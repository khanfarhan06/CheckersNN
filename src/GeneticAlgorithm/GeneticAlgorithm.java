package GeneticAlgorithm;

import Checkers.Game;
import Checkers.MatchResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {
    ArrayList<Individual> population;
    private int generationCount;
    private final int individualCount;
    private final int maxNumberOfGenerations;
    public GeneticAlgorithm(int individualCount, int maxNumberOfGenerations,int depth, int nodeCountHiddenLayer1, int nodeCountHiddenLayer2){
        this.generationCount = 1;
        this.maxNumberOfGenerations = maxNumberOfGenerations;
        this.individualCount = individualCount;
        initializeWithRandomIndividuals(depth, nodeCountHiddenLayer1, nodeCountHiddenLayer2);
    }

    public void start(){
        while(generationCount<=maxNumberOfGenerations){
            System.out.println("Generation : " +generationCount);
            playTournament();

            sortIndividuals();

            if(generationCount<maxNumberOfGenerations){
                killWeakIndividuals();

                populateWithNewIndividuals();

                generationCount++;
            }
        }

        saveFinalIndividualsToFile();
    }

    private void saveFinalIndividualsToFile(){
        try (FileOutputStream fileOutputStream = new FileOutputStream("finalIndividuals.dat")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(Individual individual: population){
                objectOutputStream.writeObject(individual);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateWithNewIndividuals(){
        int numberOfIndividualsToCreate = individualCount - population.size();
        for (int i = 0; i < numberOfIndividualsToCreate; i++) {
            Individual parent = population.get(i);
            population.add(new Individual(parent));
        }
    }

    private void killWeakIndividuals(){
        int numberOfIndividualsToRemove = (int) Math.floor(population.size()/2);
        for (int i = 0; i < numberOfIndividualsToRemove; i++) {
            population.remove(population.size()-1);
        }
    }

    private void sortIndividuals(){
        Collections.sort(population, (a,b) -> Integer.compare(b.getRoundScore(), a.getRoundScore()));
    }

    private void playTournament(){
        for(Individual individual: population){
            for(Individual otherIndividual: population){
                if(!individual.equals(otherIndividual)){
                    Game game = new Game(individual.getPlayer(), otherIndividual.getPlayer());
                    MatchResult matchResult = game.start();
                    if(matchResult == MatchResult.WON){
                        individual.updateRecordsForMatchWon();
                        otherIndividual.updateRecordsForMatchLost();
                    }else if(matchResult == MatchResult.LOST){
                        individual.updateRecordsForMatchLost();
                        otherIndividual.updateRecordsForMatchWon();
                    }else if(matchResult == MatchResult.DRAWN){
                        individual.updateRecordsForMatchDrawn();
                        otherIndividual.updateRecordsForMatchDrawn();
                    }
                }
            }
        }
    }

    private void initializeWithRandomIndividuals(int depth, int nodeCountHiddenLayer1, int nodeCountHiddenLayer2){
        this.population = new ArrayList<Individual>();
        for (int i = 0; i < individualCount; i++) {
            population.add(new Individual(depth, nodeCountHiddenLayer1, nodeCountHiddenLayer2));
        }
    }
}
