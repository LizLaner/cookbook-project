package com.techelevator.dao;

import com.techelevator.model.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientDao {

    List<RecipeIngredient> getRecipeIngredientsByRecipeId(int recipeId);
//
//    RecipeIngredient updateRecipeIngredient(RecipeIngredient updatedRecipeIngredient);
//
//    int deleteRecipeIngredientByIngredientId(int ingredientId);

}
