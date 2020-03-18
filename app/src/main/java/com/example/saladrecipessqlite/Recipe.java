package com.example.saladrecipessqlite;

public class Recipe {
    int id;
    String title;
    String description;
    String ingredients;
    String iconUrl;
    String htmlRecipe;
    int favorite;

    public Recipe(int id, String title, String iconUrl, String description, String ingredients, String htmlRecipe, int favorite) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.iconUrl = iconUrl;
        this.htmlRecipe = htmlRecipe;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getHtmlRecipe() {
        return htmlRecipe;
    }

    public void setHtmlRecipe(String htmlRecipe) {
        this.htmlRecipe = htmlRecipe;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}