package com.example.demo.Logic.High;

import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Term;
import com.example.demo.Logic.Symbols.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Immutable
public class Arguments implements Iterable<Term>, Comparable<Arguments>{

    private int nVariables = 0;
    private final Term[] args;

    public Arguments(List<Term> terms){
        this.args = new Term[terms.size()];
        for(int i = 0; i<terms.size(); i++){
            Term t = terms.get(i);
            if(t instanceof Variable) nVariables++;
            args[i] = t;
        }
    }
    private Arguments(Term[] old, Substitution substitution){
        this.args = new Term[old.length];
        for(int i = 0; i<old.length; i++){
            this.args[i] = old[i].applySub(substitution);
            if(this.args[i] instanceof Variable) nVariables++;
        }
    }
    public Arguments(Term ... terms){
        this.args = terms.clone();
        for(Term t: this.args){
            if(t instanceof Variable) nVariables++;
        }
    }
    public Arguments(Arguments toCopy, int version){
        nVariables = toCopy.nVariables;
        this.args = new Term[toCopy.args.length];
        for(int i = 0; i < this.args.length; i++){
            this.args[i] = toCopy.args[i].getVariant(version);
        }
    }

    public Arguments applySub(Substitution substitution){
        return new Arguments(this.args, substitution);
    }

    public Term get(int i){
        return args[i];
    }

    public int nVariables(){ return nVariables;}
    public int nConstants(){ return args.length - nVariables;}
    public int size(){ return args.length;}
    public boolean isEmpty(){ return args.length==0;}
    @Override
    public Iterator<Term> iterator() {
        return new Iterator<>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i<args.length;
            }

            @Override
            public Term next() {
                return args[i++];
            }
        };
    }

    @Override
    public int compareTo(Arguments o) {
        if(this.args.length != o.args.length){
            return this.args.length - o.args.length;
        }
        for(int i = 0; i<this.args.length; i++){
            Term one = this.args[i];
            Term two = o.args[i];

            if(one instanceof Constant){
                if(two instanceof Constant){
                    if(one != two){
                        return one.toString().compareTo(two.toString());
                    }
                }else{
                    return -1;  // Constants are smaller than variables.
                }
            }else{
                if(two instanceof Constant) {
                    return 1; // Variables are larger than constants.
                }
                // Variables are equal.
            }
        }
        return 0;
    }

    public boolean containTerm(Term term) {
        for(Term t: args){
            if(term == t) return true;
        }
        return false;
    }

    public Arguments add(Arguments other){
        List<Term> terms = new ArrayList<>();
        terms.addAll(Arrays.asList(this.args));
        terms.addAll(Arrays.asList(other.args));
        return new Arguments(terms);
    }

    public Arguments add(Term other){
        List<Term> terms = new ArrayList<>();
        terms.addAll(Arrays.asList(this.args));
        terms.add(other);
        return new Arguments(terms);
    }

    @Override
    public String toString() {
        if(args.length==0) return "";
        StringBuilder b = new StringBuilder();
        for(Term t: args){
            b.append(t).append(",");
        }
        b.deleteCharAt(b.length()-1);
        return b.toString();
    }
}
