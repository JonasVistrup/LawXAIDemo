// Generated from C:/Users/vistrup/Desktop/LawXAIDemo/src/main/java/com/example/demo/Grammar/LawXAI.g4 by ANTLR 4.13.1
package demo.Grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LawXAIParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LARROW=1, NEWLINE=2, COMMA=3, OR=4, AND=5, PLUS=6, MINUS=7, TIMES=8, DIVIDE=9, 
		EQUAL=10, SMALLER=11, BIGGER=12, COLON=13, SEMICOLON=14, LPAR=15, RPAR=16, 
		LBRACKET=17, RBRACKET=18, LTUBORG=19, RTUBORG=20, RAISED=21, NEGATED=22, 
		VARIABLE=23, CONSTANT=24;
	public static final int
		RULE_prog = 0, RULE_clauses = 1, RULE_clause = 2, RULE_reasonings = 3, 
		RULE_body = 4, RULE_d_atom = 5, RULE_not_d_atom = 6, RULE_c_atom = 7, 
		RULE_not_c_atom = 8, RULE_head = 9, RULE_atom = 10, RULE_predicate = 11, 
		RULE_argument = 12, RULE_g_term = 13, RULE_d_term = 14, RULE_not_d_term = 15, 
		RULE_c_term = 16, RULE_not_c_term = 17, RULE_term = 18, RULE_mathTerm = 19, 
		RULE_plusTerm = 20, RULE_minusTerm = 21, RULE_timesTerm = 22, RULE_divideTerm = 23, 
		RULE_equalTerm = 24, RULE_smallerTerm = 25, RULE_biggerTerm = 26, RULE_constant = 27, 
		RULE_variable = 28, RULE_inner_atom = 29, RULE_string = 30, RULE_math = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "clauses", "clause", "reasonings", "body", "d_atom", "not_d_atom", 
			"c_atom", "not_c_atom", "head", "atom", "predicate", "argument", "g_term", 
			"d_term", "not_d_term", "c_term", "not_c_term", "term", "mathTerm", "plusTerm", 
			"minusTerm", "timesTerm", "divideTerm", "equalTerm", "smallerTerm", "biggerTerm", 
			"constant", "variable", "inner_atom", "string", "math"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LARROW", "NEWLINE", "COMMA", "OR", "AND", "PLUS", "MINUS", "TIMES", 
			"DIVIDE", "EQUAL", "SMALLER", "BIGGER", "COLON", "SEMICOLON", "LPAR", 
			"RPAR", "LBRACKET", "RBRACKET", "LTUBORG", "RTUBORG", "RAISED", "NEGATED", 
			"VARIABLE", "CONSTANT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LawXAI.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LawXAIParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public ClausesContext clauses() {
			return getRuleContext(ClausesContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			clauses();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClausesContext extends ParserRuleContext {
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(LawXAIParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(LawXAIParser.NEWLINE, i);
		}
		public ClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClausesContext clauses() throws RecognitionException {
		ClausesContext _localctx = new ClausesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clauses);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			clause();
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(67);
					match(NEWLINE);
					setState(68);
					clause();
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(74);
				match(NEWLINE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClauseContext extends ParserRuleContext {
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public TerminalNode LARROW() { return getToken(LawXAIParser.LARROW, 0); }
		public TerminalNode COLON() { return getToken(LawXAIParser.COLON, 0); }
		public TerminalNode SEMICOLON() { return getToken(LawXAIParser.SEMICOLON, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ReasoningsContext reasonings() {
			return getRuleContext(ReasoningsContext.class,0);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			head();
			setState(78);
			match(LARROW);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 29499328L) != 0)) {
				{
				setState(79);
				body();
				}
			}

			setState(82);
			match(COLON);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 29335496L) != 0)) {
				{
				setState(83);
				reasonings();
				}
			}

			setState(86);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReasoningsContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(LawXAIParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(LawXAIParser.COLON, i);
		}
		public ReasoningsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reasonings; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterReasonings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitReasonings(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitReasonings(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReasoningsContext reasonings() throws RecognitionException {
		ReasoningsContext _localctx = new ReasoningsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reasonings);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			string();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COLON) {
				{
				{
				setState(89);
				match(COLON);
				setState(90);
				string();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public List<D_atomContext> d_atom() {
			return getRuleContexts(D_atomContext.class);
		}
		public D_atomContext d_atom(int i) {
			return getRuleContext(D_atomContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LawXAIParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LawXAIParser.COMMA, i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			d_atom();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(97);
				match(COMMA);
				setState(98);
				d_atom();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class D_atomContext extends ParserRuleContext {
		public List<Not_d_atomContext> not_d_atom() {
			return getRuleContexts(Not_d_atomContext.class);
		}
		public Not_d_atomContext not_d_atom(int i) {
			return getRuleContext(Not_d_atomContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(LawXAIParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LawXAIParser.OR, i);
		}
		public D_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterD_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitD_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitD_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final D_atomContext d_atom() throws RecognitionException {
		D_atomContext _localctx = new D_atomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_d_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			not_d_atom();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(105);
				match(OR);
				setState(106);
				not_d_atom();
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Not_d_atomContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(LawXAIParser.LBRACKET, 0); }
		public C_atomContext c_atom() {
			return getRuleContext(C_atomContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(LawXAIParser.RBRACKET, 0); }
		public Not_d_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_d_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterNot_d_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitNot_d_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitNot_d_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_d_atomContext not_d_atom() throws RecognitionException {
		Not_d_atomContext _localctx = new Not_d_atomContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_not_d_atom);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIVIDE:
			case EQUAL:
			case SMALLER:
			case BIGGER:
			case NEGATED:
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				atom();
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(LBRACKET);
				setState(114);
				c_atom();
				setState(115);
				match(RBRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class C_atomContext extends ParserRuleContext {
		public List<Not_c_atomContext> not_c_atom() {
			return getRuleContexts(Not_c_atomContext.class);
		}
		public Not_c_atomContext not_c_atom(int i) {
			return getRuleContext(Not_c_atomContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(LawXAIParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(LawXAIParser.AND, i);
		}
		public C_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterC_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitC_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitC_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C_atomContext c_atom() throws RecognitionException {
		C_atomContext _localctx = new C_atomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_c_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			not_c_atom();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(120);
				match(AND);
				setState(121);
				not_c_atom();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Not_c_atomContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(LawXAIParser.LBRACKET, 0); }
		public D_atomContext d_atom() {
			return getRuleContext(D_atomContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(LawXAIParser.RBRACKET, 0); }
		public Not_c_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_c_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterNot_c_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitNot_c_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitNot_c_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_c_atomContext not_c_atom() throws RecognitionException {
		Not_c_atomContext _localctx = new Not_c_atomContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_not_c_atom);
		try {
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIVIDE:
			case EQUAL:
			case SMALLER:
			case BIGGER:
			case NEGATED:
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				atom();
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(LBRACKET);
				setState(129);
				d_atom();
				setState(130);
				match(RBRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HeadContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LawXAIParser.LPAR, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(LawXAIParser.RPAR, 0); }
		public TerminalNode LTUBORG() { return getToken(LawXAIParser.LTUBORG, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode RTUBORG() { return getToken(LawXAIParser.RTUBORG, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			predicate();
			setState(137);
			match(LPAR);
			setState(138);
			argument();
			setState(139);
			match(RPAR);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LTUBORG) {
				{
				setState(140);
				match(LTUBORG);
				setState(141);
				term();
				setState(142);
				match(RTUBORG);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public MathContext math() {
			return getRuleContext(MathContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(LawXAIParser.VARIABLE, 0); }
		public TerminalNode CONSTANT() { return getToken(LawXAIParser.CONSTANT, 0); }
		public TerminalNode NEGATED() { return getToken(LawXAIParser.NEGATED, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEGATED) {
				{
				setState(146);
				match(NEGATED);
				}
			}

			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIVIDE:
			case EQUAL:
			case SMALLER:
			case BIGGER:
				{
				setState(149);
				math();
				}
				break;
			case VARIABLE:
				{
				setState(150);
				match(VARIABLE);
				}
				break;
			case CONSTANT:
				{
				setState(151);
				match(CONSTANT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public List<G_termContext> g_term() {
			return getRuleContexts(G_termContext.class);
		}
		public G_termContext g_term(int i) {
			return getRuleContext(G_termContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LawXAIParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LawXAIParser.COMMA, i);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			g_term();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(155);
				match(COMMA);
				setState(156);
				g_term();
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class G_termContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public D_termContext d_term() {
			return getRuleContext(D_termContext.class,0);
		}
		public C_termContext c_term() {
			return getRuleContext(C_termContext.class,0);
		}
		public G_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterG_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitG_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitG_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final G_termContext g_term() throws RecognitionException {
		G_termContext _localctx = new G_termContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_g_term);
		try {
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				d_term();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				c_term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class D_termContext extends ParserRuleContext {
		public List<Not_d_termContext> not_d_term() {
			return getRuleContexts(Not_d_termContext.class);
		}
		public Not_d_termContext not_d_term(int i) {
			return getRuleContext(Not_d_termContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(LawXAIParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(LawXAIParser.OR, i);
		}
		public D_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterD_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitD_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitD_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final D_termContext d_term() throws RecognitionException {
		D_termContext _localctx = new D_termContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_d_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			not_d_term();
			setState(170); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(168);
				match(OR);
				setState(169);
				not_d_term();
				}
				}
				setState(172); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OR );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Not_d_termContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(LawXAIParser.LBRACKET, 0); }
		public C_termContext c_term() {
			return getRuleContext(C_termContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(LawXAIParser.RBRACKET, 0); }
		public Not_d_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_d_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterNot_d_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitNot_d_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitNot_d_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_d_termContext not_d_term() throws RecognitionException {
		Not_d_termContext _localctx = new Not_d_termContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_not_d_term);
		try {
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIVIDE:
			case EQUAL:
			case SMALLER:
			case BIGGER:
			case NEGATED:
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				term();
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(LBRACKET);
				setState(176);
				c_term();
				setState(177);
				match(RBRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class C_termContext extends ParserRuleContext {
		public List<Not_c_termContext> not_c_term() {
			return getRuleContexts(Not_c_termContext.class);
		}
		public Not_c_termContext not_c_term(int i) {
			return getRuleContext(Not_c_termContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(LawXAIParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(LawXAIParser.AND, i);
		}
		public C_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_c_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterC_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitC_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitC_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final C_termContext c_term() throws RecognitionException {
		C_termContext _localctx = new C_termContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_c_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			not_c_term();
			setState(184); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				match(AND);
				setState(183);
				not_c_term();
				}
				}
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==AND );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Not_c_termContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(LawXAIParser.LBRACKET, 0); }
		public D_termContext d_term() {
			return getRuleContext(D_termContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(LawXAIParser.RBRACKET, 0); }
		public Not_c_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_c_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterNot_c_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitNot_c_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitNot_c_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_c_termContext not_c_term() throws RecognitionException {
		Not_c_termContext _localctx = new Not_c_termContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_not_c_term);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIVIDE:
			case EQUAL:
			case SMALLER:
			case BIGGER:
			case NEGATED:
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				term();
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				match(LBRACKET);
				setState(190);
				d_term();
				setState(191);
				match(RBRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public Inner_atomContext inner_atom() {
			return getRuleContext(Inner_atomContext.class,0);
		}
		public MathTermContext mathTerm() {
			return getRuleContext(MathTermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_term);
		try {
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				inner_atom();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(198);
				mathTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MathTermContext extends ParserRuleContext {
		public PlusTermContext plusTerm() {
			return getRuleContext(PlusTermContext.class,0);
		}
		public MinusTermContext minusTerm() {
			return getRuleContext(MinusTermContext.class,0);
		}
		public TimesTermContext timesTerm() {
			return getRuleContext(TimesTermContext.class,0);
		}
		public DivideTermContext divideTerm() {
			return getRuleContext(DivideTermContext.class,0);
		}
		public SmallerTermContext smallerTerm() {
			return getRuleContext(SmallerTermContext.class,0);
		}
		public BiggerTermContext biggerTerm() {
			return getRuleContext(BiggerTermContext.class,0);
		}
		public EqualTermContext equalTerm() {
			return getRuleContext(EqualTermContext.class,0);
		}
		public MathTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterMathTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitMathTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitMathTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathTermContext mathTerm() throws RecognitionException {
		MathTermContext _localctx = new MathTermContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_mathTerm);
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				plusTerm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				minusTerm();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				timesTerm();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				divideTerm();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(205);
				smallerTerm();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(206);
				biggerTerm();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(207);
				equalTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PlusTermContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LawXAIParser.PLUS, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public PlusTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterPlusTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitPlusTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitPlusTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusTermContext plusTerm() throws RecognitionException {
		PlusTermContext _localctx = new PlusTermContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_plusTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(210);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(211);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(214);
			match(PLUS);
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(215);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(216);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MinusTermContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(LawXAIParser.MINUS, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public MinusTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minusTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterMinusTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitMinusTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitMinusTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MinusTermContext minusTerm() throws RecognitionException {
		MinusTermContext _localctx = new MinusTermContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_minusTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(219);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(220);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(223);
			match(MINUS);
			setState(226);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(224);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(225);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TimesTermContext extends ParserRuleContext {
		public TerminalNode TIMES() { return getToken(LawXAIParser.TIMES, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TimesTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timesTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterTimesTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitTimesTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitTimesTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimesTermContext timesTerm() throws RecognitionException {
		TimesTermContext _localctx = new TimesTermContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_timesTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(228);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(229);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(232);
			match(TIMES);
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(233);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(234);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DivideTermContext extends ParserRuleContext {
		public TerminalNode DIVIDE() { return getToken(LawXAIParser.DIVIDE, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public DivideTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divideTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterDivideTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitDivideTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitDivideTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DivideTermContext divideTerm() throws RecognitionException {
		DivideTermContext _localctx = new DivideTermContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_divideTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(237);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(238);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(241);
			match(DIVIDE);
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT:
				{
				setState(242);
				constant();
				}
				break;
			case VARIABLE:
				{
				setState(243);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualTermContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(LawXAIParser.EQUAL, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode NEGATED() { return getToken(LawXAIParser.NEGATED, 0); }
		public EqualTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterEqualTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitEqualTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitEqualTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualTermContext equalTerm() throws RecognitionException {
		EqualTermContext _localctx = new EqualTermContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_equalTerm);
		int _la;
		try {
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(246);
					constant();
					}
					break;
				case VARIABLE:
					{
					setState(247);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEGATED) {
					{
					setState(250);
					match(NEGATED);
					}
				}

				setState(253);
				match(EQUAL);
				}
				break;
			case EQUAL:
			case NEGATED:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEGATED) {
					{
					setState(255);
					match(NEGATED);
					}
				}

				setState(258);
				match(EQUAL);
				setState(261);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(259);
					constant();
					}
					break;
				case VARIABLE:
					{
					setState(260);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SmallerTermContext extends ParserRuleContext {
		public TerminalNode SMALLER() { return getToken(LawXAIParser.SMALLER, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode NEGATED() { return getToken(LawXAIParser.NEGATED, 0); }
		public SmallerTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_smallerTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterSmallerTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitSmallerTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitSmallerTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SmallerTermContext smallerTerm() throws RecognitionException {
		SmallerTermContext _localctx = new SmallerTermContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_smallerTerm);
		int _la;
		try {
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(265);
					constant();
					}
					break;
				case VARIABLE:
					{
					setState(266);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEGATED) {
					{
					setState(269);
					match(NEGATED);
					}
				}

				setState(272);
				match(SMALLER);
				}
				break;
			case SMALLER:
			case NEGATED:
				enterOuterAlt(_localctx, 2);
				{
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEGATED) {
					{
					setState(274);
					match(NEGATED);
					}
				}

				setState(277);
				match(SMALLER);
				setState(280);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(278);
					constant();
					}
					break;
				case VARIABLE:
					{
					setState(279);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BiggerTermContext extends ParserRuleContext {
		public TerminalNode BIGGER() { return getToken(LawXAIParser.BIGGER, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode NEGATED() { return getToken(LawXAIParser.NEGATED, 0); }
		public BiggerTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_biggerTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterBiggerTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitBiggerTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitBiggerTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BiggerTermContext biggerTerm() throws RecognitionException {
		BiggerTermContext _localctx = new BiggerTermContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_biggerTerm);
		int _la;
		try {
			setState(301);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(284);
					constant();
					}
					break;
				case VARIABLE:
					{
					setState(285);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEGATED) {
					{
					setState(288);
					match(NEGATED);
					}
				}

				setState(291);
				match(BIGGER);
				}
				break;
			case BIGGER:
			case NEGATED:
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEGATED) {
					{
					setState(293);
					match(NEGATED);
					}
				}

				setState(296);
				match(BIGGER);
				setState(299);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(297);
					constant();
					}
					break;
				case VARIABLE:
					{
					setState(298);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode CONSTANT() { return getToken(LawXAIParser.CONSTANT, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(CONSTANT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(LawXAIParser.VARIABLE, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Inner_atomContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public Inner_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inner_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterInner_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitInner_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitInner_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Inner_atomContext inner_atom() throws RecognitionException {
		Inner_atomContext _localctx = new Inner_atomContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_inner_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> CONSTANT() { return getTokens(LawXAIParser.CONSTANT); }
		public TerminalNode CONSTANT(int i) {
			return getToken(LawXAIParser.CONSTANT, i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(LawXAIParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(LawXAIParser.VARIABLE, i);
		}
		public List<MathContext> math() {
			return getRuleContexts(MathContext.class);
		}
		public MathContext math(int i) {
			return getRuleContext(MathContext.class,i);
		}
		public List<TerminalNode> LPAR() { return getTokens(LawXAIParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(LawXAIParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(LawXAIParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(LawXAIParser.RPAR, i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(LawXAIParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(LawXAIParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(LawXAIParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(LawXAIParser.RBRACKET, i);
		}
		public List<TerminalNode> LTUBORG() { return getTokens(LawXAIParser.LTUBORG); }
		public TerminalNode LTUBORG(int i) {
			return getToken(LawXAIParser.LTUBORG, i);
		}
		public List<TerminalNode> RTUBORG() { return getTokens(LawXAIParser.RTUBORG); }
		public TerminalNode RTUBORG(int i) {
			return getToken(LawXAIParser.RTUBORG, i);
		}
		public List<TerminalNode> RAISED() { return getTokens(LawXAIParser.RAISED); }
		public TerminalNode RAISED(int i) {
			return getToken(LawXAIParser.RAISED, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LawXAIParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LawXAIParser.COMMA, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(320);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONSTANT:
					{
					setState(309);
					match(CONSTANT);
					}
					break;
				case VARIABLE:
					{
					setState(310);
					match(VARIABLE);
					}
					break;
				case PLUS:
				case MINUS:
				case TIMES:
				case DIVIDE:
				case EQUAL:
				case SMALLER:
				case BIGGER:
					{
					setState(311);
					math();
					}
					break;
				case LPAR:
					{
					setState(312);
					match(LPAR);
					}
					break;
				case RPAR:
					{
					setState(313);
					match(RPAR);
					}
					break;
				case LBRACKET:
					{
					setState(314);
					match(LBRACKET);
					}
					break;
				case RBRACKET:
					{
					setState(315);
					match(RBRACKET);
					}
					break;
				case LTUBORG:
					{
					setState(316);
					match(LTUBORG);
					}
					break;
				case RTUBORG:
					{
					setState(317);
					match(RTUBORG);
					}
					break;
				case RAISED:
					{
					setState(318);
					match(RAISED);
					}
					break;
				case COMMA:
					{
					setState(319);
					match(COMMA);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(322); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 29335496L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MathContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LawXAIParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LawXAIParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(LawXAIParser.TIMES, 0); }
		public TerminalNode DIVIDE() { return getToken(LawXAIParser.DIVIDE, 0); }
		public TerminalNode EQUAL() { return getToken(LawXAIParser.EQUAL, 0); }
		public TerminalNode SMALLER() { return getToken(LawXAIParser.SMALLER, 0); }
		public TerminalNode BIGGER() { return getToken(LawXAIParser.BIGGER, 0); }
		public MathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_math; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).enterMath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LawXAIListener ) ((LawXAIListener)listener).exitMath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LawXAIVisitor ) return ((LawXAIVisitor<? extends T>)visitor).visitMath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathContext math() throws RecognitionException {
		MathContext _localctx = new MathContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_math);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8128L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0018\u0147\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0005\u0001F\b\u0001\n\u0001\f\u0001I\t\u0001\u0001"+
		"\u0001\u0003\u0001L\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002Q\b\u0002\u0001\u0002\u0001\u0002\u0003\u0002U\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\\\b\u0003"+
		"\n\u0003\f\u0003_\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"d\b\u0004\n\u0004\f\u0004g\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005l\b\u0005\n\u0005\f\u0005o\t\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006v\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007{\b\u0007\n\u0007\f\u0007~\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0085\b\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u0091\b\n\u0001\u000b\u0003\u000b\u0094\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0099\b\u000b\u0001\f\u0001\f\u0001\f\u0005\f"+
		"\u009e\b\f\n\f\f\f\u00a1\t\f\u0001\r\u0001\r\u0001\r\u0003\r\u00a6\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0004\u000e\u00ab\b\u000e\u000b\u000e"+
		"\f\u000e\u00ac\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u00b4\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010"+
		"\u00b9\b\u0010\u000b\u0010\f\u0010\u00ba\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00c2\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00c8\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u00d1\b\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u00d5\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00da\b\u0014\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u00de\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u00e3\b\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u00e7\b"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00ec\b\u0016\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u00f0\b\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u00f5\b\u0017\u0001\u0018\u0001\u0018\u0003\u0018\u00f9"+
		"\b\u0018\u0001\u0018\u0003\u0018\u00fc\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u0101\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u0106\b\u0018\u0003\u0018\u0108\b\u0018\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u010c\b\u0019\u0001\u0019\u0003\u0019\u010f\b\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0114\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0003\u0019\u0119\b\u0019\u0003\u0019\u011b\b"+
		"\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u011f\b\u001a\u0001\u001a\u0003"+
		"\u001a\u0122\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u0127"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u012c\b\u001a"+
		"\u0003\u001a\u012e\b\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0004\u001e\u0141\b\u001e\u000b\u001e\f\u001e\u0142\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0000\u0000 \u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468:<>\u0000\u0001\u0001\u0000\u0006\f\u0166\u0000@\u0001\u0000\u0000"+
		"\u0000\u0002B\u0001\u0000\u0000\u0000\u0004M\u0001\u0000\u0000\u0000\u0006"+
		"X\u0001\u0000\u0000\u0000\b`\u0001\u0000\u0000\u0000\nh\u0001\u0000\u0000"+
		"\u0000\fu\u0001\u0000\u0000\u0000\u000ew\u0001\u0000\u0000\u0000\u0010"+
		"\u0084\u0001\u0000\u0000\u0000\u0012\u0086\u0001\u0000\u0000\u0000\u0014"+
		"\u0088\u0001\u0000\u0000\u0000\u0016\u0093\u0001\u0000\u0000\u0000\u0018"+
		"\u009a\u0001\u0000\u0000\u0000\u001a\u00a5\u0001\u0000\u0000\u0000\u001c"+
		"\u00a7\u0001\u0000\u0000\u0000\u001e\u00b3\u0001\u0000\u0000\u0000 \u00b5"+
		"\u0001\u0000\u0000\u0000\"\u00c1\u0001\u0000\u0000\u0000$\u00c7\u0001"+
		"\u0000\u0000\u0000&\u00d0\u0001\u0000\u0000\u0000(\u00d4\u0001\u0000\u0000"+
		"\u0000*\u00dd\u0001\u0000\u0000\u0000,\u00e6\u0001\u0000\u0000\u0000."+
		"\u00ef\u0001\u0000\u0000\u00000\u0107\u0001\u0000\u0000\u00002\u011a\u0001"+
		"\u0000\u0000\u00004\u012d\u0001\u0000\u0000\u00006\u012f\u0001\u0000\u0000"+
		"\u00008\u0131\u0001\u0000\u0000\u0000:\u0133\u0001\u0000\u0000\u0000<"+
		"\u0140\u0001\u0000\u0000\u0000>\u0144\u0001\u0000\u0000\u0000@A\u0003"+
		"\u0002\u0001\u0000A\u0001\u0001\u0000\u0000\u0000BG\u0003\u0004\u0002"+
		"\u0000CD\u0005\u0002\u0000\u0000DF\u0003\u0004\u0002\u0000EC\u0001\u0000"+
		"\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000"+
		"JL\u0005\u0002\u0000\u0000KJ\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000"+
		"\u0000L\u0003\u0001\u0000\u0000\u0000MN\u0003\u0012\t\u0000NP\u0005\u0001"+
		"\u0000\u0000OQ\u0003\b\u0004\u0000PO\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000RT\u0005\r\u0000\u0000SU\u0003\u0006"+
		"\u0003\u0000TS\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UV\u0001"+
		"\u0000\u0000\u0000VW\u0005\u000e\u0000\u0000W\u0005\u0001\u0000\u0000"+
		"\u0000X]\u0003<\u001e\u0000YZ\u0005\r\u0000\u0000Z\\\u0003<\u001e\u0000"+
		"[Y\u0001\u0000\u0000\u0000\\_\u0001\u0000\u0000\u0000][\u0001\u0000\u0000"+
		"\u0000]^\u0001\u0000\u0000\u0000^\u0007\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000`e\u0003\n\u0005\u0000ab\u0005\u0003\u0000\u0000bd\u0003"+
		"\n\u0005\u0000ca\u0001\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001"+
		"\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000f\t\u0001\u0000\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hm\u0003\f\u0006\u0000ij\u0005\u0004\u0000\u0000"+
		"jl\u0003\f\u0006\u0000ki\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000"+
		"mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000n\u000b\u0001\u0000"+
		"\u0000\u0000om\u0001\u0000\u0000\u0000pv\u0003\u0014\n\u0000qr\u0005\u0011"+
		"\u0000\u0000rs\u0003\u000e\u0007\u0000st\u0005\u0012\u0000\u0000tv\u0001"+
		"\u0000\u0000\u0000up\u0001\u0000\u0000\u0000uq\u0001\u0000\u0000\u0000"+
		"v\r\u0001\u0000\u0000\u0000w|\u0003\u0010\b\u0000xy\u0005\u0005\u0000"+
		"\u0000y{\u0003\u0010\b\u0000zx\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000"+
		"\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u000f\u0001"+
		"\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0085\u0003\u0014\n"+
		"\u0000\u0080\u0081\u0005\u0011\u0000\u0000\u0081\u0082\u0003\n\u0005\u0000"+
		"\u0082\u0083\u0005\u0012\u0000\u0000\u0083\u0085\u0001\u0000\u0000\u0000"+
		"\u0084\u007f\u0001\u0000\u0000\u0000\u0084\u0080\u0001\u0000\u0000\u0000"+
		"\u0085\u0011\u0001\u0000\u0000\u0000\u0086\u0087\u0003\u0014\n\u0000\u0087"+
		"\u0013\u0001\u0000\u0000\u0000\u0088\u0089\u0003\u0016\u000b\u0000\u0089"+
		"\u008a\u0005\u000f\u0000\u0000\u008a\u008b\u0003\u0018\f\u0000\u008b\u0090"+
		"\u0005\u0010\u0000\u0000\u008c\u008d\u0005\u0013\u0000\u0000\u008d\u008e"+
		"\u0003$\u0012\u0000\u008e\u008f\u0005\u0014\u0000\u0000\u008f\u0091\u0001"+
		"\u0000\u0000\u0000\u0090\u008c\u0001\u0000\u0000\u0000\u0090\u0091\u0001"+
		"\u0000\u0000\u0000\u0091\u0015\u0001\u0000\u0000\u0000\u0092\u0094\u0005"+
		"\u0016\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0093\u0094\u0001"+
		"\u0000\u0000\u0000\u0094\u0098\u0001\u0000\u0000\u0000\u0095\u0099\u0003"+
		">\u001f\u0000\u0096\u0099\u0005\u0017\u0000\u0000\u0097\u0099\u0005\u0018"+
		"\u0000\u0000\u0098\u0095\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000"+
		"\u0000\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0099\u0017\u0001\u0000"+
		"\u0000\u0000\u009a\u009f\u0003\u001a\r\u0000\u009b\u009c\u0005\u0003\u0000"+
		"\u0000\u009c\u009e\u0003\u001a\r\u0000\u009d\u009b\u0001\u0000\u0000\u0000"+
		"\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000"+
		"\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u0019\u0001\u0000\u0000\u0000"+
		"\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2\u00a6\u0003$\u0012\u0000\u00a3"+
		"\u00a6\u0003\u001c\u000e\u0000\u00a4\u00a6\u0003 \u0010\u0000\u00a5\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a6\u001b\u0001\u0000\u0000\u0000\u00a7\u00aa"+
		"\u0003\u001e\u000f\u0000\u00a8\u00a9\u0005\u0004\u0000\u0000\u00a9\u00ab"+
		"\u0003\u001e\u000f\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ad\u001d\u0001\u0000\u0000\u0000\u00ae\u00b4"+
		"\u0003$\u0012\u0000\u00af\u00b0\u0005\u0011\u0000\u0000\u00b0\u00b1\u0003"+
		" \u0010\u0000\u00b1\u00b2\u0005\u0012\u0000\u0000\u00b2\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b3\u00ae\u0001\u0000\u0000\u0000\u00b3\u00af\u0001\u0000"+
		"\u0000\u0000\u00b4\u001f\u0001\u0000\u0000\u0000\u00b5\u00b8\u0003\"\u0011"+
		"\u0000\u00b6\u00b7\u0005\u0005\u0000\u0000\u00b7\u00b9\u0003\"\u0011\u0000"+
		"\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb!\u0001\u0000\u0000\u0000\u00bc\u00c2\u0003$\u0012\u0000\u00bd\u00be"+
		"\u0005\u0011\u0000\u0000\u00be\u00bf\u0003\u001c\u000e\u0000\u00bf\u00c0"+
		"\u0005\u0012\u0000\u0000\u00c0\u00c2\u0001\u0000\u0000\u0000\u00c1\u00bc"+
		"\u0001\u0000\u0000\u0000\u00c1\u00bd\u0001\u0000\u0000\u0000\u00c2#\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c8\u00036\u001b\u0000\u00c4\u00c8\u00038\u001c"+
		"\u0000\u00c5\u00c8\u0003:\u001d\u0000\u00c6\u00c8\u0003&\u0013\u0000\u00c7"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c7\u00c4\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c6\u0001\u0000\u0000\u0000\u00c8"+
		"%\u0001\u0000\u0000\u0000\u00c9\u00d1\u0003(\u0014\u0000\u00ca\u00d1\u0003"+
		"*\u0015\u0000\u00cb\u00d1\u0003,\u0016\u0000\u00cc\u00d1\u0003.\u0017"+
		"\u0000\u00cd\u00d1\u00032\u0019\u0000\u00ce\u00d1\u00034\u001a\u0000\u00cf"+
		"\u00d1\u00030\u0018\u0000\u00d0\u00c9\u0001\u0000\u0000\u0000\u00d0\u00ca"+
		"\u0001\u0000\u0000\u0000\u00d0\u00cb\u0001\u0000\u0000\u0000\u00d0\u00cc"+
		"\u0001\u0000\u0000\u0000\u00d0\u00cd\u0001\u0000\u0000\u0000\u00d0\u00ce"+
		"\u0001\u0000\u0000\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d1\'\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d5\u00036\u001b\u0000\u00d3\u00d5\u00038\u001c"+
		"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d9\u0005\u0006\u0000"+
		"\u0000\u00d7\u00da\u00036\u001b\u0000\u00d8\u00da\u00038\u001c\u0000\u00d9"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da"+
		")\u0001\u0000\u0000\u0000\u00db\u00de\u00036\u001b\u0000\u00dc\u00de\u0003"+
		"8\u001c\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00dc\u0001\u0000"+
		"\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e2\u0005\u0007"+
		"\u0000\u0000\u00e0\u00e3\u00036\u001b\u0000\u00e1\u00e3\u00038\u001c\u0000"+
		"\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e1\u0001\u0000\u0000\u0000"+
		"\u00e3+\u0001\u0000\u0000\u0000\u00e4\u00e7\u00036\u001b\u0000\u00e5\u00e7"+
		"\u00038\u001c\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e5\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00eb\u0005"+
		"\b\u0000\u0000\u00e9\u00ec\u00036\u001b\u0000\u00ea\u00ec\u00038\u001c"+
		"\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ea\u0001\u0000\u0000"+
		"\u0000\u00ec-\u0001\u0000\u0000\u0000\u00ed\u00f0\u00036\u001b\u0000\u00ee"+
		"\u00f0\u00038\u001c\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f4"+
		"\u0005\t\u0000\u0000\u00f2\u00f5\u00036\u001b\u0000\u00f3\u00f5\u0003"+
		"8\u001c\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f3\u0001\u0000"+
		"\u0000\u0000\u00f5/\u0001\u0000\u0000\u0000\u00f6\u00f9\u00036\u001b\u0000"+
		"\u00f7\u00f9\u00038\u001c\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8"+
		"\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fc\u0005\u0016\u0000\u0000\u00fb\u00fa\u0001\u0000\u0000\u0000\u00fb"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd"+
		"\u00fe\u0005\n\u0000\u0000\u00fe\u0108\u0001\u0000\u0000\u0000\u00ff\u0101"+
		"\u0005\u0016\u0000\u0000\u0100\u00ff\u0001\u0000\u0000\u0000\u0100\u0101"+
		"\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u0105"+
		"\u0005\n\u0000\u0000\u0103\u0106\u00036\u001b\u0000\u0104\u0106\u0003"+
		"8\u001c\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0105\u0104\u0001\u0000"+
		"\u0000\u0000\u0106\u0108\u0001\u0000\u0000\u0000\u0107\u00f8\u0001\u0000"+
		"\u0000\u0000\u0107\u0100\u0001\u0000\u0000\u0000\u01081\u0001\u0000\u0000"+
		"\u0000\u0109\u010c\u00036\u001b\u0000\u010a\u010c\u00038\u001c\u0000\u010b"+
		"\u0109\u0001\u0000\u0000\u0000\u010b\u010a\u0001\u0000\u0000\u0000\u010c"+
		"\u010e\u0001\u0000\u0000\u0000\u010d\u010f\u0005\u0016\u0000\u0000\u010e"+
		"\u010d\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f"+
		"\u0110\u0001\u0000\u0000\u0000\u0110\u0111\u0005\u000b\u0000\u0000\u0111"+
		"\u011b\u0001\u0000\u0000\u0000\u0112\u0114\u0005\u0016\u0000\u0000\u0113"+
		"\u0112\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114"+
		"\u0115\u0001\u0000\u0000\u0000\u0115\u0118\u0005\u000b\u0000\u0000\u0116"+
		"\u0119\u00036\u001b\u0000\u0117\u0119\u00038\u001c\u0000\u0118\u0116\u0001"+
		"\u0000\u0000\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0119\u011b\u0001"+
		"\u0000\u0000\u0000\u011a\u010b\u0001\u0000\u0000\u0000\u011a\u0113\u0001"+
		"\u0000\u0000\u0000\u011b3\u0001\u0000\u0000\u0000\u011c\u011f\u00036\u001b"+
		"\u0000\u011d\u011f\u00038\u001c\u0000\u011e\u011c\u0001\u0000\u0000\u0000"+
		"\u011e\u011d\u0001\u0000\u0000\u0000\u011f\u0121\u0001\u0000\u0000\u0000"+
		"\u0120\u0122\u0005\u0016\u0000\u0000\u0121\u0120\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000"+
		"\u0123\u0124\u0005\f\u0000\u0000\u0124\u012e\u0001\u0000\u0000\u0000\u0125"+
		"\u0127\u0005\u0016\u0000\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0126"+
		"\u0127\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128"+
		"\u012b\u0005\f\u0000\u0000\u0129\u012c\u00036\u001b\u0000\u012a\u012c"+
		"\u00038\u001c\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012b\u012a\u0001"+
		"\u0000\u0000\u0000\u012c\u012e\u0001\u0000\u0000\u0000\u012d\u011e\u0001"+
		"\u0000\u0000\u0000\u012d\u0126\u0001\u0000\u0000\u0000\u012e5\u0001\u0000"+
		"\u0000\u0000\u012f\u0130\u0005\u0018\u0000\u0000\u01307\u0001\u0000\u0000"+
		"\u0000\u0131\u0132\u0005\u0017\u0000\u0000\u01329\u0001\u0000\u0000\u0000"+
		"\u0133\u0134\u0003\u0014\n\u0000\u0134;\u0001\u0000\u0000\u0000\u0135"+
		"\u0141\u0005\u0018\u0000\u0000\u0136\u0141\u0005\u0017\u0000\u0000\u0137"+
		"\u0141\u0003>\u001f\u0000\u0138\u0141\u0005\u000f\u0000\u0000\u0139\u0141"+
		"\u0005\u0010\u0000\u0000\u013a\u0141\u0005\u0011\u0000\u0000\u013b\u0141"+
		"\u0005\u0012\u0000\u0000\u013c\u0141\u0005\u0013\u0000\u0000\u013d\u0141"+
		"\u0005\u0014\u0000\u0000\u013e\u0141\u0005\u0015\u0000\u0000\u013f\u0141"+
		"\u0005\u0003\u0000\u0000\u0140\u0135\u0001\u0000\u0000\u0000\u0140\u0136"+
		"\u0001\u0000\u0000\u0000\u0140\u0137\u0001\u0000\u0000\u0000\u0140\u0138"+
		"\u0001\u0000\u0000\u0000\u0140\u0139\u0001\u0000\u0000\u0000\u0140\u013a"+
		"\u0001\u0000\u0000\u0000\u0140\u013b\u0001\u0000\u0000\u0000\u0140\u013c"+
		"\u0001\u0000\u0000\u0000\u0140\u013d\u0001\u0000\u0000\u0000\u0140\u013e"+
		"\u0001\u0000\u0000\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0141\u0142"+
		"\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0142\u0143"+
		"\u0001\u0000\u0000\u0000\u0143=\u0001\u0000\u0000\u0000\u0144\u0145\u0007"+
		"\u0000\u0000\u0000\u0145?\u0001\u0000\u0000\u0000.GKPT]emu|\u0084\u0090"+
		"\u0093\u0098\u009f\u00a5\u00ac\u00b3\u00ba\u00c1\u00c7\u00d0\u00d4\u00d9"+
		"\u00dd\u00e2\u00e6\u00eb\u00ef\u00f4\u00f8\u00fb\u0100\u0105\u0107\u010b"+
		"\u010e\u0113\u0118\u011a\u011e\u0121\u0126\u012b\u012d\u0140\u0142";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}