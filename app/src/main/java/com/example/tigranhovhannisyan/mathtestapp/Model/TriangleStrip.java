package com.example.tigranhovhannisyan.mathtestapp.Model;

import android.util.Log;

import com.example.tigranhovhannisyan.mathtestapp.MathUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiko on 2/18/2017.
 */

public class TriangleStrip implements Serializable{

    public static String key = "com.example.tigranhovhannisyan.mathtestapp.Model.TriangleStrip";

    List<Triangle> triangles;
    //List<Point> nodes;

    public TriangleStrip(List<Triangle> triangles){
        this.triangles = triangles;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    public void setNodes(List<Point> nodes) {
        //this.nodes = nodes;
        releaseTriangles();

        setTrianglesNodes(nodes);
    }

    private void setTrianglesNodes(List<Point> nodes){
        for (int i = 0; i < nodes.size(); i++) {
            Point node = nodes.get(i);
            node.setLetter("A" + (i + 1) +". ");
            for (int j = 0; j < triangles.size(); j++){
                Triangle triangle = triangles.get(j);
                if(triangle.containsPoint(node)){
                    triangle.addNode(node);
                }
            }
        }
    }

    private void releaseTriangles(){
        for (Triangle triangle:triangles) {
            triangle.getNodes().clear();
        }
    }

    public void divideByBasicProblem(int start, int end) {

    }

    private void createLeftStrip(int start) {
        List<Triangle> leftTriangles = new ArrayList<>();

        for (int i = 0; i < start; i++){
            leftTriangles.add(this.triangles.get(i));
        }

        Triangle rightSideTriangle = leftTriangles.get(leftTriangles.size() - 1);

        for (Point node : triangles.get(start).getNodes()) {
            int index = rightSideTriangle.containsNode(node);
            if(index != -1) {
                rightSideTriangle.deleteNode(index);
            }
        }

        rightSideTriangle.addVertexNodes();
        TriangleStrip leftStrip = new TriangleStrip(leftTriangles);
    }

    private void createRightStrip(){
        //ToDo
    }

    public boolean isBasic(){
        //ToDo
        for(int i = 0; i < getTriangles().size(); i++) {
            Triangle triangle = getTriangles().get(i);
            if(triangle.getNodesCount() == 2) {

            }
        }
        return false;
    }

    public IndexPair findBasicSubproblem() {
        IndexPair indexPair = new IndexPair();

        for(int i = 0; i < getTriangles().size(); i++) {
            Triangle triangle = getTriangles().get(i);

            if(triangle.getNodesCount() > 3) {
                return null;
            } else if(triangle.getNodesCount() == 3) {
                Equation equation = new Equation(triangle.getNodes().get(0), triangle.getNodes().get(1));
                if(equation.pointSatisfies(triangle.getNodes().get(2))){
                    return null;
                } else {
                    indexPair.setStart(i);
                    indexPair.setEnd(i);
                }
            }

            if(triangle.getNodesCount() == 2) {
                if(indexPair.getStart() != -1) {
                    indexPair.setEnd(i);
                    return extractBasicSubProblem(indexPair);
                }
                indexPair.setStart(i);
            } else if(triangle.getNodesCount() != 1){
                indexPair.release();
            }
        }
        return indexPair;
    }

    public IndexPair isIntersepted(IndexPair indexPair) {
        IndexPair pair = new IndexPair();

        Triangle leftTriangle = getTriangles().get(indexPair.getStart());
        Triangle rightTriangle = getTriangles().get(indexPair.getEnd());

        Equation equation1 = new Equation(leftTriangle.getVertex2(), leftTriangle.getVertex3());
        Equation equation2 = new Equation(leftTriangle.getNodes().get(0), leftTriangle.getNodes().get(1));

        Point interPoint = MathUtils.findIntersactionPoint(equation1, equation2);
        //insist of calling containsPoint method we can check it with another simple algorithm
        if(interPoint != null && leftTriangle.containsPoint(interPoint)) {
            pair.setStart(indexPair.getStart());
            pair.setLeftInterPoint(interPoint);
        } else {
            equation1.define(rightTriangle.getVertex1(), rightTriangle.getVertex2());
            equation2.define(rightTriangle.getNodes().get(0), rightTriangle.getNodes().get(1));

            interPoint = MathUtils.findIntersactionPoint(equation1, equation2);
            if(interPoint != null && rightTriangle.containsPoint(interPoint)) {
                pair.setEnd(indexPair.getEnd());
                pair.setRightInterPoint(interPoint);
            }
        }
        return pair;
    }

    public IndexPair extractBasicSubProblem(IndexPair indexPair) {
        if(indexPair.getLength() == 0) {
            Triangle triangle = getTriangles().get(indexPair.getEnd());
            if(triangle.isCollinear()) {
                //the problem finished with false
                return null;
            } else {
                //return end, start
                return indexPair;
            }
        }
        //we have a case with 2+2
        IndexPair interPair = isIntersepted(indexPair);
        if(interPair.isEmpty()) {
            return indexPair;
        } else {
            if(interPair.getStart() != -1) {
                getTriangles().get(indexPair.getStart()).performLineTransformation(interPair.getLeftInterPoint());
                indexPair.increment();
            }
            if(indexPair.getEnd() != -1) {
                getTriangles().get(indexPair.getEnd()).performLineTransformation(interPair.getRightInterPoint());
                indexPair.decrement();
            }
            return extractBasicSubProblem(indexPair);
        }
    }

}
