package com.example.tigranhovhannisyan.mathtestapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tigranhovhannisyan.mathtestapp.Model.Triangle;

import java.util.List;

/**
 * Created by tigran.hovhannisyan on 2/20/2017.
 */

public class TrianglesInfoAdapter extends RecyclerView.Adapter<TriangleInfoViewHolder> {

    List<Triangle> triangles;

    public TrianglesInfoAdapter(List<Triangle> triangles){
        this.triangles = triangles;
    }

    @Override
    public TriangleInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_triangle_info, parent, false);
        return new TriangleInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriangleInfoViewHolder holder, int position) {
        holder.bind(triangles.get(position));
    }

    @Override
    public int getItemCount() {
        return triangles.size();
    }
}
