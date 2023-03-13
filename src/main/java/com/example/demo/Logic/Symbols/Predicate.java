package com.example.demo.Logic.Symbols;

import java.util.ArrayList;
import java.util.List;

public interface Predicate {
    public boolean IDB();
    public int nArgs();

    public void addExplanation(ArrayList<String> explanation, ArrayList<Integer> termPos);
    public String explain(List<Term> terms);

    public void makeIDB();
}
