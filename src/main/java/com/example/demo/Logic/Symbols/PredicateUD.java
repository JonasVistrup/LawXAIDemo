package com.example.demo.Logic.Symbols;

import java.util.List;

public interface PredicateUD extends Predicate{
    public boolean run(List<Constant> constantList);

    public String toString(List<Term> terms);

}
