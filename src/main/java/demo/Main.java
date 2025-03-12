package demo;

import demo.Grammar.LawXAIBaseListener;
import demo.Grammar.LawXAILexer;
import demo.Grammar.LawXAIParser;
import demo.Grammar.ProgramParserVisitor;
import demo.Logic.High.Program;
import demo.Logic.ProgramBuilder;
import demo.Logic.ProgramParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        var inputStream = CharStreams.fromFileName("src/main/java/com/example/demo/res/2/§3.jlaw");
        inputStream = CharStreams.fromString("B(a,b) <- [Lol(T/\\G,1~<) /\\ ~A(B){a}]\\/Traf(P,L(a),a /\\[c\\/k],e\\/f):ah;\n");
        LawXAILexer lexer = new LawXAILexer(inputStream);
        var commonTokenStream = new CommonTokenStream(lexer);
        var parser = new LawXAIParser(commonTokenStream);
        var visitor = new ProgramParserVisitor(new ProgramParser());
        var listener = new LawXAIBaseListener();
        visitor.visitProg(parser.prog());

    }


}
