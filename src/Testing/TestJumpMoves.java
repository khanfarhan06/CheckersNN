package Testing;

import Checkers.*;

import java.util.List;

class TestJumpMoves {
    public static void main(String[] args) {
        CheckersBoard checkersBoard = new CheckersBoard();
        checkersBoard.initializeNewBoard();
        checkersBoard.makeSimpleMove(new SimpleMove(5,2,4,1));
        checkersBoard.makeSimpleMove(new SimpleMove(2,3,3,2));
        List<JumpMove> jumpMoves = checkersBoard.getAllJumpMoves(Alliance.WHITE);
        System.out.println(jumpMoves);
    }
}
