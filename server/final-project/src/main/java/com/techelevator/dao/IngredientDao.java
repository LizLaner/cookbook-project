package com.techelevator.dao;

import com.techelevator.model.Ingredient;

import java.util.List;

public interface IngredientDao {

    List<Ingredient> getIngredients();

    List<Ingredient> getIngredientsByRecipeId(int recipeId);

    Ingredient getIngredientById(int ingredientId);

    //Ingredient createIngredient(String name, String preparation, int quantity, String units);
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient updateIngredient(int ingredientId, Ingredient updatedIngredient);
    void linkRecipeIngredient(int recipeId, int ingredientId);
    void unlinkRecipeIngredient(int recipeId, int ingredientId);
    int deleteIngredientById(int ingredientId);

}
