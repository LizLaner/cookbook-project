package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/recipes")
public class RecipesController {

    private final RecipeDao recipeDao;
    private final IngredientDao ingredientDao;

    public RecipesController(RecipeDao recipeDao, IngredientDao ingredientDao) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
    }

    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recipeDao.getRecipes();
    }

    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/{recipeId}/ingredients")
    public List<Ingredient> getAllIngredientsForRecipe(@PathVariable int recipeId){
        Recipe recipe = recipeDao.getRecipeById(recipeId);
        if (recipe == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found.");
        }
        return ingredientDao.getIngredientsByRecipeId(recipeId);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe){
        return recipeDao.createRecipe(recipe);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{recipeId}", method = RequestMethod.PUT)
    public Recipe updateRecipe(@PathVariable int recipeId, @RequestBody Recipe updatedRecipe){
        return recipeDao.updateRecipe(recipeId, updatedRecipe);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{recipeId}", method = RequestMethod.DELETE)
    public void deleteRecipe(@PathVariable int recipeId){
        Recipe recipe = recipeDao.getRecipeById(recipeId);
        if (recipe == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found.");
        }
        recipeDao.deleteRecipeById(recipeId);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{recipeId}/ingredients/{ingredientId}", method = RequestMethod.POST)
    public void addIngredientToRecipe(@PathVariable int recipeId, @PathVariable int ingredientId){
        Recipe recipe = recipeDao.getRecipeById(recipeId);
        Ingredient ingredient = ingredientDao.getIngredientById(ingredientId);

        if (recipe == null || ingredient == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe or Ingredient not found.");
        }

        ingredientDao.linkRecipeIngredient(recipeId, ingredientId);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{recipeId}/ingredients/{ingredientId}", method = RequestMethod.DELETE)
    public void removeIngredientFromRecipe(@PathVariable int recipeId, @PathVariable int ingredientId){
        Recipe recipe = recipeDao.getRecipeById(recipeId);
        Ingredient ingredient = ingredientDao.getIngredientById(ingredientId);

        if (recipe == null || ingredient == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe or Ingredient not found.");
        }

        ingredientDao.unlinkRecipeIngredient(recipeId, ingredientId);
    }



}
