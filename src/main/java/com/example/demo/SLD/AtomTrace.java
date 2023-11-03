package com.example.demo.SLD;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.Clause;

import java.util.ArrayList;
import java.util.List;

public class  {



    public AtomTrace(UnifiedTrace trace){
        NodeOr top = new NodeOr(null);

        List<List<SimpleTrace>> unifiableTraces = new ArrayList<>();
        for(SimpleTrace trace: traces){
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

    }

    public static boolean unifiable(SimpleTrace a, SimpleTrace b){
        if(!a.goal.equals(b.goal)) return false;
        return a.goal.trace().clausesUsed[a.goal.trace().clausesUsed.length-1] == b.goal.trace().clausesUsed[b.goal.trace().clausesUsed.length-1];
    }
    private class NodeOr{
        Atom atom;
        List<NodeAnd> children;

        NodeOr(Atom atom){
            this.atom = atom;
            this.children = new ArrayList<>();
        }

        public void add(NodeAnd child){
            children.add(child);
        }
    }

    private class NodeAnd{
        Clause clause;
        List<NodeOr> children;

        NodeAnd(Clause clause){
            this.clause = clause;
            this.children = new ArrayList<>();
        }

        public void add(NodeOr child){
            children.add(child);
        }
    }
}
