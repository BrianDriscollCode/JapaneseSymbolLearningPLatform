<template>
  <main>
    <div id="startContainer">
      <h1> Learn Hiragana and Katakana v0.02</h1>
      <p id="subtitle"> Practicing for the JLPT? Or maybe you are just learning Japanese as a hobby. Either way, this is the best resource for preparing your reading skills! </p>
      <button class="button" @click="goToLearn"> Start Learning Now! </button>
      <img id="japanImage" src="/japan.png" height="124" width="124"/>
    </div>
  </main>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goToLearn = () => 
{
  router.push("/learn");
}

</script>

<style scoped>

main
{
  width: 100%;
}

#startContainer
{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

h1
{
  color: #1d3568;
  margin: 0;
}

#subtitle
{
  width: 800px;
  font-size: 1.3em;
  text-align: center;
  color: #1d3568;
}

.button {
  background-color: #e63746; /* Akane Red */
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  padding: 12px 24px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  box-shadow: 4px 4px 0 #8c2f39; /* Deep shadow for a modern touch */
  margin-bottom: 2em;
}

.button:hover {
  background-color: #b53434; /* Darker shade of red */
  box-shadow: 2px 2px 0 #8c2f39;
  transform: translate(2px, 2px);
}

.button:active {
  background-color: #a12727; /* Even darker for pressed effect */
  box-shadow: none;
  transform: translate(4px, 4px);
}

#japanImage
{

}
</style>
