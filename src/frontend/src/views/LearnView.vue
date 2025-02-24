<template>
    <div id="learnContainer"> 
        <div id="learnWrapper">
            <div id="settingOverlayContainer" v-if="state.overlay">
                <SettingsOverlay  @exit="exitSettings"/>
            </div>

            <div id="settingsContainer">
                <!-- <button @click="showSettings" id="settingsButton"> Settings </button>
                <button @click="showSettings" id="settingsButton"> Settings </button>
                <button @click="showSettings" id="settingsButton"> Settings </button> -->
                <div class="topButton" @click="showSettings">
                    <img src="/icons8-settings-100.png" width="50" height="50" />
                    <p class="buttonText"> Settings </p>
                </div>
                <div class="topButton">
                    <span> x </span>
                </div>
                <div class="topButton">
                    <span> x </span>
                </div>
            </div>

            <MultipleChoiceCardRomaji 
                :kana="question.kana"
                :romaji="question.romaji"
                :answer1="question.answer1"
                :answer2="question.answer2"
                :answer3="question.answer3"
                :answer4="question.answer4"
                :correctLine="question.correctLine"
                :questionNumber="count.amount"
                :questionMax="count.max"
                v-if="question.displayQuestion"
                @submit="listenForSubmit"
            />

            <FinishSession
                :numberCorrect="count.correct"
                :maxQuestions="count.max" 
                v-else
            />

            <div id="arrowContainer"> 

            </div>
        </div>
    </div>
</template>

<script setup>
import MultipleChoiceCardRomaji from '@/components/Learn/MultipleChoiceCardRomaji.vue';
import FinishSession from "@/components/Learn/FinishSession.vue"
import SettingsOverlay from '@/components/Learn/SettingsOverlay.vue';
import { reactive, onMounted } from 'vue';
import { useLearnSettingsStore } from '@/stores/LearnSettings';
import HiraganaChart from "@/components/Charts/HiraganaChart.json";
import KatakanaChart from "@/components/Charts/KatakanaChart.json";


const learnSettings = useLearnSettingsStore();

const count = reactive({
    amount: 1,
    max: 3,
    correct: 0
})


let answerArray = [];

const listenForSubmit = (answer) =>
{
    answerArray.push(answer);

    count.amount += 1;
    
    if (answer.response)
    {
        count.correct += 1;
    }

    if (count.amount <= count.max)
    {
        createQuestion();
    }
    else
    {
        for (let i = 0; i < answerArray.length; i++)
        {
            console.log("res: ", answerArray[i]);
        }
        console.log(answerArray);
        question.displayQuestion = false;
    }
    
}

onMounted(() => {
    console.log("LearnView Hiragana Rows: ", learnSettings.availableHiraganaRows);
    console.log("LearnView Katakana Rows: ", learnSettings.availableKatakanaRows);
    console.log("Learnview Hiragana Chart: ", HiraganaChart.rows);
    createQuestion();
});

const question = reactive({ 
    kana: "",
    romaji: "",
    answer1: "",
    answer2: "",
    answer3: "",
    answer4: "",
    correctLine: null,
    displayQuestion: false
});

const createQuestion = () =>
{
    const availableCharacters = getAvailableCharacters();
    console.log("Available Characters: ", availableCharacters);
    let size = availableCharacters.size - 1;

    // generate possible answers
    let answers = new Set();
    while (answers.size < 4) {
        let chooser = Math.floor(Math.random() * size) + 1;
        answers.add(chooser);
    }
    answers = [...answers]; // Convert Set to Array

    let rightAnswer = Math.floor(Math.random() * 4) + 0;

    question.answer1 = availableCharacters.get(answers[0]).character;
    question.answer2 = availableCharacters.get(answers[1]).character;
    question.answer3 = availableCharacters.get(answers[2]).character;
    question.answer4 = availableCharacters.get(answers[3]).character;

    question.correctLine = rightAnswer;
    question.romaji = availableCharacters.get(answers[rightAnswer]).romaji;
    

    question.displayQuestion = false;
    question.displayQuestion = true;

    console.log(question.answer1);
    console.log(question.answer2);
    console.log(question.answer3);
    console.log(question.answer4);
}

const getAvailableCharacters = () => 
{
    const hiraganaAvailable = isHiraganaTrue();
    //console.log("hiraganaAvailable: ", hiraganaAvailable);

    const katakanaAvailable = isKatakanaTrue();
    //console.log("katakanaAvailable: ", katakanaAvailable);

    let kanaType = getKanaType(hiraganaAvailable, katakanaAvailable);
    question.kana = kanaType;
    console.log("kanaType: " + kanaType);

    if (kanaType == "Hiragana")
    {
        const hiraganaRows = new Map();

        let positionNumber = 0;

        for (let i = 0; i < HiraganaChart.rows.length; i++)
        {
            if (learnSettings.availableHiraganaRows[HiraganaChart.rows[i].row])
            {
                for (let n = 0; n < HiraganaChart.rows[i].characters.length; n++)
                {
                    hiraganaRows.set(
                        positionNumber,
                        // {character, romaji} - object
                        {
                            character: HiraganaChart.rows[i].characters[n].character,
                            romaji: HiraganaChart.rows[i].characters[n].romaji
                        }
                    )

                    positionNumber += 1;
                }
            }
        }

        return hiraganaRows;
    }
    else if (kanaType == "Katakana")
    {
        const katakanaRows = new Map();

        let positionNumber = 0;

        for (let i = 0; i < KatakanaChart.rows.length; i++)
        {
            if (learnSettings.availableKatakanaRows[KatakanaChart.rows[i].row])
            {
                for (let n = 0; n < KatakanaChart.rows[i].characters.length; n++)
                {
                    katakanaRows.set(
                        positionNumber,
                        // {character, romaji} - object
                        {
                            character: KatakanaChart.rows[i].characters[n].character,
                            romaji: KatakanaChart.rows[i].characters[n].romaji
                        }
                    )

                    positionNumber += 1;
                }
            }
        }

        return katakanaRows;
    }
    else
    {
        console.log("error");
    }
}

const isHiraganaTrue = () => 
{
    let boolValue = false;
    for (let i = 0; i < HiraganaChart.rows.length; i++)
    {
        if (learnSettings.availableHiraganaRows[HiraganaChart.rows[i].row])
        {
            boolValue = true;
        }
    }

    return boolValue;
}

const isKatakanaTrue = () =>
{
    let boolValue = false;
    for (let i = 0; i < KatakanaChart.rows.length; i++)
    {
        if (learnSettings.availableKatakanaRows[KatakanaChart.rows[i].row])
        {
            boolValue = true;
        }
    }

    return boolValue;
}

const getKanaType = (hiraganaAvailable, katakanaAvailable) => 
{
    let kanaType;

    if (hiraganaAvailable && katakanaAvailable)
    {
        let ran = Math.floor((Math.random() * 2) + 1);

        if (ran == 1)
        {
            kanaType = "Hiragana";
        }
        else
        {
            kanaType = "Katakana";
        }
    }
    else if (hiraganaAvailable && !katakanaAvailable)
    {
        kanaType = "Hiragana";
    }
    else if (!hiraganaAvailable && katakanaAvailable)
    {
        kanaType = "Katakana";
    }
    else
    {
        kanaType = "No Type chosen";
    }

    return kanaType;
}

const state = reactive({
    overlay: false,
});

const showSettings = () => 
{
    state.overlay = true;
}

const exitSettings = () =>
{
    state.overlay = false;
}
</script>

<style scoped>

#settingsButton
{
    margin-bottom: 1em;
    padding: 1em 2em;
}

.topButton
{
    width: 100px;
    height: 100px;
    background-color: #a7dadc;
    border-radius: 1em;
    display: flex;
    justify-content: center;
    align-content: center;
    flex-direction: column;
    text-align: center;
}

.topButton:hover
{
    width: 100px;
    height: 100px;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.3);
    transition: box-shadow 0.3s;
}

.topButton img 
{
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 10px;
}

.buttonText
{
    margin: 0;
}

#settingOverlayContainer
{
    position: absolute;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(53, 53, 53, 0.884);
}

#settingsContainer
{
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    margin-bottom: 1em;

}

#learnContainer
{
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    /* background-color: blueviolet; */
}

#learnWrapper
{   width: 450px;
    /* background-color: aqua; */
}

#arrowContainer
{
    display: flex;
    justify-content:space-between;
    flex-direction: row;
    height: 100px;
}

.arrowButton
{
    margin: 0 1em;

}

.arrowButton:hover
{
    background-color: #a7dadc;
}

#flipImg
{
    transform: scaleX(-1);
}


</style>