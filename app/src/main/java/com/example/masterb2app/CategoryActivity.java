package com.example.masterb2app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recycler;

    int[] categoryIdArray = {1,2,3};
    String[] categoryNameArray = {"Electronics", "Clothes", "Books"};
    int[] categoryImageArray = {R.drawable.electronics, R.drawable.clothes, R.drawable.books};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recycler = findViewById(R.id.category_recycler);

        recycler.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, categoryIdArray, categoryNameArray, categoryImageArray);
        recycler.setAdapter(adapter);

    }
}

//1. view/layout set krna
//2. adapter ne data pass krna