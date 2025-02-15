<template>
    <div id="learnContainer"> 
        <MultipleChoiceCard />
    </div>
</template>

<script setup>
import MultipleChoiceCard from '@/components/Learn/MultipleChoiceCard.vue';
import HiraganaChart from "@/components/Charts/HiraganaChart.json";
import KatakanaChart from "@/components/Charts/KatakanaChart.json";

console.log("Hiragana Chart: ", HiraganaChart.rows);
console.log("Katakana Chart: ", KatakanaChart.rows);
</script>

<style scoped>
#learnContainer
{
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>