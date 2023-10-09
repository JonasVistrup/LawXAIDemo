package com.example.demo.Logic.Symbols.Predicates;


import com.example.demo.Logic.High.Arguments;

/**
 * Predicate that represents a relation
 */
public abstract class UDRelation extends PredicateUD {
    public UDRelation(String id, int nArgs) {
        super(id, nArgs);
    }

    @Override
    public boolean runnable(Arguments args){
        return args.nVariables()==0;
    }

    public abstract boolean test(Arguments args);
}
