package Checkers;

import Payers.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    CheckersBoard checkersBoard;
    Player player1;
    Player player2;
    Player toMove;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        checkersBoard = new CheckersBoard();
        checkersBoard.initializeNewBoard();
        toMove = player1;
    }

    public void start(){
        //Make changes to alllow different players
        MatchResult result = MatchResult.DRAWN;
        int plyCount = 1;
        do{
            Move nextMove = toMove.bestMove(checkersBoard);
            if (nextMove==null){
                result = MatchResult.LOST;
                break;
            }
            checkersBoard.makeMove(nextMove);
            checkersBoard.upgradeToKing(toMove.getAlliance());
            System.out.println("PlyCount: "+plyCount);
            checkersBoard.showBoard();
            plyCount++;
            toMove = toMove == player1 ? player2: player1;
        }while (plyCount < 200);

        if (plyCount == 200)
            result = MatchResult.DRAWN;
        matchResult(toMove, result);
    }

    private void matchResult(Player toMove, MatchResult lost) {
        System.out.println(toMove.getAlliance() + "has" + lost);
    }


}
