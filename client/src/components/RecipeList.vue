<template>
  <section id="recipes">

    <h2>Recipes</h2>
    <ul id="recipe-list">

        <li v-for="recipe in recipes" :key="recipe.recipeId" v-on:click="selected(recipe.recipeId)">
            {{ recipe.name }}
        </li>

    </ul>

  </section>
</template>

<script>
export default {

    computed: {
        recipes(){
            return this.$store.state.recipes;
        }
    },
    methods: {
        selected(recipeId){
            this.$store.commit('SET_CURRENT_RECIPE_ID', recipeId);
        }
    },
    created(){
        console.log(this.$store.state.recipeIngredients);
    }

}
</script>

<style>

</style>