<template>
    <div id="loginPageContainer">
        <LoginForm />
    </div>
</template>

<script setup>
import LoginForm from '@/components/Account/LoginForm.vue';

</script>

<style scoped>
#loginPageContainer
{
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>