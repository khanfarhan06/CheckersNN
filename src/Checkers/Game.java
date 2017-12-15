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
        player1.setAlliance(Alliance.WHITE);
        player2.setAlliance(Alliance.BLACK);
        checkersBoard = new CheckersBoard();
        checkersBoard.initializeNewBoard();
        toMove = player1;
    }

    public MatchResult start(){
        MatchResult result = MatchResult.DRAWN;
        int plyCount = 1;
        do{
            Move nextMove = toMove.bestMove(checkersBoard.clone());
            if (nextMove==null){
                if(toMove.getAlliance()==Alliance.WHITE)
                    return MatchResult.LOST;
                else
                    return MatchResult.WON;
            }
            checkersBoard.makeMove(nextMove);
            checkersBoard.upgradeToKing(toMove.getAlliance());
            plyCount++;
            toMove = toMove == player1 ? player2: player1;
        }while (plyCount < 200);

        return MatchResult.DRAWN;
    }

}
