package com.example.demo.Logic.UserDefined;

import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Predicates.UDFunction;
import com.example.demo.Logic.Symbols.Variable;
import com.example.demo.Logic.Symbols.Constants.Number;

public class Plus extends UDFunction {
    public Plus() {
        super("+", 3);
    }

    @Override
    public String toString(Arguments args) {
        return args.get(0)+"+"+args.get(1)+"="+args.get(2);
    }

    @Override
    public Substitution solve(Arguments args) {
        if(args.get(0) instanceof Number && args.get(1) instanceof Number && args.get(2) instanceof Number){
            Number first = (Number) args.get(0);
            Number second = (Number) args.get(1);
            Number result = (Number) args.get(2);
            if(first.add(second).equals(result)){
                return new Substitution();
            }else{
                return null;
            }
        }

        if(args.get(0) instanceof Number && args.get(1) instanceof Number){
            Number first = (Number) args.get(0);
            Number second = (Number) args.get(1);
            return new Substitution((Variable) args.get(2), first.add(second));

        }else if(args.get(0) instanceof Number && args.get(2) instanceof Number){
            Number first = (Number) args.get(0);
            Number result = (Number) args.get(2);
            return new Substitution((Variable) args.get(2), result.subtract(first));

        }else{
            Number second = (Number) args.get(1);
            Number result = (Number) args.get(2);
            return new Substitution((Variable) args.get(2), result.subtract(second));
        }
    }
}
