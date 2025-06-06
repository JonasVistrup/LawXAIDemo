package demo.Logic.UserDefined;

import demo.Logic.High.Arguments;
import demo.Logic.High.Substitution;
import demo.Logic.Symbols.Constant;
import demo.Logic.Symbols.Predicates.UDFunction;
import demo.Logic.Symbols.Variable;
import demo.Logic.Symbols.Constants.Number;

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
            Number first = Number.constantToNumber((Constant) args.get(0));
            Number second = Number.constantToNumber((Constant) args.get(1));
            return new Substitution((Variable) args.get(2), first.divide(second));

        }else if(args.get(0) instanceof Constant && args.get(2) instanceof Constant){
            Number first = Number.constantToNumber((Constant) args.get(0));
            Number result = Number.constantToNumber((Constant) args.get(2));
            return new Substitution((Variable) args.get(1), first.divide(result));

        }else if(args.get(1) instanceof Constant && args.get(2) instanceof Constant){
            Number second = Number.constantToNumber((Constant) args.get(1));
            Number result = Number.constantToNumber((Constant) args.get(2));
            return new Substitution((Variable) args.get(0), second.multiply(result));
        }

        return null;
    }
}