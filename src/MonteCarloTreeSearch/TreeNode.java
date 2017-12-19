package MonteCarloTreeSearch;

import Checkers.*;
import Payers.RandomPlayer;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    static double epsilon = 1e-6;
    static RandomPlayer randomPlayer1 = new RandomPlayer();
    static RandomPlayer randomPlayer2 = new RandomPlayer();
    CheckersBoard checkersBoard;
    Move lastMove;
    Alliance toMove;
    int numberOfVisits, totalValue;

    public List<TreeNode> getChildren() {
        return children;
    }

    List<TreeNode> children;

    public Move getLastMove() {
        return lastMove;
    }

    public TreeNode(CheckersBoard checkersBoard, Alliance toMove){
        this.checkersBoard = checkersBoard;
        this.toMove = toMove;
        this.lastMove = null;
    }

    private TreeNode(CheckersBoard checkersBoard, Alliance toMove, Move lastMove){
        this(checkersBoard, toMove);
        this.lastMove = lastMove;
    }

    public void selectAction(){
        List<TreeNode> visited = new ArrayList<>();
        TreeNode current = this;
        visited.add(this);
        while(!current.isLeaf()){
            current = current.select(this.toMove);
        }
        current.expand();
        TreeNode newNode = current.select(this.toMove);
        visited.add(newNode);
        int value = rollOut(newNode);

        for(TreeNode node: visited){
            node.updateStatus(value);
        }
    }

    private void updateStatus(int value) {
        this.numberOfVisits++;
        this.totalValue += value;
    }

    private int rollOut(TreeNode newNode) {
        Game game = new Game(randomPlayer1, randomPlayer2, newNode.checkersBoard.clone(), newNode.toMove);
        MatchResult matchResult = game.start();
        if(matchResult == MatchResult.WON)
            return 1;
        else if(matchResult == MatchResult.DRAWN)
            return 0;
        else
            return -1;
    }

    private void expand() {
        this.children = new ArrayList<>();
        List<Move> legalMoves = this.checkersBoard.getAllMoves(this.toMove);
        for(Move move: legalMoves){
            Alliance nextToMove = (this.toMove == Alliance.WHITE)?Alliance.BLACK:Alliance.WHITE;
            CheckersBoard nextBoard = this.checkersBoard.clone();
            nextBoard.makeMove(move);
            children.add(new TreeNode(nextBoard, nextToMove, move));
        }
    }

    public TreeNode select(Alliance toMove) {

        TreeNode selected = null;
        if(toMove == Alliance.WHITE){
            double bestValue = Double.MIN_VALUE;
            for (TreeNode child: this.children){
                double uctValue =
                        (double) child.totalValue/(child.numberOfVisits + epsilon) +
                                Math.sqrt(2)* Math.sqrt(Math.log(this.numberOfVisits+1)/(child.numberOfVisits + epsilon)) +
                                Math.random()*epsilon;
                if(uctValue>bestValue){
                    bestValue = uctValue;
                    selected = child;
                }
            }
        }else{
            double bestValue = Double.MAX_VALUE;
            for (TreeNode child: this.children){
                double uctValue =
                        (double) child.totalValue/(child.numberOfVisits + epsilon) +
                                Math.sqrt(2)* Math.sqrt(Math.log(this.numberOfVisits)/(child.numberOfVisits + epsilon)) +
                                Math.random()*epsilon;
                if(uctValue<bestValue){
                    bestValue = uctValue;
                    selected = child;
                }
            }
        }
        return selected;
    }

    private boolean isLeaf() {
        return children==null;
    }
}
