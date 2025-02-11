<template>
    <div id="pageContainer">
        <h1> Confirm Email </h1>
        <p> Go to your email and confirm it is you! </p>
        <button id="goHomeButton" @click="goHome"> Go back to home </button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goHome = () => 
{
    router.push("/");
}
</script>

<style scoped>
h1
{
    padding: 0;
    margin: 0;
}

#goHomeButton
{
    width: 150px;
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
    margin-bottom: 1em;

}

#pageContainer
{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 100%;
    width: 100%;
}
</style>