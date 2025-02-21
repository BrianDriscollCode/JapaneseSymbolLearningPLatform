<template>
    <div :class="{ cardShow: state.showCard, card: !state.showCard }">

        <div id="questionNumberContainer">
            Question: 1/10
        </div>
        
        <span> What is the {{ state.kana }} symbol for '{{ state.romaji }}'?</span>

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
import { reactive, defineProps, watchEffect, defineEmits, onMounted } from "vue";

const emit = defineEmits({
  submit: (payload) => {
    if (typeof payload.romaji === 'string' && typeof payload.response === 'boolean') {
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
    ...props,
    selectedAnswer: null,
    showCard: false
});

watchEffect(() => {
    Object.assign(state, props);
});

const pickAnswer = (numberChosen) => {
    state.selectedAnswer = numberChosen;
};

const submitAnswer = () => {
    console.log(state.selectedAnswer, state.correctLine + 1);
    const response = state.selectedAnswer === state.correctLine + 1;
    console.log(response ? "Correct!" : "Incorrect!");
    emit('submit', { romaji: state.romaji, response });
    state.selectedAnswer = null;
    restartCard();
};

const restartCard = () => {
    state.showCard = false;
    setTimeout(() => {
        state.showCard = true;
    }, 25); // Short delay ensures Vue registers the change
}

onMounted(() => {
    restartCard();
});
</script>


<style scoped>
.card 
{
    opacity: 0.0;

    border-radius: 1em;
    background-color: rgb(255, 255, 255);
    padding: 1em;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1);
}

.cardShow
{
    opacity: 1;

    border-radius: 1em;
    background-color: rgb(255, 255, 255);
    padding: 1em;
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1);
    transition: opacity 0.3s;
}

#questionNumberContainer
{
    margin-bottom: 1em;
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