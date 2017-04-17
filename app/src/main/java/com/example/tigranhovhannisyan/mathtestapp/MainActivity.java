package com.example.tigranhovhannisyan.mathtestapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.tigranhovhannisyan.mathtestapp.Model.Equation;
import com.example.tigranhovhannisyan.mathtestapp.Model.EquationSum;
import com.example.tigranhovhannisyan.mathtestapp.Model.IndexPair;
import com.example.tigranhovhannisyan.mathtestapp.Model.Point;
import com.example.tigranhovhannisyan.mathtestapp.Model.Triangle;
import com.example.tigranhovhannisyan.mathtestapp.Model.TriangleStrip;
import com.example.tigranhovhannisyan.mathtestapp.Model.TwoVarEquation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button1;
    RecyclerView recyclerView;
    PointAdapter pointAdapter;

    List<Point> points;
    FloatingActionButton fab;
    EditText countEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = new ArrayList<Point>() {
            {
                add(new Point(2,0));
                add(new Point(4,4));
                add(new Point(5,0));
                add(new Point(7,4));
                add(new Point(9,0));
                add(new Point(11,4));
            }
        };

//        points = new ArrayList<>();
//        for (int i = 0; i < 3; i++){
//            points.add(new Point());
//        }
        pointAdapter = new PointAdapter(points, false);

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        countEt = (EditText)findViewById(R.id.count_et);

        countEt.addTextChangedListener(new CustomTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                if(countEt.getText().toString().isEmpty()){
                    return;
                }
                int count = Integer.parseInt(countEt.getText().toString());
                if(count == 0){
                    points.clear();
                    return;
                }
                int vertexesCount = count + 2;

                int diff = points.size() - vertexesCount;
                if(diff > 0){
                    pointAdapter.deleteLastElements(diff);
                } else if (diff < 0){
                    pointAdapter.addItems(-1 * diff);
                }
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            TriangleStrip triangleStrip = new TriangleStrip(createRectangles(pointAdapter.getPoints()));
            NodesActivity.startActivity(getApplicationContext(), triangleStrip);
        });

        recyclerView.setAdapter(pointAdapter);
    }

    public List<Triangle> createRectangles(List<Point> points){
        ArrayList<Point> vertexes = new ArrayList<>();
        ArrayList<Triangle> triangles = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            vertexes.add(point);

            if(vertexes.size() == 3){
                Triangle triangle = new Triangle(new ArrayList<>(vertexes));
                triangles.add(triangle);
                vertexes.clear();
                i -= 2;
            }
        }

        return triangles;
    }

}
