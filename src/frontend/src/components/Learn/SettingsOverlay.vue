<template>
    <div id="settingsContainer">
        <span id="xButton" @click="random"> x </span>
        <HiraganaChartSetter />
        <HiraganaChartSetter />
    </div>
</template>

<script setup>
import HiraganaChartSetter from '../Charts/HiraganaChartSetter.vue';


const random = () =>
{

}
</script>

<style scoped>
#xButton
{
    cursor: pointer;
    position: absolute;
    left: 1em;
    top: 0em;
    font-size: 2em;
}

#settingsContainer
{
    background-color: rgba(255, 255, 255, 0.856);
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    flex-direction: row;
}
</style>