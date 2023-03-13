package com.example.demo.Logic.High;

import com.example.demo.Logic.Symbols.Predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A set of logical clauses called a program.
 */
public class Program implements Iterable<Clause>{
    /**
     * List of program clauses.
     */
    public final HashMap<Predicate, List<Clause>> clauses;


    /**
     * Constructs a program given a list of clauses
     * @param clauses clauses of the program
     */
    public Program(List<Clause> clauses) {
        this.clauses = new HashMap<>();
        for(Clause c: clauses){
            add(c);
        }
    }

    private void add(Clause c){
        if(clauses.containsKey(c.head.predicate())){
            List<Clause> clauseList = this.clauses.get(c.head.predicate());
            clauseList.add(c);
        }else{
            List<Clause> clauseList = new ArrayList<>();
            clauseList.add(c);
            clauses.put(c.head.predicate(), clauseList);
        }
    }

    /**
     * Returns a string representation of this program consisting of every clause in the program.
     * @return string representation of this program.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Clause c : this) {
            builder.append(c.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public Iterator<Clause> iterator() {
        ArrayList<Clause> allClauses = new ArrayList<>();
        for(List<Clause> clauses: this.clauses.values()){
            allClauses.addAll(clauses);
        }
        return allClauses.iterator();
    }
}
