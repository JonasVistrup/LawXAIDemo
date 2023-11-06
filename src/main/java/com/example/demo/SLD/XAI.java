package com.example.demo.SLD;


import com.example.demo.Logic.High.*;
import com.example.demo.Logic.ProgramBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class XAI {

    public static final ProgramBuilder pb = new ProgramBuilder();

    public static void addUDPs(String udpPath){
        String nextLine = "";
        try{
            Scanner input = new Scanner(new File(udpPath));
            while(input.hasNextLine()){
                nextLine = input.nextLine();
                pb.addUDPredicate(nextLine);
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
                    pb.addPredicate(parts[0], parts[1]);
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException(e.getMessage()+" (Line "+lineNumber+"), [" +line+"].");
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("file is missing at location "+predicatePath);
        }
    }

    public static void addRules(String rulesPath){
        try {
            Scanner scanner = new Scanner(new File(rulesPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() == 0 || line.charAt(0) == '!') continue;
                String[] parts = removeEOL(line).split(":");
                if(parts.length<2) throw new IllegalStateException("Clause in {"+line+"} has no reasons or is incorrectly formatted");
                ArrayList<String> reasons = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));
                pb.parseClause(parts[0],reasons,line);
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


    public static List<Substitution> query(List<String> facts, AtomList query, HashMap<Atom, List<Clause>> groundClausesUsed){
        pb.removeAllFacts();
        for(String s: facts){
            pb.addFact(s);
        }

        List<Substitution> substitutions = SLDResolution.findSubstitutions(query, groundClausesUsed);

        return removeDuplicates(substitutions,query);
    }

    private static List<Substitution> removeDuplicates(List<Substitution> substitutions, AtomList query){
        Program p = pb.getProgram();
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
        for(Clause c: pb.getProgram()){
            System.out.println("\t"+c);
        }
    }
}
