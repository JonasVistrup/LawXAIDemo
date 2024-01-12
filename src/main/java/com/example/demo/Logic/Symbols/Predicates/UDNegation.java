package com.example.demo.Logic.Symbols.Predicates;


import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.SLD.SLDResolution;

public class UDNegation extends UDRelation {

    public final Predicate predicate;

    public UDNegation(Predicate predicate){
        super(predicate.toString(), predicate.nArgs());
        this.predicate = predicate;
    }
    @Override
    public String toString(Arguments args) {
        if(predicate.toString().equals("<")) return args.get(0).toString() + ">=" + args.get(1).toString();
        if(predicate.toString().equals("+") || predicate.toString().equals("-") || predicate.toString().equals("X") || predicate.toString().equals("/")){
            return args.get(0).toString() + predicate.toString() + args.get(1).toString() + "!=" + args.get(2).toString();
        }
        return "~"+predicate.toString(args);
    }

    public String explain(Arguments args){
        StringBuilder res = new StringBuilder("IKKE ").append(predicate.explanation.get(0));
        for(int i = 0; i<predicate.termPos.size(); i++){
            res.append(args.get(predicate.termPos.get(i)));
            res.append(predicate.explanation.get(i+1));
        }
        return res.toString();
    }

    @Override
    public boolean test(Arguments args) {
        return !SLDResolution.inOrderTraversalTest(new AtomList(new Atom(this.predicate, args)));
    }
}
