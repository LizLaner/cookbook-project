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
  getIngredientsForRecipe(recipeId) {
    return axios.get(`/recipes/${recipeId}/ingredients`);
  },
  addRecipe(recipe) {
    return axios.post('/recipes', recipe);
  },
  addIngredient(ingredient) {
    return axios.post('ingredients', ingredient);
  },
  addIngredientToRecipe(recipeId, ingredientId) {
    return axios.post(`/recipes/${recipeId}/ingredients/${ingredientId}`)
  },
  deleteRecipe(recipeId) {
    return axios.delete(`/recipes/${recipeId}`);
  }
  
};


export { resourceService };

