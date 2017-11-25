public class Move {
    int initialRowPosition;
    int finalRowPosition;
    int initialColumnPosition;
    int finalColumnPosition;


    public Move(int initialRowPosition,  int initialColumnPosition, int finalRowPosition, int finalColumnPosition) {
        this.initialRowPosition = initialRowPosition;
        this.finalRowPosition = finalRowPosition;
        this.initialColumnPosition = initialColumnPosition;
        this.finalColumnPosition = finalColumnPosition;
    }
}

