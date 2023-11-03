package com.example.demo.SLD;


import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;

import java.util.Iterator;

// Immutable
public class Goal implements Iterable<Atom> {

    private final AtomList atoms;

    private final Trace trace;

    private final Substitution sub;

    public Goal(AtomList list){
        this.atoms = list;
        this.sub = new Substitution();
        this.trace = new Trace();
    }

    private Goal(Goal goal, AtomList toAdd, Atom toRemove){
        this.atoms = goal.atoms.addAndRemove(toAdd,toRemove);
        this.sub = goal.sub;
        this.trace = goal.trace;
    }

    private Goal(Goal goal, Substitution substitution){
        this.atoms = goal.atoms.applySub(substitution);
        this.sub = goal.sub.add(substitution);
        this.trace = goal.trace;
    }

    private Goal(Goal goal, Clause clause, int atomIndex, Substitution substitution){
        this.atoms = goal.atoms.applyClause(clause, atomIndex, substitution);
        this.sub = goal.sub.add(substitution);
        this.trace = goal.trace.applyClause(clause, atomIndex);
    }

    private Goal(Goal goal, int[] removedPositions, Substitution substitution) {
        this.atoms = goal.atoms.removeAndSub(removedPositions, substitution);
        this.sub = goal.sub.add(substitution);
        this.trace = goal.trace.removeUDPs(removedPositions);
    }


    public Substitution sub(){
        return sub;
    }
    public AtomList atoms(){ return atoms;}
    public Trace trace(){ return trace;}


    public Goal addAndRemove(AtomList toAdd, Atom toRemove){
        return new Goal(this, toAdd, toRemove);
    }

    public Goal applySub(Substitution substitution){
        return new Goal(this, substitution);
    }
    public Goal applyClause(Clause clause, int atomIndex, Substitution unifier){
        return new Goal(this, clause, atomIndex, unifier);
    }

    public Goal runAndRemoveGroundUDPs() {
        int[] runnableUDPsPositions = this.atoms.getRunnableUDPsPositions();
        if(runnableUDPsPositions.length == 0) return this;

        Substitution solution = this.atoms.runUDPs(runnableUDPsPositions);
        if(solution == null) return null;

        return new Goal(this, runnableUDPsPositions, solution).runAndRemoveGroundUDPs();
    }

    @Override
    public Iterator<Atom> iterator() {
        return atoms.iterator();
    }

    public boolean isEmpty() {
        return this.atoms.isEmpty();
    }

    public int size() {
        return this.atoms.size();
    }

    public Atom get(int i) {
        return atoms.get(i);
    }

    public int nStandardAtoms() {
        return atoms.nStandardAtoms();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Goal)) return false;
        Goal o = (Goal) obj;
        return this.atoms.equals(o.atoms);
    }
}
