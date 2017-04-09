package com.example.tigranhovhannisyan.mathtestapp.Model;

/**
 * Created by Arno on 4/8/2017.
 */

public class TwoVarEquation extends Equation{
    double multiplier;

    public TwoVarEquation(Point p1, Point p2, Point p3) {
        super(p1, p2);
        multiplier = 1 / (p3.y - coefficient * p3.x - offset);
    }

    public double calculate(Point point){
        return multiplier * (point.y - coefficient * point.x - offset);
    }

}
