<template>
    <div id="settingsOverlayWrapper">
        <span id="xButton" @click="$emit('exit')"> x </span>
        <p id="description"> Choose which characters you want to be tested on! </p>
        <div id="chartWrapper">
            <HiraganaChartSetter id="hiraganaChart"/>
            <KatakanaChartSetter />
        </div>
    </div>
</template>

<script setup>
import HiraganaChartSetter from '../Charts/HiraganaChartSetter.vue';
import KatakanaChartSetter from '../Charts/KatakanaChartSetter.vue';

</script>

<style scoped>


#xButton
{
    cursor: pointer;
    position: absolute;
    left: 0.35em;
    top: 0em;
    font-size: 2em;
}

/* #description
{
    position: absolute;
    font-size: 1.5em;
    left: 0;
    top: 2em;
    width: 33%;
    padding-left: 1em;
} */

#settingsOverlayWrapper
{
    text-align: center;
    background-color: rgba(255, 255, 255, 0.938);
    position: absolute;
    width: 90%;
    height: 90vh;
    display: flex;
    flex-direction: column;
    border: 1px solid rgb(221, 60, 11);
}

#chartWrapper
{
    display: flex;
    justify-content: center;
    flex-direction: row;
}

#hiraganaChart
{
    margin-left: 2em;
    margin-right: 2em;
}

</style>