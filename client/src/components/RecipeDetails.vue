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
    
    </div>
  </section>
</template>

<script>
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
}
</script>

<style>

</style>