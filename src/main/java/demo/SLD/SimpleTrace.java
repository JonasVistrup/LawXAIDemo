package demo.SLD;

import demo.Logic.High.Clause;
import demo.Logic.High.Substitution;

public class SimpleTrace {
    public Goal goal;
    public SimpleTrace child;
    public Substitution substitution;

    public SimpleTrace(Goal goal, SimpleTrace child, Substitution substitution){
        this.goal = goal;
        this.child = child;
        this.substitution = substitution;
    }

    public void fillDictonary(){

    }
}
