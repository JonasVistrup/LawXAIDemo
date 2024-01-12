package com.example.demo.Logic.Symbols.Predicates;


import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.Symbols.Term;

/**
 * A logical predicate.
 */
public class PredicateStd extends Predicate {

    /**
     * Indicator for whether this predicate is an intentional (true) or extensional (false).
     */
    private boolean IDB;

    /**
     * Constructs a predicate with signature id and nArgs number of non-temporal arguments.
     * @param id    String representation of predicate
     * @param nArgs number of non-temporal arguments
     */
    public PredicateStd(String id, int nArgs){
        super(id, nArgs);
        this.IDB = false;
    }

    @Override
    public boolean IDB(){
        return this.IDB;
    }
    @Override
    public void makeIDB(){
        this.IDB = true;
    }

    /**
     * Returns the String representation of this predicate.
     */
    @Override
    public String toString(Arguments args) {
        assert args.size() == nArgs() || args.size() == nArgs() + 1;
        if(nArgs() == 0) return toString();

        StringBuilder builder = new StringBuilder(toString());
        builder.append("(");
        for(Term t: args){
            builder.append(t).append(",");
        }
        builder.deleteCharAt(builder.length()-1).append(")");
        return builder.toString();
    }

}
