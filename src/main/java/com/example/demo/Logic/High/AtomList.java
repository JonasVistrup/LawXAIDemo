package com.example.demo.Logic.High;

import com.example.demo.Logic.Symbols.Variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AtomList implements Iterable<Atom>{

    protected ArrayList<Atom> atoms;


    /**
     * Constructs an empty list of atoms.
     */
    public AtomList(){
        this.atoms = new ArrayList<>();
    }

    /**
     * Constructs a list of atoms with atom as a single element in the list.
     * @param atom the single atom added.
     */
    public AtomList(Atom atom){
        this();
        this.atoms.add(atom);
    }

    /**
     * Constructs a list of atoms based upon the list of atoms given.
     * @param atoms list of atom this list is based upon.
     */
    public AtomList(ArrayList<Atom> atoms){
        this.atoms = new ArrayList<>(atoms);
    }

    public AtomList(AtomList list){
        this(list.atoms);
    }
    /**
     * Returns a list with an atom added to this that list.
     * @param atom atom whose presence in this collection is to be ensured
     * @return new AtomList
     */

    public AtomList add(Atom atom) {
        AtomList result = new AtomList(this.atoms);
        result.atoms.add(atom);
        return result;
    }

    /**
     * Creates a new list equal to this with the atoms in c appended onto the end.
     * @param c collection containing elements to be added to this collection.
     * @return true iff this list is changed by this operation.
     */

    public AtomList add(Collection<? extends Atom> c) {
        AtomList result = new AtomList(this.atoms);
        result.atoms.addAll(c);
        return result;
    }

    public AtomList remove(Atom atom) {
        AtomList result = new AtomList(this.atoms);
        result.atoms.remove(atom);
        return result;
    }

    public AtomList addAndRemove(AtomList toAdd, Atom toRemove){
        AtomList result = new AtomList(this.atoms);
        result.atoms.remove(toRemove);
        result.atoms.addAll(toAdd.atoms);
        return result;
    }

    public List<Atom> atoms(){
        return this.atoms;
    }

    public int size(){
        return this.atoms.size();
    }

    public Atom get(int i){
        return atoms.get(i);
    }


    /**
     * Returns a comma separated string of the atoms in the list.
     * @return string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(Atom a: atoms){
            b.append(a.toString());
            b.append(",");
        }
        if(!atoms.isEmpty()){
            b.deleteCharAt(b.length()-1);
        }
        return b.toString();
    }

    /**
     * Returns a new list consisting of all elements in this list that is not in the other list.
     * @param other list to remove from this
     * @return this list \ other
     */
    //TODO: is this slow?
    public AtomList without(AtomList other){
        AtomList atomList = new AtomList(this.atoms);
        atomList.atoms.removeAll(other.atoms);
        return atomList;
    }

    public boolean containsTerm(Variable from) {
        for(Atom a: this.atoms){
            if(a.containsTerm(from)) return true;
        }
        return false;
    }

    @Override
    public Iterator<Atom> iterator() {
        return atoms.iterator();
    }

    public boolean isEmpty() {
        return this.atoms.isEmpty();
    }

    public AtomList applySub(Substitution substitution){
        return new AtomList((ArrayList<Atom>) this.atoms.stream().map(x->x.applySub(substitution)).collect(Collectors.toList()) );
    }
}
