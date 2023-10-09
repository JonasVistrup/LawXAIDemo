package com.example.demo.SLD;


import com.example.demo.Logic.High.Clause;

// Immutable.
public class Trace {
    public final Clause[] clausesUsed;
    public final int[] posClausesApplied;

    public Trace(){
        this.clausesUsed = new Clause[0];
        this.posClausesApplied = new int[0];
    }


    private Trace(Trace trace, Clause clause, int position){
        this.clausesUsed = new Clause[trace.clausesUsed.length+1];
        this.posClausesApplied = new int[trace.posClausesApplied.length+1];

        System.arraycopy(trace.clausesUsed, 0, this.clausesUsed, 0, trace.clausesUsed.length);
        System.arraycopy(trace.posClausesApplied, 0, this.posClausesApplied, 0, trace.posClausesApplied.length);

        this.clausesUsed[trace.clausesUsed.length] = clause;
        this.posClausesApplied[trace.posClausesApplied.length] = position;
    }


    private Trace(Trace trace, int[] positions){
        this.clausesUsed = new Clause[trace.clausesUsed.length+positions.length];
        this.posClausesApplied = new int[trace.posClausesApplied.length+positions.length];

        System.arraycopy(trace.clausesUsed, 0, this.clausesUsed, 0, trace.clausesUsed.length);

        System.arraycopy(trace.posClausesApplied, 0, this.posClausesApplied, 0, trace.posClausesApplied.length);
        System.arraycopy(positions, 0, this.posClausesApplied, trace.posClausesApplied.length, positions.length);

    }

    public Trace applyClause(Clause clause, int position){
        return new Trace(this, clause, position);
    }

    public Trace removeUDPs(int[] removedPositions){
        return new Trace(this, removedPositions);
    }

}
