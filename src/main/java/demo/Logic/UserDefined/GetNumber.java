package demo.Logic.UserDefined;

import demo.Logic.High.Arguments;
import demo.Logic.High.Substitution;
import demo.Logic.Symbols.Constant;
import demo.Logic.Symbols.Predicates.UDFunction;
import demo.Logic.Symbols.Variable;
import demo.Logic.Symbols.Constants.Number;

public class GetNumber extends UDFunction {
    public GetNumber() {
        super("GetNumber", 2);
    }

    @Override
    public String toString(Arguments args) {
        return "GetNumber("+args.get(0)+")="+args.get(1);
    }

    @Override
    public Substitution solve(Arguments args) {
        if(args.get(0) instanceof Constant && args.get(1) instanceof Number){
            Number first = Number.constantToNumber((Constant)args.get(0));
            if(first.equals(args.get(1))){
                return new Substitution();
            }else{
                return null;
            }
        }
        if(args.get(0) instanceof Constant && args.get(1) instanceof Constant){
            Number first = Number.constantToNumber((Constant)args.get(0));
            Number second = Number.constantToNumber((Constant)args.get(0));
            if(first.equals(second)){
                return new Substitution();
            }else{
                return null;
            }
        }

        if(args.get(0) instanceof Constant){
            Number first = Number.constantToNumber((Constant) args.get(0));
            return new Substitution((Variable) args.get(1), first);
        }else if(args.get(1) instanceof Number){
            return new Substitution((Variable) args.get(0), args.get(1));
        }

        return null;
    }
}