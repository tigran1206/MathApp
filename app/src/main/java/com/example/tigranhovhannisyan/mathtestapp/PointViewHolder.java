package com.example.tigranhovhannisyan.mathtestapp;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tigranhovhannisyan.mathtestapp.Model.Point;

/**
 * Created by tigran.hovhannisyan on 2/16/2017.
 */

public class PointViewHolder extends RecyclerView.ViewHolder {

    EditText xEt;
    EditText yEt;
    TextView vertexNumberTv;

    Point point;
    boolean isNodes;

    public PointViewHolder(View itemView, boolean isNodes) {
        super(itemView);

        this.isNodes = isNodes;

        xEt = (EditText)itemView.findViewById(R.id.xEt);
        yEt = (EditText)itemView.findViewById(R.id.yEt);
        vertexNumberTv = (TextView)itemView.findViewById(R.id.vertex_number);

        xEt.addTextChangedListener(new CustomTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                if(xEt.getText().toString().isEmpty() || xEt.getText().toString().equals("-")) {
                    point.setX(0);
                } else {
                    point.setX(Double.parseDouble(xEt.getText().toString()));
                }
            }
        });

        yEt.addTextChangedListener(new CustomTextChangedListener() {
            @Override
            public void afterTextChanged(Editable editable) {
                if(yEt.getText().toString().isEmpty() || yEt.getText().toString().equals("-")){
                    point.setY(0);
                } else {
                    point.setY(Double.parseDouble(yEt.getText().toString()));
                }
            }
        });
    }

    public void bind(Point point, int position){
        this.point = point;

        String letter = isNodes ? "A" : "V";
        vertexNumberTv.setText(letter + String.valueOf(position + 1));

        if(point.getX() == 0){
            xEt.setText("");
        } else {
            xEt.setText(String.valueOf(point.getX()));
        }

        if(point.getY() == 0){
            yEt.setText("");
        } else {
            yEt.setText(String.valueOf(point.getY()));
        }
    }

}
