package demo.SLD;


import demo.Logic.High.*;

import java.util.*;

/**
 * A class for performing demo.SLD Resolution.
 */
public class SLDResolution {
    /**
     * Finds and returns a list of all valid substitutions, which makes the query true in the program.
     * @param query a list of atoms
     * @return all valid substitutions.
     */
    public static List<Substitution> findSubstitutions(AtomList query, HashMap<Atom, List<Clause>> groundClausesUsed){
        Goal startingGoal = new Goal(query);
        TraceNode root = new TraceNode(startingGoal);
        List<Substitution> answers = new ArrayList<>();
        inOrderTraversal(XAI.getProgram(), 1, root, new ArrayList<>(), answers, groundClausesUsed);

        return answers;
    }

    /**
     * Recursively performs depth first search of the demo.SLD-tree.
     * @param program the program.
     * @param level the current level of the demo.SLD-tree.
     */
    private static boolean inOrderTraversal(Program program, int level, TraceNode current, List<Clause> usedOnThePath, List<Substitution> answers, HashMap<Atom,List<Clause>> groundClauses){
        if(level > 50){
            System.out.println("Deep");
        }
        if(current.goal.isEmpty()){
            answers.add(current.goal.sub());
            for(Clause used: usedOnThePath){
                Clause instance = used.applySub(current.goal.sub());
                if(groundClauses.containsKey(instance.head)){
                    List<Clause> clausesList = groundClauses.get(instance.head);
                    if(!clausesList.contains(instance)) clausesList.add(instance);
                }else{
                    List<Clause> clauses = new ArrayList<>();
                    clauses.add(instance);
                    groundClauses.put(instance.head,clauses);
                }
            }
            return true;
        }

        if(current.goal.nStandardAtoms() == 0) return false;

        Atom selected = current.goal.get(0); // Always select the leftmost standard atom

        if(!program.clauses.containsKey(selected.predicate())) return false;

        for(Clause clause: program.clauses.get(selected.predicate())){
            Clause instance = clause.getInstance(level);
            Substitution unifier = Unify.findMGU(selected, instance.head);
            if (unifier != null) {
                Goal nextGoal = current.goal.applyClause(instance, 0, unifier).runAndRemoveGroundUDPs();
                if(nextGoal == null) continue;
                TraceNode child = new TraceNode(nextGoal);
                usedOnThePath.add(instance);
                if(inOrderTraversal(program, level+1, child, usedOnThePath, answers, groundClauses)){
                    current.addChild(child);
                }
                usedOnThePath.remove(instance);

            }
        }
        return !current.children.isEmpty();
    }


    public static boolean inOrderTraversalTest(AtomList toTest){
        return inOrderTraversalTest(new Goal(toTest), XAI.getProgram(), 1);
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

