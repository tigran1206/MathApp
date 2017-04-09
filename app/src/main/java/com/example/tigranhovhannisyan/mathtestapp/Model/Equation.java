package com.example.tigranhovhannisyan.mathtestapp.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Tiko on 2/18/2017.
 */

public class Equation implements Serializable {
    protected double coefficient;
    protected double offset;

    public double getCoefficient() { return coefficient; }

    public double getOffset() { return offset; }

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
        coefficient = (p2.y - p1.y) / (p2.x - p1.x);
        offset = p1.y - coefficient * p1.x;

        Log.d("coeff", String.valueOf(coefficient));
        Log.d("offset", String.valueOf(offset));
    }

    public boolean pointSatisfies(Point point){
        return point.y == point.x * coefficient + offset;
    }

    public double calculateValue(double x){
        return coefficient * x + offset;
    }

}
