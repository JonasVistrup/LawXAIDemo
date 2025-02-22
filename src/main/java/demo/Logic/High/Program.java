package demo.Logic.High;

import demo.Logic.Symbols.Predicates.Predicate;

import java.util.*;

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

    public Predicate[] EDBs(){
        Set<Predicate> EDBs = new HashSet<>();
        Set<Predicate> IDBs = new HashSet<>();
        for(List<Clause> cc: clauses.values()){
            for(Clause c: cc){
                if(!c.body.isEmpty()){
                    IDBs.add(c.head.predicate());
                    EDBs.remove(c.head.predicate());
                    for(Atom a: c.body){
                        if(!IDBs.contains(a.predicate())) EDBs.add(a.predicate());
                    }
                }else{
                    if(!IDBs.contains(c.head.predicate())) EDBs.add(c.head.predicate());
                }
            }
        }

        Predicate[] res = new Predicate[EDBs.size()];
        int pos = 0;
        for(Predicate p: EDBs){
            res[pos++] = p;
        }

        return res;
    }

    public Predicate[] IDBs(){
        Set<Predicate> IDBs = new HashSet<>();
        for(List<Clause> cc: clauses.values()){
            for(Clause c: cc){
                if(!c.body.isEmpty()) IDBs.add(c.head.predicate());
            }
        }

        Predicate[] res = new Predicate[IDBs.size()];
        int pos = 0;
        for(Predicate p: IDBs){
            res[pos++] = p;
        }

        return res;
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
