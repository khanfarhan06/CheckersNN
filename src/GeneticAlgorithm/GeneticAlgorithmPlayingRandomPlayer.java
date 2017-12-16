package GeneticAlgorithm;

import Checkers.Alliance;
import Checkers.Game;
import Checkers.MatchResult;
import Payers.RandomPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithmPlayingRandomPlayer extends GeneticAlgorithm{
    int matchesEachTournament;

    public GeneticAlgorithmPlayingRandomPlayer(int individualCount, int maxNumberOfGenerations, int depth, int nodeCountHiddenLayer1, int nodeCountHiddenLayer2, int matchesEachTournament){
        super(individualCount, maxNumberOfGenerations, depth, nodeCountHiddenLayer1, nodeCountHiddenLayer2);
        this.matchesEachTournament = matchesEachTournament;
    }

    @Override
    protected void playTournament(){
        ExecutorService executorService = Executors.newFixedThreadPool(individualCount*matchesEachTournament);
        for(Individual individual: population){
            for (int i = 0; i < matchesEachTournament/2; i++) {
                executorService.execute(new GameWithRandomPlayerRunnable(individual, Alliance.WHITE));
                executorService.execute(new GameWithRandomPlayerRunnable(individual, Alliance.BLACK));
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class GameRunnable implements Runnable{
        Individual individual, otherIndividual;

        public GameRunnable(Individual individual, Individual otherIndividual){
            this.individual = individual;
            this.otherIndividual = otherIndividual;
        }
        @Override
        public void run() {
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

    static class GameWithRandomPlayerRunnable implements Runnable{
        private static final RandomPlayer randomPlayer= new RandomPlayer();
        Individual individual;
        Alliance alliance;
        public GameWithRandomPlayerRunnable(Individual individual, Alliance alliance){
            this.individual = individual;
            this.alliance = alliance;
        }
        @Override
        public void run() {
            if(alliance == Alliance.WHITE){
                Game game = new Game(individual.getPlayer(), randomPlayer);
                MatchResult matchResult = game.start();
                if(matchResult == MatchResult.WON){
                    individual.updateRecordsForMatchWon();
                }else if(matchResult == MatchResult.LOST){
                    individual.updateRecordsForMatchLost();
                }else if(matchResult == MatchResult.DRAWN){
                    individual.updateRecordsForMatchDrawn();
                }
            }
            else if(alliance == Alliance.BLACK){
                Game game = new Game(randomPlayer, individual.getPlayer());
                MatchResult matchResult = game.start();
                if(matchResult == MatchResult.WON){
                    individual.updateRecordsForMatchLost();
                }else if(matchResult == MatchResult.LOST){
                    individual.updateRecordsForMatchWon();
                }else if(matchResult == MatchResult.DRAWN){
                    individual.updateRecordsForMatchDrawn();
                }
            }
        }
    }
}
