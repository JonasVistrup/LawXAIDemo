package demo.Logic.Symbols;

import demo.Logic.High.Substitution;

/**
 * A logical constant
 */
public class Constant implements Term {

    /**
     * String representation of this constant.
     */
    private final String id;

    /**
     * Constructs a constant.
     * @param id constant's String representation
     */
    public Constant(String id){
        this.id = id;
    }
    public Constant(double d){ this.id = d+"";}

    /**
     * Any variant is also itself, since constants do not allow instantiation.
     * @param version which variant that should be returned (is ignored)
     * @return itself
     */
    public Term getVariant(int version){
        return this;
    }

    /**
     * String representation of this constant.
     * @return id
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * Returns the result of applying the substitution to this term (which is always itself, since only variables can be substituted).
     * @param substitution substitution to apply
     * @return itself
     */
    @Override
    public Term applySub(Substitution substitution) {
        return this;
    }

    public int compareTo(Term o){
        if(o instanceof Variable){
            return -1;
        }else{
            return this.toString().compareTo(o.toString());
        }
    }

    public Constant plus(Constant other){
        String suffix = getSuffix();
        String oSuffix = other.getSuffix();
        if(!suffix.equals(oSuffix)) throw new IllegalArgumentException(this +" and "+other.toString()+" does not have same suffix");
        return new Constant(Double.parseDouble(id.substring(0,id.length()-suffix.length()))+Double.parseDouble(other.id.substring(0,other.id.length()-oSuffix.length())));
    }

    public Constant minus(Constant other){
        String suffix = getSuffix();
        String oSuffix = other.getSuffix();
        if(suffix.equals(oSuffix)) throw new IllegalArgumentException(this +" and "+other.toString()+" does not have same suffix");
        return new Constant(Double.parseDouble(id.substring(0,id.length()-suffix.length())) - Double.parseDouble(other.id.substring(0,other.id.length()-oSuffix.length())));
    }

    public Constant mul(Constant other){
        String suffix = getSuffix();
        String oSuffix = other.getSuffix();
        if(suffix.equals(oSuffix)) throw new IllegalArgumentException(this +" and "+other.toString()+" does not have same suffix");
        return new Constant(Double.parseDouble(id.substring(0,id.length()-suffix.length())) * Double.parseDouble(other.id.substring(0,other.id.length()-oSuffix.length())));
    }

    public Constant div(Constant other){
        String suffix = getSuffix();
        String oSuffix = other.getSuffix();
        if(suffix.equals(oSuffix)) throw new IllegalArgumentException(this +" and "+other.toString()+" does not have same suffix");
        return new Constant(Double.parseDouble(id.substring(0,id.length()-suffix.length())) / Double.parseDouble(other.id.substring(0,other.id.length()-oSuffix.length())));
    }

    private String getSuffix(){
        for(int i = 0; i<id.length(); i++){
            if(!isNumber(id.charAt(i))) return id.substring(i);
        }
        return "";
    }

    private boolean isNumber(char c){ //TODO allows for multiple ., as in 17.856.1
        return c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9' || c=='.';
    }
}
