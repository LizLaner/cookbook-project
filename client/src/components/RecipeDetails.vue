<template>
  <section id="recipe-details">
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId">
        <h2>{{ recipe.name }}</h2>
        <p>{{ recipe.description }}</p>
        <h2>Directions:</h2>
        <p>{{ recipe.directions }}</p>
        <h2>Ingredients:</h2>
        <ul>
            <li v-for="ingredient in ingredients" :key="ingredient.ingredientId">
                {{ ingredient.name }}
            </li>

        </ul>
        <button v-on:click="deleteRecipe">Delete Recipe</button>
    
    </div>
  </section>
</template>

<script>
import { resourceService } from '../services/resourceService';
export default {
    computed: {
        recipes(){
            return this.$store.state.recipes.filter((recipe) => {
                return recipe.recipeId == this.$store.state.currentRecipeId;
            });
        },
        recipeIngredients(){
            return this.$store.state.recipeIngredients;
        },
        ingredients() {
            return this.$store.state.ingredients;
        }
    },
    methods: {
        deleteRecipe(){
            if (confirm("Are you sure you want to delete this recipe? This action cannot be undone.")) {
                resourceService.deleteRecipe(this.recipe.recipeId).then((response) => {
                    if(response.status === 200){
                        this.$router.push({name: 'home'});
                    }
                })
            }
        }
    }
}
</script>

<style>

</style>