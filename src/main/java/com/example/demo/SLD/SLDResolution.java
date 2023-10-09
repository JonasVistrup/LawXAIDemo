package com.example.demo.SLD;


import com.example.demo.Logic.High.*;
import com.example.demo.Logic.Symbols.Predicates.PredicateUD;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for performing SLD Resolution.
 */
public class SLDResolution {
    /**
     * Finds and returns a list of all valid substitutions, which makes the query true in the program.
     * @param query a list of atoms
     * @return all valid substitutions.
     */
    public static List<Answer> findSubstitutions(AtomList query){
        List<Answer> answers = new ArrayList<>();
        inOrderTraversal(answers, new Goal(query), XAI.pb.getProgram(), 1, query);

        return answers;
    }

    /**
     * Recursively performs depth first search of the SLD-tree.
     * @param answers the list of valid substitutions found so far.
     * @param goal the current substitution and list of atoms to try to unify with the program.
     * @param program the program.
     * @param level the current level of the SLD-tree.
     */
    private static void inOrderTraversal(List<Answer> answers, Goal goal, Program program, int level, AtomList query){

        goal = goal.runAndRemoveGroundUDPs();
        if(goal == null) return;

        if(goal.isEmpty()){
            answers.add(new Answer(goal.sub(), new History(query, goal.trace(), goal.sub())));
            return;
        }

        if(goal.nStandardAtoms() == 0) return;

        for(Atom a: goal){
            if(!(a.predicate() instanceof PredicateUD) && !program.clauses.containsKey(a.predicate())) return; //TODO delete
        }

        Atom selected = goal.get(0); // Always select the leftmost atom

        if(!program.clauses.containsKey(selected.predicate())) return;

        for(Clause clause: program.clauses.get(selected.predicate())){
            Clause instance = clause.getInstance(level);
            Substitution unifier = Unify.findMGU(selected, instance.head);
            if (unifier != null) {
                inOrderTraversal(answers, goal.applyClause(instance, 0, unifier), program, level+1, query);
            }
        }
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

