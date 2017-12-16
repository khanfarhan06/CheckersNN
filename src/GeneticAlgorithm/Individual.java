package GeneticAlgorithm;

import Evaluator.EvaluatorNeuralNet;
import Payers.AlphaBetaPlayer;

import java.io.Serializable;

public class Individual implements Serializable{
    private AlphaBetaPlayer player;
    private int roundMatchesPlayed, roundMatchesWon, roundMatchesLost, roundMatchesDrawn;
    private int totalMatchesPlayed, totalMatchesWon, totalMatchesLost, totalMatchesDrawn;

    public Individual(int depth, int nodeCountHiddenLayer1,int nodeCountHiddenlayer2){
        player = new AlphaBetaPlayer(depth, new EvaluatorNeuralNet(nodeCountHiddenLayer1, nodeCountHiddenlayer2));
    }

    public Individual(Individual individual){
        player = new AlphaBetaPlayer(individual.player.getDepth(), new EvaluatorNeuralNet((EvaluatorNeuralNet) individual.player.getEvaluator()));
    }

    public AlphaBetaPlayer getPlayer() {
        return player;
    }

    public synchronized void updateRecordsForMatchWon(){
        roundMatchesPlayed++;
        roundMatchesWon++;
        totalMatchesPlayed++;
        totalMatchesWon++;
    }

    public synchronized void updateRecordsForMatchDrawn(){
        roundMatchesPlayed++;
        roundMatchesDrawn++;
        totalMatchesPlayed++;
        totalMatchesDrawn++;
    }

    public synchronized void updateRecordsForMatchLost(){
        roundMatchesPlayed++;
        roundMatchesLost++;
        totalMatchesPlayed++;
        totalMatchesLost++;
    }

    public void resetRoundScores(){
        roundMatchesPlayed = 0;
        roundMatchesWon = 0;
        roundMatchesDrawn = 0;
        roundMatchesLost = 0;
    }

    public int getRoundScore(){
        return roundMatchesWon*2 + roundMatchesDrawn - roundMatchesLost*3;
    }

    public int getRoundMatchesPlayed() {
        return roundMatchesPlayed;
    }

    public int getRoundMatchesWon() {
        return roundMatchesWon;
    }

    public int getRoundMatchesLost() {
        return roundMatchesLost;
    }

    public int getRoundMatchesDrawn() {
        return roundMatchesDrawn;
    }

    public int getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }

    public int getTotalMatchesWon() {
        return totalMatchesWon;
    }

    public int getTotalMatchesLost() {
        return totalMatchesLost;
    }

    public int getTotalMatchesDrawn() {
        return totalMatchesDrawn;
    }
}
