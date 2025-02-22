package demo.SLD;


import demo.Logic.High.Substitution;

import java.util.ArrayList;
import java.util.List;

public class TraceNode {
    public Goal goal;
    public List<TraceNode> children;

    TraceNode(Goal goal){
        this.goal = goal;
        this.children = new ArrayList<>();
    }

    public int addChild(TraceNode child){
        this.children.add(child);
        return this.children.size()-1;
    }

    public void killChild(int pos){
        this.children.remove(pos);
    }

    public List<SimpleTrace> generateTraces(){
        List<SimpleTrace> simpleTraces = new ArrayList<>();

        if(children.isEmpty()){
            simpleTraces.add(new SimpleTrace(this.goal, null, this.goal.sub()));
        }


        for(TraceNode child: children){
            List<SimpleTrace> childTraces = child.generateTraces();
            for(SimpleTrace childTrace: childTraces){
                simpleTraces.add(new SimpleTrace(this.goal.applySub(childTrace.substitution), childTrace, childTrace.substitution));
            }
        }

        return simpleTraces;
    }
}
