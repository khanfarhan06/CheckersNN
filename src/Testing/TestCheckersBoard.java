package Testing;

import Checkers.CheckersBoard;
import Checkers.JumpMove;
import Checkers.SimpleMove;

public class TestCheckersBoard {
    public static void main(String[] args) {
        CheckersBoard newCheckersBoard = new CheckersBoard();
        newCheckersBoard.initializeNewBoard();
        newCheckersBoard.showBoard();
        newCheckersBoard.makeSimpleMove(new SimpleMove(5,2,4,1));
        System.out.println("After move");
        newCheckersBoard.showBoard();
        newCheckersBoard.makeSimpleMove(new SimpleMove(2,3,3,2));
        System.out.println("After move");
        newCheckersBoard.showBoard();
        JumpMove jumpMove = new JumpMove();
        jumpMove.jumps.add(new SimpleMove(4,1,2,3));
        newCheckersBoard.makeJumpMove(jumpMove);
        System.out.println("After move");
        newCheckersBoard.showBoard();
    }
}
