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
        Counter.getInstance().setMultipleCount(2);
        Counter.getInstance().setSumCount(5);

        double value = (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
        return value;
    }

    public static Point findIntersactionPoint(Equation equation1, Equation equation2) {
        if(equation1.getCoefficient() == equation2.getCoefficient()){
            return null;
        }

        double x;
        double y;

        if(equation1.getCoefficient() == null){
            x = equation1.getOffset();
            y = equation2.calculateValue(x);
        } else if(equation2.getCoefficient() == null){
            x = equation2.getOffset();
            y = equation1.calculateValue(x);
        } else {
            Counter.getInstance().setDivideCount(1);
            Counter.getInstance().setSumCount(2);
            x = (equation2.getOffset() - equation1.getOffset()) / (equation1.getCoefficient() - equation2.getCoefficient());
            y = equation1.calculateValue(x);
        }

        return new Point(x, y);
    }

}
