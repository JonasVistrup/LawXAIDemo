// Generated from C:/Users/vistrup/Desktop/LawXAIDemo/src/main/java/com/example/demo/Grammar/LawXAI.g4 by ANTLR 4.13.1
package demo.Grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LawXAIParser}.
 */
public interface LawXAIListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LawXAIParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LawXAIParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#clauses}.
	 * @param ctx the parse tree
	 */
	void enterClauses(LawXAIParser.ClausesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#clauses}.
	 * @param ctx the parse tree
	 */
	void exitClauses(LawXAIParser.ClausesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(LawXAIParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(LawXAIParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#reasonings}.
	 * @param ctx the parse tree
	 */
	void enterReasonings(LawXAIParser.ReasoningsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#reasonings}.
	 * @param ctx the parse tree
	 */
	void exitReasonings(LawXAIParser.ReasoningsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(LawXAIParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(LawXAIParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#d_atom}.
	 * @param ctx the parse tree
	 */
	void enterD_atom(LawXAIParser.D_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#d_atom}.
	 * @param ctx the parse tree
	 */
	void exitD_atom(LawXAIParser.D_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#not_d_atom}.
	 * @param ctx the parse tree
	 */
	void enterNot_d_atom(LawXAIParser.Not_d_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#not_d_atom}.
	 * @param ctx the parse tree
	 */
	void exitNot_d_atom(LawXAIParser.Not_d_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#c_atom}.
	 * @param ctx the parse tree
	 */
	void enterC_atom(LawXAIParser.C_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#c_atom}.
	 * @param ctx the parse tree
	 */
	void exitC_atom(LawXAIParser.C_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#not_c_atom}.
	 * @param ctx the parse tree
	 */
	void enterNot_c_atom(LawXAIParser.Not_c_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#not_c_atom}.
	 * @param ctx the parse tree
	 */
	void exitNot_c_atom(LawXAIParser.Not_c_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(LawXAIParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(LawXAIParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(LawXAIParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(LawXAIParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(LawXAIParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(LawXAIParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(LawXAIParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(LawXAIParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#g_term}.
	 * @param ctx the parse tree
	 */
	void enterG_term(LawXAIParser.G_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#g_term}.
	 * @param ctx the parse tree
	 */
	void exitG_term(LawXAIParser.G_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#d_term}.
	 * @param ctx the parse tree
	 */
	void enterD_term(LawXAIParser.D_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#d_term}.
	 * @param ctx the parse tree
	 */
	void exitD_term(LawXAIParser.D_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#not_d_term}.
	 * @param ctx the parse tree
	 */
	void enterNot_d_term(LawXAIParser.Not_d_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#not_d_term}.
	 * @param ctx the parse tree
	 */
	void exitNot_d_term(LawXAIParser.Not_d_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#c_term}.
	 * @param ctx the parse tree
	 */
	void enterC_term(LawXAIParser.C_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#c_term}.
	 * @param ctx the parse tree
	 */
	void exitC_term(LawXAIParser.C_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#not_c_term}.
	 * @param ctx the parse tree
	 */
	void enterNot_c_term(LawXAIParser.Not_c_termContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#not_c_term}.
	 * @param ctx the parse tree
	 */
	void exitNot_c_term(LawXAIParser.Not_c_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(LawXAIParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(LawXAIParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#mathTerm}.
	 * @param ctx the parse tree
	 */
	void enterMathTerm(LawXAIParser.MathTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#mathTerm}.
	 * @param ctx the parse tree
	 */
	void exitMathTerm(LawXAIParser.MathTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#plusTerm}.
	 * @param ctx the parse tree
	 */
	void enterPlusTerm(LawXAIParser.PlusTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#plusTerm}.
	 * @param ctx the parse tree
	 */
	void exitPlusTerm(LawXAIParser.PlusTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#minusTerm}.
	 * @param ctx the parse tree
	 */
	void enterMinusTerm(LawXAIParser.MinusTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#minusTerm}.
	 * @param ctx the parse tree
	 */
	void exitMinusTerm(LawXAIParser.MinusTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#timesTerm}.
	 * @param ctx the parse tree
	 */
	void enterTimesTerm(LawXAIParser.TimesTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#timesTerm}.
	 * @param ctx the parse tree
	 */
	void exitTimesTerm(LawXAIParser.TimesTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#divideTerm}.
	 * @param ctx the parse tree
	 */
	void enterDivideTerm(LawXAIParser.DivideTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#divideTerm}.
	 * @param ctx the parse tree
	 */
	void exitDivideTerm(LawXAIParser.DivideTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#equalTerm}.
	 * @param ctx the parse tree
	 */
	void enterEqualTerm(LawXAIParser.EqualTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#equalTerm}.
	 * @param ctx the parse tree
	 */
	void exitEqualTerm(LawXAIParser.EqualTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#smallerTerm}.
	 * @param ctx the parse tree
	 */
	void enterSmallerTerm(LawXAIParser.SmallerTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#smallerTerm}.
	 * @param ctx the parse tree
	 */
	void exitSmallerTerm(LawXAIParser.SmallerTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#biggerTerm}.
	 * @param ctx the parse tree
	 */
	void enterBiggerTerm(LawXAIParser.BiggerTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#biggerTerm}.
	 * @param ctx the parse tree
	 */
	void exitBiggerTerm(LawXAIParser.BiggerTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(LawXAIParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(LawXAIParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LawXAIParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LawXAIParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#inner_atom}.
	 * @param ctx the parse tree
	 */
	void enterInner_atom(LawXAIParser.Inner_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#inner_atom}.
	 * @param ctx the parse tree
	 */
	void exitInner_atom(LawXAIParser.Inner_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(LawXAIParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(LawXAIParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link LawXAIParser#math}.
	 * @param ctx the parse tree
	 */
	void enterMath(LawXAIParser.MathContext ctx);
	/**
	 * Exit a parse tree produced by {@link LawXAIParser#math}.
	 * @param ctx the parse tree
	 */
	void exitMath(LawXAIParser.MathContext ctx);
}