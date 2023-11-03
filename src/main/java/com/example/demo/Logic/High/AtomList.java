package com.example.demo.Logic.High;


import com.example.demo.Logic.Symbols.Predicates.UDFunction;
import com.example.demo.Logic.Symbols.Predicates.UDRelation;
import com.example.demo.Logic.Symbols.Variable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// Immutable

public class AtomList implements Iterable<Atom>{
    private final Atom[] standardAtoms;
    private final Atom[] functionAtoms;
    private final Atom[] relationAtoms;

    private boolean sorted = false;

    /**
     * Constructs an empty list of atoms.
     */
    public AtomList(){
        this.standardAtoms = new Atom[0];
        this.functionAtoms = new Atom[0];
        this.relationAtoms = new Atom[0];
    }

    /**
     * Constructs a list of atoms with atom as a single element in the list.
     * @param atom the single atom added.
     */
    public AtomList(Atom atom){
        if(atom.type() == Atom.Type.STANDARD) {
            this.standardAtoms = new Atom[1];
            this.functionAtoms = new Atom[0];
            this.relationAtoms = new Atom[0];
            this.standardAtoms[0] = atom;
        }else if(atom.type() == Atom.Type.FUNCTION){
            this.standardAtoms = new Atom[0];
            this.functionAtoms = new Atom[1];
            this.relationAtoms = new Atom[0];
            this.functionAtoms[0] = atom;
        }else{
            this.standardAtoms = new Atom[0];
            this.functionAtoms = new Atom[0];
            this.relationAtoms = new Atom[1];
            this.relationAtoms[0] = atom;
        }
    }

    /**
     * Constructs a list of atoms based upon the list of atoms given.
     * @param atoms list of atom this list is based upon.
     */
    public AtomList(List<Atom> atoms){
        int standardAtomsCounter = 0;
        int functionAtomsCounter = 0;
        int relationAtomsCounter = 0;
        for(Atom a: atoms){
            if(a.type() == Atom.Type.STANDARD) {
                standardAtomsCounter++;
            }else if(a.type() == Atom.Type.FUNCTION) {
                functionAtomsCounter++;
            }else {
                relationAtomsCounter++;
            }
        }
        this.standardAtoms = new Atom[standardAtomsCounter];
        this.functionAtoms = new Atom[functionAtomsCounter];
        this.relationAtoms = new Atom[relationAtomsCounter];
        standardAtomsCounter = 0;
        functionAtomsCounter = 0;
        relationAtomsCounter = 0;
        for(Atom a: atoms){
            if(a.type() == Atom.Type.STANDARD) {
                this.standardAtoms[standardAtomsCounter++] = a;
            }else if(a.type() == Atom.Type.FUNCTION) {
                this.functionAtoms[functionAtomsCounter++] = a;
            }else {
                this.relationAtoms[relationAtomsCounter++] = a;
            }
        }
        Arrays.sort(this.functionAtoms);
    }

    public void sort(){
        if(sorted) return;

        Arrays.sort(this.standardAtoms);
        Arrays.sort(this.functionAtoms);
        Arrays.sort(this.relationAtoms);

        sorted = true;
    }




    private AtomList(AtomList list, Atom atom, boolean add){
        if(add) {
            if (atom.type() == Atom.Type.STANDARD) {
                this.standardAtoms = new Atom[list.standardAtoms.length + 1];
                this.functionAtoms = list.functionAtoms.clone();
                this.relationAtoms = list.relationAtoms.clone();

                System.arraycopy(list.standardAtoms, 0, this.standardAtoms, 0, list.standardAtoms.length);
                this.standardAtoms[list.standardAtoms.length] = atom;
            } else if (atom.type() == Atom.Type.FUNCTION) {
                this.standardAtoms = list.standardAtoms.clone();
                this.functionAtoms = new Atom[list.functionAtoms.length + 1];
                this.relationAtoms = list.relationAtoms.clone();

                System.arraycopy(list.functionAtoms, 0, this.functionAtoms, 0, list.functionAtoms.length);
                this.functionAtoms[list.functionAtoms.length] = atom;
            } else {
                this.standardAtoms = list.standardAtoms.clone();
                this.functionAtoms = list.functionAtoms.clone();
                this.relationAtoms = new Atom[list.relationAtoms.length + 1];

                System.arraycopy(list.relationAtoms, 0, this.relationAtoms, 0, list.relationAtoms.length);
                this.relationAtoms[list.relationAtoms.length] = atom;
            }
        }else{
            if (atom.type() == Atom.Type.STANDARD) {
                this.standardAtoms = new Atom[list.standardAtoms.length - 1];
                this.functionAtoms = list.functionAtoms.clone();
                this.relationAtoms = list.relationAtoms.clone();

                copyAllExcept(list.standardAtoms, this.standardAtoms, atom);
            } else if (atom.type() == Atom.Type.FUNCTION) {
                this.standardAtoms = list.standardAtoms.clone();
                this.functionAtoms = new Atom[list.functionAtoms.length - 1];
                this.relationAtoms = list.relationAtoms.clone();

                copyAllExcept(list.functionAtoms, this.functionAtoms, atom);
            } else {
                this.standardAtoms = list.standardAtoms.clone();
                this.functionAtoms = list.functionAtoms.clone();
                this.relationAtoms = new Atom[list.relationAtoms.length - 1];

                copyAllExcept(list.relationAtoms, this.relationAtoms, atom);
            }
        }
    }

    private AtomList(AtomList list, AtomList listToAdd){
        this.standardAtoms = new Atom[list.standardAtoms.length + listToAdd.standardAtoms.length];
        this.functionAtoms = new Atom[list.functionAtoms.length + listToAdd.functionAtoms.length];
        this.relationAtoms = new Atom[list.relationAtoms.length + listToAdd.relationAtoms.length];

        System.arraycopy(list.standardAtoms, 0, this.standardAtoms, 0, list.standardAtoms.length);
        System.arraycopy(listToAdd.standardAtoms, 0, this.standardAtoms, list.standardAtoms.length, listToAdd.standardAtoms.length);

        System.arraycopy(list.functionAtoms, 0, this.functionAtoms, 0, list.functionAtoms.length);
        System.arraycopy(listToAdd.functionAtoms, 0, this.functionAtoms, list.functionAtoms.length, listToAdd.functionAtoms.length);

        System.arraycopy(list.relationAtoms, 0, this.relationAtoms, 0, list.relationAtoms.length);
        System.arraycopy(listToAdd.relationAtoms, 0, this.relationAtoms, list.relationAtoms.length, listToAdd.relationAtoms.length);
    }

    private AtomList(AtomList list, AtomList listToAdd, Atom atomToRemove){
        if (atomToRemove.type() == Atom.Type.STANDARD) {
            this.standardAtoms = new Atom[list.standardAtoms.length + listToAdd.standardAtoms.length - 1];
            this.functionAtoms = new Atom[list.functionAtoms.length + listToAdd.functionAtoms.length];
            this.relationAtoms = new Atom[list.relationAtoms.length + listToAdd.relationAtoms.length];

            copyAllExcept(list.standardAtoms, this.standardAtoms, atomToRemove);
            System.arraycopy(listToAdd.standardAtoms, 0, this.standardAtoms, list.standardAtoms.length-1, listToAdd.standardAtoms.length);

            System.arraycopy(list.functionAtoms, 0, this.functionAtoms, 0, list.functionAtoms.length);
            System.arraycopy(listToAdd.functionAtoms, 0, this.functionAtoms, list.functionAtoms.length, listToAdd.functionAtoms.length);

            System.arraycopy(list.relationAtoms, 0, this.relationAtoms, 0, list.relationAtoms.length);
            System.arraycopy(listToAdd.relationAtoms, 0, this.relationAtoms, list.relationAtoms.length, listToAdd.relationAtoms.length);
        } else if (atomToRemove.type() == Atom.Type.FUNCTION) {
            this.standardAtoms = new Atom[list.standardAtoms.length + listToAdd.standardAtoms.length];
            this.functionAtoms = new Atom[list.functionAtoms.length + listToAdd.functionAtoms.length - 1];
            this.relationAtoms = new Atom[list.relationAtoms.length + listToAdd.relationAtoms.length];

            System.arraycopy(list.standardAtoms, 0, this.standardAtoms, 0, list.standardAtoms.length);
            System.arraycopy(listToAdd.standardAtoms, 0, this.standardAtoms, list.standardAtoms.length, listToAdd.standardAtoms.length);

            copyAllExcept(list.functionAtoms, this.functionAtoms, atomToRemove);
            System.arraycopy(listToAdd.functionAtoms, 0, this.functionAtoms, list.functionAtoms.length-1, listToAdd.functionAtoms.length);

            System.arraycopy(list.relationAtoms, 0, this.relationAtoms, 0, list.relationAtoms.length);
            System.arraycopy(listToAdd.relationAtoms, 0, this.relationAtoms, list.relationAtoms.length, listToAdd.relationAtoms.length);
        } else {
            this.standardAtoms = new Atom[list.standardAtoms.length + listToAdd.standardAtoms.length];
            this.functionAtoms = new Atom[list.functionAtoms.length + listToAdd.functionAtoms.length];
            this.relationAtoms = new Atom[list.relationAtoms.length + listToAdd.relationAtoms.length - 1];

            System.arraycopy(list.standardAtoms, 0, this.standardAtoms, 0, list.standardAtoms.length);
            System.arraycopy(listToAdd.standardAtoms, 0, this.standardAtoms, list.standardAtoms.length, listToAdd.standardAtoms.length);

            System.arraycopy(list.functionAtoms, 0, this.functionAtoms, 0, list.functionAtoms.length);
            System.arraycopy(listToAdd.functionAtoms, 0, this.functionAtoms, list.functionAtoms.length, listToAdd.functionAtoms.length);

            copyAllExcept(list.relationAtoms, this.relationAtoms, atomToRemove);
            System.arraycopy(listToAdd.relationAtoms, 0, this.relationAtoms, list.relationAtoms.length-1, listToAdd.relationAtoms.length);
        }
    }

    private AtomList(AtomList list, Substitution substitution){
        this.standardAtoms = new Atom[list.standardAtoms.length];
        this.functionAtoms = new Atom[list.functionAtoms.length];
        this.relationAtoms = new Atom[list.relationAtoms.length];

        copyAndApplySub(list.standardAtoms, this.standardAtoms, substitution);
        copyAndApplySub(list.functionAtoms, this.functionAtoms, substitution);
        copyAndApplySub(list.relationAtoms, this.relationAtoms, substitution);

    }

    private AtomList(AtomList list, Clause clause, int atomIndex, Substitution substitution){
        this.standardAtoms = new Atom[list.standardAtoms.length - 1 + clause.body.standardAtoms.length];
        this.functionAtoms = new Atom[list.functionAtoms.length + clause.body.functionAtoms.length];
        this.relationAtoms = new Atom[list.relationAtoms.length + clause.body.relationAtoms.length];

        copyAndApplySub(list.functionAtoms, this.functionAtoms, substitution);
        for(int i = 0; i<clause.body.functionAtoms.length; i++){
            this.functionAtoms[list.functionAtoms.length+i] = clause.body.functionAtoms[i].applySub(substitution);
        }

        copyAndApplySub(list.relationAtoms, this.relationAtoms, substitution);
        for(int i = 0; i<clause.body.relationAtoms.length; i++){
            this.relationAtoms[list.relationAtoms.length+i] = clause.body.relationAtoms[i].applySub(substitution);
        }

        for(int i = 0; i<atomIndex; i++){
            this.standardAtoms[i] = list.standardAtoms[i].applySub(substitution);
        }
        for(int i = atomIndex+1; i<list.standardAtoms.length; i++){
            this.standardAtoms[i-1] = list.standardAtoms[i].applySub(substitution);
        }
        for(int i = 0; i<clause.body.standardAtoms.length; i++){
            this.standardAtoms[(list.standardAtoms.length-1) + i] = clause.body.standardAtoms[i].applySub(substitution);
        }
    }

    /**
     * Assumse that removedPosition is ordered in an increasing manner and contains at least one position.
     * @param list Atomlist to copy from.
     * @param removedPositions Positions of atoms to remove from list.
     * @param substitution Substitution to apply on the atoms in list.
     */
    private AtomList(AtomList list, int[] removedPositions, Substitution substitution) {
        int functionSize = list.functionAtoms.length;
        int relationSize = list.relationAtoms.length;

        for(int pos: removedPositions){
            if(list.get(pos).type() == Atom.Type.FUNCTION){
                functionSize--;
            }else{
                relationSize--;
            }
        }

        this.standardAtoms = new Atom[list.standardAtoms.length];
        this.functionAtoms = new Atom[functionSize];
        this.relationAtoms = new Atom[relationSize];

        copyAndApplySub(list.standardAtoms, this.standardAtoms, substitution);

        int nextIndex = 0;
        int offset = list.standardAtoms.length;
        int nextPos = removedPositions[nextIndex]-offset;
        for(int i = 0; i<list.functionAtoms.length; i++){
            if(nextPos == i){
                if(++nextIndex < removedPositions.length){
                    nextPos = removedPositions[nextIndex]-offset;
                }
            }else{
                this.functionAtoms[i-nextIndex] = list.functionAtoms[i].applySub(substitution);
            }
        }

        offset = list.standardAtoms.length + list.functionAtoms.length;
        nextPos = removedPositions[nextIndex]-offset;
        for(int i = 0; i<list.relationAtoms.length; i++){
            if(nextPos == i){
                if(++nextIndex < removedPositions.length){
                    nextPos = removedPositions[nextIndex]-offset;
                }
            }else{
                this.relationAtoms[i-nextIndex] = list.relationAtoms[i].applySub(substitution);
            }
        }

    }

    private void copyAndApplySub(Atom[] src, Atom[] dest, Substitution substitution){
        for(int i = 0; i<src.length; i++){
            dest[i] = src[i].applySub(substitution);
        }
    }

    private void copyAllExcept(Atom[] src, Atom[] dest, Atom without){
        int pos = getPos(src, without);
        System.arraycopy(src, 0, dest, 0, pos);
        System.arraycopy(src, pos+1, dest, pos, (src.length-1)-pos);
    }

    private int getPos(Atom[] arr, Atom atom){ //TODO check if it can be changed to an object equality
        int pos = 0;
        while(!arr[pos].equals(atom)){
            pos++;
        }
        return pos;
    }


    /**
     * Returns a list with an atom added to this that list.
     * @param atom atom whose presence in this collection is to be ensured
     * @return new AtomList
     */
    public AtomList add(Atom atom) {
        return new AtomList(this, atom, true);
    }

    /**
     * Creates a new list equal to this with the atoms in other appended onto the end.
     * @param other the Atomlist to append to the end of this.
     * @return true iff this list is changed by this operation.
     */

    public AtomList add(AtomList other) {
        return new AtomList(this, other);
    }

    public AtomList remove(Atom atom) {
        return new AtomList(this, atom, false);
    }

    public AtomList addAndRemove(AtomList toAdd, Atom toRemove){
        return new AtomList(this, toAdd, toRemove);
    }

    public AtomList applyClause(Clause clause, int atomIndex, Substitution substitution){
        return new AtomList(this, clause, atomIndex, substitution);
    }

    public int size(){
        return this.standardAtoms.length + this.functionAtoms.length + this.relationAtoms.length;
    }

    public Atom get(int i){
        if(i < this.standardAtoms.length){
            return this.standardAtoms[i];
        }else{
            i = i - this.standardAtoms.length;
            if(i < this.functionAtoms.length){
                return this.functionAtoms[i];
            }else{
                i = i - this.functionAtoms.length;
                return this.relationAtoms[i];
            }
        }
    }


    /**
     * Returns a comma separated string of the atoms in the list.
     * @return string representation of this list
     */
    @Override
    public String toString() {
        if(this.isEmpty()){
            return "";
        }
        StringBuilder b = new StringBuilder();
        for(Atom a: standardAtoms){
            b.append(a.toString());
            b.append(",");
        }
        for(Atom a: functionAtoms){
            b.append(a.toString());
            b.append(",");
        }
        for(Atom a: relationAtoms){
            b.append(a.toString());
            b.append(",");
        }
        b.deleteCharAt(b.length()-1);

        return b.toString();
    }

    public boolean containsTerm(Variable from) {
        for(Atom a: this.standardAtoms){
            if(a.containsTerm(from)) return true;
        }
        for(Atom a: this.functionAtoms){
            if(a.containsTerm(from)) return true;
        }
        for(Atom a: this.relationAtoms){
            if(a.containsTerm(from)) return true;
        }
        return false;
    }

    @Override
    public Iterator<Atom> iterator() {
        return new Iterator<>() {
            int next = 0;
            @Override
            public boolean hasNext() {
                return next<size();
            }

            @Override
            public Atom next() {
                return get(next++);
            }
        };
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public AtomList applySub(Substitution substitution){
        return new AtomList(this, substitution);
    }

    public int[] getRunnableUDPsPositions() {
        int[] pos = new int[nRunnableUDPs()];
        int nextPos = 0;
        for(int i = 0; i<this.functionAtoms.length; i++){
            if(((UDFunction)this.functionAtoms[i].predicate()).runnable(this.functionAtoms[i].args)){
                pos[nextPos++] = this.standardAtoms.length+i;
            }
        }

        for(int i = 0; i<this.relationAtoms.length; i++){
            if(((UDRelation)this.relationAtoms[i].predicate()).runnable(this.relationAtoms[i].args)){
                pos[nextPos++] = this.standardAtoms.length+this.functionAtoms.length+i;
            }
        }
        return pos;
    }

    private int nRunnableUDPs(){
        int amount = 0;
        for (Atom functionAtom : this.functionAtoms) {
            if (((UDFunction) functionAtom.predicate()).runnable(functionAtom.args)) {
                amount++;
            }
        }

        for (Atom relationAtom : this.relationAtoms) {
            if (((UDRelation) relationAtom.predicate()).runnable(relationAtom.args)) {
                amount++;
            }
        }
        return amount;
    }

    public Substitution runUDPs(int[] runnableUDPsPositions) {
        Substitution currentSub = new Substitution();
        for(int pos : runnableUDPsPositions){
            pos = pos - this.standardAtoms.length;
            if(pos < functionAtoms.length){
                Atom a = functionAtoms[pos].applySub(currentSub);
                Substitution solution = ((UDFunction) a.predicate()).solve(a.args);
                if(solution == null){
                    return null;
                }
                currentSub = currentSub.add(solution);
            }else{
                Atom a = relationAtoms[pos - functionAtoms.length].applySub(currentSub);
                if(!((UDRelation) a.predicate()).test(a.args)){
                    return null;
                }
            }
        }
        return currentSub;
    }

    public AtomList removeAndSub(int[] removedPositions, Substitution substitution) {
        return new AtomList(this, removedPositions, substitution);
    }

    public int nStandardAtoms() {
        return this.standardAtoms.length;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AtomList)) return false;
        AtomList o = (AtomList) obj;

        this.sort();
        o.sort();

        return Arrays.equals(this.standardAtoms,o.standardAtoms) && Arrays.equals(this.functionAtoms,o.functionAtoms) && Arrays.equals(this.relationAtoms, o.relationAtoms);
    }
}
