package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Recipe;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao{

    private final String RECIPE_SELECT = "SELECT r.recipe_id, r.description, r.directions, r.name, r.course_id FROM recipe r ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;
        String sql = RECIPE_SELECT +
                "WHERE r.recipe_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            if (results.next()) {
                recipe = mapRowToRecipe(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return recipe;
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = RECIPE_SELECT +
                "ORDER BY recipe_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                recipes.add(mapRowToRecipe(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }


        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByCourseId(int courseId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = RECIPE_SELECT +
                "WHERE r.course_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
            while (results.next()) {
                recipes.add(mapRowToRecipe(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return recipes;
    }


//    @Override
//    public Recipe createRecipe(String name, String description, String directions, int courseId){
//        int newRecipeId;
//
//        String sql = "INSERT INTO recipe (name, description, directions, course_id) " +
//                "VALUES (?, ?, ?, ?) RETURNING recipe_id";
//        try {
//            newRecipeId = jdbcTemplate.queryForObject(sql, int.class, name, description, directions, courseId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//        return getRecipeById(newRecipeId);
//    }

    @Override
    public Recipe createRecipe(Recipe recipe){
        int newRecipeId;

        String sql = "INSERT INTO recipe (name, description, directions, course_id) " +
                "VALUES (?, ?, ?, ?) RETURNING recipe_id";

        try {
            newRecipeId = jdbcTemplate.queryForObject(sql, int.class, recipe.getName(), recipe.getDescription(),
                    recipe.getDirections(), recipe.getCourseId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return getRecipeById(newRecipeId);
    }

    @Override
    public Recipe updateRecipe(int recipeId, Recipe updatedRecipe) {
        Recipe recipe = null;
        String sql = "UPDATE recipe SET name = ?, description = ?, directions = ?, course_id = ? " +
                "WHERE recipe_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(sql, updatedRecipe.getName(), updatedRecipe.getDescription(),
                    updatedRecipe.getDirections(), updatedRecipe.getCourseId(), recipeId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                recipe = getRecipeById(updatedRecipe.getRecipeId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return recipe;
    }

    @Override
    public int deleteRecipeById(int recipeId) {
        int numberOfRows = 0;

        String deleteRecipeIngredientSql = "DELETE FROM recipe_ingredient WHERE recipe_id = ?";
        String deleteRecipeSql = "DELETE FROM recipe WHERE recipe_id = ?";
        try {
            jdbcTemplate.update(deleteRecipeIngredientSql, recipeId);
            numberOfRows = jdbcTemplate.update(deleteRecipeSql, recipeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or datbase", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }


    private Recipe mapRowToRecipe(SqlRowSet results){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(results.getInt("recipe_id"));
        recipe.setName(results.getString("name"));
        recipe.setDescription(results.getString("description"));
        recipe.setDirections(results.getString("directions"));
        recipe.setCourseId(results.getInt("course_id"));
        return recipe;
    }

}
