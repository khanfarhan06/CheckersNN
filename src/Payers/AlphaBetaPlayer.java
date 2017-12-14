package Payers;

import Checkers.Alliance;
import Checkers.CheckersBoard;
import Checkers.Move;
import NeuralNetwork.EvaluatorNeuralNet;

import java.util.List;

public class AlphaBetaPlayer extends Player{

    private final EvaluatorNeuralNet evaluator;
    private final int depth;
    public AlphaBetaPlayer(Alliance alliance,int depth, EvaluatorNeuralNet evaluator) {
        super(alliance);
        this.depth = depth;
        this.evaluator = evaluator;
    }

    @Override
    public Move bestMove(CheckersBoard checkersBoard) {
        List<Move> legalMoves = checkersBoard.getAllMoves(this.getAlliance());
        if(legalMoves.size()==0)
            return null;
        Move bestMove = null;
        double bestScore;
        if(this.getAlliance()==Alliance.WHITE){
            bestScore = -1.0;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                boardCopy.upgradeToKing(Alliance.WHITE);
                double currentScore = alphabeta(boardCopy, depth-1, -1.0, 1.0, Alliance.BLACK);
                if(currentScore > bestScore){
                    bestMove = move;
                    bestScore = currentScore;
                }
            }
        }
        else{
            bestScore = 1.0;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                boardCopy.upgradeToKing(Alliance.BLACK);
                double currentScore = alphabeta(boardCopy, depth-1, -1.0, 1.0, Alliance.WHITE);
                if(currentScore < bestScore){
                    bestMove = move;
                    bestScore = currentScore;
                }
            }
        }
        return bestMove;
    }

    private double alphabeta(CheckersBoard checkersBoard, int depth, double alpha, double beta,  Alliance alliance){
        if(depth == 0)
            return evaluator.evaluate(checkersBoard);
        List<Move> legalMoves = checkersBoard.getAllMoves(alliance);
        if(legalMoves.size()==0)
            return (alliance == Alliance.WHITE)? -1.0 : 1.0;
        if(alliance == Alliance.WHITE){
            double currentBest = -1.0;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                boardCopy.upgradeToKing(Alliance.WHITE);
                currentBest = Math.max(currentBest, alphabeta(boardCopy, depth-1, alpha, beta, Alliance.BLACK));
                if(currentBest >= beta)
                    break;
                alpha = Math.max(alpha, currentBest);
            }
            return currentBest;
        }else{
            double currentBest = 1.0;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                boardCopy.upgradeToKing(Alliance.BLACK);
                currentBest = Math.min(currentBest, alphabeta(boardCopy, depth-1, alpha, beta, Alliance.WHITE));
                if(currentBest <= alpha)
                    break;
                beta = Math.min(beta, currentBest);
            }
            return currentBest;
        }
    }


}
