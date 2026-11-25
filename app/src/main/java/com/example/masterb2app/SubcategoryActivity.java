package com.example.masterb2app;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubcategoryActivity extends AppCompatActivity {
    RecyclerView recycler;

    int[] subcategoryIdArray = {1,2,3,4,5,6,7,8,9};
    int[] categoryIdArray = {1,1,1,2,2,2,3,3,3};
    String[] subcategoryNameArray = {"Earbuds", "Headphones", "Mobile Phone",
            "Shirts", "Jeans", "T-shirts",
            "Fiction", "Horror", "Novel"};
    int[] subcategoryImageArray = {R.drawable.earbuds, R.drawable.headphone, R.drawable.mobile,
            R.drawable.shirt, R.drawable.jeans, R.drawable.thsirt,
            R.drawable.fiction, R.drawable.horror, R.drawable.novel};

    ArrayList<SubCategoryList> arraylist;

    SQLiteDatabase db;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_subcategory);

        recycler = findViewById(R.id.subcategory_recycler);

        sp = getSharedPreferences(ConstantSp.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase("masterapp.db", MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), email VARCHAR(30), contact VARCHAR(10), password VARCHAR(20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category (categoryId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), image VARCHAR(100))";
        db.execSQL(categoryTable);

        String subcategoryTable = "CREATE TABLE IF NOT EXISTS subcategory (subcategoryId INTEGER PRIMARY KEY AUTOINCREMENT, categoryId INTEGER, name VARCHAR(50), image VARCHAR(100))";
        db.execSQL(subcategoryTable);



        for(int i=0; i<subcategoryIdArray.length; i++){
            String checkCategory = "SELECT * FROM category WHERE name = '"+subcategoryNameArray[i]+"' AND categoryId = '"+Integer.parseInt(sp.getString(ConstantSp.CATEGORY_ID, ""))+"'";
            Cursor cursor = db.rawQuery(checkCategory, null);
            if(cursor.getCount() == 0){
                String insertCategory = "INSERT INTO subcategory VALUES (NULL, '"+categoryIdArray[i]+"', '"+subcategoryNameArray[i]+"', '"+subcategoryImageArray[i]+"')";
                db.execSQL(insertCategory);
            }
        }

        arraylist = new ArrayList<>();

        String fetchSubCategory = "SELECT * FROM subcategory WHERE categoryId = '"+sp.getInt(ConstantSp.CATEGORY_ID, 0)+"'";
        Cursor cursor = db.rawQuery(fetchSubCategory, null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                SubCategoryList list = new SubCategoryList();
                list.setId(cursor.getInt(0));
                list.setCategoryId(cursor.getInt(1));
                list.setName(cursor.getString(2));
                list.setImage(cursor.getInt(3));
                arraylist.add(list);
            }
        }

        Log.d("SubcategoryActivity", "onCreate: "+arraylist.size());

        recycler.setLayoutManager(new LinearLayoutManager(SubcategoryActivity.this));

        SubCategoryAdapter adapter = new SubCategoryAdapter(SubcategoryActivity.this, arraylist);
        recycler.setAdapter(adapter);

    }
}