package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {

    List<Recipe> getRecipes();

    List<Recipe> getRecipesByCourseId(int courseId);

    Recipe getRecipeById(int recipeId);

    Recipe createRecipe(Recipe recipe);

    Recipe updateRecipe(int recipeId, Recipe updatedRecipe);

    int deleteRecipeById(int recipeId);

}
