import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      recipes: [],
      ingredients: [],
      recipeIngredients: [],
      currentRecipeId: 1,

      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_RECIPES(state, recipes){
        state.recipes = recipes;
      },
      SET_CURRENT_RECIPE_ID(state, recipeId){
        state.currentRecipeId = recipeId;
      },
      SET_INGREDIENTS(state, ingredients){
        state.ingredients = ingredients;
      },
      SET_RECIPE_INGREDIENTS(state, recipeIngredients){
        state.recipeIngredients = recipeIngredients;
      },

      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },

  })
  return store;
}