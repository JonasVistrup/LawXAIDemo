package com.example.demo.Logic.UserDefined;

import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.High.Substitution;
import com.example.demo.Logic.Symbols.Constant;
import com.example.demo.Logic.Symbols.Predicates.UDFunction;
import com.example.demo.Logic.Symbols.Variable;
import com.example.demo.Logic.Symbols.Constants.Number;

public class Mul extends UDFunction {
    public Mul() {
        super("x", 3);
    }

    @Override
    public String toString(Arguments args) {
        return args.get(0)+"x"+args.get(1)+"="+args.get(2);
    }

    @Override
    public Substitution solve(Arguments args) {
        if(args.get(0) instanceof Number && args.get(1) instanceof Number && args.get(2) instanceof Number){
            Number first = (Number) args.get(0);
            Number second = (Number) args.get(1);
            Number result = (Number) args.get(2);
            if(first.multiply(second).equals(result)){
                return new Substitution();
            }else{
                return null;
            }
        }

        if(args.get(0) instanceof Number && args.get(1) instanceof Number){
            Number first = (Number) args.get(0);
            Number second = (Number) args.get(1);
            return new Substitution((Variable) args.get(2), first.multiply(second));

        }else if(args.get(0) instanceof Constant && args.get(2) instanceof Constant){
            Number first = (Number) args.get(0);
            Number result = (Number) args.get(2);
            return new Substitution((Variable) args.get(2), result.divide(first));

        }else{
            Number second = (Number) args.get(1);
            Number result = (Number) args.get(2);
            return new Substitution((Variable) args.get(2), result.divide(second));
        }
    }
}
