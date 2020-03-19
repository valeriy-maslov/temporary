package com.example.saladrecipessqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    ListView listView;
    ArrayList<Recipe> list;
    RecipeListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadFavorites();
    }

    private void loadFavorites() {
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new RecipeListAdapter(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM recipe WHERE favorite != 0");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String iconUrl = cursor.getString(2);
            String description = cursor.getString(3);
            String ingredients = cursor.getString(4);
            String htmlRecipe = cursor.getString(5);
            int favorite = cursor.getInt(6);

            list.add(new Recipe(id, title, iconUrl, description, ingredients, htmlRecipe, favorite));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onStart() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genLayout(position);
            }
        });
        super.onStart();
    }
    public void genLayout(int position) {
        Intent intent = new Intent(this, Description.class);
        intent.putExtra("recipeTitle", list.get(position).title);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

