package com.example.demo.Logic;

import com.example.demo.Logic.High.*;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Predicates.*;
import com.example.demo.Logic.Symbols.Term;
import com.example.demo.Logic.Symbols.Variable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    private ArrayList<Atom> facts = new ArrayList<>();
    private String line = "NO LINE";


    /**
     * Returns a program consisting of the current clauses.
     * @return program made from the added clauses
     */
    public Program getProgram(){
        ArrayList<Clause> allClauses = new ArrayList<>(this.clauses);
        ArrayList<String> reasons = new ArrayList<>();
        reasons.add("Faktum");
        for(Atom a: facts){
            allClauses.add(new Clause(a, new AtomList(), reasons));
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
        Atom atom = parseAtomOld(atomRep);
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
     * Adds a clause to the Logic.Logic.ProgramBuilder based upon the string representation of the clause given in the format HEAD{@literal <}-BODY.
     * @param representation string representation of the clause
     */
    public void parseClause(String representation, ArrayList<String> reasons, String line) {
        this.line = line;
        representation = representation.replaceAll(" ", "");
        String[] parts = representation.split("<-");
        if (parts.length == 1 && parts[0].equals(representation)){
            String [] temp_parts = representation.split("->");
            if (temp_parts.length == 1 && temp_parts[0].equals(representation)) {
                throw new IllegalArgumentException("In Line: "+line+".\nThe clause should consist of a \"head<-body\" or  \"body->head\"");
            }
            parts = new String[temp_parts.length];
            parts[0] = temp_parts[temp_parts.length-1];
            if(parts.length > 1) {
                parts[1] = temp_parts[0];
            }
        }
        if (parts.length >= 3) {
            throw new IllegalArgumentException("In Line: "+line+".\nThe clause should consist of a \"head<-body\" or  \"body->head\"");
        }
        Atom head = parseAtomOld(parts[0]);
        if(parts.length==1){
            clauses.add(new Clause(head, new AtomList()));
            return;
        }
        addClauses(head, parts[1], reasons);

    }

    public void addClauses(Atom head, String bodyStr, ArrayList<String> reasons){
        String[] AtomStrings = splitAtoms(bodyStr);


        ArrayList<AtomList> bodies = new ArrayList<>();
        bodies.add(new AtomList());
        for (String s : AtomStrings) {
            ArrayList<AtomList> atomLists = parseAtomGroup(s);
            ArrayList<AtomList> newBodies = new ArrayList<>();
            for(AtomList body: bodies){
                for(AtomList atomlist: atomLists){
                    newBodies.add(body.add(atomlist));
                }
            }
            bodies = newBodies;
        }

        for(AtomList body: bodies){
            clauses.add(new Clause(head, body, reasons));
        }
    }

    private String[] splitAtoms(String bodyStr) {
        ArrayList<String> atoms = new ArrayList<>();
        boolean inParentheses = false;
        int lastSplitPos = 0;
        for(int i = 0; i<bodyStr.length(); i++){
            char c = bodyStr.charAt(i);
            if(c == '(') inParentheses = true;
            else if(c == ')') inParentheses = false;
            else if(c == ',' && !inParentheses) {
                atoms.add(bodyStr.substring(lastSplitPos,i));
                lastSplitPos = i+1;
            }
        }
        atoms.add(bodyStr.substring(lastSplitPos));
        String[] result = new String[atoms.size()];
        for(int i = 0; i<atoms.size(); i++){
            result[i] = atoms.get(i);
        }
        return result;
    }


    private ArrayList<AtomList> parseAtomGroup(String s) {
        if(!correctFormat(s)) throw new IllegalArgumentException("In Line: "+line+".\nAtom {"+s+"} are incorrectly formated");

        if(isDisjunction(s)){
            return parseDisjunction(s,0);
        }else if(isConjunction(s)){
            return parseConjunction(s,0);
        }
        return parseAtom(s);
    }

    private boolean isConjunction(String s) {
        int nOpen = 0;
        int nCloses = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '[') nOpen++;
            else if(c == ']') nCloses++;
            else if(c =='/' && s.charAt(i+1) == '\\' && nOpen == nCloses) return true;
            else if(c =='\\' && s.charAt(i+1) == '/' && nOpen == nCloses) return false;
        }
        return false;
    }


    public boolean isDisjunction(String s){
        int nOpen = 0;
        int nCloses = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '[') nOpen++;
            else if(c == ']') nCloses++;
            else if(c =='/' && s.charAt(i+1) == '\\' && nOpen == nCloses) return false;
            else if(c =='\\' && s.charAt(i+1) == '/' && nOpen == nCloses) return true;
        }
        return false;
    }

    private ArrayList<AtomList> parseDisjunction(String s, int level){
        s = removeBlock(s);
        if(level>10){
            throw new IllegalArgumentException("In Line: "+line+"Section {"+s+"} is over 10 levels deep");
        }
        boolean inParentheses = false;
        ArrayList<AtomList> atomLists = new ArrayList<>();

        if(!isDisjunction(s)){
            return parseAtom(s);
        }

        int lastSplitPosition = 0;
        int nOpen = 0;
        int nClosed = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') inParentheses = true;
            else if(c == ')') inParentheses = false;
            else if(c == '[') nOpen++;
            else if(c == ']') nClosed++;
            else if(c == '\\' && s.charAt(i+1) == '/' && nOpen == nClosed && !inParentheses){
                ArrayList<AtomList> conjunctions = parseConjunction(s.substring(lastSplitPosition, i), level+1);

                atomLists.addAll(conjunctions);
                lastSplitPosition = i+2;
                i = i+1;
            }
        }

        ArrayList<AtomList> conjunctions = parseConjunction(s.substring(lastSplitPosition), level+1);
        atomLists.addAll(conjunctions);
        return atomLists;

    }

    private String removeBlock(String s) {
        if(s.charAt(0) =='[' && s.charAt(s.length()-1)==']') return s.substring(1,s.length()-1);
        return s;
    }

    private ArrayList<AtomList> parseConjunction(String s, int level){
        s = removeBlock(s);
        if(level>10){
            throw new IllegalArgumentException("In Line: "+line+"Section {"+s+"} is over 10 levels deep");
        }
        boolean inParentheses = false;
        ArrayList<AtomList> atomLists = new ArrayList<>();
        if(!isConjunction(s)){
            return parseAtom(s);
        }

        atomLists.add(new AtomList());
        int lastSplitPosition = 0;
        int nOpen = 0;
        int nClosed = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '(') inParentheses = true;
            else if(c == ')') inParentheses = false;
            else if(c == '[') nOpen++;
            else if(c == ']') nClosed++;
            else if(c == '\\' && s.charAt(i+1) == '/' && nOpen == nClosed && !inParentheses){
                ArrayList<AtomList> disjunctions = parseDisjunction(s.substring(lastSplitPosition, i), level+1);
                ArrayList<AtomList> newAtomList = new ArrayList<>();
                for(AtomList list: atomLists){
                    for(AtomList list2: disjunctions){
                        newAtomList.add(list.add(list2));
                    }
                }
                atomLists = newAtomList;
                lastSplitPosition = i+2;
                i = i+1;
            }
        }

        ArrayList<AtomList> disjunctions = parseDisjunction(s.substring(lastSplitPosition),level+1);
        ArrayList<AtomList> newAtomList = new ArrayList<>();
        for(AtomList list: atomLists){
            for(AtomList list2: disjunctions){
                newAtomList.add(list.add(list2));
            }
        }
        return newAtomList;
    }

    public boolean correctFormat(String atomRep){
        if(atomRep.isEmpty()) throw new IllegalArgumentException("In Line: "+line+".\n Atom representation must be non-empty");
        if(!noBlockLeftUncloses(atomRep)) throw new IllegalArgumentException("In Line: "+line+".\n Atom {"+atomRep+"} number of open and closes blocks does not match");

        return true;
    }

    private boolean noBlockLeftUncloses(String atomRep) {
        int numberOfOpens = 0;
        int numberOfCloses = 0;
        boolean parenthesesOpen = false;
        for(char c: atomRep.toCharArray()){
            if(c == '[') numberOfOpens++;
            else if(c == ']') numberOfCloses++;
            else if(c == '('){
                if(parenthesesOpen) throw new IllegalArgumentException("In Line: "+line+".\n Atom {"+atomRep+"} has multiple open parentheses");
                parenthesesOpen = true;
            }else if(c == ')'){
                if(!parenthesesOpen) throw new IllegalArgumentException("In Line: "+line+".\n Atom {"+atomRep+"} has multiple closed parentheses");
                parenthesesOpen = false;
            }
        }
        return numberOfOpens == numberOfCloses;
    }


    public ArrayList<AtomList> parseAtom(String atomRep){


        String[] parts = atomRep.split("\\(");

        if (parts.length == 1){
            Predicate p = getPredicate(parts[0],0);
            ArrayList<AtomList> list = new ArrayList<>();
            list.add(new AtomList(new Atom(p,new Arguments())));
            return list;
        }

        if (!parts[1].contains(")")) throw new IllegalArgumentException("In Line: "+line+".\n Atom {"+atomRep+"( must end with a )");

        String[] strArguments = parts[1].split("\\)")[0].split(",");
        int numberOfArgs = strArguments.length;

        Predicate p = getPredicate(parts[0], numberOfArgs);

        return parseArgs(p,strArguments);

    }


    public ArrayList<AtomList> parseArgs(Predicate p, String[] argsRep){
        ArrayList<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(new Arguments());
        for(String argRep: argsRep){
            ArrayList<Arguments> newArgumentsList = new ArrayList<>();
            for(Term t: parseArg(argRep)){
                for(Arguments arguments: argumentsList){
                    newArgumentsList.add(arguments.add(t));
                }
            }
            argumentsList = newArgumentsList;
        }

        ArrayList<AtomList> result = new ArrayList<>();
        for(Arguments arguments: argumentsList){
            result.add(new AtomList(new Atom(p, arguments)));
        }
        return result;
    }

    public Arguments parseArg(String argRep){
        Arguments arguments = new Arguments();
        String[] argParts = argRep.split("\\\\/");
        for(String argPart: argParts){
            arguments = arguments.add(getTerm(argPart));
        }
        return arguments;
    }

    /**
     * Returns an atom based upon the string representation given.
     * @param atomRep string representation
     * @return atom which the string representation corresponds to
     */
    public Atom parseAtomOld(String atomRep) {
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


    private Term getTerm(String name) {
        if (terms.containsKey(name)) {
            return terms.get(name);
        } else {
            if (name.toLowerCase().equals(name)) {
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


    private Predicate getPredicate(String name, int numberOfArgs) {
        if (numberOfArgs < 0) throw new IllegalArgumentException("In Line: "+line+".\n Only a non-negative amount of arguments allowed");

        Predicate res;

        if (predicates.containsKey(name)) {
            res = predicates.get(name);
            if (res.nArgs() != numberOfArgs) {
                throw new IllegalArgumentException("In Line: "+line+".\n Logic.Predicate " + name + " contains an inconsistent of arguments");
            }
        } else {
            if(name.charAt(0) == '~'){
                String positiveName = name.substring(1);
                res = new UDNegation(getPredicate(positiveName, numberOfArgs));
            }else {
                res = new PredicateStd(name, numberOfArgs);
            }
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