<template>
    <div>
        <table id="chart">
            <thead>
                <th>  </th>
                <th> a </th> 
                <th> i </th>
                <th> u </th>
                <th> e </th>
                <th> o </th>
            </thead>
            <tbody v-for="item in KatakanaChart.rows">
                <tr>
                    <td> {{ item.row.toLowerCase() }}</td>
                    
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
                </tr>
            </tbody>

        </table>
    </div>
</template>

<script setup>
import KatakanaChart from "@/components/Charts/KatakanaChart.json"

console.log("KatakanaChart: ", KatakanaChart);

</script>

<style scoped>
#chart {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#chart td, #chart th {
  border: 1px solid #ddd;
  padding: 4px;
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
</style>