package com.example.demo.Logic.High;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A logical clause.
 */
public class Clause {
    /**
     * Head of the clause.
     */
    public Atom head;
    /**
     * Body of the clause.
     */
    public AtomList body;


    public ArrayList<String> reasons;

    /**
     * Map of different variants of this clause.
     */
    public Map<Integer, Clause> instances;

    /**
     * @param head head of the clause
     * @param body body of the clause
     */
    public Clause(Atom head, AtomList body, ArrayList<String> reasons){
        this.head = head;
        this.body = body;
        this.reasons = reasons;
        this.instances = new HashMap<>();
    }

    /**
     * Constructs a variant of a clause.
     * @param clause original clause for which this is a variant
     * @param version version of the variant
     */
    public Clause(Clause clause, int version){
        this.head = clause.head.getInstance(version);
        ArrayList<Atom> body = new ArrayList<>();
        for(Atom a: clause.body){
            body.add(a.getInstance(version));
        }
        this.body = new AtomList(body);
        this.reasons = clause.reasons;
    }

    /**
     * Returns a variant of this clause.
     * @param version which variant that should be returned
     * @return a variant of this atom
     */
    public Clause getInstance(int version){
        if(this.instances.containsKey(version)){
            return this.instances.get(version);
        }else{
            Clause instance = new Clause(this, version);
            this.instances.put(version, instance);
            return instance;
        }
    }



    /** Returns a string representation of this clause in the form of HEAD{@literal <}-BODY.
     * @return string representation of clause
     */
    @Override
    public String toString() {
        if (body.isEmpty()) {
            return head.toString() + "<-";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(head.toString());
        builder.append("<-");
        for (Atom atom : body) {
            builder.append(atom.toString());
            builder.append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public String explain(){
        if(body.isEmpty()){
            return head.explain()+".";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(head.explain());
        builder.append(" fordi ");
        for(int i = 0; i<body.size()-1; i++){
            builder.append(body.get(i));
            builder.append(", ");
        }
        if(body.size() > 1){
            builder.deleteCharAt(builder.length()-2);
            builder.append("og ");
        }
        builder.append(body.get(body.size()-1));

        return builder.toString();
    }

    public Clause applySub(Substitution sub){
        return new Clause(head.applySub(sub), body.applySub(sub), this.reasons);
    }
}
