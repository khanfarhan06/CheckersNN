package Checkers;

public enum Piece {
    BLACK_PAWN{
        public Alliance getAlliance(){
            return Alliance.BLACK;
        }
        @Override
        public String toString() {
            return "b";
        }
    },
    WHITE_PAWN{
        public Alliance getAlliance(){
            return Alliance.WHITE;
        }
        @Override
        public String toString() {
            return "w";
        }
    },
    BLACK_KING{
        public Alliance getAlliance(){
            return Alliance.BLACK;
        }
        @Override
        public String toString() {
            return "B";
        }
    },
    WHITE_KING{
        public Alliance getAlliance(){
            return Alliance.WHITE;
        }
        @Override
        public String toString() {
            return "W";
        }
    },
    EMPTY{
        @Override
        public Alliance getAlliance() {
            return null;
        }
        @Override
        public String toString() {
            return "X";
        }
    };
    public abstract Alliance getAlliance();
}
