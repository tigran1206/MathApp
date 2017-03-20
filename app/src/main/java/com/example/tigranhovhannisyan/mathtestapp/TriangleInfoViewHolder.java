package com.example.tigranhovhannisyan.mathtestapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tigranhovhannisyan.mathtestapp.Model.Point;
import com.example.tigranhovhannisyan.mathtestapp.Model.Triangle;

/**
 * Created by tigran.hovhannisyan on 2/20/2017.
 */

public class TriangleInfoViewHolder extends RecyclerView.ViewHolder {

    TextView vertexTv;
    TextView nodesTv;

    public TriangleInfoViewHolder(View itemView) {
        super(itemView);

        vertexTv = (TextView) itemView.findViewById(R.id.vertex1_tv);
        nodesTv = (TextView) itemView.findViewById(R.id.nodes_tv);
    }

    public void bind(Triangle triangle){
        vertexTv.setText(tuneVertex("V1.  ", triangle.getVertex1()) + "\n" + tuneVertex("V2.  ", triangle.getVertex2()) + "\n" + tuneVertex("V3.  ", triangle.getVertex3()));
        String nodesText = "";
        for(int i = 0; i < triangle.getNodes().size(); i++){
            nodesText += tuneVertex(triangle.getNodes().get(i).getLetter(), triangle.getNodes().get(i)) + "\n";
        }
        nodesTv.setText(nodesText);
    }

    public String tuneVertex(String v, Point point){
        return v + "(" + point.x + ", " + point.y + ")";
    }

}
