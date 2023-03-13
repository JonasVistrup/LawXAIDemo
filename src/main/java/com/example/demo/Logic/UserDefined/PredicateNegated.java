package com.example.demo.Logic.UserDefined;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.Program;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.PredicateUD;
import com.example.demo.Logic.Symbols.Term;
import com.example.demo.SLD.Goal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.SLD.SLDResolution.inOrderTraversalTest;

public class PredicateNegated implements PredicateUD {

    private String id;
    private int nArgs;
    private Program program;

    private ArrayList<String> explanation;

    private HashMap<List<Constant>, Boolean> tested;


    public PredicateNegated(String id, int nArgs, Program program, ArrayList<String> explanation){
        this.id = id;
        this.nArgs = nArgs;
        this.program = program;
        this.explanation = explanation;
        this.tested = new HashMap<>();
    }

    @Override
    public boolean IDB() {
        return false;
    }

    @Override
    public int nArgs() {
        return this.nArgs;
    }

    @Override
    public void addExplanation(ArrayList<String> explanation, ArrayList<Integer> termPos) {
        //TODO
    }

    @Override
    public String explain(List<Term> terms) {
        if(nArgs == terms.size()) throw new IllegalArgumentException("Must be given "+nArgs+" terms.");
        StringBuilder res = new StringBuilder(explanation.get(0));
        for(int i = 0; i<terms.size(); i++){
            res.append(terms.get(i));
            res.append(explanation.get(i+1));
        }
        return res.toString();
    }

    @Override
    public boolean run(List<Constant> constantList) {
        if(tested.containsKey(constantList)){
            return tested.get(constantList);
        }
        tested.put(constantList, false); // In case of loops

        List<Term> terms = constantList.stream().map(x -> (Term) x).collect(Collectors.toList());
        if(!inOrderTraversalTest(new Goal(new Atom(this, terms), new Substitution()), program, 0)){
            tested.put(constantList, true);
            return true;
        }
        return false;
    }

    @Override
    public String toString(List<Term> terms) {
        if(terms.isEmpty()){
            return "-"+id;
        }
        StringBuilder builder = new StringBuilder("-"+id);
        builder.append("(");
        for(Term t: terms){
            builder.append(t);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(")");
        return builder.toString();
    }

    public void makeIDB(){
        return;
    }


}
