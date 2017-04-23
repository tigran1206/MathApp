package com.example.tigranhovhannisyan.mathtestapp.Model;

import com.example.tigranhovhannisyan.mathtestapp.Counter;

/**
 * Created by Arno on 4/8/2017.
 */

public class TwoVarEquation extends Equation {
    double multiplier;

    public TwoVarEquation(Point p1, Point p2, Point p3) {
        super(p1, p2);

        Counter.getInstance().setSumCount(2);
        Counter.getInstance().setMultipleCount(1);
        Counter.getInstance().setDivideCount(1);

        //Todo x = a bug
        multiplier = 1 / (p3.y - coefficient * p3.x - offset);
    }

    public double calculate(Point point) {
        Counter.getInstance().setSumCount(2);
        Counter.getInstance().setMultipleCount(2);

        return multiplier * (point.y - coefficient * point.x - offset);
    }
}
