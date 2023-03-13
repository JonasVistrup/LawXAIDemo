package com.example.demo.Logic;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.High.Program;
import com.example.demo.Logic.Symbols.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class which builds programs using string versions of its clauses.
 */
public class ProgramBuilder {
    private final Map<String, Predicate> predicates = new HashMap<>();
    private final Map<String, Term> terms = new HashMap<>();

    private final ArrayList<Clause> clauses = new ArrayList<>();


    /**
     * Returns a program consisting of the current clauses.
     * @return program made from the added clauses
     */
    public Program getProgram(){
        return new Program(clauses);
    }

    /**
     * Returns the number of clauses added.
     * @return number of clauses added.
     */
    public int size(){
        return clauses.size();
    }

    public void addPredicate(String atomRep, String explanation){
        Atom atom = parseAtom(atomRep);
        ArrayList<String> explanationParts = new ArrayList<>();
        ArrayList<Integer> termPos = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean foundMatch = false;
        for(int i = 0; i<explanation.length(); i++){
            for(int j=0; j<atom.args.size() && !foundMatch; j++){         //If earlier term is prefix of later term, later term is never recognized
                Term t = atom.args.get(j);
                if(t.toString().equals(explanation.substring(i, i+t.toString().length()))){
                    foundMatch = true;
                    explanationParts.add(current.toString());
                    termPos.add(j);
                    i = i+t.toString().length()-1;
                }
            }
            if(foundMatch){
                foundMatch = false;
                current = new StringBuilder();
            }else{
                current.append(explanation.charAt(i));
            }
        }
        explanationParts.add(current.toString());

        atom.predicate().addExplanation(explanationParts, termPos);
    }


    public void addFunctionPredicate(FunctionalInterface fPredicate){
        //TODO


    }

    public void addFact(String factRep){
        ArrayList<String> reasons = new ArrayList<>();
        reasons.add("Faktum");
        addClause(factRep+"<-", reasons);
    }

    /**
     * Adds a clause to the com.example.demo.Logic.com.example.demo.Logic.ProgramBuilder based upon the string representation of the clause given in the format HEAD{@literal <}-BODY.
     * @param representation string representation of the clause
     */
    public void addClause(String representation, ArrayList<String> reasons) {
        representation = representation.replaceAll(" ", "");
        String[] parts = representation.split("<-");
        if (parts.length == 1 && parts[0].equals(representation)){
            String [] temp_parts = representation.split("->");
            if (temp_parts.length == 1 && temp_parts[0].equals(representation)) {
                throw new IllegalArgumentException("The clause should consist of a \"head<-body\" or  \"body->head\"");
            }
            parts = new String[temp_parts.length];
            parts[0] = temp_parts[temp_parts.length-1];
            if(parts.length > 1) {
                parts[1] = temp_parts[0];
            }
        }
        if (parts.length >= 3) {
            throw new IllegalArgumentException("The clause should consist of a \"head<-body\" or  \"body->head\"");
        }
        Atom head = parseAtom(parts[0]);
        if(parts.length==1){
            clauses.add(new Clause(head, new AtomList(),reasons));
            return;
        }



        parts[1] = parts[1].replaceAll("\\),", ")<-");
        String[] strBody = parts[1].split("<-");

        ArrayList<Atom> body = new ArrayList<>();
        for (String s : strBody) {
            body.add(parseAtom(s));
        }
        clauses.add(new Clause(head, new AtomList(body), reasons));
    }



    /**
     * Returns an atom based upon the string representation given.
     * @param atomRep string representation
     * @return atom which the string representation corresponds to
     */
    public Atom parseAtom(String atomRep) {
        atomRep = atomRep.replaceAll(" ", "");
        String[] strArguments;
        if (atomRep.length() == 0) {
            throw new IllegalArgumentException("Atom representation must not be empty");
        }
        String[] parts = atomRep.split("\\(");
        int numberOfArgs;
        if (parts.length >= 2) {
            if (!parts[1].contains(")"))
                throw new IllegalArgumentException("( must end with a )");
            strArguments = parts[1].split("\\)");
            strArguments = strArguments[0].split(",");
            numberOfArgs = strArguments.length;
        } else {
            throw new IllegalArgumentException("All predicates must contain a temporal argument");
        }

        Predicate p = getPredicate(parts[0], numberOfArgs);


        // Get terms arguments
        List<Term> arguments = new ArrayList<>(numberOfArgs);
        for (int i = 0; i<numberOfArgs; i++) {
            arguments.add(getTerm(strArguments[i]));
        }

        return new Atom(p,arguments);
    }


    private Term getTerm(String name) {
        if (terms.containsKey(name)) {
            return terms.get(name);
        } else {
            if (name.toLowerCase().equals(name)) {
                Constant constant = new Constant(name);
                terms.put(name, constant);
                return constant;
            } else if(name.toUpperCase().equals(name)) {
                Variable var = new Variable(name);
                terms.put(name, var);
                return var;
            } else {
                throw new IllegalArgumentException("Terms must either be all uppercase for variables or all lowercase for constants");
            }
        }
    }

    private Predicate getPredicate(String name, int numberOfArgs) {
        if (numberOfArgs < 0) {
            throw new IllegalArgumentException("Only a non-negative amount of arguments allowed");
        }
        Predicate res;
        if (predicates.containsKey(name)) {
            res = (Predicate) predicates.get(name);
            if (res.nArgs() != numberOfArgs) {
                throw new IllegalArgumentException("com.example.demo.Logic.Predicate " + name + " contains an inconsistent of arguments");
            }
        } else {
            res = new PredicateStd(name, numberOfArgs);
            predicates.put(name, res);
        }
        return res;
    }

    public ProgramBuilder copy() {
        ProgramBuilder copy = new ProgramBuilder();
        copy.predicates.putAll(this.predicates);
        copy.terms.putAll(this.terms);
        copy.clauses.addAll(this.clauses);
        return copy;
    }
}