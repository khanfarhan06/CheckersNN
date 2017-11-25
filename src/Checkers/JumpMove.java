package Checkers;

import java.util.ArrayList;
import java.util.List;

public class JumpMove implements Move,Cloneable{
    public final List<SimpleMove> jumps = new ArrayList<>();

    public JumpMove clone(){
        JumpMove copy = new JumpMove();
        copy.jumps.addAll(jumps);
        return copy;
    }
}
