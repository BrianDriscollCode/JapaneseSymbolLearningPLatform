<template>
    <div class="kanaBarContainer">
        <div class="kanaBarWrapper">
            <v-progress-linear
                color="light-blue"
                height="24"
                :model-value="calculateWeightedScore()"
                striped
            >
                <strong>{{ Math.ceil(calculateWeightedScore()) }}%</strong>
            </v-progress-linear>
            <table>
                <tbody>
                    <tr>
                        <th> {{ state.romaji1 }} {{ state.kana1 }} </th> 
                        <th> {{ state.romaji2 }} {{ state.kana2 }} </th>
                        <th> {{ state.romaji3 }} {{ state.kana3 }} </th>
                        <th> {{ state.romaji4 }} {{ state.kana4 }} </th>
                        <th> {{ state.romaji5 }} {{ state.kana5 }} </th>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { reactive, defineProps, onMounted, watchEffect } from 'vue';

const value = reactive({
    amount: (1 * 10)
})

const props = defineProps({
    romaji1: '',
    kana1: '',
    romaji2: '',
    kana2: '',
    romaji3: '',
    kana3: '',
    romaji4: '',
    kana4: '',
    romaji5: '',
    kana5: '',
    score: 0
});

const state = reactive({
    romaji1: props.romaji1,
    kana1: props.kana1,
    romaji2: props.romaji2,
    kana2: props.kana2,
    romaji3: props.romaji3,
    kana3: props.kana3,
    romaji4: props.romaji4,
    kana4: props.kana4,
    romaji5: props.romaji5,
    kana5: props.kana5,
    score: props.score
});

const calculateWeightedScore = () => 
{
    return state.score
}

// const calculateWeightedScore = () => {
//     let scoreArray = [state.score1, state.score2, state.score3, state.score4, state.score5]
//         .filter(score => score !== null && score !== undefined);

//     let weightedScoreArray = [];

//     for (let i = 0; i < scoreArray.length; i++) {
//         let temp;

//         if (scoreArray[i] > 10) {
//             temp = 10;   
//         } else if (scoreArray[i] < 0) {
//             temp = 0;
//         } else {
//             temp = scoreArray[i];
//         }

//         weightedScoreArray.push(temp);
//         //console.log(temp);  // Log each score
//     }

//     let total = 0;  // Initialize total to 0
//     //console.log(weightedScoreArray);

//     for (let i = 0; i < weightedScoreArray.length; i++) {
//         total = total + Number(weightedScoreArray[i]);
//         //console.log(total)
//     }

//     if (weightedScoreArray.length == 1)
//     {
//         return total * 10;
//     }
//     else if (weightedScoreArray.length == 3)
//     {
//         return total * 3.33;
//     }
//     else if (weightedScoreArray.length == 5)
//     {
//         return total * 2;
//     }

//     return 0;
//     // console.log("Score: ", total);  // Log the final weighted score
//     // return total;  // Return the final score
// }

onMounted(() => {
    calculateWeightedScore();  // Call the function when the component is mounted
})

watchEffect(() => {
    state.romaji1 = props.romaji1;
    state.kana1 = props.kana1;
    state.score1 = props.score1;
    state.romaji2 = props.romaji2;
    state.kana2 = props.kana2;
    state.score2 = props.score2;
    state.romaji3 = props.romaji3;
    state.kana3 = props.kana3;
    state.score3 = props.score3;
    state.romaji4 = props.romaji4;
    state.kana4 = props.kana4;
    state.score4 = props.score4;
    state.romaji5 = props.romaji5;
    state.kana5 = props.kana5;
    state.score5 = props.score5;
});

</script>


<style scoped>
.kanaBarContainer 
{
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 2em;
}

.kanaBarWrapper
{
    width: 500px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 18px;
    text-align: center;
    table-layout: fixed; /* Ensures equal width for columns */
}

th, td {
    width: 20%; /* Ensures equal width for each column */
    height: 50px; /* Adjust this to your preference */
    padding: 12px;
    font-size: 1.25em;
    border: 1px solid #ddd;
    text-align: center;
}

th {
    background-color: #4CAF50;
    color: white;
}

tbody tr {
    background-color: #f9f9f9;
}

tbody tr:hover {
    background-color: #f1f1f1;
}



</style>