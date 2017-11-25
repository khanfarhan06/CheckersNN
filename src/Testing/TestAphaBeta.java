package Testing;

import Checkers.Alliance;
import Checkers.Game;
import NeuralNetwork.EvaluatorNeuralNet;
import Payers.AlphaBetaPlayer;
import Payers.RandomPlayer;

public class TestAphaBeta {
    public static void main(String[] args) {
        AlphaBetaPlayer player1 = new AlphaBetaPlayer(Alliance.WHITE, 4, new EvaluatorNeuralNet());
        RandomPlayer player2 = new RandomPlayer(Alliance.BLACK);
        Game game = new Game(player1, player2);
        game.start();
    }
}
