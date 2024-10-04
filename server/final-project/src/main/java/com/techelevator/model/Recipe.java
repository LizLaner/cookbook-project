package com.techelevator.model;

import java.util.Objects;

public class Recipe {

    private int recipeId;
    private String name;
    private String description;
    private String directions;
    private int courseId;


    public Recipe(){}

    public Recipe(int recipeId, String name, String description, String directions, int courseId){
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.courseId = courseId;
    }

    //Start test
    public Recipe(String name, String description, String directions, int courseId){
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.courseId = courseId;
    }
    //Finish test

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", directions='" + directions + '\'' +
                ", courseId=" + courseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && courseId == recipe.courseId && Objects.equals(name, recipe.name) && Objects.equals(description, recipe.description) && Objects.equals(directions, recipe.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, name, description, directions, courseId);
    }
}
