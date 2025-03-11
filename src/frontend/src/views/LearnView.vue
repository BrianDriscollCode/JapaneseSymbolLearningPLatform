<template>
    <div id="learnContainer"> 
        <div id="learnWrapper">
            <div id="settingOverlayContainer" v-if="state.kanaOverlay || state.kanjiOverlay">
                <KanaSettingsOverlay  @exit="exitKanaSettings" v-if="state.kanaOverlay"/>
                <KanjiSettingsOverlay @exit="exitKanjiSettings" v-if="state.kanjiOverlay"/>
            </div>

            <div id="settingsContainer">
                <!-- <button @click="showSettings" id="settingsButton"> Settings </button>
                <button @click="showSettings" id="settingsButton"> Settings </button>
                <button @click="showSettings" id="settingsButton"> Settings </button> -->
                <div class="topButton" @click="showKanaSettings">
                    <img src="/LearnIcons/icons8-japanese-50.png" width="50" height="50" />
                    <p class="buttonText"> Kana </p>
                </div>
                <div class="topButton" @click="showKanjiSettings">
                    <img src="/LearnIcons/icons8-kanji-50.png" width="50" height="50" />
                    <p class="buttonText"> Kanji </p>
                </div>
                <div class="topButton" @click="goToInfo">
                    <img src="/LearnIcons/icons8-info-50.png" width="50" height="50" />
                    <p class="buttonText"> Info </p>
                </div>
            </div>

            <MultipleChoiceCardRomaji 
                :kana="question.kana"
                :romaji="question.romaji"
                :answer1="question.answer1"
                :answer2="question.answer2"
                :answer3="question.answer3"
                :answer4="question.answer4"
                :row="question.row"
                :correctLine="question.correctLine"
                :questionNumber="count.amount"
                :questionMax="count.max"
                v-if="question.displayQuestion"
                @submit="listenForSubmit"
            />

            <FinishSession
                :numberCorrect="count.correct"
                :maxQuestions="count.max" 
                @restart="restart"
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
import KanaSettingsOverlay from '@/components/Learn/KanaSettingsOverlay.vue';
import KanjiSettingsOverlay from '@/components/Learn/KanjiSettingsOverlay.vue';
import { reactive, onMounted } from 'vue';
import { useLearnSettingsStore } from '@/stores/LearnSettings';
import { useAccountStore } from '@/stores/account';
import HiraganaChart from "@/components/Charts/HiraganaChart.json";
import KatakanaChart from "@/components/Charts/KatakanaChart.json";
import { useRouter } from 'vue-router';

const router = useRouter();

const learnSettings = useLearnSettingsStore();
const account = useAccountStore();

const count = reactive({
    amount: 1,
    max: learnSettings.numberOfQuestions,
    correct: 0
})


let answerArray = [];

const listenForSubmit = (answer) =>
{
    console.log(answer);
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
        question.displayQuestion = false;
        submitEntries(answerArray);
    }
}

const submitEntries = async (answerArray) =>
{
    if (account.status)
    {
        const data = 
        {
            entries: answerArray,
            uuid: account.uuid
        }

        const res = await fetch("/api/entryProgress/insert", 
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        console.log(JSON.stringify(data));
    }

    answerArray = [];
    
}

onMounted(() => {
    createQuestion();
});

const question = reactive({ 
    kana: "",
    romaji: "",
    answer1: "",
    answer2: "",
    answer3: "",
    answer4: "",
    row: "",
    correctLine: null,
    displayQuestion: false
});

const createQuestion = () =>
{
    const availableCharacters = getAvailableCharacters();
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
    console.log("****HERE NOW ****" , availableCharacters.get(answers[3]));
    question.row = availableCharacters.get(answers[rightAnswer]).row;
    question.correctLine = rightAnswer;
    question.romaji = availableCharacters.get(answers[rightAnswer]).romaji;
    

    question.displayQuestion = false;
    question.displayQuestion = true;
}

const getAvailableCharacters = () => 
{
    const hiraganaAvailable = isHiraganaTrue();

    const katakanaAvailable = isKatakanaTrue();

    let kanaType = getKanaType(hiraganaAvailable, katakanaAvailable);
    question.kana = kanaType;

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

                    console.log("Romaji: ", HiraganaChart.rows[i].characters[n].romaji);
                    console.log("Row: ", HiraganaChart.rows[i].row);
                    hiraganaRows.set(
                        positionNumber,
                        // {character, romaji} - object
                        {
                            row:  HiraganaChart.rows[i].row,
                            character: HiraganaChart.rows[i].characters[n].character,
                            romaji: HiraganaChart.rows[i].characters[n].romaji,
                            column: HiraganaChart.rows[i].characters[n].column
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
                            row: KatakanaChart.rows[i].row,
                            character: KatakanaChart.rows[i].characters[n].character,
                            romaji: KatakanaChart.rows[i].characters[n].romaji,
                            column: KatakanaChart.rows[i].characters[n].column
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
    kanaOverlay: false,
    kanjiOverlay: false,
});

const showKanaSettings = () => 
{
    state.kanaOverlay = true;
}

const exitKanaSettings = () =>
{
    learnSettings.checkForActiveRow();
    state.kanaOverlay = false;
}

const showKanjiSettings = () => 
{
    state.kanjiOverlay = true;
}

const exitKanjiSettings = () =>
{
    state.kanjiOverlay = false;
}

const goToInfo = () =>
{
    router.push("/");
}

const restart = () => 
{
    count.amount = 1;
    count.correct = 0;
    question.displayQuestion = true;
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