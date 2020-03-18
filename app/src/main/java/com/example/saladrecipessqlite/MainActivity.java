package com.example.saladrecipessqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    //new
    ListView listView;
    ArrayList<Recipe> list;
    RecipeListAdapter adapter = null;
public static SQLiteHelper sqLiteHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        sqLiteHelper = new SQLiteHelper(this,"Recipes.sqlite",null,1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS recipe ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "title TEXT, " +
                "description TEXT, " +
                "ingredients TEXT, " +
                "iconUrl TEXT, " +
                "htmlRecipe TEXT, " +
                "favorite INTEGER " +
                ")");
       /* sqLiteHelper.insertData("Говно1","im0a6604a5dd_340crop.jpg","fsdfsdf", "sdsdss", "dsdsdsd", 0);
        sqLiteHelper.insertData("Говно2","im0a6604a5dd_340crop.jpg","fsdfsdf", "sdsdss", "dsdsdsd", 1);
        sqLiteHelper.insertData("Говно3","im0a6604a5dd_340crop.jpg","fsdfsdf", "sdsdss", "dsdsdsd", 0);
        sqLiteHelper.insertData("не говно","im0a6604a5dd_340crop","","","",2);
*/
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new RecipeListAdapter(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM recipe");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String iconUrl = cursor.getString(2);



            list.add(new Recipe(id, title, iconUrl,"","","",0));
        }
        adapter.notifyDataSetChanged();
    }
}
