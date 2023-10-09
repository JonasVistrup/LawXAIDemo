package com.example.demo.Logic.Symbols.Predicates;

import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.High.Substitution;

/**
 * Predicate that represents a bijective function
 */
public abstract class UDFunction extends PredicateUD {
    public UDFunction(String id, int nArgs) {
        super(id, nArgs);
    }

    @Override
    public boolean runnable(Arguments args){
        return args.nVariables()<=1;
    }

    public abstract Substitution solve(Arguments args);
}
