package demo.Logic.Symbols.Constants;


import demo.Logic.Symbols.Constant;

public class Date extends Constant implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        super(day+"/"+month+"/"+year);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Date)) return false;
        Date other = (Date) obj;
        return this.day == other.day && this.month == other.month && this.year == other.year;
    }


    @Override
    public int compareTo(Date o) {
        if(this.year != o.year){
            return this.year-o.year;
        }else if(this.month != o.month){
            return this.month - o.month;
        }else if(this.day != o.day){
            return this.day - o.day;
        }
        return 0;
    }
}
