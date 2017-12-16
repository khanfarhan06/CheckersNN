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
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestReadObjectsFromFile {
    private static RandomPlayer randomPlayer = new RandomPlayer();

    public static void main(String[] args) {
        ArrayList<Individual> finalIndividuals = readIndividualsFromFile();

        Collections.sort(finalIndividuals,
                (a,b) -> Integer.compare((b.getTotalMatchesWon()*2 + b.getTotalMatchesDrawn())/b.getRoundMatchesPlayed(),
                (a.getTotalMatchesWon()*2 + a.getTotalMatchesDrawn())/a.getRoundMatchesPlayed()));

        printRecords(finalIndividuals);

        /*int i=0;
        for(Individual individual: finalIndividuals){
            System.out.println("Individual : "+ i++);
            playGame(individual);
        }*/
    }

    private static void printRecords(ArrayList<Individual> finalIndividuals){
        int i=0;
        for(Individual individual: finalIndividuals){
            System.out.println("====================================\nIndividual "+i++);
            System.out.println("Total: "+individual.getTotalMatchesPlayed()+" "+individual.getTotalMatchesWon()+" "+
                    individual.getTotalMatchesDrawn()+" "+individual.getTotalMatchesLost());
            System.out.println("Total: "+individual.getRoundMatchesPlayed()+" "+individual.getRoundMatchesWon()+" "+
                    individual.getRoundMatchesDrawn()+" "+individual.getRoundMatchesLost());
            System.out.println("====================================");
        }
    }

    private static void playGame(Individual individual){
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        final int[] won = {0};
        final int[] lost = { 0 };
        final int[] drawn = { 0 };
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Game game = new Game(individual.getPlayer(), randomPlayer);
                    MatchResult matchResult = game.start();
                    if(matchResult == MatchResult.WON)
                        won[0]++;
                    else if(matchResult == MatchResult.LOST)
                        lost[0]++;
                    else
                        drawn[0]++;
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("As WHITE: "+ won[0] +" "+ drawn[0] +" "+ lost[0]);
        executorService = Executors.newFixedThreadPool(100);
        won[0] = lost[0] = drawn[0] = 0;
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Game game = new Game(randomPlayer, individual.getPlayer());
                    MatchResult matchResult = game.start();
                    if(matchResult == MatchResult.WON)
                        lost[0]++;
                    else if(matchResult == MatchResult.LOST)
                        won[0]++;
                    else
                        drawn[0]++;
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("As BLACK: "+ won[0] +" "+ drawn[0] +" "+ lost[0]);
    }

    private static ArrayList<Individual> readIndividualsFromFile(){
        ArrayList<Individual> finalIndividuals = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("finalIndividualsAgainstRandom.dat");
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Individual individual;
                int readCount = 0;
                while (readCount<15){
                    individual = (Individual) objectInputStream.readObject();
                    finalIndividuals.add(individual);
                    readCount++;
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
