package Checkers;

import Payers.Player;

public class Game {
    private final CheckersBoard checkersBoard;
    private final Player player1;
    private final Player player2;
    private Player toMove;

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
            Move nextMove = toMove.bestMove(checkersBoard.clone());
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
