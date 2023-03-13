package com.example.demo.Logic.Symbols;


import java.util.ArrayList;
import java.util.List;

/**
 * A logical predicate.
 */
public class PredicateStd implements Predicate {


    /**
     * String representation of predicate.
     */
    private final String id;
    /**
     * Number of non-temporal arguments of the predicate.
     */
    private final int nArgs;

    /**
     * Indicator for whether this predicate is an intentional (true) or extensional (false).
     */
    private boolean IDB;

    private ArrayList<String> explanation;
    private ArrayList<Integer> termPos;

    /**
     * Constructs a predicate with signature id and nArgs number of non-temporal arguments.
     * @param id    String representation of predicate
     * @param nArgs number of non-temporal arguments
     */
    public PredicateStd(String id, int nArgs){

        this.id = id;
        this.nArgs = nArgs;
        this.IDB = false;
    }

    public void addExplanation(ArrayList<String> explanation, ArrayList<Integer> termPos){
        if(termPos.size()+1 != explanation.size()){
            throw new IllegalArgumentException("Explanations should contain "+(termPos.size()+1)+" strings.");
        }
        this.explanation = explanation;
        this.termPos = termPos;
    }

    public int nArgs(){
        return this.nArgs;
    }

    @Override
    public String explain(List<Term> terms) {
        if(explanation == null){
            throw new IllegalStateException("Predicate "+this.id+" has no explanation.");
        }
        if(nArgs != terms.size()){
            throw new IllegalArgumentException("Must be given "+nArgs+" terms.");
        }
        StringBuilder res = new StringBuilder(explanation.get(0));
        for(int i = 0; i<termPos.size(); i++){
            res.append(terms.get(termPos.get(i)));
            res.append(explanation.get(i+1));
        }
        return res.toString();
    }

    public boolean IDB(){
        return this.IDB;
    }

    public void makeIDB(){
        this.IDB = true;
    }

    /**
     * Returns the String representation of this predicate.
     * @return id
     */
    @Override
    public String toString() {
        return id;
    }

}
