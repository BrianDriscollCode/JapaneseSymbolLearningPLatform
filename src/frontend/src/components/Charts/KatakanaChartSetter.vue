<template>
    <div>
        <div id="titleContainer">
            <h2> Katakana </h2>
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
       
            <tbody v-for="item in KatakanaChart.rows">
                <tr v-if="isValidRow(item.row)">

                    <!-- row check box -->
                    <td id="noStyleTableCell"> 
                        <!-- Bind v-model to the selected row value -->
                        <input
                            type="checkbox"
                            :value="item.row"
                            v-model="myData[item.row]"
                            @change="toggleRow(item.row)"
                            :disabled="isPaidRow(item.row) && !account.subscription || !account.status" 
                        />
                    </td>

                    <!-- Row Letter -->
                    <td id="noStyleTableCell"> {{ item.row.toLowerCase() }}</td>
                    
                    <!-- Columns: 'a' 'i' 'u' 'e' 'o' -->
                    <!-- list of characters - logic prints the object data with exceptions for 'u' and 'o' columns -->

                    <!-- a -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[0]?.character"> {{ item.characters[0].column == 'a' ? item.characters[0].character : ' ' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>

                    <!-- i -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[1]?.character"> {{ item.characters[1].column == 'i' ? item.characters[1].character : ' ' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>

                    <!-- u -->
                    <!-- For exceptions of 'u' column -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[2]?.character"> {{ item.characters[2].column == 'u' ? item.characters[2].character : item.characters[1].character }} </td> 
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>

                    <!-- e -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[3]?.character"> {{ item.characters[3].column == 'e' ? item.characters[3].character : ' ' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>
                    
                    <!-- o -->
                    <!-- For exceptions of 'o' column -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[4]?.character"> {{ item.characters[4].column == 'o' ? item.characters[4].character : 'f' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else-if="item.characters[2]?.character"> {{ item.characters[2].character }}</td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else-if="item.characters[1]?.character"> {{ item.characters[1].character }}</td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>
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
       
            <tbody v-for="item in KatakanaChart.rows">
                <tr v-if="!isValidRow(item.row)">

                    <!-- row check box -->
                    <td id="noStyleTableCell"> 
                        <!-- Bind v-model to the selected row value -->
                        <input
                            type="checkbox"
                            :value="item.row"
                            v-model="myData[item.row]"
                            @change="toggleRow(item.row)"
                            :disabled="isPaidRow(item.row) && !account.subscription || !account.status" 
                        />
                    </td>

                    <!-- Row Letter -->
                    <td id="noStyleTableCell"> {{ item.row.toLowerCase() }}</td>
                    
                    <!-- Columns: 'a' 'i' 'u' 'e' 'o' -->
                    <!-- list of characters - logic prints the object data with exceptions for 'u' and 'o' columns -->

                    <!-- a -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[0]?.character"> {{ item.characters[0].column == 'a' ? item.characters[0].character : ' ' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>

                    <!-- i -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[1]?.character"> {{ item.characters[1].column == 'i' ? item.characters[1].character : ' ' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>

                    <!-- u -->
                    <!-- For exceptions of 'u' column -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[2]?.character"> {{ item.characters[2].column == 'u' ? item.characters[2].character : item.characters[1].character }} </td> 
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>

                    <!-- e -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[3]?.character"> {{ item.characters[3].column == 'e' ? item.characters[3].character : ' ' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>
                    
                    <!-- o -->
                    <!-- For exceptions of 'o' column -->
                    <td :id="getCellStyle(myData[item.row], item.row)" v-if="item.characters?.[4]?.character"> {{ item.characters[4].column == 'o' ? item.characters[4].character : 'f' }} </td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else-if="item.characters[2]?.character"> {{ item.characters[2].character }}</td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else-if="item.characters[1]?.character"> {{ item.characters[1].character }}</td>
                    <td :id="getCellStyle(myData[item.row], item.row)" v-else> </td>
                </tr>
            </tbody>

        </table>

        </div>
        <p v-if="!account.status"> Log in to unlock more characters </p>
        <p v-else-if="account.status && !account.subscription"> To unlock more characters requires a subscription. </p>
    </div>
</template>

<script setup>
import KatakanaChart from "@/components/Charts/KatakanaChart.json";
import { reactive, watchEffect } from "vue";
import { useLearnSettingsStore } from "@/stores/LearnSettings";
import { useAccountStore } from "@/stores/account";

const learningSettings = useLearnSettingsStore();
const account = useAccountStore();

const random = reactive({
    selectedRow: null,
});

const myData = reactive({
    A: learningSettings.availableKatakanaRows.A,
    K: learningSettings.availableKatakanaRows.K,
    S: learningSettings.availableKatakanaRows.S,
    T: learningSettings.availableKatakanaRows.T,
    N: learningSettings.availableKatakanaRows.N,
    H: learningSettings.availableKatakanaRows.H,
    M: learningSettings.availableKatakanaRows.M,
    Y: learningSettings.availableKatakanaRows.Y,
    R: learningSettings.availableKatakanaRows.R,
    W: learningSettings.availableKatakanaRows.W,
    NN: learningSettings.availableKatakanaRows.NN,
    G: learningSettings.availableKatakanaRows.G,
    Z: learningSettings.availableKatakanaRows.Z,
    D: learningSettings.availableKatakanaRows.D,
    B: learningSettings.availableKatakanaRows.B,
    P: learningSettings.availableKatakanaRows.P,
});


console.log("Katakana Chart: ", KatakanaChart);
function toggleRow(row) {
    learningSettings.toggleKatakanaRow(row);
}

const isValidRow = (row) => !['G', 'Z', 'D', 'B', 'P'].includes(row);

// greys out rows that only paid customers can use
const isPaidRow = (row) => ['N', 'T', 'H','M', 'Y', 'R', 'W', 'NN', 'G', 'Z', 'D', 'B', 'P'].includes(row);

const getCellStyle = (status, row) => 
{
    console.log("getCellStyle: ", status, row);

    const viableRow = isPaidRow(row);

    if (!account.status || viableRow && !account.subscription)
    {
        return 'cellOff'
    }

    if (status)
    {
        return 'cellFilled';
    }
    else
    {
        return 'cellRow';
    }
}

watchEffect(() => {
    console.log(learningSettings.availableKatakanaRows);
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
  height: 0;
}

#cellRow
{
    background-color: #c5ffea;
}

#cellFilled
{
    background-color: #ffc251; 
}

#cellOff
{
    background-color: #929292; 
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