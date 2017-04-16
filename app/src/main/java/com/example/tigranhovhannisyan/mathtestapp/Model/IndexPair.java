package com.example.tigranhovhannisyan.mathtestapp.Model;

import com.example.tigranhovhannisyan.mathtestapp.Counter;

/**
 * Created by tigran.hovhannisyan on 3/31/2017.
 */

public class IndexPair {
    private int start = -1;
    private int end = -1;

    private Point leftInterPoint;
    private Point rightInterPoint;

    public void release() {
        start = -1;
        end = -1;
    }

    public boolean isEmpty(){
        return start == -1 && end == -1;
    }

    public int getLength() {
        Counter.getInstance().setSumCount(1);

        return end - start;
    }

    public void increment(){
        Counter.getInstance().setSumCount(1);
        start ++;
    }

    public void decrement() {
        Counter.getInstance().setSumCount(1);
        end --;
    }

    public void setLeftInterPoint(Point leftInterPoint) {
        this.leftInterPoint = leftInterPoint;
    }

    public void setRightInterPoint(Point rightInterPoint) {
        this.rightInterPoint = rightInterPoint;
    }

    public Point getLeftInterPoint() { return leftInterPoint; }

    public Point getRightInterPoint() { return rightInterPoint; }

    public void setEnd(int end) { this.end = end; }

    public void setStart(int start) { this.start = start; }

    public int getEnd() { return end; }

    public int getStart() { return start; }
}
