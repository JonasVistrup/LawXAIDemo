package com.example.demo.Logic.Symbols.Predicates;


import com.example.demo.Logic.High.Arguments;

import java.util.ArrayList;

public abstract class Predicate {

    /**
     * String representation of predicate.
     */
    private final String id;

    /**
     * Number of non-temporal arguments of the predicate.
     */
    private final int nArgs;

    ArrayList<String> explanation;
    ArrayList<Integer> termPos;

    public Predicate(String id, int nArgs){
        this.id = id;
        this.nArgs = nArgs;
    }


    public boolean IDB() {
        return false;
    }

    public int nArgs(){ return nArgs; }

    public void addExplanation(ArrayList<String> explanation, ArrayList<Integer> termPos){
        if(termPos.size()+1 != explanation.size()){
            throw new IllegalArgumentException("Explanations should contain "+(termPos.size()+1)+" strings.");
        }
        this.explanation = explanation;
        this.termPos = termPos;
    }
    public String explain(Arguments args){
        if(nArgs != args.size() && nArgs+1 != args.size()) throw new IllegalArgumentException("Must be given "+nArgs+" terms or "+nArgs+" terms and a temporal term.");

        StringBuilder res = new StringBuilder(explanation.get(0));
        for(int i = 0; i<termPos.size(); i++){
            res.append(args.get(termPos.get(i)));
            res.append(explanation.get(i+1));
        }
        if(nArgs+1 == args.size()){
            res.append(" på tidspunkt ").append(args.get(nArgs));
        }
        return res.toString();
    }

    public void makeIDB(){
        throw new IllegalStateException("This method has to be overriden to be used");
    }

    public String toString() {
        return id;
    }

    public abstract String toString(Arguments args);
}
