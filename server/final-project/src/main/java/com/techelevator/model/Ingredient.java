package com.techelevator.model;

import java.util.Objects;

public class Ingredient {

    private int ingredientId;
    private String name;
    private String preparation;
    private int quantity;
    private String units;

    public Ingredient(){}

    public Ingredient(int ingredientId, String name, String preparation, int quantity, String units){
        this.ingredientId = ingredientId;
        this.name = name;
        this.preparation = preparation;
        this.quantity = quantity;
        this.units = units;
    }

    public Ingredient(String name, String preparation, int quantity, String units){
        this.name = name;
        this.preparation = preparation;
        this.quantity = quantity;
        this.units = units;
    }


    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", name='" + name + '\'' +
                ", preparation='" + preparation + '\'' +
                ", quantity=" + quantity +
                ", units='" + units + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientId == that.ingredientId && quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(preparation, that.preparation) && Objects.equals(units, that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, name, preparation, quantity, units);
    }
}
