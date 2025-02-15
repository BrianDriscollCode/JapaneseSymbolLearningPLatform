<template>
    <div id="learnContainer"> 
        <SettingsOverlay v-if="state.overlay" />
        <button @click="showSettings" id="settingsButton"> Settings </button>
        <MultipleChoiceCard />
        <div id="arrowContainer"> 
            <button class="arrowButton"> <- </button>
            <button class="arrowButton"> -> </button>
        </div>
    </div>
</template>

<script setup>
import MultipleChoiceCard from '@/components/Learn/MultipleChoiceCard.vue';
import SettingsOverlay from '@/components/Learn/SettingsOverlay.vue';
import { reactive } from 'vue';

const state = reactive({
    overlay: false,
});

const showSettings = () => 
{
    state.overlay = true;
}
</script>

<style scoped>
#settingsButton
{
    margin:2em;
    padding: 1em 2em;
}

#learnContainer
{
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

#arrowContainer
{
    display: flex;
    justify-content: center;
    flex-direction: row;
    margin-top: 1em;
}

.arrowButton
{
    padding: 1em 2em;
    margin: 1em
}
</style>