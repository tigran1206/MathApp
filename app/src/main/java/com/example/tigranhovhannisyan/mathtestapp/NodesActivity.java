package com.example.tigranhovhannisyan.mathtestapp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tigranhovhannisyan.mathtestapp.Model.Point;
import com.example.tigranhovhannisyan.mathtestapp.Model.TriangleStrip;

import java.util.ArrayList;
import java.util.List;

public class NodesActivity extends AppCompatActivity {

    TextView titleTv;
    RecyclerView recyclerView;

    PointAdapter adapter;
    FloatingActionButton fab;

    private TriangleStrip triangleStrip;
    List<Point> nodes;

    public static void startActivity(Activity activity, TriangleStrip strip){
        Intent intent = new Intent(activity, NodesActivity.class);

        intent.putExtra(TriangleStrip.key, strip);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodes);

        nodes = new ArrayList<>();
        if(getIntent() != null) {
            triangleStrip = (TriangleStrip) getIntent().getSerializableExtra(TriangleStrip.key);
        }

        titleTv = (TextView) findViewById(R.id.title_tv);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(view -> {
            nodes = new ArrayList<Point>() {
                {
                    add(new Point(0,3));
                    add(new Point(2,0));
                    add(new Point(3,6));
                    add(new Point(6,1));
                    add(new Point(7,4));
                    add(new Point(9,6));
                    add(new Point(12,3));
                    add(new Point(15,7));
                    add(new Point(16,2));
                    add(new Point(18,3));
                }
            };

            triangleStrip.setNodes(nodes);
            ResultActivity.startActivity(this, triangleStrip);
        });

        titleTv.setText(getString(R.string.title_text).replace("count", String.valueOf(triangleStrip.getTriangles().size() + 2)));
        for (int i = 0; i < triangleStrip.getTriangles().size() + 2; i++) {
            nodes.add(new Point());
        }

        adapter = new PointAdapter(nodes, true);
        recyclerView.setAdapter(adapter);
    }
}
