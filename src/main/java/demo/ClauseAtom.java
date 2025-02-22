package demo;

import demo.Logic.High.Atom;
import demo.Logic.High.Clause;

public class ClauseAtom{
    public final Clause clause;
    public final Atom atom;

    public ClauseAtom(Clause clause){
        this.clause = clause;
        this.atom = null;
    }
    public ClauseAtom(Atom atom){
        this.clause = null;
        this.atom = atom;
    }

}
