package com.example.demo.Logic;

import com.example.demo.Logic.High.*;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Predicates.*;
import com.example.demo.Logic.Symbols.Term;
import com.example.demo.Logic.Symbols.Variable;
import com.example.demo.Logic.UserDefined.Less;
import com.example.demo.Logic.UserDefined.Minus;
import com.example.demo.Logic.UserDefined.Mul;
import com.example.demo.Logic.UserDefined.Plus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class which builds programs using string versions of its clauses.
 */
public class ProgramParser {
    private final Map<String, Predicate> predicates = new HashMap<>();
    private final Map<String, Term> terms = new HashMap<>();

    private final ArrayList<Clause> clauses = new ArrayList<>();
    private ArrayList<Atom> facts = new ArrayList<>();
    private String line = "NO LINE";

    private int counter = 1;


    /**
     * Returns a program consisting of the current clauses.
     * @return program made from the added clauses
     */
    public Program getProgram(){
        ArrayList<Clause> allClauses = new ArrayList<>(this.clauses);
        ArrayList<String> reasons = new ArrayList<>();
        reasons.add("Faktum");

        ArrayList<String> temporalReason = new ArrayList<>();
        reasons.add("Er sandt på et tidspunkt hvis altid sandt");
        for(Atom a: facts){
            allClauses.add(new Clause(a, new AtomList(), reasons));
            if(!a.hasTemporalTerm() && !(a.predicate() instanceof PredicateUD)){
                allClauses.add(new Clause(new Atom(a.predicate(), a.args.add(generateTerm("Var0"))), new AtomList(a), temporalReason));
            }
        }
        return new Program(allClauses);
    }

    /**
     * Returns the number of clauses added.
     * @return number of clauses added.
     */
    public int size(){
        return clauses.size();
    }

    public void addPredicate(String atomRep, String explanation){
        Atom atom = parseNewAtom(atomRep);
        ArrayList<String> explanationParts = new ArrayList<>();
        ArrayList<Integer> termPos = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        String[] parts = explanation.split("[\\[\\]]",explanation.length());
        for(int i = 0; i<parts.length; i++){
            if(i%2==0){
                explanationParts.add(parts[i]);
            }else{
                int pos = -1;
                for(int j = 0; j<atom.args.size(); j++){
                    if(atom.args.get(j).toString().equals(parts[i])){
                        pos = j;
                        break;
                    }
                }
                if(pos == -1) {
                    throw new IllegalArgumentException(parts[i] + " is not a part of the atom " + atom);
                }
                termPos.add(pos);
            }
        }

        atom.predicate().addExplanation(explanationParts, termPos);
    }


    public void addUDPredicate(String classString) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class functionClass = Class.forName(classString);
        if(UDRelation.class.isAssignableFrom(functionClass)) {
            Constructor ct = functionClass.getConstructor(new Class[0]);
            UDRelation udr = (UDRelation) ct.newInstance(new Object[0]);
            addUDRelation(udr);
        }else if(UDFunction.class.isAssignableFrom(functionClass)){
            Constructor ct = functionClass.getConstructor(new Class[0]);
            UDFunction udf = (UDFunction) ct.newInstance(new Object[0]);
            addUDFunction(udf);
        }else{
            throw new IllegalArgumentException("Class "+functionClass.getName() + " does not implement UserDefinedPredicate.");
        }
    }

    public void addUDRelation(UDRelation udr){
        this.predicates.put(udr.toString(), udr);
    }

    public void addUDFunction(UDFunction udf){
        predicates.put(udf.toString(), udf);
    }

    public void addFact(String factRep){
        this.line = factRep;
        this.facts.add(parseAtomOld(factRep));
    }

    public void removeFact(String factRep){
        Atom toRemove = parseAtomOld(factRep);
        this.facts.remove(toRemove);
    }

    public void removeAllFacts(){
        this.facts = new ArrayList<>();
    }

    /**
     * Returns an atom based upon the string representation given.
     * @param atomRep string representation
     * @return atom which the string representation corresponds to
     */
    public Atom parseAtomOld(String atomRep) {
        //atomRep = atomRep.replaceAll(" ", "");
        String[] strArguments;
        if (atomRep.length() == 0) {
            throw new IllegalArgumentException("In Line: "+line+".\n Atom representation must not be empty");
        }
        String[] parts = atomRep.split("\\(");
        int numberOfArgs;
        if (parts.length >= 2) {
            if (!parts[1].contains(")"))
                throw new IllegalArgumentException("In Line: "+line+".\n ( must end with a )");
            strArguments = parts[1].split("\\)");
            strArguments = strArguments[0].split(",");
            numberOfArgs = strArguments.length;
        } else {
            throw new IllegalArgumentException("In Line: "+line+".\n All predicates must contain a temporal argument");
        }

        Predicate p = getPredicate(parts[0], numberOfArgs);

        // Get terms arguments
        List<Term> arguments = new ArrayList<>(numberOfArgs);
        for (int i = 0; i<numberOfArgs; i++) {
            arguments.add(getTerm(strArguments[i]));
        }

        return new Atom(p,arguments);
    }

    public Atom parseNewAtom(String atomRep) {
        //atomRep = atomRep.replaceAll(" ", "");
        String[] strArguments;
        if (atomRep.length() == 0) {
            throw new IllegalArgumentException("In Line: "+line+".\n Atom representation must not be empty");
        }
        String[] parts = atomRep.split("\\(");
        int numberOfArgs;
        if (parts.length >= 2) {
            if (!parts[1].contains(")"))
                throw new IllegalArgumentException("In Line: "+line+".\n ( must end with a )");
            strArguments = parts[1].split("\\)");
            strArguments = strArguments[0].split(",");
            numberOfArgs = strArguments.length;
        } else {
            throw new IllegalArgumentException("In Line: "+line+".\n All predicates must contain a temporal argument");
        }

        if (predicates.containsKey(parts[0])) throw new IllegalArgumentException("Predicate "+parts[0]+" has already been defined");
        Predicate p = new PredicateStd(parts[0], numberOfArgs);
        predicates.put(parts[0],p);

        // Get terms arguments
        List<Term> arguments = new ArrayList<>(numberOfArgs);
        for (int i = 0; i<numberOfArgs; i++) {
            arguments.add(getTerm(strArguments[i]));
        }

        return new Atom(p,arguments);
    }

    public Atom parseAtom(String predicate, Arguments arguments, Term reification){
        return new Atom(getPredicate(predicate,arguments.size()),arguments,reification);
    }

    public Atom parseAtom(String predicate, Arguments arguments){
        return new Atom(getPredicate(predicate,arguments.size()),arguments);
    }

    public Atom parseAtomReification(String atomRep) {
        atomRep = atomRep.replaceAll(" ", "");
        String[] strArguments;
        if (atomRep.length() == 0) {
            throw new IllegalArgumentException("In Line: "+line+".\n Atom representation must not be empty");
        }
        String[] parts = atomRep.split("\\(");
        int numberOfArgs;
        if (parts.length >= 2) {
            if (!parts[1].contains(")"))
                throw new IllegalArgumentException("In Line: "+line+".\n ( must end with a )");
            strArguments = parts[1].split("\\)");
            strArguments = strArguments[0].split(",");
            numberOfArgs = strArguments.length;
        } else {
            throw new IllegalArgumentException("In Line: "+line+".\n All predicates must contain a temporal argument");
        }

        Predicate p = getPredicate(parts[0], numberOfArgs);

        // Get terms arguments
        List<Term> arguments = new ArrayList<>(numberOfArgs);
        for (int i = 0; i<numberOfArgs; i++) {
            arguments.add(getTerm(strArguments[i]));
        }

        return new Atom(p,arguments);
    }


    public Term getTerm(String name) {
        if (terms.containsKey(name)) {
            return terms.get(name);
        } else {
            if(illegalTerm(name)) throw new IllegalArgumentException("In Line: "+line+".\nTerm "+name+" is a reserved keyword");
            if (!Character.isUpperCase(name.charAt(0))) {
                Constant constant = new Constant(name);
                terms.put(name, constant);
                return constant;
            } else{ //if(name.toUpperCase().equals(name)) {
                Variable var = new Variable(name);
                terms.put(name, var);
                return var;
            }// else {
            //    throw new IllegalArgumentException("Terms must either be all uppercase for variables or all lowercase for constants");
            //}
        }
    }

    public Term generateTerm(String name) {
        if (terms.containsKey(name)) {
            return terms.get(name);
        } else {
            if (name.toLowerCase().equals(name)) {
                Constant constant = new Constant(name);
                terms.put(name, constant);
                return constant;
            } else { //if(name.toUpperCase().equals(name)) {
                Variable var = new Variable(name);
                terms.put(name, var);
                return var;
            }
        }
    }

    public Term genUniqueVariable(){
        return generateTerm("Var"+(counter++));
    }

    private boolean illegalTerm(String name) {
        return name.startsWith("Var") && isInt(name.substring(3));
    }

    private boolean isInt(String intRep){
        try{
            int i = Integer.parseInt(intRep);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    private Predicate getPredicate(String name, int numberOfArgs) {
        if (numberOfArgs < 0) throw new IllegalArgumentException("In Line: "+line+".\n Only a non-negative amount of arguments allowed");

        Predicate res;

        if (predicates.containsKey(name)) {
            res = predicates.get(name);
            if (res.nArgs() != numberOfArgs && (res.nArgs() +1 != numberOfArgs || (res instanceof PredicateUD && !(res instanceof UDNegation)))) {
                throw new IllegalArgumentException("In Line: "+line+".\n Logic.Predicate " + name + " contains an inconsistent of arguments");
            }
        } else {
            if(name.charAt(0) == '~'){
                String positiveName = name.substring(1);
                res = new UDNegation(getPredicate(positiveName, numberOfArgs));
            }else { //TODO rewrite this
                throw new IllegalArgumentException("In Line: "+line+".\n Logic.Predicate " + name + " has not been previously specified");
                //res = new PredicateStd(name, numberOfArgs);
            }
            predicates.put(name, res);
        }
        return res;
    }

    public ProgramParser copy() {
        ProgramParser copy = new ProgramParser();
        copy.predicates.putAll(this.predicates);
        copy.terms.putAll(this.terms);
        copy.clauses.addAll(this.clauses);
        return copy;
    }

    public int predicateSize() {
        return predicates.size();
    }
}