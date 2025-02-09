<template>
    <div id="loginPageContainer">
        <div id="imageContainer">
            <div class="overlay">
                <div id="titleContainer">
                    <h1> Your Path to Reading Japanese </h1>
                </div>
            </div>
        </div>
        <CreateAccountForm />
    </div>
</template>

<script setup>
import CreateAccountForm from '@/components/Account/CreateAccountForm.vue';

</script>

<style scoped>
#loginPageContainer
{
    height: 100%;
}

#imageContainer {
    width: 55%;
    height: 100%;
    background: url('/bridge_japan.jpg') center/cover no-repeat;
    position: relative;
}

/* Semi-transparent black overlay */
.overlay {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.7); /* 50% see-through black */
    display: flex;
    justify-content: center;
    align-items: center;

}

#loginPageContainer
{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
}

#titleContainer
{
    text-align: center;
    width: 80%;
    background-color: rgba(0, 0, 0, 0.384);
    border: 2px solid antiquewhite;
    color: white;
}
</style>