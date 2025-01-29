// Generated from C:/Users/vistrup/Desktop/LawXAIDemo/src/main/java/com/example/demo/Grammar/LawXAI.g4 by ANTLR 4.13.1
package com.example.demo.Grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LawXAIParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LawXAIVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(LawXAIParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#clauses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClauses(LawXAIParser.ClausesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(LawXAIParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#reasonings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReasonings(LawXAIParser.ReasoningsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(LawXAIParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#d_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitD_atom(LawXAIParser.D_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#not_d_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_d_atom(LawXAIParser.Not_d_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#c_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_atom(LawXAIParser.C_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#not_c_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_c_atom(LawXAIParser.Not_c_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(LawXAIParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(LawXAIParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(LawXAIParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(LawXAIParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#g_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitG_term(LawXAIParser.G_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#d_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitD_term(LawXAIParser.D_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#not_d_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_d_term(LawXAIParser.Not_d_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#c_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_term(LawXAIParser.C_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#not_c_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_c_term(LawXAIParser.Not_c_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(LawXAIParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#mathTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathTerm(LawXAIParser.MathTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#plusTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusTerm(LawXAIParser.PlusTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#minusTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusTerm(LawXAIParser.MinusTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#timesTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimesTerm(LawXAIParser.TimesTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#divideTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivideTerm(LawXAIParser.DivideTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#equalTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualTerm(LawXAIParser.EqualTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#smallerTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmallerTerm(LawXAIParser.SmallerTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#biggerTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBiggerTerm(LawXAIParser.BiggerTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(LawXAIParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LawXAIParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#inner_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInner_atom(LawXAIParser.Inner_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(LawXAIParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link LawXAIParser#math}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath(LawXAIParser.MathContext ctx);
}