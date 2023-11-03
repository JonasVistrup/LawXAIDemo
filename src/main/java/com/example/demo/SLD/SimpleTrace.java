package com.example.demo.SLD;

import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;

public class SimpleTrace {
    public Goal goal;
    public SimpleTrace child;
    public Substitution substitution;

    public SimpleTrace(Goal goal, SimpleTrace child, Substitution substitution){
        this.goal = goal;
        this.child = child;
        this.substitution = substitution;
    }
}
