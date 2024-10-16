<template>
  <div id="recipe-app">
    <header>
      <img src="./assets/Fizz Buzz.jpg" alt="">
      <h1>Fizz Buzz Recipe Blog</h1>

      <!-- <nav>
        <router-link v-bind:to="{name: 'home'}"> <button>Home</button> </router-link>

        <router-link v-bind:to="{name: 'create-new-recipe'}"> <button>Create Recipe</button> </router-link>

        <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
          <button>Logout</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else> <button>Login</button> </router-link>
    </nav> -->
    </header>

    <nav>
        <router-link v-bind:to="{name: 'home'}"> <button>Home</button> </router-link>

        <router-link v-bind:to="{name: 'create-new-recipe'}"> <button>Create Recipe</button> </router-link>

        <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
          <button>Logout</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else> <button>Login</button> </router-link>
    </nav>

    <main>
      <router-view />
    </main>


    <footer>&copy; 2024 All Rights Reserved</footer>
  </div>
</template>

<style>
body{
    display: grid;
    /* grid-template-rows: 130px 30px 1fr 30px;
    grid-template-areas: 
    "header"
    "nav"
    "main"
    "footer"; */
    gap: 10px;
    background-color: #CBE4BD; 
    font-family: Georgia, 'Times New Roman', Times, serif;
    cursor: default;  
    color: #655B40;
}


header{
    /* grid-area: header; */
    display: flex;
    background-color: #CBE4BD;
    justify-content:flex-start; 
    padding-left: 50px;
    /* padding-right: 250px; */
    align-items:center;
    font-size: x-large;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000;
}

header h1{
  font-size: xx-large;
}

img{
    height: 150px;
    width: auto;
    padding-right: 100px;
}

nav {
    /* grid-area: nav; */
    display: flex;
    background-color: #f5f7db;
    justify-content: space-evenly;
    align-items: center;
    text-transform: uppercase;
    border-radius: 10px;
    padding: 5px; 
    margin-top: 140px; 
}

button{
    /* list-style: none; */
    cursor: pointer;
    padding: 5px;
    border-radius: 10px;
    background-color: #e5e8b8;
}

button:hover{
    background-color: #d2d69d;
}


main{
    grid-area: main;
    /* display: grid;
    grid-template-columns: 1fr 3fr;
    grid-template-areas: "recipes ingredients"; */
    background-color: #f5f7db;
    padding: 30px;
    border-radius: 10px;
    overflow: auto;
    margin-top: 5px;
}

footer{
    grid-area: footer;
    background-color: #f5f7db;
    text-align: center;
    margin-top: 5px;
    border-radius: 10px;
    /* position: fixed; */
    bottom: 0;
    left: 0;
    width: 100%;
    /* z-index: 1000; */
}

@media only screen and (max-width: 450px){

    body{
        grid-template-rows: 80px 20px 1fr 20px;
        gap: 5px;
    }
    header{
        padding: 0%;
        align-items: center;
        justify-self: unset;
        font-size: x-small;
    }
    img{
        height: 80px;
        padding: 0%;
    }
    nav{
        margin-top: 80px;
    }
    main{
        grid-template-columns: 1fr;
        padding: 20px;
        /* grid-template-areas: 
            "recipes"
            "ingredients"; */
    }

    footer{
        padding: 0%;
    }

}

</style>