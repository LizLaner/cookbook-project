<template>
  <section id="new_ingredient_form">
    <h2>Add Ingredient:</h2>
    <form v-on:submit.stop.prevent="">
        
    
        <div>
            <label for="ingredientName">Ingredient Name:</label>
            <input type="text"
            id="ingredientName"
            name="ingredientName"
            v-model="newIngredient.name">
        </div>

        <div>
            <label for="ingredientPrep">Ingredient Preparation:</label>
            <input type="text"
            id="ingredientPrep"
            name="ingredientPrep"
            v-model="newIngredient.preparation">
        </div>

        <div>
            <label for="ingredientQuantity">Ingredient Quantity:</label>
            <input type="number"
            id="ingredientQuantity"
            name="ingredientQuantity"
            v-model="newIngredient.quantity">
        </div>

        <div>
            <label for="ingredientUnits">Ingredient Units:</label>
            <input type="text"
            id="ingredientUnits"
            name="ingredientUnits"
            v-model="newIngredient.units">
        </div>

        <button  v-on:click="addAnotherIngredient">Add Another Ingredient</button>

        <button  v-on:click="finalizeRecipe">Finalize Recipe</button>
    
    </form>
    
  </section>

</template>

<script>
import { resourceService } from '../services/resourceService';

export default {
    data(){
        return {
            newIngredient: {
                ingredientId: null,
                name: "",
                preparation: "",
                quantity: 0,
                units: ""
            }
        }
    },
    methods: {
        submitIngredient(){
            return resourceService.addIngredient(this.newIngredient).then((response) => {
                const createdIngredient = response.data;
                this.newIngredient.ingredientId = createdIngredient.ingredientId;

                return resourceService.addIngredientToRecipe(
                    this.$store.state.currentRecipeId, this.newIngredient.ingredientId
                );
            }).catch((error) => {
                console.log(error);
            });

        },

        clearForm() {
            this.newIngredient = {
                ingredientId: null,
                name: "",
                preparation: "",
                quantity: 0,
                units: ""
            };
        },

        addAnotherIngredient(){
            this.submitIngredient().then((response) => {
                this.clearForm();
            })
        },


        finalizeRecipe(){
            this.submitIngredient().then((response) => {
                this.$router.push({name: 'home'});
            })
        }

    }

}
</script>

<style>

</style>