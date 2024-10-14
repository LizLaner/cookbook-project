<template>
  <div id="create_new_recipe">
    <create-recipe-form id="create_form"/>
  </div>
</template>

<script>
import CreateRecipeForm from '../components/CreateRecipeForm.vue';
export default {
    components: {CreateRecipeForm},
}
</script>

<style>
#create_new_recipe{
    grid-area: main;
    background-color: #f5f7db;
    padding: 30px;
    border-radius: 10px;
}

</style>