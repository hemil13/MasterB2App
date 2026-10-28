package com.example.masterb2app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    int[] categoryIdArray;
    String[] categoryNameArray;
    int[] categoryImageArray;

    // constructor
    public CategoryAdapter(Context context, int[] categoryIdArray, String[] categoryNameArray, int[] categoryImageArray) {
        this.context = context;
        this.categoryIdArray = categoryIdArray;
        this.categoryNameArray = categoryNameArray;
        this.categoryImageArray = categoryImageArray;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
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
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(categoryImageArray[position]);
        holder.name.setText(categoryNameArray[position]);
    }

    @Override
    public int getItemCount() {
        return categoryIdArray.length;
    }


}

//1. extend category adapter with recyvlerview.adapter
//2. replace categoryActivity with context
//3. declare all arguements in constructor
//4. initialize all declared objects inside constructor
//5. implement methonds from the suggestion
//6. create viewholder class and extends it with recyclerView.ViewHolder
//7. implement super cell from the suggestion
//8. cut viewHolder class and paste it below onCreateViewHolder

//2nd class - define layout (Konsa layout lena hai)
//3rd class - id define
//4th class - setText and setImage
//5th class - define number of count (Kitne cards hai)