package com.example.tigranhovhannisyan.mathtestapp.Model;

/**
 * Created by tigran.hovhannisyan on 3/31/2017.
 */

public class IndexPair {
    int start = -1;
    int end = -1;

    public void release(){
        start = -1;
        end = -1;
    }

    public void setEnd(int end) { this.end = end; }

    public void setStart(int start) { this.start = start; }

    public int getEnd() { return end; }

    public int getStart() { return start; }
}
