import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Board {
    Piece[][] board = new Piece[8][8];

    public Board(Piece[][] board){
        this.board = board;
    }

    public Board() {

    }

    @Override
    public Board clone(){
        Board copy = new Board();
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                copy.board[i][j] = this.board[i][j];
            }
        }
        return copy;
    }

    public List<JumpMove> allJumpMovesWhitePawn(int row, int column) {
        return allJumpMovesWhitePawn(new JumpMove(), row, column, this.clone());
    }

    private List<JumpMove> allJumpMovesWhitePawn(JumpMove jumpMove, int row, int column, Board boardCopy){
        List<JumpMove> legalMoves = new ArrayList<>();
        if(row>1){
            if(column>1 && boardCopy.board[row-1][column-1].getAlliance()==Alliance.BLACK
                    && boardCopy.board[row-2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row-2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row-2,column-2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row-2, column-2, boardCopy.clone()));
            }
            if(column<6 && boardCopy.board[row-1][column+1].getAlliance()==Alliance.BLACK
                    && boardCopy.board[row-2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row-2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row-2,column+2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row-2, column+2, boardCopy.clone()));
            }
        }
        return legalMoves;
    }

    public List<JumpMove> allJumpMovesBlackPawn(int row, int column){
        return allJumpMovesBlackPawn(new JumpMove(), row, column, this.clone());
    }

    private List<JumpMove> allJumpMovesBlackPawn(JumpMove jumpMove, int row, int column, Board boardCopy){
        List<JumpMove> legalMoves = new ArrayList<>();
        if(row<6){
            if(column>1 && boardCopy.board[row+1][column-1].getAlliance()==Alliance.WHITE
                    && boardCopy.board[row+2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row+2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row+2,column-2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row+2, column-2, boardCopy.clone()));
            }
            if(column<6 && boardCopy.board[row+1][column+1].getAlliance()==Alliance.WHITE
                    && boardCopy.board[row+2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row+2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row+2,column+2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row+2, column+2, boardCopy.clone()));
            }
        }
        return legalMoves;
    }

    public List<JumpMove> allJumpMovesWhiteKing(int row, int column){
        return allJumpMovesWhiteKing(new JumpMove(), row,column, this.clone());
    }

    private List<JumpMove> allJumpMovesWhiteKing(JumpMove jumpMove, int row, int column, Board boardCopy){
        List<JumpMove> legalMoves = new ArrayList<>();
        if(row>1){
            if(column>1 && boardCopy.board[row-1][column-1].getAlliance()==Alliance.BLACK
                    && boardCopy.board[row-2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row-2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row-2,column-2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row-2, column-2, boardCopy.clone()));
            }
            if(column<6 && boardCopy.board[row-1][column+1].getAlliance()==Alliance.BLACK
                    && boardCopy.board[row-2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row-2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row-2,column+2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row-2, column+2, boardCopy.clone()));
            }
        }
        if(row<6){
            if(column>1 && boardCopy.board[row+1][column-1].getAlliance()==Alliance.BLACK
                    && boardCopy.board[row+2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row+2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row+2,column-2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row+2, column-2, boardCopy.clone()));
            }
            if(column<6 && boardCopy.board[row+1][column+1].getAlliance()==Alliance.BLACK
                    && boardCopy.board[row+2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row+2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row+2,column+2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row+2, column+2, boardCopy.clone()));
            }
        }
        return legalMoves;
    }

    public List<JumpMove> allJumpMovesBlackKing(int row, int column){
        return allJumpMovesBlackKing(new JumpMove(), row,column, this.clone());
    }

    private List<JumpMove> allJumpMovesBlackKing(JumpMove jumpMove, int row, int column, Board boardCopy){
        List<JumpMove> legalMoves = new ArrayList<>();
        if(row>1){
            if(column>1 && boardCopy.board[row-1][column-1].getAlliance()==Alliance.WHITE
                    && boardCopy.board[row-2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row-2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row-2,column-2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row-2, column-2, boardCopy.clone()));
            }
            if(column<6 && boardCopy.board[row-1][column+1].getAlliance()==Alliance.WHITE
                    && boardCopy.board[row-2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row-2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row-2,column+2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row-2, column+2, boardCopy.clone()));
            }
        }
        if(row<6){
            if(column>1 && boardCopy.board[row+1][column-1].getAlliance()==Alliance.WHITE
                    && boardCopy.board[row+2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row+2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row+2,column-2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row+2, column-2, boardCopy.clone()));
            }
            if(column<6 && boardCopy.board[row+1][column+1].getAlliance()==Alliance.WHITE
                    && boardCopy.board[row+2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new Move(row, column,row+2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new Move(row, column,row+2,column+2));
                boardCopy.makeJumpMove(singleJumpMove);
                legalMoves.addAll(allJumpMovesWhitePawn(jumpMove, row+2, column+2, boardCopy.clone()));
            }
        }
        return legalMoves;
    }

    public List<Move> allSimpleMoveWhitePawn(int row, int column){
        List<Move> legalMoves = new ArrayList<>();
        if(column>0 && this.board[row-1][column-1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row-1,column-1));
        if(column<7 && this.board[row-1][column+1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row-1,column+1));
        return legalMoves;
    }

    public List<Move> allSimpleMoveBlackPawn(int row, int column){
        List<Move> legalMoves = new ArrayList<>();
        if(column>0 && this.board[row+1][column-1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row+1,column-1));
        if(column<7 && this.board[row+1][column+1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row+1,column+1));
        return legalMoves;
    }

    public List<Move> allSimpleMovesKing(int row, int column){
        List<Move> legalMoves = new ArrayList<>();
        if(column>0 && row>0 && this.board[row-1][column-1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row-1,column-1));
        if(column<7 && row>0 && this.board[row-1][column+1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row-1,column+1));
        if(column>0 && row<7 && this.board[row+1][column-1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row+1,column-1));
        if(column<7 && row<7 && this.board[row+1][column+1].getAlliance()==null)
            legalMoves.add(new Move(row,column,row+1,column+1));
        return legalMoves;
    }


    public void makeJumpMove(JumpMove jumpMove) {
        for(Move move: jumpMove.jumps){
            int initialRow = move.initialRowPosition;
            int initialColumn = move.initialColumnPosition;
            int finalRow = move.finalRowPosition;
            int finalColumn = move.finalColumnPosition;
            int midRow = (initialRow+finalRow)/2;
            int midColumn = (initialColumn+finalColumn)/2;

            board[midRow][midColumn] = Piece.EMPTY;
            board[finalRow][finalColumn] = board[initialRow][initialColumn];
            board[initialRow][initialColumn] = Piece.EMPTY;
        }
    }

    public void makeSimpleMove(Move move){
        board[move.finalRowPosition][move.finalColumnPosition] = board[move.initialRowPosition][move.initialColumnPosition];
        board[move.initialRowPosition][move.initialColumnPosition] = Piece.EMPTY;
    }

    public void showBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2!=0)
                    System.out.print(board[i][j]);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void initializeNewBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2!=0){
                    if (i<3)
                        board[i][j] = Piece.BLACK_PAWN;
                    else if(i>4)
                        board[i][j] = Piece.WHITE_PAWN;
                    else
                        board[i][j] = Piece.EMPTY;
                }
            }
        }
    }
    /***
     * public List<Move> allLegalMovesOfBlack(){
     List<Move> legalMoves = new ArrayList<>();
     for(int i=0; i<32; i++){
     if(board[i] == Piece.BLACK_PAWN)
     legalMoves.addAll(allBlackPawnMoves());
     else if(board[i] == Piece.BLACK_KING)
     legalMoves.addAll(allBlackKingMoves());
     }
     return legalMoves;
     }


     public List<Move> allLegalMovesOfWhite(){
     List<Move> legalMoves = new ArrayList<>();
     for(int i=0; i<32; i++){
     if(board[i] == Piece.WHITE_PAWN)
     legalMoves.addAll(allWhitePawnMoves());
     else if(board[i] == Piece.WHITE_KING)
     legalMoves.addAll(allWhiteKingMoves());
     }
     return legalMoves;
     }

     private List<Move> allWhiteKingMoves() {
     List<Move> legalMoves = new ArrayList<>();
     //TODO complete this
     return legalMoves;
     }

     private List<Move> allBlackKingMoves() {
     List<Move> legalMoves = new ArrayList<>();
     //TODO complete this
     return legalMoves;
     }

     private List<Move> allWhitePawnMoves() {
     List<Move> legalMoves = new ArrayList<>();
     //TODO complete this
     return legalMoves;
     }

     private List<Move> allBlackPawnMoves() {
     List<Move> legalMoves = new ArrayList<>();
     //TODO complete this
     return legalMoves;
     }
     */


}
