package com.example.masterb2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    ArrayList<SubCategoryList> arraylist;
    Context context;
    public SubCategoryAdapter(Context context, ArrayList<SubCategoryList> arraylist) {
        this.context = context;
        this.arraylist = arraylist;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new SubCategoryAdapter.ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_category_image);
            name = itemView.findViewById(R.id.item_category_name);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder holder, int position) {
        SubCategoryList list = arraylist.get(position);
        holder.image.setImageResource(list.getImage());
        holder.name.setText(list.getName());
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

}
