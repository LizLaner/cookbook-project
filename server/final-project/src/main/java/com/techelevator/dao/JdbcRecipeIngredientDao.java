package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RecipeIngredient;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeIngredientDao implements RecipeIngredientDao{

    private final String RECIPE_INGREDIENT_SELECT = "SELECT ri.recipe_id, ri.ingredient_id " +
            "FROM recipe_ingredient ri ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeIngredientDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(int recipeId) {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        String sql = RECIPE_INGREDIENT_SELECT +
                "WHERE ri.recipe_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,recipeId);
            while (results.next()){
                recipeIngredients.add(mapRowToRecipeIngredient(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return recipeIngredients;
    }


    private RecipeIngredient mapRowToRecipeIngredient(SqlRowSet results){
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipeId(results.getInt("recipe_id"));
        recipeIngredient.setIngredientId(results.getInt("ingredient_id"));
        return recipeIngredient;
    }


}
