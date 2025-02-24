<template>
    <div id="finishSessionContainer">
        <p> Done! </p>
        <p> You got {{ props.numberCorrect }} questions correct out of {{ props.maxQuestions }} questions!</p>
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
    background-color: white;
    height:250px;
}
</style>