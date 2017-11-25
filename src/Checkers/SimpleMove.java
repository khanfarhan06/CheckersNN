package Checkers;

public class SimpleMove implements Move{
    final int initialRowPosition;
    final int finalRowPosition;
    final int initialColumnPosition;
    final int finalColumnPosition;


    public SimpleMove(int initialRowPosition, int initialColumnPosition, int finalRowPosition, int finalColumnPosition) {
        this.initialRowPosition = initialRowPosition;
        this.finalRowPosition = finalRowPosition;
        this.initialColumnPosition = initialColumnPosition;
        this.finalColumnPosition = finalColumnPosition;
    }
}

