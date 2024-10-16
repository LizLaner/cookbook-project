<template>
  <section id="new_recipe_form">
    <h2>Create New Recipe:</h2>
    <form v-on:submit.stop.prevent="submitRecipe">
        <div>
            <label for="recipeName">Recipe Name:</label>
            <input type="text"
            id="recipeName"
            name="recipeName"
            v-model="newRecipe.name">
        </div>

        <div>
            <label for="recipeDescription">Description:</label>
            <input type="text"
            id="recipeDescription"
            name="recipeDescription"
            v-model="newRecipe.description">
        </div>

        <div>
            <label for="recipeDirections">Directions:</label>
            <input type="text"
            id="recipeDirections"
            name="recipeDirections"
            v-model="newRecipe.directions">
        </div>

        <!-- <div>
            <label for="courseId">Course ID:</label>
            <input type="number"
            id="courseId"
            name="courseId"
            v-model="newRecipe.course_id">
        </div> -->

        

        <div>
            <button type="submit" >Continue to Ingredients</button>
        </div>


    </form>
  </section>
</template>

<script>
import {resourceService} from "../services/resourceService"

export default {
    data() {
        return {
            newRecipe: {
                recipeId: null,
                name: "",
                description: "",
                directions: "",
                course_id: 1
            }
        }
    },
    methods: {
        submitRecipe(){
            resourceService.addRecipe(this.newRecipe).then((response) => {
                const createdRecipe = response.data;
                this.newRecipe.recipeId = createdRecipe.recipeId;
                this.$store.commit("SET_CURRENT_RECIPE_ID", this.newRecipe.recipeId);
                this.$router.push({ name: 'create-new-ingredients', params: {recipeId : this.newRecipe.recipeId}});
            })
        }
    }
}
</script>

<style>

</style>