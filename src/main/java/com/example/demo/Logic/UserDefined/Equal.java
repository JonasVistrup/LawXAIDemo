package com.example.demo.Logic.UserDefined;


import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Predicates.UDRelation;
import com.example.demo.Logic.Symbols.Constants.Number;

public class Equal extends UDRelation {
    public Equal() {
        super("=", 2);
    }

    @Override
    public String toString(Arguments args) {
        return args.get(0) + "=" + args.get(1);
    }

    @Override
    public boolean test(Arguments args) {
        if(args.get(0) instanceof Constant & args.get(1) instanceof Constant)
            return args.get(0) == args.get(1);
        throw new IllegalArgumentException("Args ("+args.get(0)+","+args.get(1)+") to = are not constant");
    }
}
