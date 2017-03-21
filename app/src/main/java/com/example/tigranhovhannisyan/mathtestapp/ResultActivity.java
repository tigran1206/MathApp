package com.example.tigranhovhannisyan.mathtestapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.tigranhovhannisyan.mathtestapp.Model.Equation;
import com.example.tigranhovhannisyan.mathtestapp.Model.Triangle;
import com.example.tigranhovhannisyan.mathtestapp.Model.TriangleStrip;

public class ResultActivity extends AppCompatActivity {

    TriangleStrip triangleStrip;
    RecyclerView recyclerView;
    TrianglesInfoAdapter infoAdapter;

    public static void startActivity(Activity activity, TriangleStrip strip){
        Intent intent = new Intent(activity, ResultActivity.class);

        intent.putExtra(TriangleStrip.key, strip);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(getIntent() != null) {
            triangleStrip = (TriangleStrip) getIntent().getSerializableExtra(TriangleStrip.key);
        }

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        infoAdapter = new TrianglesInfoAdapter(triangleStrip.getTriangles());
        recyclerView.setAdapter(infoAdapter);

        processStrip(triangleStrip);
    }

    private void processStrip(TriangleStrip triangleStrip) {

        for(int i = 0; i < triangleStrip.getTriangles().size(); i++){
            Triangle triangle = triangleStrip.getTriangles().get(i);

            if(triangle.getNodesCount() > 3) {
                //return false
            } else if(triangle.getNodesCount() == 3) {
                //check if the nodes are collinear
                Equation equation = new Equation(triangle.getNodes().get(0), triangle.getNodes().get(1));
                if(equation.pointSatisfies(triangle.getNodes().get(2))){
                    Log.d("result", "are collinear");
                } else {
                    triangleStrip.divideByBasicProblem(i, 0);
                    return;
                }
            }
        }
    }

    public boolean testRecursion(TriangleStrip triangleStrip){
        if(triangleStrip.isBasic()) {
            //ToDo check poisedness of basic subproblem
            return true;
        }
        return false;
    }


}