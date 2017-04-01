package com.example.tigranhovhannisyan.mathtestapp;

import android.util.Log;

import com.example.tigranhovhannisyan.mathtestapp.Model.Equation;
import com.example.tigranhovhannisyan.mathtestapp.Model.Point;

/**
 * Created by tigran.hovhannisyan on 2/16/2017.
 */

public class MathUtils {
    public static double sign(Point p1, Point p2, Point p3)
    {
        double value = (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
        Log.d("Expression", p1.x + " - " + p3.x + " * " + p2.y + " - " + p3.y + "  -  " + p2.x + " - " + p3.x + " * " + p1.y + " - " + p3.y + " = " + value);
        return value;
    }

    //Todo check zugaher
    public static Point findIntersactionPoint(Equation equation1, Equation equation2) {
        if(equation1.getCoefficient() == equation2.getCoefficient()){
            return null;
        }

        double x = (equation2.getOffset() - equation1.getOffset()) / (equation1.getCoefficient() - equation2.getCoefficient());
        double y = equation1.calculateValue(x);

        return new Point(x, y);
    }

}
