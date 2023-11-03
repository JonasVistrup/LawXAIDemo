package com.example.demo.SLD;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.Clause;

import java.util.*;

public class UnifiedTrace {

    UnifiedTraceNode root;

    public UnifiedTrace(List<SimpleTrace> traces){
        root = new UnifiedTraceNode(traces, null, null);
    }

    public HashMap<Atom, Set<Clause>> generateMap(){
        HashMap<Atom, Set<Clause>> result = new HashMap<>();
        for(UnifiedTraceNode node: root.children){
            for(Atom a: node.goal){
                generateDictAtom(a, node, result);
            }
        }
        return result;
    }

    private void generateDictAtom(Atom a, UnifiedTraceNode node, HashMap<Atom, Set<Clause>> dictionary){
        //Find a
        UnifiedTraceNode foundNode = node.find(a);
        if(foundNode == null) throw new IllegalStateException("Could not find atom "+a.toString()+" in UnifiedTrace!");
        //Find clause
        if(dictionary.containsKey(a)){
            dictionary.get(a).add(foundNode.clauseUsed);
        }else{
            Set<Clause> clauses = new HashSet<>();
            clauses.add(foundNode.clauseUsed);
            dictionary.put(a,clauses);
        }

        dictionary.put(a, foundNode.clauseUsed);
        for()
    }

    public static class UnifiedTraceNode{
        List<UnifiedTraceNode> children;
        Clause clauseUsed;
        Goal goal;

        public UnifiedTraceNode find(Atom a){
            if(clauseUsed == null) return null;
            if(clauseUsed.head.equals(a)) return this;
            for(UnifiedTraceNode child: children){
                UnifiedTraceNode result = child.find(a);
                if(result != null) return result;
            }

            return null;
        }

        UnifiedTraceNode(List<SimpleTrace> traces, Goal goal, Clause clause){
            this.children = new ArrayList<>();
            this.goal = goal;
            this.clauseUsed = clause;

            List<List<SimpleTrace>> unifiableTraces = new ArrayList<>();
            for(SimpleTrace trace: traces){
                if(trace == null) continue;

                boolean match = false;
                for(List<SimpleTrace> unifiableTrace: unifiableTraces){
                    if(unifiable(unifiableTrace.get(0), trace)){
                        match = true;
                        unifiableTrace.add(trace);
                        break;
                    }
                }
                if(!match){
                    List<SimpleTrace> uniqueTrace = new ArrayList<>();
                    uniqueTrace.add(trace);
                    unifiableTraces.add(uniqueTrace);
                }
            }

            for(List<SimpleTrace> uTraces: unifiableTraces){
                List<SimpleTrace> childrenTraces = new ArrayList<>();
                for(SimpleTrace trace: uTraces){
                    childrenTraces.add(trace.child);
                }
                Goal nextGoal = uTraces.get(0).goal;
                Trace trace = nextGoal.trace();
                this.children.add(new UnifiedTraceNode(childrenTraces, nextGoal, trace.clausesUsed[trace.clausesUsed.length-1].applySub(nextGoal.sub())));
            }

        }

    }

    public static boolean unifiable(SimpleTrace a, SimpleTrace b){
        if(!a.goal.equals(b.goal)) return false;
        return a.goal.trace().clausesUsed[a.goal.trace().clausesUsed.length-1] == b.goal.trace().clausesUsed[b.goal.trace().clausesUsed.length-1];
    }
}
