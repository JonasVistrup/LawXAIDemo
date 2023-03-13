package com.example.demo.SLD;

import com.example.demo.Logic.High.Substitution;

public class Answer {
    public Substitution answer;
    public History reason;

    public Answer(Substitution answer, History reason){
        this.answer = answer;
        this.reason = reason;
    }
}
