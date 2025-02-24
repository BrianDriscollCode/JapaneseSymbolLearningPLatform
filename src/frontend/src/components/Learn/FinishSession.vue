<template>
    <div id="finishSessionContainer">
            <p class="text"> Done! </p>
            <p class="text"> You got {{ props.numberCorrect }} questions correct out of {{ props.maxQuestions }} questions!</p>
            <button id="button" @click="$emit('restart')"> Restart </button>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    numberCorrect: Number,
    maxQuestions: Number
});

</script>

<style scoped>
#finishSessionContainer
{
    opacity: 1;
    border-radius: 1em;
    background-color: rgb(255, 255, 255);
    padding: 1em;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1);
    transition: opacity 0.3s;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.text
{
    margin-top: 0;
    margin-bottom: 1em;
}

#button {
    margin-top: .5em;
  background-color: #e63746; /* Akane Red */
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  padding: 6px 12px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  box-shadow: 4px 4px 0 #8c2f39; /* Deep shadow for a modern touch */
}


</style>