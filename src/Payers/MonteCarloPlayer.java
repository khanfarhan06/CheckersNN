package Payers;

import Checkers.Alliance;
import Checkers.CheckersBoard;
import Checkers.Move;
import MonteCarloTreeSearch.TreeNode;

public class MonteCarloPlayer extends Player {
    int maximumThinkingTime;

    public MonteCarloPlayer(int maximumThinkingTime){
        this.maximumThinkingTime = maximumThinkingTime;
    }

    @Override
    public Move bestMove(CheckersBoard checkersBoard) {
        TreeNode treeNode = new TreeNode(checkersBoard, this.getAlliance());
        //long startingTime = System.currentTimeMillis();
        int count=0;
        while (count < maximumThinkingTime){
            treeNode.selectAction();
            count++;
        }
        Move bestMove = treeNode.select(this.getAlliance()).getLastMove();
        return bestMove;
    }

}
