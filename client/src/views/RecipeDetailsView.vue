<template>
  <div id="recipe_details">
    <recipe-details id="details"/>
  </div>
</template>

<script>
import RecipeDetails from '../components/RecipeDetails.vue';
export default {
  components: {RecipeDetails},

}
</script>

<style>
#recipe_details{
    grid-area: main;
    /* display: grid;
    grid-template-columns: 1fr 3fr;
    grid-template-areas: "recipes recipe-description"; */
    background-color: #f5f7db;
    padding: 30px;
    border-radius: 10px;
}

</style>