package com.example.demo.Logic.Symbols.Predicates;

import com.example.demo.Logic.High.Arguments;

public abstract class PredicateUD extends Predicate {
    public PredicateUD(String id, int nArgs) {
        super(id, nArgs);
    }

    public abstract boolean runnable(Arguments args);

}
