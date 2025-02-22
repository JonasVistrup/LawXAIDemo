package demo.Logic.UserDefined;

import demo.Logic.High.Arguments;
import demo.Logic.Symbols.Constants.Date;
import demo.Logic.Symbols.Constants.Number;
import demo.Logic.Symbols.Predicates.UDRelation;
import demo.Logic.Symbols.Term;

import java.util.ArrayList;

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

        int suffixPos = getSameSuffix(args.get(0),args.get(1));
        return compareIfComparable(args.get(0).toString().substring(0,args.get(0).toString().length()-suffixPos), args.get(1).toString().substring(0,args.get(1).toString().length()-suffixPos));
    }

    private boolean compareIfComparable(String s0, String s1) {
        try {
            double d0 = Double.parseDouble(s0);
            double d1 = Double.parseDouble(s1);
            return d0 < d1;
        }catch (Exception e){
            return false;
        }
    }

    private int getSameSuffix(Term term0, Term term1) {
        String str0 = term0.toString();
        String str1 = term1.toString();
        int smallestTermLength = Math.min(str0.length(), str1.length());
        for(int i = 1; i<=smallestTermLength; i++){
            if(str0.charAt(str0.length()-i) != str1.charAt(str1.length()-i)){
                return i-1;
            }
        }
        return smallestTermLength;
    }
}
