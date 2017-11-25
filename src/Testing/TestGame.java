package Testing;

import Checkers.Alliance;
import Checkers.Game;
import Payers.RandomPlayer;

public class TestGame {
    public static void main(String[] args) {
        RandomPlayer randomPlayer1 = new RandomPlayer(Alliance.WHITE);
        RandomPlayer randomPlayer2 = new RandomPlayer(Alliance.BLACK);
        Game game = new Game(randomPlayer1,randomPlayer2);
        game.start();

    }
}
