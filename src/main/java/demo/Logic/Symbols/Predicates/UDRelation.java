package demo.Logic.Symbols.Predicates;


import demo.Logic.High.Arguments;

import java.util.ArrayList;

/**
 * Predicate that represents a relation
 */
public abstract class UDRelation extends PredicateUD {
    public UDRelation(String id, int nArgs) {
        super(id, nArgs);
        ArrayList<String> explanation = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();
        explanation.add("");
        explanation.add(id);
        explanation.add("");
        pos.add(0);
        pos.add(1);
        super.addExplanation(explanation,pos);
    }

    @Override
    public boolean runnable(Arguments args){
        return args.nVariables()==0;
    }

    public abstract boolean test(Arguments args);
}
