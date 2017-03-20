package com.example.tigranhovhannisyan.mathtestapp.Model;


import java.io.Serializable;

/**
 * Created by tigran.hovhannisyan on 2/16/2017.
 */
public class Point implements Serializable{
    public double x;
    public double y;

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    String letter;

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public Point(){

    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point) {
        return (x == point.x && y == point.y);
    }
}
