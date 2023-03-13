package com.example.demo.SLD;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.PredicateUD;

import java.util.ArrayList;
import java.util.List;

public class Goal extends AtomList {

    public Trace trace;

    private Substitution sub;

    public Goal(Atom atom, Substitution substitution){
        super(atom);
        this.sub = substitution;
        this.trace = new Trace();
    }
    public Goal(Goal goal){
        super(goal.atoms);
        this.sub = goal.sub;
        this.trace = goal.trace;
    }

    public Goal(ArrayList<Atom> atoms, Substitution substitution){
        super(atoms);
        this.sub = substitution;
        this.trace = new Trace();
    }

    public Goal(ArrayList<Atom> atoms, Substitution substitution, Trace trace){
        super(atoms);
        this.sub = substitution;
        this.trace = trace;
    }

    public Goal(AtomList list, Substitution substitution){
        super(list);
        this.sub = substitution;
        this.trace = new Trace();
    }


    public Substitution sub(){
        return sub;
    }

    @Override
    public Goal addAndRemove(AtomList toAdd, Atom toRemove){
        Goal copy = new Goal(this);
        copy.atoms.remove(toRemove);
        copy.atoms.addAll(toAdd.atoms());
        return copy;
    }

    @Override
    public Goal applySub(Substitution substitution){
        Goal goal = new Goal(super.applySub(substitution), sub.add(substitution));
        goal.trace = this.trace;
        return goal;
    }
    public Goal applyClause(Clause clause, int atomIndex, Substitution unifier){
        Goal goal = new Goal(this);
        goal.sub = goal.sub.add(unifier);


        goal.atoms.remove(atomIndex);
        goal.atoms.addAll(clause.body.atoms());
        goal = goal.applySub(unifier);
        goal.trace = goal.trace.applyClause(clause, atomIndex);

        return goal;
    }

    public Goal runAndRemoveGroundPUDs() {
        ArrayList<Atom> left = new ArrayList<>();
        Goal result = new Goal(this);
        for(int i = 0; i< atoms.size(); i++){
            Atom a = atoms.get(i);
            if(a.userDefined()){
                List<Constant> constants = a.getConstantsIfGround();
                if(constants != null){
                    PredicateUD pud = (PredicateUD) a.predicate();
                    if (pud.run(constants)) {
                        result.atoms.remove(i);
                        result.trace.applyClause(null, i);
                        continue;
                    }else{
                        return null;
                    }
                }
            }
            left.add(a);
        }

        return new Goal(left, this.sub, this.trace);
    }
}
