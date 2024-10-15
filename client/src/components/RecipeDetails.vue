<template>
  <section id="recipe-details">
    <!-- <div v-for="recipe in recipes" v-bind:key="recipe.recipeId"> -->
        <div :key="recipe.recipeId">
        
        <h2>{{ recipe.name }}</h2>
        <p>{{ recipe.description }}</p>
        <h2>Directions:</h2>
        <p>{{ recipe.directions }}</p>
        <h2>Ingredients:</h2>
        <ul>
            <li v-for="ingredient in currentIngredients" :key="ingredient.ingredientId">
                {{ ingredient.name }}
            </li>

        </ul>
        <button v-on:click="deleteRecipe(recipe)">Delete Recipe</button>
    
    </div>
  </section>
</template>

<script>
import { resourceService } from '../services/resourceService';
export default {
    computed: {
        // recipes(){
        //     return this.$store.state.recipes.filter((recipe) => {
        //         return recipe.recipeId == this.$store.state.currentRecipeId;
        //     });
        // },
        recipe(){
            return this.$store.state.recipes.find((recipe) => {
                return recipe.recipeId == this.$store.state.currentRecipeId;
            });
        },
        currentIngredients(){
            return this.$store.state.currentIngredients;
        }

    },
    methods: {
        deleteRecipe(recipe){
            if (confirm("Are you sure you want to delete this recipe? This action cannot be undone.")) {
                resourceService.deleteRecipe(recipe.recipeId).then((response) => {
                    if (response.status === 200 || response.status === 204){
                        this.$router.push({name: 'home'});
                    }
                    
                });
            }
        },

    },
    created(){
        const currentRecipeId = this.$store.state.currentRecipeId;
        this.$store.commit("SET_CURRENT_RECIPE_INGREDIENTS", currentRecipeId);
        this.$store.commit("SET_CURRENT_INGREDIENTS");
        console.log(this.currentRecipeIngredients);
    }
}
</script>

<style>

</style>