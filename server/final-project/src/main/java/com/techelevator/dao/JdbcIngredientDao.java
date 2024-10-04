package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Ingredient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIngredientDao implements IngredientDao{

    private final String INGREDIENT_SELECT = "SELECT i.ingredient_id, i.name, i.preparation, i.quantity, i.units " +
            "FROM ingredient i ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = INGREDIENT_SELECT +
                "ORDER BY ingredient_id";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ingredients.add(mapRowToIngredient(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return ingredients;
    }

    @Override
    public List<Ingredient> getIngredientsByRecipeId(int recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = INGREDIENT_SELECT +
                "JOIN recipe_ingredient ri ON i.ingredient_id = ri.ingredient_id " +
                "WHERE ri.recipe_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            while (results.next()) {
                ingredients.add(mapRowToIngredient(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return ingredients;
    }

    @Override
    public Ingredient getIngredientById(int ingredientId) {
        Ingredient ingredient = null;
        String sql = INGREDIENT_SELECT +
                "WHERE i.ingredient_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ingredientId);
            if (results.next()) {
                ingredient = mapRowToIngredient(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return ingredient;
    }


//    @Override
//    public Ingredient createIngredient(String name, String preparation, int quantity, String units) {
//        int newIngredientId;
//        String sql = "INSERT INTO ingredient (name, preparation, quantity, units) " +
//                "VALUES (?, ?, ?, ?) RETURNING ingredient_id";
//        try {
//            newIngredientId = jdbcTemplate.queryForObject(sql, int.class, name, preparation, quantity, units);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//
//        return getIngredientById(newIngredientId);
//    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient){
        int newIngredientId;

        String sql = "INSERT INTO ingredient (name, preparation, quantity, units) " +
                "VALUES (?, ?, ?, ?) RETURNING ingredient_id";

        try {
            newIngredientId = jdbcTemplate.queryForObject(sql, int.class, ingredient.getName(), ingredient.getPreparation(),
                    ingredient.getQuantity(), ingredient.getUnits());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return getIngredientById(newIngredientId);
    }

    @Override
    public Ingredient updateIngredient(int ingredientId, Ingredient updatedIngredient){
        Ingredient ingredient = null;
        String sql = "UPDATE ingredient SET name = ?, preparation = ?, quantity = ?, units = ? " +
                "WHERE ingredient_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(sql, updatedIngredient.getName(), updatedIngredient.getPreparation(), updatedIngredient.getQuantity(),
                    updatedIngredient.getUnits(), ingredientId);
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                ingredient = getIngredientById(updatedIngredient.getIngredientId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return ingredient;
    }

    @Override
    public void linkRecipeIngredient(int recipeId, int ingredientId){
        String sql = "INSERT INTO recipe_ingredient (recipe_id, ingredient_id) " +
                "VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, recipeId, ingredientId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void unlinkRecipeIngredient(int recipeId, int ingredientId){
        String sql = "DELETE FROM recipe_ingredient WHERE recipe_id = ? AND ingredient_id = ?";
        try {
            jdbcTemplate.update(sql, recipeId, ingredientId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public int deleteIngredientById(int ingredientId) {
        int numberOfRows = 0;

        String deleteRecipeIngredientSql = "DELETE FROM recipe_ingredient WHERE ingredient_id = ?";
        String deleteIngredientSql = "DELETE FROM ingredient WHERE ingredient_id = ?";
        try {
            jdbcTemplate.update(deleteRecipeIngredientSql, ingredientId);
            numberOfRows = jdbcTemplate.update(deleteIngredientSql, ingredientId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;
    }

    private Ingredient mapRowToIngredient(SqlRowSet results){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(results.getInt("ingredient_id"));
        ingredient.setName(results.getString("name"));
        ingredient.setPreparation(results.getString("preparation"));
        ingredient.setQuantity(results.getInt("quantity"));
        ingredient.setUnits(results.getString("units"));
        return ingredient;
    }

}
