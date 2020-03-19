package com.example.saladrecipessqlite;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class Description extends AppCompatActivity {
    private Toolbar mToolbar;
    String title;
    String description;
    String ingredients;
    String iconUrl;
    String htmlRecipe;
    int id;
    int favorite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        //custom toolbar initialization
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeFields();
        getSupportActionBar().setTitle(title);
        ImageView topImage = findViewById(R.id.imageViewTop);
        Picasso.with(this).load("file:///android_asset/icon/"+iconUrl).into(topImage);

        TextView textDescription = findViewById(R.id.textDescription);
        textDescription.setText(description);
        TextView textIngredients = findViewById(R.id.textIngredients);
        textIngredients.setText(ingredients);

        WebView webView = findViewById(R.id.webView1);
        webView.loadData(htmlRecipe, "text/html", "UTF-8");
        webView.setBackgroundColor(Color.TRANSPARENT);

    }

    private void initializeFields() {
        title = getIntent().getExtras().getString("recipeTitle");
        favorite = getIntent().getExtras().getInt("favorite");
        description = getIntent().getExtras().getString("description");
        ingredients = getIntent().getExtras().getString("ingredients");
        iconUrl = getIntent().getExtras().getString("iconUrl");
        htmlRecipe = getIntent().getExtras().getString("htmlRecipe");
        id = getIntent().getExtras().getInt("position");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                this.finish();
                return true;
            case R.id.favorites:
                if (favorite == 0) {
                    item.setIcon(R.drawable.ic_favorite_24_px);
                    addToFavorite();
                } else {
                    item.setIcon(R.drawable.favorite);
                    deleteFromFavorite();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_description, menu);
        MenuItem menuItem = menu.findItem(R.id.favorites);
        if (favorite != 0) {
            menuItem.setIcon(R.drawable.ic_favorite_24_px);
        } else {
            menuItem.setIcon(R.drawable.favorite);
        }
        System.out.println(favorite);
        return super.onCreateOptionsMenu(menu);
    }

    private void addToFavorite() {
        MainActivity.sqLiteHelper.updateData(title,iconUrl,description,ingredients,htmlRecipe,1, id);


    }

    private void deleteFromFavorite() {
        MainActivity.sqLiteHelper.updateData(title,iconUrl,description,ingredients,htmlRecipe,0, id);

    }

}
