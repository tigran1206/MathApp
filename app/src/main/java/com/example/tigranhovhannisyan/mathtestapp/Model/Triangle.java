package com.example.tigranhovhannisyan.mathtestapp.Model;

import android.util.Log;

import com.example.tigranhovhannisyan.mathtestapp.MathUtils;
import com.example.tigranhovhannisyan.mathtestapp.Model.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tigran.hovhannisyan on 2/16/2017.
 */

public class Triangle implements Serializable {
    List<Point> vertexes = new ArrayList<>();
    List<Point> nodes = new ArrayList<>();

    public List<Point> getNodes() {
        return nodes;
    }

    public int getNodesCount(){
        return nodes.size();
    }

    public Triangle(List<Point> vertexes){
        this.vertexes = vertexes;
    }

    public Triangle(Triangle triangle){
        this.vertexes = new ArrayList<>(triangle.vertexes);
        this.nodes = new ArrayList<>(triangle.nodes);
    }

    public boolean containsPoint(Point pt)
    {
        double sign1 = MathUtils.sign(pt, getVertex1(), getVertex2());
        double sign2 = MathUtils.sign(pt, getVertex2(), getVertex3());
        double sign3 = MathUtils.sign(pt, getVertex3(), getVertex1());

        return (sign1 >= 0 && sign2 >= 0 && sign3 >= 0) || (sign1 <= 0 && sign2 <= 0 && sign3 <= 0);
    }

    public void addNode(Point node){
        nodes.add(node);
    }

    public int containsNode(Point node){
        for(int i = 0; i < nodes.size(); i++) {
            Point mNode = nodes.get(i);
            if(mNode.equals(node)){
                return i;
            }
        }
        return -1;
    }

    public void deleteNode(int index) {
        nodes.remove(index);
    }

    public void addVertexNodes(boolean fromLeft){
        if(fromLeft){
            nodes.add(getVertex1());
            nodes.add(getVertex2());
        } else {
            nodes.add(getVertex2());
            nodes.add(getVertex3());
        }
    }

    public boolean isCollinear() {
        Equation equation = new Equation(getNodes().get(0), getNodes().get(1));
        if(equation.pointSatisfies(getNodes().get(2))){
            return true;
        } else {
            return false;
        }
    }

    public void performLineTransformation(Point interPoint) {
        if(nodes.size() == 2){
            nodes.set(0, interPoint);
        }
    }

    public Point getSingleNode(){
        return nodes.get(0);
    }

    public Point getVertex1() {
        return vertexes.get(0);
    }

    public Point getVertex2() {
        return vertexes.get(1);
    }

    public Point getVertex3() {
        return vertexes.get(2);
    }
}
