package com.example.demo.Logic.UserDefined;


import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.Symbols.Predicates.UDRelation;
import com.example.demo.Logic.Symbols.Constants.Number;

public class Floor extends UDRelation {
    public Floor() {
        super("Floor", 2);
    }

    @Override
    public String toString(Arguments args) {
        return "Floor(" + args.get(0) + ")=" + args.get(1);
    }

    @Override
    public boolean test(Arguments args) {
        if(args.get(0) instanceof Number && args.get(1) instanceof Number){
            Number one = (Number) args.get(0);
            Number two = (Number) args.get(0);
            if(two.denominator() != 1) return false;
            return one.numerator()/one.denominator() == two.numerator();
        }
        return false;
    }
}
