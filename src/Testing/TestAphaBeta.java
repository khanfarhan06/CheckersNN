package Testing;

import Checkers.Alliance;
import Checkers.Game;
import NeuralNetwork.EvaluatorNeuralNet;
import Payers.AlphaBetaPlayer;
import Payers.RandomPlayer;

class TestAphaBeta {
    public static void main(String[] args) {
        play(Alliance.WHITE,Alliance.BLACK);
        play(Alliance.BLACK,Alliance.WHITE);
    }

    public static void play(Alliance alphaBetaAlliance, Alliance randomAlliance){
        System.out.println("As "+alphaBetaAlliance);
        for (int depth = 2; depth <=6 ; depth+=2) {
            System.out.print("Depth "+depth);
            AlphaBetaPlayer player1 = new AlphaBetaPlayer( depth, new EvaluatorNeuralNet(10,10));
            RandomPlayer player2 = new RandomPlayer();
            int wins = 0,loss =0, draw=0;
            for (int i = 0; i < 100; i++) {
                Game game;
                if(alphaBetaAlliance==Alliance.WHITE)
                    game = new Game(player1, player2);
                else
                    game = new Game(player2,player1);
                /*int result = game.start();
                if(result==1)
                    wins++;
                else if(result==0)
                    draw++;
                else
                    loss++;*/
            }
            System.out.println(": "+wins+" "+draw+" "+loss);
        }
    }
}
