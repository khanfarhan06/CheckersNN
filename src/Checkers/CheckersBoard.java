package Checkers;

import java.util.ArrayList;
import java.util.List;

public class CheckersBoard implements Cloneable {
    public final Piece[][] board = new Piece[8][8];

    public CheckersBoard() {

    }

    @Override
    public CheckersBoard clone() {
        CheckersBoard copy = new CheckersBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy.board[i][j] = this.board[i][j];
            }
        }
        return copy;
    }

    public List<Move> getAllMoves(Alliance toMove) {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(getAllJumpMoves(toMove));
        if (legalMoves.isEmpty())
            legalMoves.addAll(getAllSimpleMoves(toMove));
        return legalMoves;
    }

    public List<JumpMove> getAllJumpMoves(Alliance toMove) {
        List<JumpMove> legalMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    if (board[i][j].getAlliance() == toMove) {
                        if (board[i][j].getAlliance() == Alliance.WHITE) {
                            if (board[i][j] == Piece.WHITE_PAWN)
                                legalMoves.addAll(getAllJumpMovesWhitePawn(i, j));
                            else
                                legalMoves.addAll(getAllJumpMovesWhiteKing(i, j));
                        } else {
                            if (board[i][j] == Piece.BLACK_PAWN)
                                legalMoves.addAll(getAllJumpMovesBlackPawn(i, j));
                            else
                                legalMoves.addAll(getAllJumpMovesBlackKing(i, j));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    private List<SimpleMove> getAllSimpleMoves(Alliance toMove) {
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    if (board[i][j].getAlliance() == toMove) {
                        if (board[i][j].getAlliance() == Alliance.WHITE) {
                            if (board[i][j] == Piece.WHITE_PAWN)
                                legalSimpleMoves.addAll(getAllSimpleMovesWhitePawn(i, j));
                            else
                                legalSimpleMoves.addAll(getAllSimpleMovesKing(i, j));
                        } else {
                            if (board[i][j] == Piece.BLACK_PAWN)
                                legalSimpleMoves.addAll(getAllSimpleMovesBlackPawn(i, j));
                            else
                                legalSimpleMoves.addAll(getAllSimpleMovesKing(i, j));
                        }
                    }
                }
            }
        }
        return legalSimpleMoves;
    }

    private List<JumpMove> getAllJumpMovesWhitePawn(int row, int column) {
        List<JumpMove> legalMoves = new ArrayList<>();
        getAllJumpMovesWhitePawn(legalMoves, new JumpMove(), row, column, this.clone());
        return legalMoves;
    }

    private void getAllJumpMovesWhitePawn(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy) {
        boolean isLastJump = true;
        //TODO check if jumpmove has been made the only pass a clone of it
        if (row > 1) {
            if (column > 1 && checkersBoardCopy.board[row - 1][column - 1].getAlliance() == Alliance.BLACK
                    && checkersBoardCopy.board[row - 2][column - 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row - 2, column - 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row - 2, column - 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row - 2, column - 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if (column < 6 && checkersBoardCopy.board[row - 1][column + 1].getAlliance() == Alliance.BLACK
                    && checkersBoardCopy.board[row - 2][column + 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row - 2, column + 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row - 2, column + 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row - 2, column + 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (isLastJump && jumpMove.jumps.size() != 0)
            legalMoves.add(jumpMove);
    }

    private List<JumpMove> getAllJumpMovesBlackPawn(int row, int column) {
        List<JumpMove> legalMoves = new ArrayList<>();
        getAllJumpMovesBlackPawn(legalMoves, new JumpMove(), row, column, this.clone());
        return legalMoves;
    }

    private void getAllJumpMovesBlackPawn(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy) {
        boolean isLastJump = true;
        //TODO check if jumpmove has been made the only pass a clone of it
        if (row < 6) {
            if (column > 1 && checkersBoardCopy.board[row + 1][column - 1].getAlliance() == Alliance.WHITE
                    && checkersBoardCopy.board[row + 2][column - 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row + 2, column - 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row + 2, column - 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row + 2, column - 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if (column < 6 && checkersBoardCopy.board[row + 1][column + 1].getAlliance() == Alliance.WHITE
                    && checkersBoardCopy.board[row + 2][column + 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row + 2, column + 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row + 2, column + 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhitePawn(legalMoves, jumpMove.clone(), row + 2, column + 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (isLastJump && jumpMove.jumps.size() != 0)
            legalMoves.add(jumpMove);
    }

    private List<JumpMove> getAllJumpMovesWhiteKing(int row, int column) {
        List<JumpMove> legalMoves = new ArrayList<>();
        getAllJumpMovesWhiteKing(legalMoves, new JumpMove(), row, column, this.clone());
        return legalMoves;
    }

    private void getAllJumpMovesWhiteKing(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy) {
        boolean isLastJump = true;
        if (row > 1) {
            if (column > 1 && checkersBoardCopy.board[row - 1][column - 1].getAlliance() == Alliance.BLACK
                    && checkersBoardCopy.board[row - 2][column - 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row - 2, column - 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row - 2, column - 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhiteKing(legalMoves, jumpMove.clone(), row - 2, column - 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if (column < 6 && checkersBoardCopy.board[row - 1][column + 1].getAlliance() == Alliance.BLACK
                    && checkersBoardCopy.board[row - 2][column + 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row - 2, column + 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row - 2, column + 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhiteKing(legalMoves, jumpMove.clone(), row - 2, column + 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (row < 6) {
            if (column > 1 && checkersBoardCopy.board[row + 1][column - 1].getAlliance() == Alliance.BLACK
                    && checkersBoardCopy.board[row + 2][column - 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row + 2, column - 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row + 2, column - 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhiteKing(legalMoves, jumpMove.clone(), row + 2, column - 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if (column < 6 && checkersBoardCopy.board[row + 1][column + 1].getAlliance() == Alliance.BLACK
                    && checkersBoardCopy.board[row + 2][column + 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row + 2, column + 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row + 2, column + 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesWhiteKing(legalMoves, jumpMove, row + 2, column + 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (isLastJump && jumpMove.jumps.size() != 0)
            legalMoves.add(jumpMove);
    }

    private List<JumpMove> getAllJumpMovesBlackKing(int row, int column) {
        List<JumpMove> legalMoves = new ArrayList<>();
        getAllJumpMovesBlackKing(legalMoves, new JumpMove(), row, column, this.clone());
        return legalMoves;
    }

    private void getAllJumpMovesBlackKing(List<JumpMove> legalMoves, JumpMove jumpMove, int row, int column, CheckersBoard checkersBoardCopy) {
        boolean isLastJump = true;
        if (row > 1) {
            if (column > 1 && checkersBoardCopy.board[row - 1][column - 1].getAlliance() == Alliance.WHITE
                    && checkersBoardCopy.board[row - 2][column - 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row - 2, column - 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row - 2, column - 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesBlackKing(legalMoves, jumpMove.clone(), row - 2, column - 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if (column < 6 && checkersBoardCopy.board[row - 1][column + 1].getAlliance() == Alliance.WHITE
                    && checkersBoardCopy.board[row - 2][column + 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row - 2, column + 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row - 2, column + 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesBlackKing(legalMoves, jumpMove.clone(), row - 2, column + 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (row < 6) {
            if (column > 1 && checkersBoardCopy.board[row + 1][column - 1].getAlliance() == Alliance.WHITE
                    && checkersBoardCopy.board[row + 2][column - 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row + 2, column - 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row + 2, column - 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesBlackKing(legalMoves, jumpMove.clone(), row + 2, column - 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
            if (column < 6 && checkersBoardCopy.board[row + 1][column + 1].getAlliance() == Alliance.WHITE
                    && checkersBoardCopy.board[row + 2][column + 2].getAlliance() == null) {
                jumpMove.jumps.add(new SimpleMove(row, column, row + 2, column + 2));
                JumpMove singleJumpMove = new JumpMove();
                singleJumpMove.jumps.add(new SimpleMove(row, column, row + 2, column + 2));
                checkersBoardCopy.makeJumpMove(singleJumpMove);
                getAllJumpMovesBlackKing(legalMoves, jumpMove.clone(), row + 2, column + 2, checkersBoardCopy.clone());
                isLastJump = false;
            }
        }
        if (isLastJump && jumpMove.jumps.size() != 0)
            legalMoves.add(jumpMove);
    }

    private List<SimpleMove> getAllSimpleMovesWhitePawn(int row, int column) {
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        if (column > 0 && this.board[row - 1][column - 1].getAlliance() == null)
            legalSimpleMoves.add(new SimpleMove(row, column, row - 1, column - 1));
        if (column < 7 && this.board[row - 1][column + 1].getAlliance() == null)
            legalSimpleMoves.add(new SimpleMove(row, column, row - 1, column + 1));
        return legalSimpleMoves;
    }

    private List<SimpleMove> getAllSimpleMovesBlackPawn(int row, int column) {
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        try {
            if (column > 0 && this.board[row + 1][column - 1].getAlliance() == null)
                legalSimpleMoves.add(new SimpleMove(row, column, row + 1, column - 1));
            if (column < 7 && this.board[row + 1][column + 1].getAlliance() == null)
                legalSimpleMoves.add(new SimpleMove(row, column, row + 1, column + 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("row: " + row);
            System.err.println("column: " + column);
            System.err.println("Piece: " + board[row][column]);
            e.printStackTrace();
        }
        return legalSimpleMoves;
    }

    private List<SimpleMove> getAllSimpleMovesKing(int row, int column) {
        List<SimpleMove> legalSimpleMoves = new ArrayList<>();
        if (column > 0 && row > 0 && this.board[row - 1][column - 1].getAlliance() == null)
            legalSimpleMoves.add(new SimpleMove(row, column, row - 1, column - 1));
        if (column < 7 && row > 0 && this.board[row - 1][column + 1].getAlliance() == null)
            legalSimpleMoves.add(new SimpleMove(row, column, row - 1, column + 1));
        if (column > 0 && row < 7 && this.board[row + 1][column - 1].getAlliance() == null)
            legalSimpleMoves.add(new SimpleMove(row, column, row + 1, column - 1));
        if (column < 7 && row < 7 && this.board[row + 1][column + 1].getAlliance() == null)
            legalSimpleMoves.add(new SimpleMove(row, column, row + 1, column + 1));
        return legalSimpleMoves;
    }

    public void makeMove(Move move) {
        if (move instanceof JumpMove)
            makeJumpMove((JumpMove) move);
        else
            makeSimpleMove((SimpleMove) move);
    }

    public void upgradeToKing(Alliance toMove) {
        int lastRow = (toMove == Alliance.WHITE) ? 0 : 7;
        for (int j = 0; j < 8; j++) {
            if ((lastRow + j) % 2 != 0)
                if (board[lastRow][j].getAlliance() == toMove)
                    board[lastRow][j] = (toMove == Alliance.WHITE) ? Piece.WHITE_KING : Piece.BLACK_KING;
        }
    }

    public void makeJumpMove(JumpMove jumpMove) {
        for (SimpleMove simpleMove : jumpMove.jumps) {
            int initialRow = simpleMove.initialRowPosition;
            int initialColumn = simpleMove.initialColumnPosition;
            int finalRow = simpleMove.finalRowPosition;
            int finalColumn = simpleMove.finalColumnPosition;
            int midRow = (initialRow + finalRow) / 2;
            int midColumn = (initialColumn + finalColumn) / 2;

            board[midRow][midColumn] = Piece.EMPTY;
            board[finalRow][finalColumn] = board[initialRow][initialColumn];
            board[initialRow][initialColumn] = Piece.EMPTY;
        }
        int finalColumn = jumpMove.jumps.get(jumpMove.jumps.size()-1).finalColumnPosition;
        int finalRow = jumpMove.jumps.get(jumpMove.jumps.size()-1).finalRowPosition;

        if(finalRow == 0 && board[finalRow][finalColumn] == Piece.WHITE_PAWN )
            board[finalRow][finalColumn] = Piece.WHITE_KING;
        if(finalRow == 7 && board[finalRow][finalColumn] == Piece.BLACK_PAWN )
            board[finalRow][finalColumn] = Piece.BLACK_KING;
    }

    public void makeSimpleMove(SimpleMove simpleMove) {
        board[simpleMove.finalRowPosition][simpleMove.finalColumnPosition] = board[simpleMove.initialRowPosition][simpleMove.initialColumnPosition];
        board[simpleMove.initialRowPosition][simpleMove.initialColumnPosition] = Piece.EMPTY;

        int finalRow = simpleMove.finalRowPosition;
        int finalColumn = simpleMove.finalColumnPosition;
        if(finalRow == 0 && board[finalRow][finalColumn] == Piece.WHITE_PAWN )
            board[finalRow][finalColumn] = Piece.WHITE_KING;
        if(finalRow == 7 && board[finalRow][finalColumn] == Piece.BLACK_PAWN )
            board[finalRow][finalColumn] = Piece.BLACK_KING;
    }

    public void showBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0)
                    System.out.print(board[i][j]);
                else
                    System.out.print("-");
            }
            System.out.println();
        }
    }

    public void initializeNewBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 3)
                        board[i][j] = Piece.BLACK_PAWN;
                    else if (i > 4)
                        board[i][j] = Piece.WHITE_PAWN;
                    else
                        board[i][j] = Piece.EMPTY;
                }
            }
        }
    }
}
