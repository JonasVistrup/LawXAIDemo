package demo.SLD;


import demo.Logic.High.Substitution;

public class Answer {
    public Substitution answer;
    public History reason;

    public Answer(Substitution answer, History reason){
        this.answer = answer;
        this.reason = reason;
    }
}
