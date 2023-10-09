package com.example.demo.Logic.UserDefined;

import com.example.demo.Logic.High.Arguments;
import com.example.demo.Logic.Symbols.Constants.Date;
import com.example.demo.Logic.Symbols.Constants.Number;
import com.example.demo.Logic.Symbols.Predicates.UDRelation;

public class Less extends UDRelation {
    public Less() {
        super("<", 2);
    }

    @Override
    public String toString(Arguments args) {
        return args.get(0) + "<" + args.get(1);
    }

    @Override
    public boolean test(Arguments args) {
        if(args.get(0) instanceof Number && args.get(1) instanceof Number){
            return ((Number) args.get(0)).compareTo((Number) args.get(1)) < 0;
        }else if(args.get(0) instanceof Date && args.get(1) instanceof Date){
            return ((Date) args.get(0)).compareTo((Date) args.get(1)) < 0;
        }
        return false;
    }
}
