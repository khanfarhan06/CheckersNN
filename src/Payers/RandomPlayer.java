package Payers;

import Checkers.Alliance;
import Checkers.CheckersBoard;
import Checkers.Move;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player{

    private final Random randomGenerator = new Random();


    @Override
    public Move bestMove(CheckersBoard checkersBoard) {
        List<Move> legalMoves = checkersBoard.getAllMoves(this.getAlliance());
        if (legalMoves.size()==0)
            return null;
        int index = randomGenerator.nextInt(legalMoves.size());
        return legalMoves.get(index);
    }
}
