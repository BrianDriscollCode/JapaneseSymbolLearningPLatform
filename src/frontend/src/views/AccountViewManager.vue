<template>
    <div>
        <button @click="getAccount"> get account </button>
    </div>
</template>

<script setup>
import { useAccountStore } from '@/stores/account';

const account = useAccountStore();

const getAccount = async () => 
{
    console.log(account.name, account.uuid);

    const data = await fetch(`/api/account/getUser/${account.uuid}`)
        .then(response => response.json())
        .then(data => {
            console.log("Data", data);
        })

}


</script>