package com.example.demo.SLD;

import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;

import java.util.ArrayList;

public class AnswerTreeNode {
    Substitution unification;
    Clause clause;
    Goal current;

    ArrayList<AnswerTreeNode> children = new ArrayList<>();

    public AnswerTreeNode(Substitution unification, Clause clause, Goal current) {
        this.unification = unification;
        this.clause = clause;
        this.current = current;
    }

    public AnswerTreeNode addChild(Substitution unification, Clause clause, Goal next) {
        AnswerTreeNode child = new AnswerTreeNode(unification, clause, next);
        children.add(child);
        return child;
    }

    public void deleteChild(AnswerTreeNode child){
        children.remove(child);
    }

    public AndOrHistory getHistory() {
        return new AndOrHistory(this);
    }
}
