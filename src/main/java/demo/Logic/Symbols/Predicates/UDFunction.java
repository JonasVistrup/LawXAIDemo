package demo.Logic.Symbols.Predicates;

import demo.Logic.High.Arguments;
import demo.Logic.High.Substitution;

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
