package com.example.saladrecipessqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    public static SQLiteHelper sqLiteHelper; //FInAL ???

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        sqLiteHelper = new SQLiteHelper(this, "Recipes.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS recipe ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "title TEXT, " +
                "description TEXT, " +
                "ingredients TEXT, " +
                "iconUrl TEXT, " +
                "htmlRecipe TEXT, " +
                "favorite INTEGER " +
                ")");
        /*sqLiteHelper.insertData("Стрёмный","im0a6604a5dd_340crop.jpg","Стрёмный салат", "Кабачок - 1 шт", "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /><center><div class=\"instructions\" itemprop=\"recipeInstructions\">\n" +
                "<div class=\"instruction\"><h4 id='gotovim-salatik-idilliya'>Шаг 1: Готовим салатик Идиллия.</h4>\n" +
                "<br><img itemprop=\"image\" class=\"photo\" src=\"file:///android_asset/photo/f74d38.jpg\" alt=\"готовим яичный блинчик\" /><br>\n" +
                " Первым делом ставим варить мясо. Так как его несильно много, то свариться оно быстро минут 30 (если только у вас не говядина, так как она вариться немого дольше). Не забываем посолить.\n" +
                " В это время в неглубокой миске взбиваем при помощи венчика яйца, солим и перчим.\n" +
                " <br><img itemprop=\"image\" class=\"photo\" src=\"file:///android_asset/photo/4d824d.jpg\" alt=\"варим и нарезаем мясо\" /><br>\n" +
                " Ставим на средний огонь разогреваться сковороду, предварительно налив растительное масло, после чего выливаем содержимое миски и жарим с двух сторон яичный блинчик, который потом нарежем соломкой.\n" +
                " Готовое холодное мясо, яблоки (заранее очищенные) и огурцы нарезаем несильно толстой соломкой.\n" +
                " Чеснок очистить и перепустить через чесночницу.\n" +
                " Готовить салат будем слоями.\n" +
                " <br><img itemprop=\"image\" class=\"photo\" src=\"file:///android_asset/photo/3bf9b6.jpg\" alt=\"1 слой\" /><br>\n" +
                " Берём сервировочное блюдо и <strong>первым слоем выкладываем</strong> отваренное мясо и смазываем майонезом.\n" +
                " <br><img itemprop=\"image\" class=\"photo\" src=\"file:///android_asset/photo/28520b.jpg\" alt=\"2 слой\" /><br>\n" +
                " <strong>Второй слой</strong> – огурцы, слегка посоленные и смазанные майонезом.\n" +
                " <br><img itemprop=\"image\" class=\"photo\" src=\"file:///android_asset/photo/9aff96.jpg\" alt=\"3 слой\" /><br>\n" +
                " <strong>Третий слой</strong> – яблоки, политые майонезом.\n" +
                " <br><img itemprop=\"image\" class=\"photo\" src=\"file:///android_asset/photo/562267.jpg\" alt=\"4 слой\" /><br>\n" +
                " И, наконец, пятым слоем – яичный блинчик.\n" +
                " Посыпаем сверху чесноком и украшаем листьями зелени.\n" +
                " <hr /></div>\n" +
                "<aside id=\"recipe-useful-last\"></aside>\n" +
                "<hr />\n" +
                "</center>", 0);*/
   //     sqLiteHelper.insertData("Невкусный","im0c0da8eca1_340crop.jpg","Невкусный какойто салат", "Кабачок - 2 шт", "dsdsdsd", 0);
 //       sqLiteHelper.insertData("Салатик тугосеря","im0ca556e97f_340crop.jpg","Ну вы поняли", "Кабачок - 3 шт", "dsdsdsd", 0);
  //      sqLiteHelper.insertData("Оливьешка","im00b30d8bb2_340crop.jpg","Обычная оливьеха","Кабачок - 4 шт","fsdfsdf",1);
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new RecipeListAdapter(this, R.layout.list_item, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                generateDescriptionLayout(position);
            }
        });

        refresh();
        /*Cursor cursor = sqLiteHelper.getData("SELECT * FROM recipe");
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
        adapter.notifyDataSetChanged();*/
    }
    public void generateDescriptionLayout(int position) {
        Intent intent = new Intent(this, Description.class);
        intent.putExtra("recipeTitle", list.get(position).title);
        intent.putExtra("position", position);
        startActivityForResult(intent, 1);
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
        intent.putExtra("favorite", list.get(position).favorite);
        intent.putExtra("description", list.get(position).description);
        intent.putExtra("ingredients", list.get(position).ingredients);
        intent.putExtra("iconUrl", list.get(position).iconUrl);
        intent.putExtra("htmlRecipe", list.get(position).htmlRecipe);
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.notifyDataSetChanged();
        }
    }
    public void refresh(){
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM recipe");
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

}
