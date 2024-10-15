<template>
  <div id="main-div">
    <recipe-list id="recipes"/>
    <recipe-description id="recipe-description"/>

  </div>
</template>

<script>

import RecipeList from '../components/RecipeList.vue';
import RecipeDescription from '../components/RecipeDescription.vue';
import {resourceService} from '../services/resourceService';

export default {
  components: {RecipeList, RecipeDescription},
  data() {
    return {
      isLoading: false
    };
  },
  created(){
    this.isLoading = true;

    Promise.all([
      resourceService.getRecipes(),
      resourceService.getIngredients(),
      resourceService.getRecipeIngredients()
    ]).then(([recipeResponse, ingredientResponse, recipeIngredientResponse]) => {
      this.$store.commit("SET_RECIPES", recipeResponse.data);
      this.$store.commit("SET_INGREDIENTS", ingredientResponse.data);
      this.$store.commit('SET_RECIPE_INGREDIENTS', recipeIngredientResponse.data);
    }).catch((error) => {
      console.log(error)
    }).finally(() => {
      this.isLoading = false;
    })
  }

}
</script>

<style>
#main-div{
    grid-area: main;
    display: grid;
    grid-template-columns: 1fr 3fr;
    grid-template-areas: "recipes recipe-description";
    background-color: #f5f7db;
    padding: 30px;
    border-radius: 10px;
}

#recipes{
  grid-area: recipes;
  background-color: #e5e8b8;
  border-radius: 10px;
  padding-left: 20px;
  padding-bottom: 20px;
  margin-right: 20px;
}

#recipe-description{
  grid-area: recipe-description;
}
@media only screen and (max-width: 450px){

  #main-div{
      grid-template-columns: 1fr;
      grid-template-areas: 
          "recipes"
          "ingredients";
  }

}


</style>