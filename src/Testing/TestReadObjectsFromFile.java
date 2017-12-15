package Testing;

import Checkers.Game;
import Checkers.MatchResult;
import GeneticAlgorithm.Individual;
import Payers.RandomPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TestReadObjectsFromFile {
    private static RandomPlayer randomPlayer = new RandomPlayer();

    public static void main(String[] args) {
        ArrayList<Individual> finalIndividuals = readIndividualsFromFile();
        int i=0;
        for(Individual individual: finalIndividuals){
            System.out.println("====================================\nIndividual "+i);
            playGame(individual);
            System.out.println("====================================");
        }
    }

    private static void playGame(Individual individual){
        int won = 0, lost = 0, drawn = 0;
        for (int i = 0; i < 100; i++) {
            Game game = new Game(individual.getPlayer(), randomPlayer);
            MatchResult matchResult = game.start();
            if(matchResult == MatchResult.WON)
                won++;
            else if(matchResult == MatchResult.LOST)
                lost++;
            else
                drawn++;
        }
        System.out.println("As WHITE: "+won +" "+drawn+" "+lost);
        won = lost = drawn = 0;
        for (int i = 0; i < 100; i++) {
            Game game = new Game(randomPlayer, individual.getPlayer());
            MatchResult matchResult = game.start();
            if(matchResult == MatchResult.WON)
                lost++;
            else if(matchResult == MatchResult.LOST)
                won++;
            else
                drawn++;
        }
        System.out.println("As BLACK: "+won +" "+drawn+" "+lost);
    }

    private static ArrayList<Individual> readIndividualsFromFile(){
        ArrayList<Individual> finalIndividuals = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("finalIndividuals.dat");
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Individual individual;
                System.out.println("Reading started");
                int readCount = 0;
                while (readCount<10){
                    System.out.println("Read = " + ++readCount);
                    individual = (Individual) objectInputStream.readObject();
                    finalIndividuals.add(individual);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return finalIndividuals;
    }
}
