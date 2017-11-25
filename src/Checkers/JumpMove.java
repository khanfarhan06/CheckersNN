package Checkers;

import java.util.ArrayList;
import java.util.List;

public class JumpMove implements Move{
    public List<SimpleMove> jumps = new ArrayList<>();

    public JumpMove clone(){
        JumpMove copy = new JumpMove();
        for(SimpleMove simpleMove: jumps)
            copy.jumps.add(simpleMove);
        return copy;
    }
}
