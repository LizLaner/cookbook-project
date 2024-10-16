<template>
    <div id="new_ingredients">
      <create-ingredient-form id="create_new_ingredient"/>
    </div>
  </template>
  
  <script>
  import CreateIngredientForm from '../components/CreateIngredientForm.vue';

  

  export default {
      components: {CreateIngredientForm}
  }
  </script>
  
  <style>
  #new_ingredients{
      grid-area: main;
      background-color: #f5f7db;
      padding: 30px;
      border-radius: 10px;
      display: grid;
      /* grid-template-columns: 3fr 1fr; */
      /* grid-template-areas: "create_new_ingredient"; */
  }

  /* #create_new_ingredient{
    grid-area: create_new_ingredient;
    background-color: #e5e8b8;
    border-radius: 10px;
    padding-left: 20px;
    padding-bottom: 20px;
    margin-right: 20px;
  } */

  


  
  </style>