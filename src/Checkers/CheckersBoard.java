package Checkers;

import java.util.ArrayList;
import java.util.List;

public class CheckersBoard {
    public Piece[][] board = new Piece[8][8];

    public CheckersBoard(Piece[][] board){
        this.board = board;
    }

    public CheckersBoard() {

    }

    @Override
    public CheckersBoard clone(){
        CheckersBoard copy = new CheckersBoard();
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                copy.board[i][j] = this.board[i][j];
            }
        }
        return copy;
    }

    public List<Move> allMoves(Alliance toMove){
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(allJumpMoves(toMove));
        if (legalMoves.isEmpty())
            legalMoves.addAll(allSimleMoves(toMove));
        return legalMoves;
    }

    public List<JumpMove> allJumpMoves(Alliance toMove){
        List<JumpMove> legalMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2!=0){
                    if(board[i][j].getAlliance() == toMove){
                        if(board[i][j].getAlliance() == Alliance.WHITE){
                            if (board[i][j] == Piece.WHITE_PAWN)
                                legalMoves.addAll(allJumpMovesWhitePawn(i,j));
                            else
                                legalMoves.addAll(allJumpMovesWhiteKing(i,j));
                        }else {
                            if(board[i][j] == Piece.BLACK_PAWN)
                                legalMoves.addAll(allJumpMovesBlackPawn(i,j));
                            else
                                legalMoves.addAll(allJumpMovesBlackKing(i,j));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    public List<SimpleMove> allSimleMoves(Alliance toMove){
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2!=0){
                    if(board[i][j].getAlliance() == toMove){
                        if(board[i][j].getAlliance() == Alliance.WHITE){
                            if (board[i][j] == Piece.WHITE_PAWN)
                                legalSimpleMoves.addAll(allSimpleMovesWhitePawn(i,j));
                            else
                                legalSimpleMoves.addAll(allSimpleMovesKing(i,j));
                        }else {
                            if(board[i][j] == Piece.BLACK_PAWN)
                                legalSimpleMoves.addAll(allSimpleMovesBlackPawn(i,j));
                            else
                                legalSimpleMoves.addAll(allSimpleMovesKing(i,j));
                        }
                    }
                }
            }
        }
        return legalSimpleMoves;
    }


    public List<JumpMove> allJumpMovesWhitePawn(int row, int column) {
        List<JumpMove> legalMoves = new ArrayList<>();
        allJumpMovesWhitePawn(legalMoves, new JumpMove(), row, column, this.clone());
        return legalMoves;
    }

    private void allJumpMovesWhitePawn(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy){
        boolean isLastJump = true;
        //TODO check if jumpmove has been made the only pass a clone of it
        if(row>1){
            if(column>1 && checkersBoardCopy.board[row-1][column-1].getAlliance()==Alliance.BLACK
                    && checkersBoardCopy.board[row-2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row-2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row-2,column-2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row-2, column-2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if(column<6 && checkersBoardCopy.board[row-1][column+1].getAlliance()==Alliance.BLACK
                    && checkersBoardCopy.board[row-2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row-2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row-2,column+2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row-2, column+2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if(isLastJump && jumpMove.jumps.size()!=0)
            legalMoves.add(jumpMove);
    }

    public List<JumpMove> allJumpMovesBlackPawn(int row, int column){
        List<JumpMove> legalMoves = new ArrayList<>();
        allJumpMovesBlackPawn(legalMoves, new JumpMove(), row, column, this.clone());
        return legalMoves;
    }

    private void allJumpMovesBlackPawn(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy){
        boolean isLastJump = true;
        //TODO check if jumpmove has been made the only pass a clone of it
        if(row<6){
            if(column>1 && checkersBoardCopy.board[row+1][column-1].getAlliance()==Alliance.WHITE
                    && checkersBoardCopy.board[row+2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row+2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row+2,column-2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhitePawn(legalMoves,jumpMove.clone(), row+2, column-2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if(column<6 && checkersBoardCopy.board[row+1][column+1].getAlliance()==Alliance.WHITE
                    && checkersBoardCopy.board[row+2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row+2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row+2,column+2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row+2, column+2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (isLastJump && jumpMove.jumps.size()!=0)
            legalMoves.add(jumpMove);
    }

    public List<JumpMove> allJumpMovesWhiteKing(int row, int column){
        List<JumpMove> legalMoves = new ArrayList<>();
        allJumpMovesWhiteKing(legalMoves, new JumpMove(), row,column, this.clone());
        return legalMoves;
    }

    private void allJumpMovesWhiteKing(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy){
        boolean isLastJump = true;
        if(row>1){
            if(column>1 && checkersBoardCopy.board[row-1][column-1].getAlliance()==Alliance.BLACK
                    && checkersBoardCopy.board[row-2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row-2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row-2,column-2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhiteKing(legalMoves, jumpMove.clone(), row-2, column-2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if(column<6 && checkersBoardCopy.board[row-1][column+1].getAlliance()==Alliance.BLACK
                    && checkersBoardCopy.board[row-2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row-2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row-2,column+2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhiteKing(legalMoves, jumpMove.clone(), row-2, column+2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if(row<6){
            if(column>1 && checkersBoardCopy.board[row+1][column-1].getAlliance()==Alliance.BLACK
                    && checkersBoardCopy.board[row+2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row+2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row+2,column-2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhiteKing(legalMoves, jumpMove.clone(), row+2, column-2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if(column<6 && checkersBoardCopy.board[row+1][column+1].getAlliance()==Alliance.BLACK
                    && checkersBoardCopy.board[row+2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row+2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row+2,column+2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesWhiteKing(legalMoves, jumpMove, row+2, column+2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if(isLastJump && jumpMove.jumps.size()!=0)
            legalMoves.add(jumpMove);
    }

    public List<JumpMove> allJumpMovesBlackKing(int row, int column){
        List<JumpMove> legalMoves = new ArrayList<>();
        allJumpMovesBlackKing(legalMoves,new JumpMove(), row,column, this.clone());
        return legalMoves;
    }

    private void allJumpMovesBlackKing(List<JumpMove> legalMoves ,JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy){
        boolean isLastJump = true;
        if(row>1){
            if(column>1 && checkersBoardCopy.board[row-1][column-1].getAlliance()==Alliance.WHITE
                    && checkersBoardCopy.board[row-2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row-2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row-2,column-2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesBlackKing(legalMoves,jumpMove.clone(), row-2, column-2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if(column<6 && checkersBoardCopy.board[row-1][column+1].getAlliance()==Alliance.WHITE
                    && checkersBoardCopy.board[row-2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row-2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row-2,column+2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesBlackKing(legalMoves, jumpMove.clone(), row-2, column+2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if(row<6){
            if(column>1 && checkersBoardCopy.board[row+1][column-1].getAlliance()==Alliance.WHITE
                    && checkersBoardCopy.board[row+2][column-2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row+2,column-2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row+2,column-2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesBlackKing(legalMoves, jumpMove.clone(), row+2, column-2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if(column<6 && checkersBoardCopy.board[row+1][column+1].getAlliance()==Alliance.WHITE
                    && checkersBoardCopy.board[row+2][column+2].getAlliance()==null){
                jumpMove.jumps.add(new SimpleMove(row, column,row+2,column+2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column,row+2,column+2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                allJumpMovesBlackKing(legalMoves, jumpMove.clone(), row+2, column+2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if(isLastJump && jumpMove.jumps.size()!=0)
            legalMoves.add(jumpMove);
    }

    public List<SimpleMove> allSimpleMovesWhitePawn(int row, int column){
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        if(column>0 && this.board[row-1][column-1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row-1,column-1));
        if(column<7 && this.board[row-1][column+1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row-1,column+1));
        return legalSimpleMoves;
    }

    public List<SimpleMove> allSimpleMovesBlackPawn(int row, int column){
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        if(column>0 && this.board[row+1][column-1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row+1,column-1));
        if(column<7 && this.board[row+1][column+1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row+1,column+1));
        return legalSimpleMoves;
    }

    public List<SimpleMove> allSimpleMovesKing(int row, int column){
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        if(column>0 && row>0 && this.board[row-1][column-1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row-1,column-1));
        if(column<7 && row>0 && this.board[row-1][column+1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row-1,column+1));
        if(column>0 && row<7 && this.board[row+1][column-1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row+1,column-1));
        if(column<7 && row<7 && this.board[row+1][column+1].getAlliance()==null)
            legalSimpleMoves.add(new SimpleMove(row,column,row+1,column+1));
        return legalSimpleMoves;
    }

    public void makeMove(Move move){
        if (move instanceof JumpMove)
            makeJumpMove((JumpMove) move);
        else
            makeSimpleMove((SimpleMove)move);
    }

    public void upgradeToKing(Alliance toMove){
        int lastRow = (toMove == Alliance.WHITE)? 0 : 7;
        for (int j = 0; j < 8; j++) {
            if ((lastRow+j)%2!=0)
                if(board[lastRow][j].getAlliance() == toMove)
                    board[lastRow][j] = (toMove==Alliance.WHITE)? Piece.WHITE_KING:Piece.BLACK_KING;
        }
    }

    public void makeJumpMove(JumpMove jumpMove) {
        for(SimpleMove simpleMove : jumpMove.jumps){
            int initialRow = simpleMove.initialRowPosition;
            int initialColumn = simpleMove.initialColumnPosition;
            int finalRow = simpleMove.finalRowPosition;
            int finalColumn = simpleMove.finalColumnPosition;
            int midRow = (initialRow+finalRow)/2;
            int midColumn = (initialColumn+finalColumn)/2;

            board[midRow][midColumn] = Piece.EMPTY;
            board[finalRow][finalColumn] = board[initialRow][initialColumn];
            board[initialRow][initialColumn] = Piece.EMPTY;
        }
    }

    public void makeSimpleMove(SimpleMove simpleMove){
        board[simpleMove.finalRowPosition][simpleMove.finalColumnPosition] = board[simpleMove.initialRowPosition][simpleMove.initialColumnPosition];
        board[simpleMove.initialRowPosition][simpleMove.initialColumnPosition] = Piece.EMPTY;

    }

    public void showBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2!=0)
                    System.out.print(board[i][j]);
                else
                    System.out.print("-");
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
}
