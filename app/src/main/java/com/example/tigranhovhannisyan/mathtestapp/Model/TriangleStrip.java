package com.example.tigranhovhannisyan.mathtestapp.Model;

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
            if(triangle.getNodesCount() == 2) {
                if(indexPair.getStart() != -1) {
                    indexPair.setEnd(i);
                    //int interIndex  = isIntersepted(indexPair.getStart(), indexPair.getEnd());

                    //if(interIndex != -1) {
                    return extractBasicSubProblem(indexPair);
//                        if(indexPair.getStart() == interIndex){
//
//                        } else {
//
//                        }
                    //} else {
                        //ToDo founded
                    //    return indexPair;
                    //}
                }
                indexPair.setStart(i);
            } else if(triangle.getNodesCount() != 1){
                indexPair.release();
            }
        }
        return indexPair;
    }

    public int isIntersepted(IndexPair indexPair) {
        Triangle leftTriangle = getTriangles().get(indexPair.getStart());
        Triangle rightTriangle = getTriangles().get(indexPair.getEnd());

        Equation equation1 = new Equation(leftTriangle.getVertex2(), leftTriangle.getVertex3());
        Equation equation2 = new Equation(leftTriangle.getNodes().get(0), leftTriangle.getNodes().get(1));

        Point interPoint = MathUtils.findIntersactionPoint(equation1, equation2);
        if(interPoint != null && leftTriangle.containsPoint(interPoint)) {
            return indexPair.getStart();
        } else {
            equation1.define(rightTriangle.getVertex1(), rightTriangle.getVertex2());
            equation2.define(rightTriangle.getNodes().get(0), rightTriangle.getNodes().get(1));

            interPoint = MathUtils.findIntersactionPoint(equation1, equation2);
            if(interPoint != null && rightTriangle.containsPoint(interPoint)) {
                return indexPair.getEnd();
            }
        }
        return -1;
    }

    public IndexPair extractBasicSubProblem(IndexPair indexPair){
        if(indexPair.getStart() - indexPair.getEnd() == 0) {
            Triangle triangle = getTriangles().get(indexPair.getEnd());
            if(triangle.isCollinear()) {
                //the problem finished with false
                return null;
            } else {
                //return end, start
            }
        }

        int index = isIntersepted(indexPair);
        if(index == -1) {
            return indexPair;
        } else {
            return indexPair;
        }
    }

}
