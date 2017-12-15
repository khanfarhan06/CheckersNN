package Testing;

import Checkers.Alliance;
import Checkers.Game;
import Payers.RandomPlayer;

class TestGame {
    public static void main(String[] args) {
        RandomPlayer randomPlayer1 = new RandomPlayer();
        RandomPlayer randomPlayer2 = new RandomPlayer();
        Game game = new Game(randomPlayer1,randomPlayer2);
        game.start();
    }
}
