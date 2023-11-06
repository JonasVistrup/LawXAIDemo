package com.example.demo.Logic.High;


import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Term;
import com.example.demo.Logic.Symbols.Variable;

import java.util.HashMap;
import java.util.Map;


/**
 * A set of substitutions. Immutable.
 */
public class Substitution {
    private final HashMap<Variable, Term> subs;
    /**
     * Constructs an empty set of substitutions.
     */
    public Substitution(){
        this.subs = new HashMap<>();
    }

    /**
     * Constructs a set of substitutions with a single sub.
     * @param from variable which to substitute.
     * @param to term to substitute the variable into.
     */
    public Substitution(Variable from, Term to){
        this.subs = new HashMap<>();
        this.subs.put(from, to);
    }

    /**
     * Creates the composition of two substitutions.
     * @param other the other substitutions.
     * @return the composition of this and answer.
     */
    public Substitution add(Substitution other){
        Substitution resultingSubs = new Substitution();
        for(Map.Entry<Variable, Term> sub: subs.entrySet()){
            if(sub.getValue() instanceof Variable){
                Term term = other.getSubstitution((Variable) sub.getValue());
                if(term != null){
                    resultingSubs.subs.put(sub.getKey(), term);
                }else{
                    resultingSubs.subs.put(sub.getKey(), sub.getValue());
                }
            }else{
                resultingSubs.subs.put(sub.getKey(), sub.getValue());
            }
        }

        for(Map.Entry<Variable, Term> sub: other.subs.entrySet()){
            if(!resultingSubs.hasSubstitution(sub.getKey())){
                resultingSubs.subs.put(sub.getKey(), sub.getValue());
            }
        }

        return resultingSubs;
    }


    /**
     * Returns the term that a variable is substituted into. Null if the variable is not substituted.
     * @param var variable for which its substitution is searched for.
     * @return the term var is substituted into or null if no substitution of var is in this list of substitutions.
     */
    public Term getSubstitution(Variable var){
        return subs.get(var);
    }

    public Boolean hasSubstitution(Variable var){
        return subs.containsKey(var);
    }


    /** Returns a string representation of the substitution.
     * @return string representation of the substitution.
     */
    @Override
    public String toString() {
        AtomList list = null;
        return toString(list);
    }


    /**Returns a string representation of the all substitutions relevant to the atom.
     * @param relevantQuery atom for which the representation is relevant for.
     * @return string representation.
     */
    public String toString(Atom relevantQuery) {
        return toString(new AtomList(relevantQuery));
    }

    /**
     * Returns a string representation of the all substitutions relevant to an atom in a list of atoms.
     * @param relevantQuery list of atom for which the representation is relevant for.
     * @return string representation.
     */
    public String toString(AtomList relevantQuery) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        for(Map.Entry<Variable, Term> sub: subs.entrySet()){
            if(!relevantSub(sub.getKey(), relevantQuery)) continue;
            builder.append("(");
            builder.append(sub.getKey().toString()).append("/").append(sub.getValue().toString());
            builder.append(")");
            builder.append(",");
        }
        if(builder.length()>1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("}");
        return builder.toString();
    }


    /**
     * Returns whether the sub would affect any of the atoms in the query.
     * @param from the variable
     * @param relevantQuery the list of atoms
     * @return true iff relevantQuery contains the variable which is substituted in s.
     */
    private static boolean relevantSub(Variable from, AtomList relevantQuery) {
        if(relevantQuery == null) return true;
        return relevantQuery.containsTerm(from);
    }

    public Substitution relevantSubsetSubstitution(AtomList relevantQuery){
        Substitution result = new Substitution();
        for(Atom a: relevantQuery){
            for(Term from: a.args){
                if(from instanceof Variable && result.getSubstitution((Variable) from) == null){
                    Term to = getSubstitution((Variable) from);
                    if(to != null){
                        result = result.add(new Substitution((Variable) from,to));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Substitution)) return false;
        Substitution o = (Substitution) obj;
        for(Variable v: this.subs.keySet()){
            Term result1 = this.getSubstitution(v);
            Term result2 = o.getSubstitution(v);
            if(result2 == null) return false;
            if(result1 instanceof Variable != result2 instanceof Variable) return false;
            if(result1 instanceof Constant && result1 != result2) return false;
        }

        for(Variable v: o.subs.keySet()){
            Term result1 = this.getSubstitution(v);
            Term result2 = o.getSubstitution(v);
            if(result1 == null) return false;
            if(result1 instanceof Variable != result2 instanceof Variable) return false;
            if(result1 instanceof Constant && result1 != result2) return false;
        }
        return true;
    }
}
