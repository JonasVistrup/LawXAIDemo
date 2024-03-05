package com.example.demo.Grammar;

import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.High.Atom;
import com.example.demo.Logic.High.AtomList;
import com.example.demo.Logic.High.Clause;
import com.example.demo.Logic.ProgramParser;
import com.example.demo.Logic.Symbols.Term;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramParserVisitor extends LawXAIBaseVisitor<Object>{

    private final ProgramParser pp;

    public ProgramParserVisitor(ProgramParser pp){
        this.pp = pp;
    }

    @Override public List<Clause> visitProg(LawXAIParser.ProgContext ctx) { return visitClauses(ctx.clauses()); }
    @Override public List<Clause> visitClauses(LawXAIParser.ClausesContext ctx) {
        return ctx.clause().stream().map(this::visitClause).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override public List<Clause> visitClause(LawXAIParser.ClauseContext ctx) {
        ArrayList<String> reasonings = ctx.reasonings() != null? visitReasonings(ctx.reasonings()): new ArrayList<>();
        Atom head = visitHead(ctx.head());
        List<Clause> result = new ArrayList<>();
        if(ctx.body() == null){
            result.add(new Clause(head, new AtomList(), reasonings));
        }else{
            for(AtomList body: visitBody(ctx.body())){
                result.add(new Clause(head, body, reasonings));
            }
        }
        return result;
    }

    @Override public ArrayList<String> visitReasonings(LawXAIParser.ReasoningsContext ctx) {
        return ctx.string().stream().map(this::visitString).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override public List<AtomList> visitBody(LawXAIParser.BodyContext ctx) {
        List<AtomList> result = new ArrayList<>();
        result.add(new AtomList());
        for(LawXAIParser.D_atomContext d_atom: ctx.d_atom()){
            List<AtomList> next = new ArrayList<>();

            for(AtomList aListSecond: visitD_atom(d_atom)){
                for(AtomList aListFirst: result){
                    next.add(aListFirst.add(aListSecond));
                }
            }
            result = next;
        }
        return result;
    }

    @Override public Atom visitHead(LawXAIParser.HeadContext ctx) {
        List<AtomList> headAtom = visitAtom(ctx.atom());
        if(headAtom.size() != 1 || headAtom.get(0).size() != 1) throw new IllegalStateException("PARSE EXCEPTION: Head generates to many atoms");
        return headAtom.get(0).get(0);
    }

    @Override public List<AtomList> visitD_atom(LawXAIParser.D_atomContext ctx) {
        return ctx.not_d_atom().stream().map(this::visitNot_d_atom).flatMap(Collection::stream).collect(Collectors.toList());
    }
    @Override public List<AtomList> visitC_atom(LawXAIParser.C_atomContext ctx) {
        List<AtomList> ORatomlist = new ArrayList<>();
        ORatomlist.add(new AtomList());
        List<List<AtomList>> childList = ctx.not_c_atom().stream().map(this::visitNot_c_atom).collect(Collectors.toList());
        for(List<AtomList> ORchildAtoms: childList){
            List<AtomList> next = new ArrayList<>();
            for(AtomList atomList1: ORatomlist){
                for(AtomList atomList2: ORchildAtoms){
                    next.add(atomList1.add(atomList2));
                }
            }
            ORatomlist = next;
        }

        return ORatomlist;
    }
    @Override public List<AtomList> visitNot_d_atom(LawXAIParser.Not_d_atomContext ctx) {
        if(ctx.atom() != null) return visitAtom(ctx.atom());
        else if(ctx.c_atom() != null) return visitC_atom(ctx.c_atom());
        else throw new IllegalStateException();
    }

    @Override public List<AtomList> visitNot_c_atom(LawXAIParser.Not_c_atomContext ctx) {
        if(ctx.atom() != null) return visitAtom(ctx.atom());
        else if(ctx.d_atom() != null) return visitD_atom(ctx.d_atom());
        else throw new IllegalStateException();
    }

    @Override public List<AtomList> visitAtom(LawXAIParser.AtomContext ctx) {
        String p_string = visitPredicate(ctx.predicate());

        Pair<List<List<Arguments>>,List<AtomList>> pair = visitArgument(ctx.argument());
        List<List<Arguments>> ORargumentsList = pair.getKey();
        List<AtomList> ORAtoms = pair.getValue();

        List<AtomList> result = new ArrayList<>();
        for(int i = 0; i<ORargumentsList.size(); i++){
            List<Arguments> ANDargList = ORargumentsList.get(i);
            List<Atom> next = new ArrayList<>();
            for(Arguments args: ANDargList){
                next.add(pp.parseAtomOld(p_string+"("+args.toString()+")"));
            }

            result.add(new AtomList(next).add(ORAtoms.get(i)));
        }
        result.addAll(pair.getValue());
        return result;
    }

    @Override public String visitPredicate(LawXAIParser.PredicateContext ctx) {
        String s;
        if(ctx.math() != null){
            s = visitMath(ctx.math());
        }else if(ctx.VARIABLE() != null){
            s = ctx.VARIABLE().toString();
        }else if(ctx.CONSTANT() != null) {
            s = ctx.CONSTANT().toString();
        }else{
            throw new IllegalStateException();
        }
        if(ctx.NEGATED() != null){
            return "~" + s;
        }else{
            return s;
        }
    }


    //
    @Override public Pair<List<List<Arguments>>,List<AtomList>> visitArgument(LawXAIParser.ArgumentContext ctx) {
        List<List<Arguments>> ORlist = new ArrayList<>();
        ORlist.add(new ArrayList<>());
        ORlist.get(0).add(new Arguments());

        List<AtomList> current = new ArrayList<>();
        current.add(new AtomList());

        List<Pair<List<List<Term>>,List<AtomList>>> children = ctx.g_term().stream().map(this::visitG_term).collect(Collectors.toList());


        List<List<Arguments>> res1 = new ArrayList<>();
        List<AtomList> res2 = new ArrayList<>();


        for(Pair<List<List<Term>>,List<AtomList>> pair: children){
            ORlist = ParseG_term1(ORlist,pair.getKey());
            current = ParseG_term2(current, pair.getValue());
        }

        return new Pair<>(ORlist,current);
    }


    private  Pair<List<List<Arguments>>,List<AtomList>> ParseG_term(Pair<List<List<Arguments>>,List<AtomList>> current, Pair<List<List<Term>>,List<AtomList>> g_term){
        return new Pair<>(ParseG_term1(current.getKey(),g_term.getKey()), ParseG_term2(current.getValue(),g_term.getValue()));
    }
    private List<List<Arguments>> ParseG_term1(List<List<Arguments>> ORlist, List<List<Term>> g_terms){


        List<List<Arguments>> nextORlist = new ArrayList<>();
        for(List<Term> ANDterms: g_terms) {
            List<Arguments> nextANDlist = new ArrayList<>();
            for (List<Arguments> ANDlist : ORlist) {
                for (Arguments args : ANDlist) {
                    for (Term t : ANDterms) {
                        nextANDlist.add(args.add(t));
                    }
                }
            }
            nextORlist.add(nextANDlist);
        }

        return nextORlist;

    }

    private List<AtomList> ParseG_term2(List<AtomList> ORlist, List<AtomList> g_terms){
        List<AtomList> nextORlist = new ArrayList<>();
        for(AtomList ANDlist2: g_terms){
            for(AtomList ANDlist1: ORlist){
                nextORlist.add(ANDlist1.add(ANDlist2));
            }
        }
        return nextORlist;
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitG_term(LawXAIParser.G_termContext ctx) {
        return null; //TODO
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitD_term(LawXAIParser.D_termContext ctx) {
        return null; //TODO
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitC_term(LawXAIParser.C_termContext ctx) {
        return null; //TODO
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitNot_d_term(LawXAIParser.Not_d_termContext ctx) {
        return null; //TODO
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitNot_c_term(LawXAIParser.Not_c_termContext ctx) {
        return null; //TODO
    }
    @Override public Pair<Term, List<AtomList>> visitTerm(LawXAIParser.TermContext ctx) {
        Term t;
        if(ctx.variable() != null){
            return new Pair<>(visitVariable(ctx.variable()),new ArrayList<>());
        }else if(ctx.constant() != null){
            return new Pair<>(visitConstant(ctx.constant()),null);
        }else if(ctx.inner_atom() != null){
            return visitInner_atom(ctx.inner_atom());
        }else{
            throw new IllegalStateException("PARSE EXCEPTION: String does not have CONSTANT nor VARIABLE child");
        }

    } //TODO math_term

    @Override public Pair<Term, List<Atom>> visitMathTerm(LawXAIParser.MathTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitPlusTerm(LawXAIParser.PlusTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitMinusTerm(LawXAIParser.MinusTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitTimesTerm(LawXAIParser.TimesTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitDivideTerm(LawXAIParser.DivideTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitEqualTerm(LawXAIParser.EqualTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitSmallerTerm(LawXAIParser.SmallerTermContext ctx) { return null; }

    @Override public Pair<Term, List<Atom>> visitBiggerTerm(LawXAIParser.BiggerTermContext ctx) { return null;}

    @Override public Pair<Term, List<AtomList>> visitInner_atom(LawXAIParser.Inner_atomContext ctx) {
        return null;
    }

    @Override public String visitString(LawXAIParser.StringContext ctx) {
        if(ctx.CONSTANT() != null){
            return ctx.CONSTANT().toString();
        }else if(ctx.VARIABLE() != null){
            return ctx.VARIABLE().toString();
        }else{
            throw new IllegalStateException("PARSE EXCEPTION: String does not have CONSTANT nor VARIABLE child");
        }
    }
    @Override public Term visitConstant(LawXAIParser.ConstantContext ctx) {
        System.out.println(ctx.CONSTANT().toString());
        return pp.getTerm(ctx.CONSTANT().toString());
    }

    @Override public Term visitVariable(LawXAIParser.VariableContext ctx) {
        System.out.println(ctx.VARIABLE().toString());
        return pp.getTerm(ctx.VARIABLE().toString());
    }

    @Override public String visitMath(LawXAIParser.MathContext ctx) { return ctx.children.get(0).toString(); }

}
