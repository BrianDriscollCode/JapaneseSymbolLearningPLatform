<template>
    <div id="card">
        <span> What is the {{ state.kana }} symbol for '{{ state.romaji }}'?</span>

        <!-- <div id="multipleChoiceAnswers">
            <div class="entry"> 
                1. <input type="radio" name="answer" v-model="state.answer1" @click="pickAnswer(1)"> <span> {{ state.answer1 }} </span>
            </div>
            <div class="entry"> 
                2. <input type="radio" name="answer" v-model="state.answer2" @click="pickAnswer(2)"> <span> {{ state.answer2 }} </span>
            </div>
            <div class="entry"> 
                3. <input type="radio" name="answer" v-model="state.answer3" @click="pickAnswer(3)"> <span> {{ state.answer3 }} </span>
            </div>
            <div class="entry"> 
                4. <input type="radio" name="answer" v-model="state.answer4" @click="pickAnswer(4)"> <span> {{ state.answer4 }} </span>
            </div>

            <button @click="submitAnswer" class="button"> Submit </button>
        </div> -->
        <div id="multipleChoiceAnswers">
            <div class="entry" v-for="(answer, index) in [state.answer1, state.answer2, state.answer3, state.answer4]" :key="index">
                <input 
                    type="radio"
                    name="answer"
                    :value="index + 1"
                    v-model="state.selectedAnswer"
                    @change="pickAnswer(index + 1)"
                />
                <span>{{ answer }}</span>
            </div>
            <button @click="submitAnswer" class="button"> Submit </button>
        </div>
    </div>
</template>

<script setup> 
import { reactive, defineProps, watchEffect, defineEmits  } from "vue";

// which answer is answer line is correct.
// generate random kana for other lines
// I have to know if it is going to be hiragana or katana
// I have to know what romaji is chosen

const emit = defineEmits({
  submit: (payload) => {
    if (typeof payload.romaji === 'string' && typeof payload.response === 'string') {
      return true;
    }
    console.warn("Invalid payload for submit event", payload);
    return false;
  }
});

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
    kana: props.kana,
    romaji: props.romaji,
    answer1: props.answer1,
    answer2: props.answer2,
    answer3: props.answer3,
    answer4: props.answer4,
    selectedAnswer: null,
    correctLine: props.correctLine
});

watchEffect(() => {
    console.log("card state.kana: ", props.romaji);
    state.kana = props.kana,
    state.romaji = props.romaji,
    state.answer1 = props.answer1,
    state.answer2 = props.answer2,
    state.answer3 = props.answer3,
    state.answer4 = props.answer4,
    state.correctLine = props.correctLine
})

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
    let correctAnswer = state.correctLine + 1;
    let response = false;

    if (answer.one && correctAnswer == 1)
    {
        console.log("Correct!");
        response = true;
    }
    else if (answer.two && correctAnswer == 2)
    {
        console.log("Correct!");
        response = true;
    }
    else if (answer.three && correctAnswer == 3)
    {
        console.log("Correct!");
        response = true;
    }
    else if (answer.four && correctAnswer == 4)
    {
        console.log("Correct!");
        response = true;
    }
    else
    {
        console.log("Incorrect!");
        response = false;
    }


    emit('submit', {romaji: state.romaji, response: response });
    state.selectedAnswer = null;

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