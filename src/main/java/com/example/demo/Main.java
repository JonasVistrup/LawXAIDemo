package com.example.demo;

import com.example.demo.Grammar.LawXAIBaseListener;
import com.example.demo.Grammar.LawXAILexer;
import com.example.demo.Grammar.LawXAIParser;
import com.example.demo.Grammar.ProgramParserVisitor;
import com.example.demo.Logic.ProgramBuilder;
import com.example.demo.Logic.ProgramParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        var inputStream = CharStreams.fromFileName("src/main/java/com/example/demo/res/2/§3.jlaw");
        inputStream = CharStreams.fromString("B(a) <- Traf(P,T):ah;\n");
        LawXAILexer lexer = new LawXAILexer(inputStream);
        var commonTokenStream = new CommonTokenStream(lexer);
        var parser = new LawXAIParser(commonTokenStream);
        System.out.println(parser);
        var visitor = new ProgramParserVisitor(new ProgramParser());
        var listener = new LawXAIBaseListener();
        //ParseTreeWalker.DEFAULT.walk(listener,parser.prog());
        visitor.visitProg(parser.prog());
    }
}
