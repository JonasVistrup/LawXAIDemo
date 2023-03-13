package com.example.demo.SLD;

import com.example.demo.Logic.High.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for performing com.example.demo.SLD Resolution.
 */
public class SLDResolution {
    /**
     * Finds and returns a list of all valid substitutions, which makes the query true in the program.
     * @param program the program.
     * @param query a list of atoms
     * @return all valid substitutions.
     */
    public static List<Answer> findSubstitutions(Program program, AtomList query){
        List<Answer> answers = new ArrayList<>();
        inOrderTraversal(answers, new Goal(query, new Substitution()), program, 1, query);

        return answers;
    }

    /**
     * Recursively performs depth first search of the com.example.demo.SLD-tree.
     * @param answers the list of valid substitutions found so far.
     * @param goal the current substitution and list of atoms to try to unify with the program.
     * @param program the program.
     * @param level the current level of the com.example.demo.SLD-tree.
     */
    private static void inOrderTraversal(List<Answer> answers, Goal goal, Program program, int level, AtomList query){
        goal = goal.runAndRemoveGroundPUDs();
        if(goal == null) return;
        if(goal.isEmpty()){
            answers.add(new Answer(goal.sub(), new History(query, goal.trace, goal.sub())));
            return;
        }

        int pos = selectAtom(goal);
        if(pos==-1) return;

        Atom selected = goal.get(pos);

        if(!program.clauses.containsKey(selected.predicate())){
            return;
        }

        for(Clause clause: program.clauses.get(selected.predicate())){
            Clause instance = clause.getInstance(level);
            Substitution unifier = Unify.findMGU(selected, instance.head);
            if (unifier != null) {
                inOrderTraversal(answers, goal.applyClause(instance, pos, unifier), program, level+1, query);
            }
        }
    }

    private static int selectAtom(Goal goal) {
        for(int i = 0; i<goal.size(); i++){
            if(!goal.get(i).userDefined()){
                return i;
            }
        }
        return -1;
    }

    public static boolean inOrderTraversalTest(Goal goal, Program program, int level){
        goal = goal.runAndRemoveGroundPUDs();

        if(goal == null) return false;

        if(goal.isEmpty()){
            return true;
        }

        int pos = selectAtom(goal);
        if(pos==-1) return false;

        for(Clause clause: program){
            Clause instance = clause.getInstance(level);
            Substitution unifier = Unify.findMGU(goal.get(pos), instance.head);
            if (unifier != null) {
                if(inOrderTraversalTest(goal.applyClause(instance, pos, unifier), program, level+1)){
                    return true;
                }
            }
        }
        return false;
    }

}

