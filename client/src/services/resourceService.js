import axios from "axios";


const resourceService = {
  getRecipes() {
    return axios.get('/recipes');
  },
  getIngredients() {
    return axios.get('/ingredients');
  },
  getRecipeIngredients() {
    return axios.get('/recipeIngredients');
  },
  deleteRecipe(recipeId) {
    return axios.delete(`/recipes/${recipeId}`);
  }
  
};


export { resourceService };

