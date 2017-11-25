package Payers;

import Checkers.Alliance;
import Checkers.CheckersBoard;
import Checkers.Move;

public abstract class Player {
    final Alliance alliance;

    Player(Alliance alliance) {
        this.alliance = alliance;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    abstract public Move bestMove(CheckersBoard checkersBoard);

}
