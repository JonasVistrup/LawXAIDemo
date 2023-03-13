package com.example.demo.Logic.High;


import com.example.demo.Logic.Symbols.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A logical atom.
 */
public class Atom implements Comparable<Atom>{


    private final boolean userDefined;
    /**
     * com.example.demo.Logic.Predicate of the atom.
     */
    private final Predicate predicate;
    /**
     * Non-temporal arguments of the atom.
     */
    public final List<Term> args;

    /**
     * Map of different variants of this atom.
     */
    private final Map<Integer, Atom> instances;

    /**
     * Constructs an atom.
     * @param predicate predicate of atom.
     * @param args non-temporal arguments of atom.
     */

    public Atom(Predicate predicate, List<Term> args){
        if(predicate.nArgs() != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        if(predicate instanceof PredicateStd) {
            this.userDefined = false;
        }
        else if (predicate instanceof  PredicateUD){
            this.userDefined = true;
        }else{
            throw new IllegalArgumentException("Predicate must either be PredicateStd or PredicateUD.");
        }
        this.predicate = predicate;
        this.args = args;
        this.instances = new HashMap<>();
    }


    /**
     * Constructs a standard atom.
     * @param predicate predicate of atom.
     * @param args non-temporal arguments of atom.
     */
    public Atom(PredicateUD predicate, List<Term> args){
        if(predicate.nArgs() != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        this.userDefined = true;
        this.predicate = predicate;
        this.args = args;
        this.instances = new HashMap<>();
    }

    /**
     * Constructs a user-defined atom.
     * @param predicate predicate of atom.
     * @param args non-temporal arguments of atom.
     */
    public Atom(PredicateStd predicate, List<Term> args){
        if(predicate.nArgs() != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        this.userDefined = false;
        this.predicate = predicate;
        this.args = args;
        this.instances = new HashMap<>();
    }

    /**
     * Constructs a variant of an atom.
     * @param parent original atom for which this is a variant
     * @param version version of the variant
     */
    public Atom(Atom parent, int version){
        this.userDefined = parent.userDefined;
        this.predicate = parent.predicate;
        this.args = new ArrayList<>();
        for(Term t: parent.args){
            this.args.add(t.getVariant(version));
        }
        this.instances = new HashMap<>();
    }


    /**
     * Returns a variant of this atom.
     * @param version which variant that should be returned
     * @return a variant of this atom
     */
    public Atom getInstance(int version){
        if(this.instances.containsKey(version)){
            return this.instances.get(version);
        }else{
            Atom instance = new Atom(this, version);
            this.instances.put(version, instance);
            return instance;
        }
    }

    /**
     * Applies a substitution on every term in the atom, returning the resulting atom.
     * @param substitution applied substitution
     * @return new atom with substituted terms
     */
    public Atom applySub(Substitution substitution){
        List<Term> new_terms = new ArrayList<>();
        for(Term t: args){
            new_terms.add(t.applySub(substitution));
        }
        return new Atom(this.predicate, new_terms);
    }

    public boolean containsTerm(Term term){
        for(Term t: args){
            if(term == t) return true;
        }
        return false;
    }


    /**
     * Returns a String representation of this atom.
     * @return representation of this atom.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(predicate.toString());
        builder.append('(');
        for(Term t: args){
            builder.append(t.toString());
            builder.append(',');
        }
        if(!args.isEmpty()) builder.deleteCharAt(builder.length()-1);

        builder.append(')');
        return builder.toString();
    }

    public boolean userDefined(){
        return userDefined;
    }

    public boolean ground(){
        for(Term t: args){
            if(t instanceof Variable) return false;
        }
        return true;
    }

    public Predicate predicate(){
        return this.predicate;
    }

    /**
     * Compares the temporal aspects of atom o and this atom. See Temporal.compareTo for details.
     * @param o the object to be compared
     * @return a negative, 0 or a positive number
     */
    @Override
    public int compareTo(Atom o) {
        if(this.predicate != o.predicate){
            return this.predicate.toString().compareTo(o.predicate.toString());
        }

        for(int i = 0; i<this.args.size(); i++){
            Term one = this.args.get(i);
            Term two = o.args.get(i);

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

    /**
     * @param obj other object.
     * @return true iff this and obj have the same predicate and same arguments.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(!(obj instanceof Atom)){
            return false;
        }
        Atom other = (Atom) obj;
        return this.compareTo(other) == 0;
    }

    public List<Constant> getConstantsIfGround() {
        List<Constant> constants = new ArrayList<>();
        for(Term t: args){
            if(t instanceof Constant){
                constants.add((Constant) t);
            }else{
                return null;
            }
        }
        return constants;
    }


    public String explain(){
        return this.predicate.explain(this.args);
    }
}
