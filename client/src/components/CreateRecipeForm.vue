<template>
  <section id="new_recipe_form">
    <h2>Create New Recipe:</h2>
    <form v-on:submit.stop.prevent="submitRecipe">
        <div>
            <label for="recipeName">Recipe Name:</label>
            <input type="text"
            id="recipeName"
            name="recipeName"
            v-model="recipe.name">
        </div>

        <div>
            <label for="recipeDescription">Description:</label>
            <input type="text"
            id="recipeDescription"
            name="recipeDescription"
            v-model="recipe.description">
        </div>

        <div>
            <label for="recipeDirections">Directions:</label>
            <input type="text"
            id="recipeDirections"
            name="recipeDirections"
            v-model="recipe.directions">
        </div>

        <div>
            <button type="submit">Continue to Ingredients</button>
        </div>


    </form>
  </section>
</template>

<script>
import {resourceService} from "../services/resourceService"

export default {
    data() {
        return {
            recipe: {
                name: "",
                description: "",
                directions: "",
                course_id: 1
            }
        }
    },
    methods: {
        submitRecipe(){
            resourceService.addRecipe(this.recipe).then((response) => {
                this.$router.push({ name: 'home'});
            })
        }
    }
}
</script>

<style>

</style>