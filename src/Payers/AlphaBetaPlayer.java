package Payers;

import Checkers.Alliance;
import Checkers.CheckersBoard;
import Checkers.Move;
import NeuralNetwork.EvaluatorNeuralNet;

import java.util.List;

public class AlphaBetaPlayer extends Player{
    EvaluatorNeuralNet evaluator;
    int depth;
    public AlphaBetaPlayer(Alliance alliance,int depth, EvaluatorNeuralNet evaluator) {
        super(alliance);
        this.depth = depth;
        this.evaluator = evaluator;
    }

    @Override
    public Move bestMove(CheckersBoard checkersBoard) {
        List<Move> legalMoves = checkersBoard.allMoves(this.getAlliance());
        if(legalMoves.size()==0)
            return null;
        Move bestMove = null;
        double bestScore;
        if(this.getAlliance()==Alliance.WHITE){
            bestScore = Double.MIN_VALUE;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                double currentScore = alphabeta(boardCopy, depth-1, Double.MIN_VALUE, Double.MAX_VALUE, Alliance.BLACK);
                if(currentScore > bestScore){
                    bestMove = move;
                    bestScore = currentScore;
                }
            }
        }
        else{
            bestScore = Double.MAX_VALUE;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                double currentScore = alphabeta(boardCopy, depth-1, Double.MIN_VALUE, Double.MAX_VALUE, Alliance.WHITE);
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
        List<Move> legalMoves = checkersBoard.allMoves(alliance);
        if(legalMoves.size()==0)
            return (alliance == Alliance.WHITE)? -1.0 : 1.0;
        if(alliance == Alliance.WHITE){
            double currentBest = Double.MIN_VALUE;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                currentBest = Math.max(currentBest, alphabeta(boardCopy, depth-1, alpha, beta, Alliance.BLACK));
                alpha = Math.max(alpha, currentBest);
                if(beta <= alpha)
                    break;
            }
            return currentBest;
        }else{
            double currentBest = Double.MAX_VALUE;
            for(Move move: legalMoves){
                CheckersBoard boardCopy = checkersBoard.clone();
                boardCopy.makeMove(move);
                currentBest = Math.min(currentBest, alphabeta(boardCopy, depth-1, alpha, beta, Alliance.WHITE));
                beta = Math.min(beta, currentBest);
                if(beta <= alpha)
                    break;
            }
            return currentBest;
        }
    }


}
