
package demo.Logic.UserDefined;

import demo.Logic.High.Arguments;
import demo.Logic.High.Substitution;
import demo.Logic.Symbols.Constant;
import demo.Logic.Symbols.Predicates.UDFunction;
import demo.Logic.Symbols.Variable;

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
        if(args.get(0) instanceof Constant && args.get(1) instanceof Constant && args.get(2) instanceof Constant){
            Constant first = (Constant) args.get(0);
            Constant second = (Constant) args.get(1);
            Constant result = (Constant) args.get(2);
            if(first.plus(second).equals(result)){
                return new Substitution();
            }else{
                return null;
            }
        }

        if(args.get(0) instanceof Constant && args.get(1) instanceof Constant){
            Constant first = (Constant) args.get(0);
            Constant second = (Constant) args.get(1);
            return new Substitution((Variable) args.get(2), first.plus(second));

        }else if(args.get(0) instanceof Constant && args.get(2) instanceof Constant){
            Constant first = (Constant) args.get(0);
            Constant result = (Constant) args.get(2);
            return new Substitution((Variable) args.get(1), result.minus(first));

        }else if(args.get(1) instanceof Constant && args.get(2) instanceof Constant){
            Constant second = (Constant) args.get(1);
            Constant result = (Constant) args.get(2);
            return new Substitution((Variable) args.get(0), result.minus(second));
        }
        return null;
    }


}
