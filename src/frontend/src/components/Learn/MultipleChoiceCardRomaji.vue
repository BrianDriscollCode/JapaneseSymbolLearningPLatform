<template>
    <div id="card">
        <span> What is the {{ state.kana }} symbol for '{{ state.romaji }}'?</span>

        <div id="multipleChoiceAnswers">
            <div class="entry"> 
                <input type="radio" name="answer" @click="pickAnswer(1)"> <span> {{ state.answer1 }} </span>
            </div>
            <div class="entry"> 
                <input type="radio" name="answer" @click="pickAnswer(2)"> <span> {{ state.answer2 }} </span>
            </div>
            <div class="entry"> 
                <input type="radio" name="answer" @click="pickAnswer(3)"> <span> {{ state.answer3 }} </span>
            </div>
            <div class="entry"> 
                <input type="radio" name="answer" @click="pickAnswer(4)"> <span> {{ state.answer4 }} </span>
            </div>

            <button @click="submitAnswer" class="button"> Submit </button>
        </div>
    </div>
</template>

<script setup> 
import { reactive, defineProps } from "vue";

// which answer is answer line is correct.
// generate random kana for other lines
// I have to know if it is going to be hiragana or katana
// I have to know what romaji is chosen

const props = defineProps({
    kana: String,
    romaji: String,
    answer1: String,
    answer2: String,
    answer3: String,
    answer4: String,
    correctLine: Number
});

const state = reactive({
    kana: "Hiragana",
    romaji: "a",
    answer1: "あ",
    answer2: "い",
    answer3: "う",
    answer4: "お",
    correctLine: 1
});

const answer = reactive({
    one: false,
    two: false,
    three: false,
    four: false
});

const pickAnswer = (numberChosen) => 
{
    if (numberChosen == 1)
    {
        answer.one = true;
        answer.two = false;
        answer.three = false;
        answer.four = false;
    }
    else if (numberChosen == 2)
    {
        answer.one = false;
        answer.two = true;
        answer.three = false;
        answer.four = false;
    }
    else if (numberChosen == 3)
    {
        answer.one = false;
        answer.two = false;
        answer.three = true;
        answer.four = false;
    }
    else if (numberChosen == 4)
    {
        answer.one = false;
        answer.two = false;
        answer.three = false;
        answer.four = true;
    }
}

const submitAnswer = () =>
{
    console.log("One: " + answer.one);
    console.log("Two: " + answer.two);
    console.log("Three: " + answer.three);
    console.log("Four: " + answer.four);
}

</script>

<style scoped>
#card 
{
    height: 225px;
    border-radius: 1em;
    background-color: rgb(255, 255, 255);
    padding: 1em;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1); /* Light shadow */
}

#multipleChoiceAnswers
{
    margin-top: 2em;
}

.entry 
{
    margin-bottom: .5em;
}

.button {
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
  margin-bottom: 2em;
}

.button:hover {
  background-color: #b53434; /* Darker shade of red */
  box-shadow: 2px 2px 0 #8c2f39;
  transform: translate(2px, 2px);
}

.button:active {
  background-color: #a12727; /* Even darker for pressed effect */
  box-shadow: none;
  transform: translate(4px, 4px);
}

</style>