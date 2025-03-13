<template>
  <main>
    <div id="startContainer">
      <h1> Learn Hiragana and Katakana (v0.04)</h1>
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

h1 {
  color: #1d3568;
  margin: 0;
  font-size: 2.6rem;
  font-weight: 700;
  text-align: center;
  text-transform: uppercase;
  letter-spacing: 2px;
  text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
}

#subtitle {
  max-width: 60%;
  font-size: 1.5rem;
  text-align: center;
  color: #1d3568;
  margin: 0.5rem auto 1.5rem auto;
  line-height: 1.6;
  font-family: 'Arial', sans-serif;
  font-weight: 600;
  letter-spacing: 1px;
  background-image: linear-gradient(45deg, #1d3568, #94b341);
  -webkit-background-clip: text;
  color: transparent;
}

/* Button Styling */
.button {
  background-color: #e63746; /* Akane Red */
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  padding: 12px 24px;
  border: 2px solid #e63746; /* Added border for definition */
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  box-shadow: 4px 4px 0 #8c2f39;
  margin-bottom: 2em;
}

.button:hover {
  background-color: #b53434;
  box-shadow: 2px 2px 0 #8c2f39;
  transform: translate(2px, 2px);
  transition: all 0.2s ease-in-out; /* Smooth transition on hover */
}

.button:active {
  background-color: #a12727;
  box-shadow: none;
  transform: translate(4px, 4px);
  transition: all 0.1s ease-in-out; /* Slightly faster on active */
}


#japanImage
{

}
</style>
