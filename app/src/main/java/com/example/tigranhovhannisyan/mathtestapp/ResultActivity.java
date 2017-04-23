package com.example.tigranhovhannisyan.mathtestapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.tigranhovhannisyan.mathtestapp.Model.Equation;
import com.example.tigranhovhannisyan.mathtestapp.Model.IndexPair;
import com.example.tigranhovhannisyan.mathtestapp.Model.Triangle;
import com.example.tigranhovhannisyan.mathtestapp.Model.TriangleStrip;

public class ResultActivity extends AppCompatActivity {

    TriangleStrip triangleStrip;
    RecyclerView recyclerView;
    TrianglesInfoAdapter infoAdapter;

    FloatingActionButton fab;

    public static void startActivity(Context context, TriangleStrip strip){
        Intent intent = new Intent(context, ResultActivity.class);

        intent.putExtra(TriangleStrip.key, strip);

        context.startActivity(intent);
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

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
//            processStrip(triangleStrip);
            try {
                Counter.getInstance().setCount(true);
                boolean poised = triangleStrip.isPoised();
                String message = "Poised: " + String.valueOf(poised) + "\n" +
                        "Sums count: " + String.valueOf(Counter.getInstance().getSumCount()) + "\n" +
                        "Multiplications count: " + String.valueOf(Counter.getInstance().getMultipleCount()) + "\n" +
                        "Divisions count: " + String.valueOf(Counter.getInstance().getDivideCount()) + "\n";
                Counter.getInstance().setCount(false);
                Counter.getInstance().release();
                if(poised) {

                    DialogUtils.showAlertDialog(this, message, R.drawable.success_icon, data -> {

                        onBackPressed();
                    });
                } else {
                    DialogUtils.showAlertDialog(this, message, R.drawable.warning_icon, data -> {
                        onBackPressed();
                    });
                }
            } catch (Exception e){
                Counter.getInstance().setCount(false);
                Counter.getInstance().release();
                DialogUtils.showAlertDialog(this, "Sorry, something went wrong.", R.drawable.warning_icon, data -> {
                    onBackPressed();
                });
            }
        });

        //processStrip(triangleStrip);
    }

//    private void processStrip(TriangleStrip triangleStrip) {
//
//        Log.d("result", String.valueOf(triangleStrip.isPoised()));
//        Log.d("divide count", String.valueOf(Counter.getInstance().getDivideCount()));
//        Log.d("sum count", String.valueOf(Counter.getInstance().getSumCount()));
//        Log.d("multiple count", String.valueOf(Counter.getInstance().getMultipleCount()));
//    }

}