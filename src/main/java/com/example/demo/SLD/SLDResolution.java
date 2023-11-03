package com.example.demo.SLD;


import com.example.demo.Logic.High.*;

/**
 * A class for performing SLD Resolution.
 */
public class SLDResolution {
    /**
     * Finds and returns a list of all valid substitutions, which makes the query true in the program.
     * @param query a list of atoms
     * @return all valid substitutions.
     */
    public static AtomTrace findSubstitutions(AtomList query){
        Goal startingGoal = new Goal(query);
        TraceNode root = new TraceNode(startingGoal);
        if(inOrderTraversal(XAI.pb.getProgram(), 1, root)){
            UnifiedTrace uTrace = new UnifiedTrace(root.generateTraces());

            return new AtomTrace(root.generateTraces());
        }else{
            return null;
        }

    }

    /**
     * Recursively performs depth first search of the SLD-tree.
     * @param program the program.
     * @param level the current level of the SLD-tree.
     */
    private static boolean inOrderTraversal(Program program, int level, TraceNode current){
        if(current.goal.isEmpty()) return true;

        if(current.goal.nStandardAtoms() == 0) return false;

        Atom selected = current.goal.get(0); // Always select the leftmost standard atom

        if(!program.clauses.containsKey(selected.predicate())) return false;

        for(Clause clause: program.clauses.get(selected.predicate())){
            Clause instance = clause.getInstance(level);
            Substitution unifier = Unify.findMGU(selected, instance.head);
            if (unifier != null) {
                Goal nextGoal = current.goal.applyClause(instance, 0, unifier).runAndRemoveGroundUDPs();
                if(nextGoal == null) return false;
                TraceNode child = new TraceNode(nextGoal);
                if(inOrderTraversal(program, level+1, child)) current.addChild(child);
            }
        }
        return !current.children.isEmpty();
    }


    public static boolean inOrderTraversalTest(AtomList toTest){
        return inOrderTraversalTest(new Goal(toTest), XAI.pb.getProgram(), 1);
    }

    private static boolean inOrderTraversalTest(Goal goal, Program program, int level){
        goal = goal.runAndRemoveGroundUDPs();
        if(goal == null) return false;

        if(goal.isEmpty()) return true;

        if(goal.nStandardAtoms() == 0) return false;

        Atom selected = goal.get(0); // Always select the leftmost atom

        if(!program.clauses.containsKey(selected.predicate())) return false;

        for(Clause clause: program.clauses.get(selected.predicate())){
            Clause instance = clause.getInstance(level);
            Substitution unifier = Unify.findMGU(goal.get(0), instance.head);
            if (unifier != null) {
                if(inOrderTraversalTest(goal.applyClause(instance, 0, unifier), program, level+1)){
                    return true;
                }
            }
        }
        return false;
    }
}

