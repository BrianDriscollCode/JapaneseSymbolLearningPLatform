<template>
    <div>
        <div id="titleContainer">
            <h2> Hiragana </h2>
        </div>
        <table id="chart">
            <thead>
                <th id="noStyleTableCell">  </th>
                <th id="noStyleTableCell">  </th>
                <th id="noStyleTableCell"> a </th> 
                <th id="noStyleTableCell"> i </th>
                <th id="noStyleTableCell"> u </th>
                <th id="noStyleTableCell"> e </th>
                <th id="noStyleTableCell"> o </th>
            </thead>
            <tbody v-for="item in HiraganaChart.rows">
                <tr @click="learningSettings.toggleRow(item.row)">
                    <td id="noStyleTableCell"> 
                        <!-- Bind v-model to the selected row value -->
                        <input
                            type="checkbox"
                            :value="item.row"
                            v-model="myData[item.row]"
                            @change="toggleRow(item.row)"
                        />
                    </td>
                    <td id="noStyleTableCell"> {{ item.row.toLowerCase() }}</td>
                    
                    <td v-if="item.characters?.[0]?.character"> {{ item.characters[0].column == 'a' ? item.characters[0].character : ' ' }} </td>
                    <td v-else> </td>

                    <td v-if="item.characters?.[1]?.character"> {{ item.characters[1].column == 'i' ? item.characters[1].character : ' ' }} </td>
                    <td v-else> </td>

                    <td v-if="item.characters?.[2]?.character"> {{ item.characters[2].column == 'u' ? item.characters[2].character : item.characters[1].character }} </td> 
                    <td v-else> </td>

                    <td v-if="item.characters?.[3]?.character"> {{ item.characters[3].column == 'e' ? item.characters[3].character : ' ' }} </td>
                    <td v-else> </td>
                    
                    <td v-if="item.characters?.[4]?.character"> {{ item.characters[4].column == 'o' ? item.characters[4].character : 'f' }} </td>
                    <td v-else-if="item.characters[2]?.character"> {{ item.characters[2].character }}</td>
                    <td v-else-if="item.characters[1]?.character"> {{ item.characters[1].character }}</td>
                    <td v-else> </td>
                </tr>
            </tbody>

        </table>
    </div>
</template>

<script setup>
import HiraganaChart from "@/components/Charts/HiraganaChart.json";
import { reactive, onMounted, watchEffect } from "vue";
import { useLearnSettingsStore } from "@/stores/LearnSettings";

const learningSettings = useLearnSettingsStore();

const random = reactive({
    selectedRow: null,
});

const myData = reactive({
    A: learningSettings.availableRows.A,
    K: learningSettings.availableRows.K,
    S: learningSettings.availableRows.S,
    T: learningSettings.availableRows.T,
    N: learningSettings.availableRows.N,
    H: learningSettings.availableRows.H,
    M: learningSettings.availableRows.M,
    Y: learningSettings.availableRows.Y,
    R: learningSettings.availableRows.R,
    W: learningSettings.availableRows.W,
    NN: learningSettings.availableRows.NN,
    G: learningSettings.availableRows.G,
    Z: learningSettings.availableRows.Z,
    D: learningSettings.availableRows.D,
    B: learningSettings.availableRows.B,
    P: learningSettings.availableRows.P,
})

console.log("Hiragana Chart: ", HiraganaChart);
function toggleRow(row) {
    learningSettings.toggleRow(row);
}



watchEffect(() => {
    console.log(learningSettings.availableRows);
})

</script>

<style scoped>
#titleContainer
{
    text-align: center;
}

#chart {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#chart td, #chart th {
  border: 1px solid #ddd;
  padding: 4px;
  background-color: #c5ffea;
}

#chart tr:nth-child(even){background-color: #f2f2f2;}

#chart tr:hover {background-color: #ddd;}

#chart th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}

#chart #noStyleTableCell
{
    background-color: rgba(255, 255, 255, 0);
    border: none;
    color: black;
    text-align: center;

}


</style>