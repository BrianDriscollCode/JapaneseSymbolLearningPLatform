<template>
    <div>
        <button @click="getAccount"> get account </button>
    </div>
</template>

<script setup>
import { useAccountStore } from '@/stores/account';

const account = useAccountStore();

const uuid = "53c9edbc-49be-49ee-8293-d4c1ad2daa39";

const getAccount = async () => 
{
    console.log(account.name, account.uuid);

    const data = await fetch(`/api/account/getUser/${uuid}`)
        .then(response => response.json())
        .then(data => {
            console.log("Data", data);
        })

}


</script>