package GeneticAlgorithm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public abstract class GeneticAlgorithm {
    ArrayList<Individual> population;
    private int generationCount;
    protected final int individualCount;
    private final int maxNumberOfGenerations;

    protected GeneticAlgorithm(int individualCount, int maxNumberOfGenerations, int depth, int nodeCountHiddenLayer1, int nodeCountHiddenLayer2){
        this.individualCount = individualCount;
        this.maxNumberOfGenerations = maxNumberOfGenerations;
        generationCount=1;
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

                resetRoundScores();
            }

            generationCount++;
        }
    }

    private void initializeWithRandomIndividuals(int depth, int nodeCountHiddenLayer1, int nodeCountHiddenLayer2){
        this.population = new ArrayList<Individual>();
        for (int i = 0; i < individualCount; i++) {
            population.add(new Individual(depth, nodeCountHiddenLayer1, nodeCountHiddenLayer2));
        }
    }

    protected abstract void playTournament();

    private void sortIndividuals(){
        Collections.sort(population, (a, b) -> Integer.compare(b.getRoundScore(), a.getRoundScore()));
    }

    private void killWeakIndividuals(){
        int numberOfIndividualsToRemove = (int) Math.floor(population.size()/2);
        for (int i = 0; i < numberOfIndividualsToRemove; i++) {
            population.remove(population.size()-1);
        }
    }

    private void populateWithNewIndividuals(){
        int numberOfIndividualsToCreate = individualCount - population.size();
        for (int i = 0; i < numberOfIndividualsToCreate; i++) {
            Individual parent = population.get(i);
            population.add(new Individual(parent));
        }
    }

    private void resetRoundScores(){
        for(Individual individual: population)
            individual.resetRoundScores();
    }

    public void saveFinalIndividualsToFile(String filename){
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
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
}
