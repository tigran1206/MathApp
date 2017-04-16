package com.example.tigranhovhannisyan.mathtestapp;

/**
 * Created by Arno on 4/16/2017.
 */

public class Counter {
    public static Counter instance = new Counter();
    public static Counter getInstance(){ return instance; }

    private Counter(){}

    private int multipleCount;
    private int sumCount;
    private int divideCount;

    private boolean isCount = false;

    public void setCount(boolean count) {
        isCount = count;
    }

    public void setDivideCount(int divideCount) {
        if(isCount){
            this.divideCount += divideCount;
        }
    }

    public void setMultipleCount(int multipleCount) {
        if(isCount){
            this.multipleCount += multipleCount;
        }
    }

    public void setSumCount(int sumCount) {
        if(isCount){
            this.sumCount += sumCount;
        }
    }

    public int getDivideCount() {
        return divideCount;
    }

    public int getMultipleCount() {
        return multipleCount;
    }

    public int getSumCount() {
        return sumCount;
    }
}
