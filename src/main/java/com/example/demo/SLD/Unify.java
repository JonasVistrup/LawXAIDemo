package com.example.demo.SLD;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Term;
import com.example.demo.Logic.Symbols.Variable;

/**
 * A class for unified two atoms.
 */
public class Unify {
    /**
     * Finds a most general unifier between selectedAtom and head if one exist, otherwise returns 0.
     * @param selectedAtom atom which variables are only substituted if absolutely necessary.
     * @param head atom which variables are substituted whenever possible.
     * @return a substitution which is a most general unifier.
     */
    static Substitution findMGU(Atom selectedAtom, Atom head) {

        if(head.predicate() != selectedAtom.predicate()){
            return null;
        }
        Substitution sub = new Substitution();
        for(int i = 0; i<selectedAtom.args.size(); i++){
            Term selectInstance = selectedAtom.args.get(i);
            Term headInstance = head.args.get(i);

            if(headInstance != selectInstance){
                Substitution unifier = unify(selectInstance, headInstance);
                if(unifier == null){
                    return null;
                }else{
                    sub = sub.add(unifier);
                    selectedAtom = selectedAtom.applySub(unifier);
                }
            }
        }

        return sub;
    }

    private static Substitution unify(Term one, Term two){
        if(two instanceof Variable){
            return new Substitution((Variable) two, one); //No matter if two is a constant or a variable, we choose to sub two with one
        }else{
            if(one instanceof Variable){
                return new Substitution((Variable) one, two);
            }else{
                assert !one.equals(two);
                return null;
            }
        }
    }
}
