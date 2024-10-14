<template>
  <section id="recipe-description">
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId">
    <h2>{{ recipe.name }}</h2>
    <p>{{ recipe.description }}</p> 
    <router-link v-bind:to="{name: 'recipe-details', params: {recipeId : recipe.recipeId}}"><button>Recipe Details</button></router-link>
    <button v-on:click="delete(recipe.recipe_id)">Remove Recipe</button>
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
        }
    },
    methods: {
        delete(recipeId){
            
        }
    }
}
</script>

<style>

</style>
