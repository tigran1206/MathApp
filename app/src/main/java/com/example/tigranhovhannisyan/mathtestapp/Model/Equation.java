package com.example.tigranhovhannisyan.mathtestapp.Model;

import android.util.Log;

import com.example.tigranhovhannisyan.mathtestapp.Counter;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Tiko on 2/18/2017.
 */

public class Equation implements Serializable {
    protected Double coefficient;
    protected Double offset;

    public Double getCoefficient() { return coefficient; }

    public Double getOffset() { return offset; }

    public Equation(){

    }

    public Equation(double coefficient, double offset) {
        this.coefficient = coefficient;
        this.offset = offset;
    }

    public Equation(Point p1, Point p2) {
        define(p1, p2);
    }

    public void define(Point p1, Point p2) {
        if(p1.x == p2.x){
            coefficient = null;
            offset = p1.x;
        } else {
            Counter.getInstance().setSumCount(3);
            Counter.getInstance().setMultipleCount(1);
            Counter.getInstance().setDivideCount(1);

            coefficient = (p2.y - p1.y) / (p2.x - p1.x);
            offset = p1.y - coefficient * p1.x;
        }
    }

    public boolean pointSatisfies(Point point) {
        if(coefficient == null){
            return point.x == offset;
        }
        Counter.getInstance().setSumCount(1);
        Counter.getInstance().setMultipleCount(1);
        return point.y == point.x * coefficient + offset;
    }

    public double calculateValue(double x) {
        Counter.getInstance().setSumCount(1);
        Counter.getInstance().setMultipleCount(1);
        return coefficient * x + offset;
    }

}
