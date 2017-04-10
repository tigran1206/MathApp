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
//            node.setLetter("A" + (i + 1) +". ");
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

    private TriangleStrip createLeftStrip(int start) {
        List<Triangle> leftTriangles = new ArrayList<>();

        for (int i = 0; i < start; i++){
            leftTriangles.add(new Triangle(this.triangles.get(i)));
        }

        if(leftTriangles.size() > 0){
            Triangle rightSideTriangle = leftTriangles.get(leftTriangles.size() - 1);

            for (Point node : triangles.get(start).getNodes()) {
                int index = rightSideTriangle.containsNode(node);
                if(index != -1) {
                    rightSideTriangle.deleteNode(index);
                }
            }

            rightSideTriangle.addVertexNodes(false);
        }

        return new TriangleStrip(leftTriangles);
    }

    private TriangleStrip createRightStrip(int end) {
        List<Triangle> rightTriangles = new ArrayList<>();

        for (int i = end + 1; i < getTriangles().size(); i++) {
            rightTriangles.add(new Triangle(this.triangles.get(i)));
        }

        if(rightTriangles.size() > 0){
            Triangle leftSideTriangle = rightTriangles.get(0);

            for (Point node : triangles.get(end).getNodes()) {
                int index = leftSideTriangle.containsNode(node);
                if(index != -1) {
                    leftSideTriangle.deleteNode(index);
                }
            }

            leftSideTriangle.addVertexNodes(true);
        }

        return new TriangleStrip(rightTriangles);
    }

    public boolean isBasicSubProblemPoised(IndexPair indexPair) {
        if(indexPair.getLength() == 1) {
            return true;
        } else {
            Triangle startTriangle = getTriangles().get(indexPair.getStart());
            TwoVarEquation twoVarEquation = new TwoVarEquation(startTriangle.getNodes().get(0), startTriangle.getNodes().get(1), startTriangle.getVertex1());

            EquationSum equationSum = null;

            for (int i = indexPair.getStart() + 1; i <= indexPair.getEnd(); i++) {
                Triangle triangle = getTriangles().get(i);
                if(equationSum == null){
                    triangle.getVertex1().setValue(twoVarEquation);
                    triangle.getVertex2().setValue(twoVarEquation);
                } else {
                    triangle.getVertex1().setValue(equationSum);
                    triangle.getVertex2().setValue(equationSum);
                }

                TwoVarEquation p1 = new TwoVarEquation(triangle.getSingleNode(), triangle.getVertex2(), triangle.getVertex1());
                TwoVarEquation p2 = new TwoVarEquation(triangle.getSingleNode(), triangle.getVertex1(), triangle.getVertex2());
                equationSum = new EquationSum(p1, p2, triangle.getVertex1().getValue(), triangle.getVertex2().getValue());
            }

            return equationSum.calculateSum(getTriangles().get(indexPair.getEnd()).getNodes().get(1)) == 0;

        }
    }

    public boolean isPoised() {
        if(getTriangles().size() == 0){
            return true;
        }
        IndexPair indexPair = findBasicSubproblem();
        if(indexPair == null || indexPair.isEmpty()){
            return false;
        }
        return isBasicSubProblemPoised(indexPair) && createLeftStrip(indexPair.getStart()).isPoised() && createRightStrip(indexPair.getEnd()).isPoised();
    }

    public IndexPair find2m2BSubProblem() {
        IndexPair indexPair = new IndexPair();

        for(int i = 0; i < getTriangles().size(); i++) {
            Triangle triangle = getTriangles().get(i);

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

    public IndexPair find3BSubProblem() {
        IndexPair indexPair = new IndexPair();

        for (int i = 0; i < getTriangles().size(); i++){
            Triangle triangle = getTriangles().get(i);
            if(triangle.getNodesCount() > 3){
                return null;
            } else if(triangle.getNodesCount() == 3) {
                if(triangle.isCollinear()){
                    return null;
                } else {
                    indexPair.setStart(i);
                    indexPair.setEnd(i);
                }
            }
        }

        return indexPair;
    }

    public IndexPair findBasicSubproblem() {
        IndexPair pair = find3BSubProblem();
        if(pair == null){
            return null;
        }
        if(pair.isEmpty()) {
            pair = find2m2BSubProblem();
            return pair;
        }
        return pair;
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
                return null;
            } else {
                return indexPair;
            }
        }

        IndexPair interPair = isIntersepted(indexPair);
        if(interPair.isEmpty()) {
            return indexPair;
        } else {
            if(interPair.getStart() != -1) {
                getTriangles().get(indexPair.getStart()).performLineTransformation(interPair.getLeftInterPoint());
                indexPair.increment();
            }
            if(indexPair.getEnd() != -1 && !(indexPair.getLength() == 1 && interPair.getStart() == -1)) {
                getTriangles().get(indexPair.getEnd()).performLineTransformation(interPair.getRightInterPoint());
                indexPair.decrement();
            }
            return extractBasicSubProblem(indexPair);
        }
    }

}
