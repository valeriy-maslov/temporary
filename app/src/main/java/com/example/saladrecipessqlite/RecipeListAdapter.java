package com.example.saladrecipessqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Recipe> recipeList;

    public RecipeListAdapter(Context context, int layout, ArrayList<Recipe> recipeList) {
        this.context = context;
        this.layout = layout;
        this.recipeList = recipeList;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(layout, null);


        Recipe recipe = recipeList.get(position);
        System.out.println(recipe.getTitle());
        if(recipe.getId() == 3){
            row = inflater.inflate(R.layout.list_item,parent,false);
        } else{
            row = inflater.inflate(R.layout.list_item_favorite,parent,false);
        }
        holder.txtTitle = row.findViewById(R.id.text_list_item);

        //row.setTag(holder);

        holder.txtTitle.setText(recipe.getTitle());
        holder.imageView = row.findViewById(R.id.image_list_item);
        Picasso.with(context).load("file:///android_asset/icon/" + recipeList.get(position).getIconUrl()).into(holder.imageView);
        return row;
    }
}
