<template>
  <section id="recipe-details">
    
        <div :key="recipe.recipeId">
        
        <h2>{{ recipe.name }}</h2>
        <p>{{ recipe.description }}</p>
        <h2>Directions:</h2>
        <p>{{ recipe.directions }}</p>
        <h2>Ingredients:</h2>
        <ul>
            <li v-for="ingredient in currentIngredients" :key="ingredient.ingredientId">
                {{ ingredient.name }}, 
                {{ ingredient.quantity }} 
                {{ ingredient.units }}, 
                {{ ingredient.preparation }}
            </li>

        </ul>
        <button v-on:click="deleteRecipe(recipe)">Delete Recipe</button>
    
    </div>
  </section>
</template>

<script>
import { resourceService } from '../services/resourceService';
export default {
    data(){
        return {
            currentIngredients: [],
        }
    },
    computed: {
        
        recipe(){
            return this.$store.state.recipes.find((recipe) => {
                return recipe.recipeId == this.$store.state.currentRecipeId;
            });
        },
        

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
        resourceService.getIngredientsForRecipe(this.$store.state.currentRecipeId).then((response) => {
            this.currentIngredients = response.data;
        })
    }
}
</script>

<style>

</style>