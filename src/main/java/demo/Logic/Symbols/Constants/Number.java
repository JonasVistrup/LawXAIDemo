package demo.Logic.Symbols.Constants;

import demo.Logic.Symbols.Constant;

/**
 * A rational number
 */
public class Number extends Constant implements Comparable<Number> {

    private final int numerator;
    private final int denominator;

    /**
     * Constructs an integer.
     * @param number The integer.
     */
    public Number(int number) {
        super(""+number);
        this.numerator = number;
        this.denominator = 1;
    }


    public static Number convertToIntNumber(Constant constant) {
        try {
            return new Number(Integer.parseInt(constant.toString()));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public Number(int numerator, int denominator){
        super(""+ ((double)numerator/(double)denominator));
        while(numerator % 2 == 0 && denominator % 2 == 0){
            numerator = numerator/2;
            denominator = denominator/2;
        }
        if(numerator % denominator == 0){
            numerator = numerator / denominator;
            denominator = 1;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Number extend(int extender){
        return new Number(this.numerator*extender, this.denominator*extender);
    }

    public Number add(Number other){
        return new Number(this.numerator*other.denominator+other.numerator*this.denominator,this.denominator*other.denominator);
    }


    public Number subtract(Number other){
        return new Number(this.numerator*other.denominator-other.numerator*this.denominator,this.denominator*other.denominator);
    }

    public Number multiply(Number other){
        return new Number(this.numerator*other.numerator,this.denominator*other.denominator);
    }

    public Number divide(Number other){
        return new Number(this.numerator*other.denominator,this.denominator*other.numerator);
    }

    public int numerator(){
        return numerator;
    }
    public int denominator(){
        return denominator;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if(!(obj instanceof Number)) return false;

        Number other = (Number) obj;
        return this.numerator*other.denominator==other.numerator*this.denominator;
    }

    @Override
    public int compareTo(Number o) {
        return this.numerator*o.denominator-o.numerator*this.denominator;
    }

    public static Number constantToNumber(Constant c){
        Double d = null;
        int i;
        try {
            for (i = 1; i <= c.toString().length(); i++) {
                d = Double.parseDouble(c.toString().substring(0,i));
            }
            return doubleToNumber(d);
        } catch (Exception ignored) {
        }
        if(d == null) throw new IllegalArgumentException(c.toString()+" is not a valid number");
        return doubleToNumber(d);
    }

    public static Number doubleToNumber(double d){
        String dString = d + "";
        if(!dString.contains(".")) return new Number(Integer.parseInt(dString));

        String[] parts = dString.split("\\.");
        int denominator = (int) Math.pow(10,parts[1].length());
        int numerator = Integer.parseInt(parts[0])*denominator+Integer.parseInt(parts[1]);
        return new Number(numerator,denominator);
    }
}
