package Testing;

import Checkers.Game;
import Checkers.MatchResult;
import Payers.MonteCarloPlayer;
import Payers.RandomPlayer;

public class TestMCTSPlayer {
    public static void main(String[] args) {
        MonteCarloPlayer monteCarloPlayer = new MonteCarloPlayer(100);
        RandomPlayer randomPlayer = new RandomPlayer();
        Game game = new Game(monteCarloPlayer, randomPlayer);
        game.start();
        /*int wins = 0, draws = 0, losses = 0;
        for (int i = 0; i < 100; i++) {
            Game game = new Game(monteCarloPlayer, randomPlayer);
            MatchResult matchResult = game.start();
            if(matchResult == MatchResult.WON)
                wins++;
            else if(matchResult == MatchResult.DRAWN)
                draws++;
            else
                losses++;
        }
        System.out.println("As White: "+wins+" "+draws+" "+losses);
        wins = 0; draws = 0; losses = 0;
        for (int i = 0; i < 100; i++) {
            Game game = new Game(randomPlayer, monteCarloPlayer);
            MatchResult matchResult = game.start();
            if(matchResult == MatchResult.WON)
                losses++;
            else if(matchResult == MatchResult.DRAWN)
                draws++;
            else
                wins++;
        }
        System.out.println("As White: "+wins+" "+draws+" "+losses);*/
    }
}
