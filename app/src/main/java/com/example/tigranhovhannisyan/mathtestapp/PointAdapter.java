package com.example.tigranhovhannisyan.mathtestapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tigranhovhannisyan.mathtestapp.Model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tigran.hovhannisyan on 2/16/2017.
 */

public class PointAdapter extends RecyclerView.Adapter<PointViewHolder>{

    List<Point> points;
    boolean isNodes;

    public PointAdapter(List<Point> points, boolean isNodes){
        this.points = points;
        this.isNodes = isNodes;
    }

    @Override
    public PointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_point, parent, false);
        return new PointViewHolder(view, isNodes);
    }

    @Override
    public void onBindViewHolder(PointViewHolder holder, int position) {
        holder.bind(points.get(position), position);
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    public List<Point> getPoints(){
        return points;
    }

    public void deleteLastElements(int count){
        for(int i = 0; i < count; i++) {
            points.remove(points.size() - 1);
        }
        notifyDataSetChanged();
    }

    public void addItems(int count) {
        int lastPosition = points.size();
        List<Point> newPoints = new ArrayList<>();
        for (int i = 0; i < count; i++){
            newPoints.add(new Point());
        }

        points.addAll(newPoints);

        notifyItemRangeChanged(lastPosition, newPoints.size());
        notifyItemChanged(lastPosition);
    }

}
