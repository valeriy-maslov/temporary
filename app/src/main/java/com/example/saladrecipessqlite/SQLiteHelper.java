package com.example.saladrecipessqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String title, String iconUrl, String description, String ingredients, String htmlRecipe, int favorite) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO recipe VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, title);
        statement.bindString(2, iconUrl);
        statement.bindString(3, description);
        statement.bindString(4, ingredients);
        statement.bindString(5, htmlRecipe);
        statement.bindLong(6, favorite);

        statement.executeInsert();
    }
    public void updateData(String title, String iconUrl, String description, String ingredients, String htmlRecipe, int favorite, int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE recipe SET title=?, iconUrl=?, description=?, ingredients=?,  htmlRecipe=?, favorite=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1,title);
        statement.bindString(2,description);
        statement.bindString(3,ingredients);
        statement.bindString(4,iconUrl);
        statement.bindString(5,htmlRecipe);
        statement.bindLong(6,favorite);
        statement.bindDouble(7,(double)id);

        statement.executeInsert();
        database.close();
    }


    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
