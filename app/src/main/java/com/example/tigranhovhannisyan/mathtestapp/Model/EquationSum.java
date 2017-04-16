package com.example.tigranhovhannisyan.mathtestapp.Model;

import com.example.tigranhovhannisyan.mathtestapp.Counter;

/**
 * Created by Arno on 4/9/2017.
 */

public class EquationSum {
    TwoVarEquation twoVarEquation1;
    TwoVarEquation twoVarEquation2;

    double c1;
    double c2;

    public EquationSum(){

    }

    public EquationSum(TwoVarEquation twoVarEquation1, TwoVarEquation twoVarEquation2, double c1, double c2){
        this.twoVarEquation1 = twoVarEquation1;
        this.twoVarEquation2 = twoVarEquation2;
        this.c1 = c1;
        this.c2 = c2;
    }

    public double calculateSum(Point p) {
        Counter.getInstance().setSumCount(1);
        Counter.getInstance().setMultipleCount(2);
        return c1 * twoVarEquation1.calculate(p) + c2 * twoVarEquation2.calculate(p);
    }

}
