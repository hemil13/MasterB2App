package com.example.masterb2app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recycler;

    int[] categoryIdArray = {1,2,3};
    String[] categoryNameArray = {"Electronics", "Clothes", "Books"};
    int[] categoryImageArray = {R.drawable.electronics, R.drawable.clothes, R.drawable.books};

    ArrayList<CategoryList> arraylist;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recycler = findViewById(R.id.category_recycler);

        db = openOrCreateDatabase("masterapp.db", MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category (categoryId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), image VARCHAR(100))";
        db.execSQL(categoryTable);

        for(int i=0; i<categoryIdArray.length; i++){
            String checkCategory = "SELECT * FROM category WHERE name = '"+categoryNameArray[i]+"'";
            Cursor cursor = db.rawQuery(checkCategory, null);
            if(cursor.getCount() == 0){
                String insertCategory = "INSERT INTO category VALUES(NULL, '"+categoryNameArray[i]+"', '"+categoryImageArray[i]+"')";
                db.execSQL(insertCategory);
            }
        }


        arraylist = new ArrayList<>();

        String fetchCategory = "SELECT * FROM category";
        Cursor cursor = db.rawQuery(fetchCategory, null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                CategoryList list = new CategoryList();
                list.setId(cursor.getInt(0));
                list.setName(cursor.getString(1));
                list.setImage(cursor.getInt(2));
                arraylist.add(list);
            }
        }

        recycler.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, arraylist);
        recycler.setAdapter(adapter);

    }
}

//1. view/layout set krna
//2. adapter ne data pass krna




//{[1, "Electronics", img1],[2, "Clothes", img2],[3, "Books", img3]}

//data -> cursor
//[1, "Electronics", img1]
// {[1, "Electronics", img1]}