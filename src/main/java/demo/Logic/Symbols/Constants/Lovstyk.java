package demo.Logic.Symbols.Constants;


import demo.Logic.Symbols.Constant;

public class Lovstyk extends Constant {
    private final int paragraf;
    private final int stk;
    private final int nr;

    public Lovstyk(int paragraf, int stk, int nr) {
        super("§"+paragraf+" Stk."+stk+" Nr."+nr);
        this.paragraf = paragraf;
        this.stk = stk;
        this.nr = nr;
    }

    public static Lovstyk convertToLovstyk(Constant constant) {
        if(constant.toString().contains("§")){
            String[] parts = constant.toString().split(" ");
            String[] lovstykParts = parts[0].split("§");
            int paragraf = tryParseInt(lovstykParts[1]);
            int stk = parts.length >= 2 && parts[1].split("stk.").length==2? tryParseInt(parts[1].split("stk.")[1]) : -1; //TODO make faster
            int nr = parts.length >= 3 && parts[2].split("nr.").length==2? tryParseInt(parts[2].split("nr.")[1]): -1;
            if(paragraf == -1) return null;
            return new Lovstyk(paragraf, stk, nr);
        }
        return null;
    }

    private static int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Lovstyk)) return false;
        Lovstyk other = (Lovstyk) obj;
        return this.paragraf == other.paragraf && this.stk == other.stk && this.nr == other.nr;
    }

    public int getLov() {
        return paragraf;
    }
    public int getStk() {
        return stk;
    }
    public int getNr() {
        return nr;
    }
}
