package demo.Logic.UserDefined;

import demo.Logic.High.Arguments;
import demo.Logic.High.Substitution;
import demo.Logic.Symbols.Constant;
import demo.Logic.Symbols.Predicates.UDFunction;
import demo.Logic.Symbols.Variable;

public class Combine extends UDFunction {
    public Combine() {
        super("Combine", 3);
    }

    @Override
    public String toString(Arguments args) {
        return args.get(0)+" ++ "+args.get(1)+"="+args.get(2);
    }

    @Override
    public Substitution solve(Arguments args) {
        if(args.get(0) instanceof Constant && args.get(1) instanceof Constant && args.get(2) instanceof Constant){
            if(( args.get(0).toString()+args.get(1).toString() ).equals(args.get(2).toString())){
                return new Substitution();
            }else{
                return null;
            }
        }

        if(args.get(0) instanceof Constant && args.get(1) instanceof Constant){
            Constant first = (Constant) args.get(0);
            Constant second = (Constant) args.get(1);
            return new Substitution((Variable) args.get(2), new Constant(first.toString() + second.toString()));

        }else if(args.get(0) instanceof Constant && args.get(2) instanceof Constant){
            Constant first = (Constant) args.get(0);
            Constant result = (Constant) args.get(2);
            String[] parts = result.toString().split(first.toString());
            if(parts.length == 2){
                return new Substitution((Variable) args.get(1), new Constant(parts[1]));
            }else{
                return null;
            }

        }else if(args.get(1) instanceof Constant && args.get(2) instanceof Constant){
            Constant second = (Constant) args.get(1);
            Constant result = (Constant)args.get(2);
            String[] parts = result.toString().split(second.toString());
            if(parts.length == 2){
                return new Substitution((Variable) args.get(0), new Constant(parts[0]));
            }else{
                return null;
            }
        }
        return null;
    }
}