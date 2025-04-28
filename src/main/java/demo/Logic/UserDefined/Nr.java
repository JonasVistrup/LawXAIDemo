package demo.Logic.UserDefined;

import demo.Logic.High.Arguments;
import demo.Logic.Symbols.Constants.Number;
import demo.Logic.Symbols.Predicates.UDRelation;
import demo.Logic.Symbols.Constant;
import demo.Logic.Symbols.Constants.Lovstyk;

public class Nr extends UDRelation {
    public Nr() {
        super("Nr", 2);
    }

    @Override
    public String toString(Arguments args) {
        return "Nr("+ args.get(0) + ")=" + args.get(1);
    }

    @Override
    public boolean test(Arguments args) {
        if(args.size() != 2) throw new IllegalArgumentException("Lov takes exactly two arguments.");
        if(!(args.get(0) instanceof Constant) || !(args.get(1) instanceof Constant)) throw new IllegalArgumentException("Lov arguments must be constants.");
        
        Lovstyk lovstyk = Lovstyk.convertToLovstyk((Constant) args.get(0));
        Number number = Number.convertToIntNumber((Constant) args.get(1));
        if(lovstyk == null || number == null) return false;
        return lovstyk.getNr()!=-1 && lovstyk.getNr() == number.numerator();
    }
}

