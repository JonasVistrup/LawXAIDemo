package com.example.demo.SLD;


import com.example.demo.Grammar.LawXAILexer;
import com.example.demo.Grammar.LawXAIParser;
import com.example.demo.Grammar.ProgramParserVisitor;
import com.example.demo.Logic.High.*;
import com.example.demo.Logic.ProgramBuilder;
import com.example.demo.Logic.ProgramParser;
import com.example.demo.Logic.Symbols.Predicates.PredicateUD;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class XAI {

    public static final ProgramParser pp = new ProgramParser();
    private static List<Clause> clauses = new ArrayList<>();
    private static List<Atom> facts = new ArrayList<>();

    public static void addUDPs(String udpPath){
        String nextLine = "";
        try{
            Scanner input = new Scanner(new File(udpPath));
            while(input.hasNextLine()){
                nextLine = input.nextLine();
                pp.addUDPredicate(nextLine);
            }
            input.close();
        }catch (FileNotFoundException e) {
            throw new IllegalStateException("file " + udpPath +  " can not be found.");
        }catch (ClassNotFoundException e){
            throw new IllegalArgumentException("Class not found for "+nextLine+".");
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            throw new IllegalArgumentException("Constructor not found.");
        }
    }
    public static void addPredicates(String predicatePath){
        int lineNumber = 0;
        try {
            Scanner scanner = new Scanner(new File(predicatePath));
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                if(line.length() == 0 || line.charAt(0) == '!') continue;
                String[] parts = removeEOL(line).split(":");
                if(parts.length!=2) throw new IllegalStateException("Predicate in {"+line+"} has no explanation or is not correctly formatted");
                try {
                    pp.addPredicate(parts[0], parts[1]);
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException(e.getMessage()+" (Line "+lineNumber+"), [" +line+"].");
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("file is missing at location "+predicatePath);
        }
    }

    public static void addRules(String rulesPath){
        System.out.println(rulesPath);
        try {
            CharStream inputStream = CharStreams.fromFileName(rulesPath);
            LawXAILexer lexer = new LawXAILexer(inputStream);
            var commonTokenStream = new CommonTokenStream(lexer);
            var parser = new LawXAIParser(commonTokenStream);
            var visitor = new ProgramParserVisitor(pp);
            clauses.addAll(visitor.visitProg(parser.prog()));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("file is missing at location "+rulesPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addRulesOLD(String rulesPath){
        try {
            Scanner scanner = new Scanner(new File(rulesPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() == 0 || line.charAt(0) == '!') continue;
                String[] parts = removeEOL(line).split(":");
                if(parts.length<2) throw new IllegalStateException("Clause in {"+line+"} has no reasons or is incorrectly formatted");
                ArrayList<String> reasons = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));
                //pp.parseClause(parts[0],reasons,line);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("file is missing at location "+rulesPath);
        }
    }

    private static String removeEOL(String line) {
        for(int i = line.length()-1; i>=0; i--){
            if(line.charAt(i) == ';') return line.substring(0,i);
            if(line.charAt(i) != ' ') throw new IllegalStateException("Predicate in {"+line+"} does not end with ;");
        }
        throw new IllegalStateException("Predicate in {"+line+"} does not end with ;");
    }


    public static List<Substitution> query(List<String> stringfacts, AtomList query, HashMap<Atom, List<Clause>> groundClausesUsed){
        facts = stringfacts.stream().map(pp::parseAtomOld).collect(Collectors.toList());
        Program p = getProgram();
        List<Substitution> substitutions = SLDResolution.findSubstitutions(query, groundClausesUsed);

        return removeDuplicates(substitutions,query);
    }

    private static List<Substitution> removeDuplicates(List<Substitution> substitutions, AtomList query){
        Program p = getProgram();
        List<Substitution> uniqueSubstitutions = new ArrayList<>();
        for(Substitution s: substitutions){
            boolean uniqueSubstitution = true;
            Substitution onlyRelevant = s.relevantSubsetSubstitution(query);
            for(Substitution s2: uniqueSubstitutions){
                if(s2.equals(onlyRelevant)) uniqueSubstitution = false;
            }
            if(uniqueSubstitution) uniqueSubstitutions.add(onlyRelevant);
        }
        return uniqueSubstitutions;
    }


    public static void printRules() {
        System.out.println("RULES:");
        for(Clause c: pp.getProgram()){
            System.out.println("\t"+c);
        }
    }

    public static Program getProgram(){
        ArrayList<Clause> allClauses = new ArrayList<>(clauses);
        ArrayList<String> reasons = new ArrayList<>();
        reasons.add("Faktum");

        ArrayList<String> temporalReason = new ArrayList<>();
        reasons.add("Er sandt på et tidspunkt hvis altid sandt");
        for(Atom a: facts){
            allClauses.add(new Clause(a, new AtomList(), reasons));
            if(!a.hasTemporalTerm() && !(a.predicate() instanceof PredicateUD)){
                allClauses.add(new Clause(new Atom(a.predicate(), a.args.add(pp.generateTerm("Var0"))), new AtomList(a), temporalReason));
            }
        }
        return new Program(allClauses);
    }
}
