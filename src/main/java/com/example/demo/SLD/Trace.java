package com.example.demo.SLD;

import com.example.demo.Logic.High.Clause;

import java.util.ArrayList;
import java.util.List;

public class Trace {
    public List<Clause> clausesUsed;
    public List<Integer> posClausesApplied;

    public Trace(){
        clausesUsed = new ArrayList<>();
        posClausesApplied = new ArrayList<>();
    }

    public Trace applyClause(Clause clause, int position){
        Trace res = this.copy();

        res.clausesUsed.add(clause);
        res.posClausesApplied.add(position);

        return res;
    }

    public Trace copy(){
        Trace res = new Trace();
        res.clausesUsed.addAll(this.clausesUsed);
        res.posClausesApplied.addAll(this.posClausesApplied);

        return res;
    }
}
