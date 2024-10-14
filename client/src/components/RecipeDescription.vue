<template>
  <section id="recipe-description">
    <div v-for="recipe in recipes" v-bind:key="recipe.recipeId">
    <h2>{{ recipe.name }}</h2>
    <p>{{ recipe.description }}</p> 
    <router-link v-bind:to="{name: 'recipe-details', params: {recipeId : recipe.recipeId}}"><button>Recipe Details</button></router-link>
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
    }
}
</script>

<style>

</style>
