<template>
    <div id="competencePageContainer">
        <div id="noSessionOverlay" v-if="!account.status">
            <p id="noSessionText"> Login to use the competence page </p>
        </div>

        <div id="kanaPickerContainer" v-if="!state.loading">
            <div id="kanaPicker">
                <span 
                    class="topButton" 
                    id="topLeftButton" 
                    @click="changeChart('Hiragana')"
                    :style="{ backgroundColor: chart.type === 'Hiragana' ? 'rgb(250, 183, 94)' : 'rgb(167, 226, 203)' }"
                > Hiragana </span>
                
                <span 
                    class="topButton"
                    id="topRightButton"
                    @click="changeChart('Katakana')"
                    :style="{ backgroundColor: chart.type === 'Katakana' ? 'rgb(250, 183, 94)' : 'rgb(167, 226, 203)' }"
                > Katakana </span>
            </div>
        </div>

        <div class="loaderContainer" v-if="state.loading">
            <AnimatedLoader />
        </div>
        <div v-for="item in chart.current.rows" v-else>
            <!-- Gets row -->
            <DisplayKanaBar 
                :romaji1="item.characters?.[0]?.romaji"
                :kana1="item.characters?.[0]?.character"
                :romaji2="item.characters?.[1]?.romaji"
                :kana2="item.characters?.[1]?.character"
                :romaji3="item.characters?.[2]?.romaji"
                :kana3="item.characters?.[2]?.character"
                :romaji4="item.characters?.[3]?.romaji"
                :kana4="item.characters?.[3]?.character"
                :romaji5="item.characters?.[4]?.romaji"
                :kana5="item.characters?.[4]?.character"
                :score=chart.scores[item.row]
            />
        </div>
        
    </div>
</template>

<script setup>
import DisplayKanaBar from '@/components/Competence/DisplayKanaBar.vue';
import HiraganaChart from "@/components/Charts/HiraganaChart.json"
import KatakanaChart from "@/components/Charts/KatakanaChart.json"
import { reactive, onMounted } from 'vue';
import { useAccountStore } from '@/stores/account';
import AnimatedLoader from '@/components/Visual/AnimatedLoader.vue';


const account = useAccountStore();

const hiraganaScore = reactive({
    A: 0,
    K: 0,
    S: 0,
    T: 0,
    N: 0,
    H: 0,
    M: 0,
    Y: 0,
    R: 0,
    W: 0,
    NN: 0,
    G: 0,
    Z: 0,
    D: 0,
    B: 0,
    P: 0
});

const katakanaScore = reactive({
    A: 0,
    K: 0,
    S: 0,
    T: 0,
    N: 0,
    H: 0,
    M: 0,
    Y: 0,
    R: 0,
    W: 0,
    NN: 0,
    G: 0,
    Z: 0,
    D: 0,
    B: 0,
    P: 0
});

const chart = reactive({
    type: 'Hiragana',
    current: HiraganaChart,
    scores: hiraganaScore
});

const state = reactive({
    loading: true,
    account: account.session
})

const changeChart = (newType) => {
    console.log(newType, chart); // Debugging log

    if (newType === "Hiragana") {
        chart.type = "Hiragana";
        chart.current = HiraganaChart;
        chart.scores = hiraganaScore;
    } else {
        chart.type = "Katakana";
        chart.current = KatakanaChart;
        chart.scores = katakanaScore;
    }

    console.log(chart.type);
};

const getEntries = async () => 
{
    const data = await fetch(`/api/entryProgress/get/${account.uuid}`)
    const res = await data.json();

    console.log(res)

    for (const [key, value] of Object.entries(res)) 
    {
        console.log(value);

        if (value.kanaType == "Hiragana")
        {
            hiraganaScore[value.row] += value.score
        }
        else
        {
            katakanaScore[value.row] += value.score
        }
        
    }

    state.loading = false
}


onMounted(() => {
    getEntries();
});

</script>

<style scoped>
#competencePageContainer
{
    width: 100%;
    height: 100vh;
    overflow-y: scroll;
}

#kanaPickerContainer
{
    display: flex;
    justify-content: center;
    width: 100%;
    margin-top: 1em;
}

#kanaPicker
{
    width: 300px;
    height: 30px;
    border-radius: 1em;
    display: flex;
    justify-content: space-around;
    font-size: 1.25em;
}

.topButton
{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50%;
}

#topLeftButton
{
    /* active color */
    background-color: rgb(250, 183, 94);
    border-top-left-radius: 80px 80px;
    border-bottom-left-radius: 80px 80px;
}

#topLeftButton:hover
{
    /* default color */
    background-color: rgb(48, 151, 137); 
    cursor: pointer;
}

#topRightButton
{
    background-color: rgb(167, 226, 203);
    border-top-right-radius: 80px 80px;
    border-bottom-right-radius: 80px 80px;
}

#topRightButton:hover
{
    background-color: rgb(48, 151, 137);
    cursor: pointer;
}

.loaderContainer 
{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    width: 100%;
}

#noSessionOverlay 
{
    z-index: 9999;
    background: linear-gradient(to bottom, rgba(255, 255, 255, 0.897), rgba(255, 255, 255, 1)); 
    position: absolute;
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
}

#noSessionText {
    margin-top: 3em;
    font-size: 2em;
    font-weight: bold;
    text-align: center;
    color: #333;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2); 
    letter-spacing: 1px;
    margin-right: 6em;
}


</style>