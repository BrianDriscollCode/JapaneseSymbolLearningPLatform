<template>
    <div>
        <button> see account </button>
    </div>
</template>

<script setup>
import { useAccountStore } from '@/stores/account';

const account = useAccountStore();

const seeAccount = () => 
{
    console.log(account.name);
}


</script>