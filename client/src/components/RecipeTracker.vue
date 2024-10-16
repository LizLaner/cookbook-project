<template>
  <section id="current_recipe_tracker">
    
    <h2 v-if="recipe">{{ recipe.name }}</h2>
    <ul>
        <li v-for="ingredient in currentIngredients" :key="ingredient.ingredientId">
            {{ ingredient.name }}
        </li>
    </ul>
    
  </section>
</template>

<script>
import { resourceService } from '../services/resourceService';
export default {
    data(){
        return {
            currentIngredients: []
        }
    },
    computed: {
        recipe(){
            return this.$store.state.recipes.find((recipe) => {
                return recipe.recipeId == this.$store.state.currentRecipeId;
            });
        }
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