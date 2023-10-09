package com.example.demo.Logic.Symbols.Constants;

import com.example.demo.Logic.Symbols.Constant;

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
}
