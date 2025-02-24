<template>
    <div>
        <div id="titleContainer">
            <h2> Hiragana </h2>
        </div>

        <div id="tableContainer">
            <table id="chart">
            <tbody>
                <tr>
                    <th id="noStyleTableCell">  </th>
                    <th id="noStyleTableCell">  </th>
                    <th id="noStyleTableCell"> a </th> 
                    <th id="noStyleTableCell"> i </th>
                    <th id="noStyleTableCell"> u </th>
                    <th id="noStyleTableCell"> e </th>
                    <th id="noStyleTableCell"> o </th>
                </tr>
            </tbody>
       
            <tbody v-for="item in HiraganaChart.rows">
                <tr v-if="isValidRow(item.row)">

                    <!-- row check box -->
                    <td id="noStyleTableCell"> 
                        <!-- Bind v-model to the selected row value -->
                        <input
                            type="checkbox"
                            :value="item.row"
                            v-model="myData[item.row]"
                            @change="toggleRow(item.row)"
                        />
                    </td>

                    <!-- Row Letter -->
                    <td id="noStyleTableCell"> {{ item.row.toLowerCase() }}</td>
                    
                    <!-- Columns: 'a' 'i' 'u' 'e' 'o' -->
                    <!-- list of characters - logic prints the object data with exceptions for 'u' and 'o' columns -->

                    <!-- a -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[0]?.character"> {{ item.characters[0].column == 'a' ? item.characters[0].character : ' ' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>

                    <!-- i -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[1]?.character"> {{ item.characters[1].column == 'i' ? item.characters[1].character : ' ' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>

                    <!-- u -->
                    <!-- For exceptions of 'u' column -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[2]?.character"> {{ item.characters[2].column == 'u' ? item.characters[2].character : item.characters[1].character }} </td> 
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>

                    <!-- e -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[3]?.character"> {{ item.characters[3].column == 'e' ? item.characters[3].character : ' ' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>
                    
                    <!-- o -->
                    <!-- For exceptions of 'o' column -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[4]?.character"> {{ item.characters[4].column == 'o' ? item.characters[4].character : 'f' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else-if="item.characters[2]?.character"> {{ item.characters[2].character }}</td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else-if="item.characters[1]?.character"> {{ item.characters[1].character }}</td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>
                </tr>
            </tbody>

        </table>

        <table id="chart">
            <tbody>
                <tr>
                    <th id="noStyleTableCell">  </th>
                    <th id="noStyleTableCell">  </th>
                    <th id="noStyleTableCell"> a </th> 
                    <th id="noStyleTableCell"> i </th>
                    <th id="noStyleTableCell"> u </th>
                    <th id="noStyleTableCell"> e </th>
                    <th id="noStyleTableCell"> o </th>
                </tr>
            </tbody>
       
            <tbody v-for="item in HiraganaChart.rows">
                <tr v-if="!isValidRow(item.row)">

                    <!-- row check box -->
                    <td id="noStyleTableCell"> 
                        <!-- Bind v-model to the selected row value -->
                        <input
                            type="checkbox"
                            :value="item.row"
                            v-model="myData[item.row]"
                            @change="toggleRow(item.row)"
                        />
                    </td>

                    <!-- Row Letter -->
                    <td id="noStyleTableCell"> {{ item.row.toLowerCase() }}</td>
                    
                    <!-- Columns: 'a' 'i' 'u' 'e' 'o' -->
                    <!-- list of characters - logic prints the object data with exceptions for 'u' and 'o' columns -->

                    <!-- a -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[0]?.character"> {{ item.characters[0].column == 'a' ? item.characters[0].character : ' ' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>

                    <!-- i -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[1]?.character"> {{ item.characters[1].column == 'i' ? item.characters[1].character : ' ' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>

                    <!-- u -->
                    <!-- For exceptions of 'u' column -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[2]?.character"> {{ item.characters[2].column == 'u' ? item.characters[2].character : item.characters[1].character }} </td> 
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>

                    <!-- e -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[3]?.character"> {{ item.characters[3].column == 'e' ? item.characters[3].character : ' ' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>
                    
                    <!-- o -->
                    <!-- For exceptions of 'o' column -->
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-if="item.characters?.[4]?.character"> {{ item.characters[4].column == 'o' ? item.characters[4].character : 'f' }} </td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else-if="item.characters[2]?.character"> {{ item.characters[2].character }}</td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else-if="item.characters[1]?.character"> {{ item.characters[1].character }}</td>
                    <td :id="[myData[item.row] ? 'cellFilled': 'cellRow']" v-else> </td>
                </tr>
            </tbody>

        </table>

        </div>
        
    </div>
</template>

<script setup>
import HiraganaChart from "@/components/Charts/HiraganaChart.json";
import { reactive, watchEffect, computed } from "vue";
import { useLearnSettingsStore } from "@/stores/LearnSettings";

const learningSettings = useLearnSettingsStore();

const random = reactive({
    selectedRow: null,
});

const myData = reactive({
    A: learningSettings.availableHiraganaRows.A,
    K: learningSettings.availableHiraganaRows.K,
    S: learningSettings.availableHiraganaRows.S,
    T: learningSettings.availableHiraganaRows.T,
    N: learningSettings.availableHiraganaRows.N,
    H: learningSettings.availableHiraganaRows.H,
    M: learningSettings.availableHiraganaRows.M,
    Y: learningSettings.availableHiraganaRows.Y,
    R: learningSettings.availableHiraganaRows.R,
    W: learningSettings.availableHiraganaRows.W,
    NN: learningSettings.availableHiraganaRows.NN,
    G: learningSettings.availableHiraganaRows.G,
    Z: learningSettings.availableHiraganaRows.Z,
    D: learningSettings.availableHiraganaRows.D,
    B: learningSettings.availableHiraganaRows.B,
    P: learningSettings.availableHiraganaRows.P,
});


console.log("Hiragana Chart: ", HiraganaChart);
function toggleRow(row) {
    learningSettings.toggleHiraganaRow(row);
}

const isValidRow = (row) => !['G', 'Z', 'D', 'B', 'P'].includes(row);

watchEffect(() => {
    console.log(learningSettings.availableHiraganaRows);
});


</script>

<style scoped>
#titleContainer
{
    text-align: center;
}

#tableContainer
{
    display: flex;
    flex-direction: row;
}

#chart {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
  height:0px;
}

#cellRow
{
    background-color: #c5ffea;
}

#cellFilled
{
    background-color: #ffc251; 
}

#chart td, #chart th {
  border: 1px solid #ddd;
  padding: 4px;
  /* background-color: #c5ffea; */
}

#chart tr:nth-child(even){background-color: #f2f2f2;}


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