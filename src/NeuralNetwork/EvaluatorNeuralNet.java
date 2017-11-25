package NeuralNetwork;

import Checkers.CheckersBoard;
import Checkers.Piece;

public class EvaluatorNeuralNet {


    //TODO just currently working as static evaluation function, willchange to NN later
    public double evaluate(CheckersBoard checkersBoard){
        double value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2==0){
                    if(checkersBoard.board[i][j] == Piece.WHITE_PAWN)
                        value += 1.0;
                    else if(checkersBoard.board[i][j] == Piece.WHITE_KING)
                        value += 3.0;
                    else if(checkersBoard.board[i][j] == Piece.BLACK_PAWN)
                        value += -1.0;
                    else if(checkersBoard.board[i][j] == Piece.BLACK_KING)
                        value += -3.0;
                }
            }
        }
        return value/24;
    }
}
