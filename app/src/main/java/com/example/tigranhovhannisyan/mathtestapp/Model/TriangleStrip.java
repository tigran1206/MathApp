package com.example.tigranhovhannisyan.mathtestapp.Model;

import com.example.tigranhovhannisyan.mathtestapp.Model.Point;
import com.example.tigranhovhannisyan.mathtestapp.Model.Triangle;

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
        return false;
    }

}
