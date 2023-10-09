package com.example.demo.Logic.UserDefined;

import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Predicates.UDFunction;
import com.example.demo.Logic.Symbols.Variable;
import com.example.demo.Logic.Symbols.Constants.Number;

public class Div extends UDFunction {
    public Div() {
        super("/", 3);
    }

    @Override
    public String toString(Arguments args) {
        return args.get(0)+"/"+args.get(1)+"="+args.get(2);
    }

    @Override
    public Substitution solve(Arguments args) {
        if(args.get(0) instanceof Number && args.get(1) instanceof Number && args.get(2) instanceof Number){
            Number first = (Number) args.get(0);
            Number second = (Number) args.get(1);
            Number result = (Number) args.get(2);
            if(first.divide(second).equals(result)){
                return new Substitution();
            }else{
                return null;
            }
        }

        if(args.get(0) instanceof Constant && args.get(1) instanceof Constant){
            Number first = (Number) args.get(0);
            Number second = (Number) args.get(1);
            return new Substitution((Variable) args.get(2), first.divide(second));
        }else if(args.get(0) instanceof Constant && args.get(2) instanceof Constant){
            Number first = (Number) args.get(0);
            Number result = (Number) args.get(2);
            return new Substitution((Variable) args.get(1), first.divide(result));
        }else{
            Number second = (Number) args.get(1);
            Number result = (Number) args.get(2);
            return new Substitution((Variable) args.get(0), second.multiply(result));
        }
    }
}
