package com.techelevator.controller;

import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.model.RecipeIngredient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/recipeIngredients")
public class RecipeIngredientController {

    private final RecipeIngredientDao recipeIngredientDao;

    public RecipeIngredientController(RecipeIngredientDao recipeIngredientDao) {
        this.recipeIngredientDao = recipeIngredientDao;
    }

    @GetMapping
    public List<RecipeIngredient> getAllRecipeIngredients(){
        return recipeIngredientDao.getRecipeIngredients();
    }

}
