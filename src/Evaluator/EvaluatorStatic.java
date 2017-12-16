package Evaluator;

import Checkers.CheckersBoard;
import Checkers.Piece;

public class EvaluatorStatic implements Evaluator{
    @Override
    public float evaluate(CheckersBoard checkersBoard) {
        float evaluationValue = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0){
                    if(checkersBoard.board[i][j] == Piece.WHITE_PAWN)
                        evaluationValue += 1;
                    else if(checkersBoard.board[i][j] == Piece.BLACK_PAWN)
                        evaluationValue += -1;
                    else if(checkersBoard.board[i][j] == Piece.WHITE_KING)
                        evaluationValue += 3;
                    else if(checkersBoard.board[i][j] == Piece.BLACK_KING)
                        evaluationValue += -3;
                }
            }
        }
        return  evaluationValue/24;
    }
}
