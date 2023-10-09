package com.example.demo.SLD;


import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Substitution;

import java.util.ArrayList;
import java.util.List;

public class History{

    public List<HistoryNode> tops;
    List<HistoryNode> frontier;

    public History(AtomList query, Trace trace, Substitution substitution){
        tops = new ArrayList<>();
        frontier = new ArrayList<>();
        for(Atom atom : query){
            HistoryNode node = new HistoryNode(atom.applySub(substitution));
            tops.add(node);
            frontier.add(node);
        }
        for(int i = 0; i<trace.clausesUsed.length; i++){
            int pos = trace.posClausesApplied[i];
            Clause clauseUsed = trace.clausesUsed[i];

            HistoryNode node = frontier.remove(pos);
            if(clauseUsed != null) {
                node.clauseUsed = clauseUsed.applySub(substitution);
                node.children = new ArrayList<>();
                for (Atom atom : node.clauseUsed.body) {
                    node.children.add(new HistoryNode(atom));
                }
                frontier.addAll(node.children);
            }
        }
    }

    public static class HistoryNode{
        public Atom atomSolved;
        public Clause clauseUsed;

        public List<HistoryNode> children;

        public HistoryNode(Atom atom) {this.atomSolved = atom;}
    }



}
