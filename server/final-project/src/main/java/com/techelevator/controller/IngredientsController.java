package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.model.Ingredient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/ingredients")
public class IngredientsController {

    private final IngredientDao ingredientDao;

    public IngredientsController(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Ingredient> getAllIngredients(){
        return ingredientDao.getIngredients();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient){
        return ingredientDao.createIngredient(ingredient);
    }

   @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{ingredientId}", method = RequestMethod.PUT)
    public Ingredient updateIngredient(@PathVariable int ingredientId, @RequestBody Ingredient ingredient){
        return ingredientDao.updateIngredient(ingredientId, ingredient);
    }

   @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{ingredientId}", method = RequestMethod.DELETE)
    public void deleteIngredient(@PathVariable int ingredientId){
        ingredientDao.deleteIngredientById(ingredientId);
    }

}
