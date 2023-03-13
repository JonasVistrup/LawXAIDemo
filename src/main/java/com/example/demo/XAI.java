package com.example.demo;

import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.ProgramBuilder;
import com.example.demo.SLD.Answer;
import com.example.demo.SLD.SLDResolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class XAI {

    public ProgramBuilder pb;
    public XAI(String predicatePath){
        this.pb = new ProgramBuilder();
        try {
            Scanner scanner = new Scanner(new File(predicatePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length()==0) continue;
                String[] parts = line.split(";");
                if(parts.length!=2) throw new IllegalStateException("Predicate in {"+line+"} has no explanation or is not correctly formatted");
                pb.addPredicate(parts[0],parts[1]);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("file is missing at location "+predicatePath);
        }
    }

    public void addRules(String rulesPath){
        try {
            Scanner scanner = new Scanner(new File(rulesPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length()==0) continue;
                String[] parts = line.split(";");
                if(parts.length<2) throw new IllegalStateException("Clause in {"+line+"} has no reasons or is incorrectly formatted");
                ArrayList<String> reasons = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));
                pb.addClause(parts[0],reasons);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("file is missing at location "+rulesPath);
        }
    }

    public List<Answer> query(List<String> facts, String queryRep){
        String[] atomListRep = queryRep.replaceAll(" ","").replaceAll("\\),\\(",");(").split(";");
        ArrayList<Atom> query = new ArrayList<>();
        for(String atomRep: atomListRep){
            query.add(pb.parseAtom(atomRep));
        }

        return this.query(facts, new AtomList(query));
    }

    public List<Answer> query(List<String> facts, AtomList query){
        ProgramBuilder programBuilder = pb.copy();
        for(String s: facts){
            programBuilder.addFact(s);
        }

        return SLDResolution.findSubstitutions(programBuilder.getProgram(), new AtomList(query));
    }


}
