package com.example.demo.Logic.High;


import com.example.demo.Logic.Symbols.Predicates.*;
import com.example.demo.Logic.Symbols.Term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A logical atom.
 */
public class Atom implements Comparable<Atom>{

    public Atom(Atom atom, Substitution substitution) {
        this.type = atom.type;
        this.predicate = atom.predicate;
        this.instances = new HashMap<>();
        this.args = atom.args.applySub(substitution);
    }

    public enum Type{
        STANDARD,
        FUNCTION,
        RELATION
    }
    private final Type type;
    /**
     * Logic.Predicate of the atom.
     */
    private final Predicate predicate;
    /**
     * Non-temporal arguments of the atom.
     */
    public final Arguments args;

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
        if(predicate.nArgs() != args.size() && predicate.nArgs()+1 != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        if(predicate instanceof PredicateStd) {
            this.type = Type.STANDARD;
        }else if (predicate instanceof UDFunction) {
            this.type = Type.FUNCTION;
        }else {
            this.type = Type.RELATION;
        }
        this.predicate = predicate;
        this.args = new Arguments(args);
        this.instances = new HashMap<>();
    }

    public Atom(Predicate predicate, Arguments args){
        if(predicate.nArgs() != args.size() && predicate.nArgs()+1 != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        if((predicate instanceof UDNegation && ((UDNegation) predicate).predicate instanceof UDNegation)) throw new IllegalArgumentException("Illegal use of double negation");
        if(predicate instanceof PredicateStd){//|| (predicate instanceof UDNegation && ((UDNegation) predicate).predicate instanceof PredicateStd)) {
            this.type = Type.STANDARD;
        }else if (predicate instanceof UDFunction) {
            if(predicate.nArgs() != args.size()) throw new IllegalArgumentException("Number of arguments does not match function predicate");
            this.type = Type.FUNCTION;
        }else {
            if(predicate.nArgs() != args.size() && !(predicate instanceof UDNegation && ((UDNegation) predicate).predicate instanceof PredicateStd && predicate.nArgs()+1 == args.size())){
                throw new IllegalArgumentException("Number of arguments does not match relation predicate " +predicate);
            }
            this.type = Type.RELATION;
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
    public Atom(UDFunction predicate, List<Term> args){
        if(predicate.nArgs() != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        this.type = Type.FUNCTION;
        this.predicate = predicate;
        this.args = new Arguments(args);
        this.instances = new HashMap<>();
    }

    /**
     * Constructs a standard atom.
     * @param predicate predicate of atom.
     * @param args non-temporal arguments of atom.
     */
    public Atom(UDRelation predicate, List<Term> args){
        if(predicate.nArgs() != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        this.type = Type.RELATION;
        this.predicate = predicate;
        this.args = new Arguments(args);
        this.instances = new HashMap<>();
    }

    /**
     * Constructs a user-defined atom.
     * @param predicate predicate of atom.
     * @param args non-temporal arguments of atom.
     */
    public Atom(PredicateStd predicate, List<Term> args){
        if(predicate.nArgs() != args.size() && predicate.nArgs()+1 != args.size()) throw new IllegalArgumentException("Number of arguments does not match predicate");
        this.type = Type.STANDARD;
        this.predicate = predicate;
        this.args = new Arguments(args);
        this.instances = new HashMap<>();
    }

    /**
     * Constructs a variant of an atom.
     * @param parent original atom for which this is a variant
     * @param version version of the variant
     */
    public Atom(Atom parent, int version){
        this.type = parent.type;
        this.predicate = parent.predicate;
        this.args = new Arguments(parent.args, version);
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
        return new Atom(this, substitution);
    }

    public boolean containsTerm(Term term){
        return this.args.containTerm(term);
    }


    /**
     * Returns a String representation of this atom.
     * @return representation of this atom.
     */
    @Override
    public String toString() {
        return predicate.toString(args);
    }

    public Type type(){
        return type;
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

        return this.args.compareTo(o.args);
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
        if(this.toString().equals("BrudtLoven(tiltalte,§4_stk.1,1210)")){
            System.out.println("in here");
        }
        return this.compareTo((Atom) obj) == 0;
    }

    public boolean hasTemporalTerm(){
        return this.predicate.nArgs() + 1 == this.args.size();
    }


    public String explain(){
        return this.predicate.explain(this.args);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
