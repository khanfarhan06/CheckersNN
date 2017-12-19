package Testing;

import Checkers.Alliance;
import Checkers.Game;
import Checkers.MatchResult;
import Evaluator.EvaluatorNeuralNet;
import Evaluator.EvaluatorStatic;
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
            AlphaBetaPlayer player1 = new AlphaBetaPlayer( depth, new EvaluatorStatic());
            RandomPlayer player2 = new RandomPlayer();
            int wins = 0,loss =0, draw=0;
            for (int i = 0; i < 100; i++) {
                Game game = new Game(player1, player2);

                MatchResult result = game.start();
                if(result==MatchResult.WON)
                    wins++;
                else if(result== MatchResult.DRAWN)
                    draw++;
                else
                    loss++;
            }
            System.out.println(": "+wins+" "+draw+" "+loss);
        }
    }
}
