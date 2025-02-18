package com.example.demo.Grammar;

import com.example.demo.Logic.High.*;
import com.example.demo.Logic.ProgramParser;
import com.example.demo.Logic.Symbols.Term;
import javafx.util.Pair;
import org.antlr.v4.runtime.tree.ParseTree;

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
        ArrayList<String> reasoningsPrelim = ctx.reasonings() != null? visitReasonings(ctx.reasonings()): new ArrayList<>();
        ArrayList<String> reasonings = new ArrayList<>();
        for(String reason: reasoningsPrelim){
            if(reason.equals("[]")) reasonings.add("Argument");
            else reasonings.add(reason);
        }
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
        if(ctx.term() != null) return visitAtom(ctx,visitTerm(ctx.term()).getKey());
        String p_string = visitPredicate(ctx.predicate());

        Pair<List<List<Arguments>>,List<AtomList>> pair = visitArgument(ctx.argument());
        List<List<Arguments>> ORargumentsList = pair.getKey();
        List<AtomList> ORAtoms = pair.getValue();

        List<AtomList> result = new ArrayList<>();
        for(int i = 0; i<ORargumentsList.size(); i++) {
            List<Arguments> ANDargList = ORargumentsList.get(i);
            List<Atom> next = new ArrayList<>();
            for (Arguments args : ANDargList) {
                next.add(pp.parseAtom(p_string,args));
            }

            result.add(new AtomList(next).add(ORAtoms.get(i)));
        }
        return result;
    }

    private List<AtomList> visitAtom(LawXAIParser.AtomContext ctx, Term reification) {
        String p_string = visitPredicate(ctx.predicate());

        Pair<List<List<Arguments>>,List<AtomList>> pair = visitArgument(ctx.argument());
        List<List<Arguments>> ORargumentsList = pair.getKey();
        List<AtomList> ORAtoms = pair.getValue();

        List<AtomList> result = new ArrayList<>();
        for(int i = 0; i<ORargumentsList.size(); i++){
            List<Arguments> ANDargList = ORargumentsList.get(i);
            List<Atom> next = new ArrayList<>();
            for(Arguments args: ANDargList){
                next.add(pp.parseAtom(p_string,args,reification));
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
            throw new IllegalStateException(ctx.getText());
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


        Pair<List<List<Arguments>>,List<AtomList>> result = new Pair<>(ORlist,current);


        for(Pair<List<List<Term>>,List<AtomList>> pair: children){
            result = ParseG_term(result, pair);
        }

        return result;
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
        if(ctx.term() != null){
            return termToG_term(visitTerm(ctx.term()));
        }else if(ctx.c_term() != null){
            return visitC_term(ctx.c_term());
        }else if (ctx.d_term() != null){
            return visitD_term(ctx.d_term());
        }else{
            throw new IllegalStateException("PARSE EXCEPTION: G_term has no valid children");
        }
    }

    private Pair<List<List<Term>>,List<AtomList>> termToG_term(Pair<Term, List<AtomList>> pair){
        List<List<Term>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        outer.get(0).add(pair.getKey());
        return new Pair<>(outer,pair.getValue());
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitD_term(LawXAIParser.D_termContext ctx) {
        List<List<Term>> nextTerms = new ArrayList<>();
        List<AtomList> nextAtomlist = new ArrayList<>();
        for(LawXAIParser.Not_d_termContext not_d: ctx.not_d_term()){
            Pair<List<List<Term>>,List<AtomList>> child = visitNot_d_term(not_d);
            nextTerms.addAll(child.getKey());
            nextAtomlist.addAll(child.getValue());
        }
        return new Pair<>(nextTerms,nextAtomlist);
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitC_term(LawXAIParser.C_termContext ctx) {
        List<List<Term>> resultTerms = emptyTwoDArray();
        List<AtomList> resultAtomlist = new ArrayList<>();
        resultAtomlist.add(new AtomList());

        for(LawXAIParser.Not_c_termContext not_c: ctx.not_c_term()){
            Pair<List<List<Term>>,List<AtomList>> childPair = visitNot_c_term(not_c);
            List<List<Term>> childTerms = childPair.getKey();
            List<AtomList> childAtoms = childPair.getValue();
            assert childTerms.size() == childAtoms.size();

            List<List<Term>> nextTerms = new ArrayList<>();
            List<AtomList> nextAtomlist = new ArrayList<>();
            for(int i = 0; i<childTerms.size(); i++){
                List<Term> andTerms = childTerms.get(i);
                AtomList andAtoms = childAtoms.get(i);

                for(List<Term> currentTerms: resultTerms){
                    List<Term> combined = new ArrayList<>(currentTerms);
                    combined.addAll(andTerms);
                    nextTerms.add(combined);
                }

                for(AtomList currentAtomlist: resultAtomlist){
                    nextAtomlist.add(currentAtomlist.add(andAtoms));
                }
            }
            resultTerms = nextTerms;
            resultAtomlist = nextAtomlist;
        }
        return new Pair<>(resultTerms,resultAtomlist);
    }

    private <T> List<List<T>> twoDArrayWithSingleElm(T elm){
        List<List<T>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.get(0).add(elm);
        return result;
    }

    private <T> List<T> arrayWithElm(T elm){
        List<T> result = new ArrayList<>();
        result.add(elm);
        return result;
    }
    private <T> List<List<T>> emptyTwoDArray(){
        List<List<T>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        return result;
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitNot_d_term(LawXAIParser.Not_d_termContext ctx) {
        if(ctx.c_term() != null) return visitC_term(ctx.c_term());
        else return termToG_term(visitTerm(ctx.term()));
    }

    @Override public Pair<List<List<Term>>,List<AtomList>> visitNot_c_term(LawXAIParser.Not_c_termContext ctx) {
        if(ctx.d_term() != null) return visitD_term(ctx.d_term());
        else return termToG_term(visitTerm(ctx.term()));
    }
    @Override public Pair<Term, List<AtomList>> visitTerm(LawXAIParser.TermContext ctx) {
        Term t;
        if(ctx.variable() != null){
            return new Pair<>(visitVariable(ctx.variable()),arrayWithElm(new AtomList()));
        }else if(ctx.constant() != null){
            return new Pair<>(visitConstant(ctx.constant()),arrayWithElm(new AtomList()));
        }else if(ctx.inner_atom() != null) {
            return visitInner_atom(ctx.inner_atom());
        }else if(ctx.mathTerm() != null){
            Pair<Term,Atom> res = visitMathTerm(ctx.mathTerm());
            List<AtomList> atoms = new ArrayList<>();
            atoms.add(new AtomList(res.getValue()));
            return new Pair<>(res.getKey(), atoms);
        }else{
            throw new IllegalStateException("PARSE EXCEPTION: String does not have CONSTANT nor VARIABLE child");
        }
    }

    @Override public Pair<Term, Atom> visitMathTerm(LawXAIParser.MathTermContext ctx) {
        if(ctx.plusTerm() != null) return visitPlusTerm(ctx.plusTerm());
        else if (ctx.minusTerm() != null) return visitMinusTerm(ctx.minusTerm());
        else if (ctx.timesTerm() != null) return visitTimesTerm(ctx.timesTerm());
        else if (ctx.divideTerm() != null) return visitDivideTerm(ctx.divideTerm());
        else if (ctx.smallerTerm() != null) return visitSmallerTerm(ctx.smallerTerm());
        else if (ctx.biggerTerm() != null) return visitBiggerTerm(ctx.biggerTerm());
        else if (ctx.equalTerm() != null) return visitEqualTerm(ctx.equalTerm());
        else throw new IllegalStateException("PARSE EXCEPTION: mathTerm has no valid child");
    }

    private Pair<Term, Atom> parseEquation(String mathSymbol, ParseTree child1, ParseTree child2){
        Term t = pp.genUniqueVariable();
        Term first;
        Term second;
        if(child1 instanceof LawXAIParser.ConstantContext){
            first = visitConstant((LawXAIParser.ConstantContext)child1);
        }else{
            first = visitVariable((LawXAIParser.VariableContext)child1);
        }

        if(child2 instanceof LawXAIParser.ConstantContext){
            second = visitConstant((LawXAIParser.ConstantContext)child2);
        }else{
            second = visitVariable((LawXAIParser.VariableContext)child2);
        }

        return new Pair<>(t, pp.parseAtomOld(mathSymbol+"("+first.toString()+","+second.toString()+","+t.toString()+")"));
    }

    @Override public Pair<Term, Atom> visitPlusTerm(LawXAIParser.PlusTermContext ctx) {
        return parseEquation("+",ctx.children.get(0), ctx.children.get(2));
    }



    @Override public Pair<Term, Atom> visitMinusTerm(LawXAIParser.MinusTermContext ctx) {
        return parseEquation("-",ctx.children.get(0), ctx.children.get(2));
    }

    @Override public Pair<Term, Atom> visitTimesTerm(LawXAIParser.TimesTermContext ctx) {
        return parseEquation("*",ctx.children.get(0), ctx.children.get(2));
    }

    @Override public Pair<Term, Atom> visitDivideTerm(LawXAIParser.DivideTermContext ctx) {
        return parseEquation("/",ctx.children.get(0), ctx.children.get(2));
    }

    @Override public Pair<Term, Atom> visitEqualTerm(LawXAIParser.EqualTermContext ctx) {
        Term generated = pp.genUniqueVariable();
        Term specified;
        if(ctx.children.get(0) instanceof LawXAIParser.ConstantContext){
            specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(0));
        }else if(ctx.children.get(0) instanceof LawXAIParser.VariableContext){
            specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(0));
        }else if(ctx.NEGATED() != null){
            if(ctx.children.get(2) instanceof LawXAIParser.ConstantContext){
                specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(2));
            }else{
                specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(2));
            }
        }else{
            if(ctx.children.get(1) instanceof LawXAIParser.ConstantContext){
                specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(1));
            }else{
                specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(1));
            }
        }

        if(ctx.NEGATED() != null){
            return new Pair<>(generated, pp.parseAtomOld("~=("+specified+","+generated+")"));
        }

        return new Pair<>(generated, pp.parseAtomOld("=("+specified+","+generated+")"));
    }

    @Override public Pair<Term, Atom> visitSmallerTerm(LawXAIParser.SmallerTermContext ctx) {
        Term generated = pp.genUniqueVariable();
        Term specified;
        if(ctx.children.get(0) instanceof LawXAIParser.ConstantContext){
            specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(0));

            if(ctx.NEGATED() != null) return new Pair<>(generated, pp.parseAtomOld("~<("+specified+","+generated+")"));
            else return new Pair<>(generated, pp.parseAtomOld("<("+specified+","+generated+")"));


        }else if(ctx.children.get(0) instanceof LawXAIParser.VariableContext){
            specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(0));

            if(ctx.NEGATED() != null) return new Pair<>(generated, pp.parseAtomOld("~<("+specified+","+generated+")"));
            else return new Pair<>(generated, pp.parseAtomOld("<("+specified+","+generated+")"));

        }else if(ctx.NEGATED() != null){
            if(ctx.children.get(2) instanceof LawXAIParser.ConstantContext){
                specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(2));
            }else{
                specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(2));
            }
            return new Pair<>(generated, pp.parseAtomOld("~<("+generated+","+specified+")"));
        }else{
            if(ctx.children.get(1) instanceof LawXAIParser.ConstantContext){
                specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(1));
            }else{
                specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(1));
            }
            return new Pair<>(generated, pp.parseAtomOld("<("+generated+","+specified+")"));
        }
    }

    @Override public Pair<Term, Atom> visitBiggerTerm(LawXAIParser.BiggerTermContext ctx) {
        Term generated = pp.genUniqueVariable();
        Term specified;
        if(ctx.children.get(0) instanceof LawXAIParser.ConstantContext){
            specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(0));

            if(ctx.NEGATED() != null) return new Pair<>(generated, pp.parseAtomOld("~<("+generated+","+specified+")"));
            else return new Pair<>(generated, pp.parseAtomOld("<("+generated+","+specified+")"));


        }else if(ctx.children.get(0) instanceof LawXAIParser.VariableContext){
            specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(0));

            if(ctx.NEGATED() != null) return new Pair<>(generated, pp.parseAtomOld("~<("+generated+","+specified+")"));
            else return new Pair<>(generated, pp.parseAtomOld("<("+generated+","+specified+")"));

        }else if(ctx.NEGATED() != null){
            if(ctx.children.get(2) instanceof LawXAIParser.ConstantContext){
                specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(2));
            }else{
                specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(2));
            }
            return new Pair<>(generated, pp.parseAtomOld("~<("+specified+","+generated+")"));
        }else{
            if(ctx.children.get(1) instanceof LawXAIParser.ConstantContext){
                specified = visitConstant((LawXAIParser.ConstantContext) ctx.children.get(1));
            }else{
                specified = visitVariable((LawXAIParser.VariableContext) ctx.children.get(1));
            }
            return new Pair<>(generated, pp.parseAtomOld("<("+specified+","+generated+")"));
        }
    }

    @Override public Pair<Term, List<AtomList>> visitInner_atom(LawXAIParser.Inner_atomContext ctx) {
        Term t;
        if(ctx.atom().term() != null){
            t = visitTerm(ctx.atom().term()).getKey();
            return new Pair<>(t,visitAtom(ctx.atom()));
        }else {
            t = pp.genUniqueVariable();
            return new Pair<>(t, visitAtom(ctx.atom(),t));
        }
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
        return pp.getTerm(ctx.CONSTANT().toString());
    }

    @Override public Term visitVariable(LawXAIParser.VariableContext ctx) {
        return pp.getTerm(ctx.VARIABLE().toString());
    }

    @Override public String visitMath(LawXAIParser.MathContext ctx) { return ctx.children.get(0).toString(); }

}
