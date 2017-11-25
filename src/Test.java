public class Test {
    public static void main(String[] args) {
        Board newBoard = new Board();
        newBoard.initializeNewBoard();
        newBoard.showBoard();
        newBoard.makeSimpleMove(new Move(5,2,4,1));
        System.out.println("After move");
        newBoard.showBoard();
        newBoard.makeSimpleMove(new Move(2,3,3,2));
        System.out.println("After move");
        newBoard.showBoard();
        JumpMove jumpMove = new JumpMove();
        jumpMove.jumps.add(new Move(4,1,2,3));
        newBoard.makeJumpMove(jumpMove);
        System.out.println("After move");
        newBoard.showBoard();
    }
}
