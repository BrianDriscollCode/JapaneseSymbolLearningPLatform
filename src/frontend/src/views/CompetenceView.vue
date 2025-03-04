<template>
    <div id="competencePageContainer">
        <div id="kanaPickerContainer">
            <div id="kanaPicker">
                <span class="topButton" id="topLeftButton"> Hiragana </span>
                <span class="topButton" id="topRightButton"> Katakana </span>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
#competencePageContainer
{
    width: 100%;
}

#kanaPickerContainer
{
    display: flex;
    justify-content: center;
    width: 100%;
    margin-top: 1em;
}

#kanaPicker
{
    width: 300px;
    height: 30px;
    border-radius: 1em;
    display: flex;
    justify-content: space-around;
    font-size: 1.25em;
}

.topButton
{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50%;
}

#topLeftButton
{
    background-color: rgb(250, 183, 94);
    border-top-left-radius: 80px 80px;
    border-bottom-left-radius: 80px 80px;
}

#topLeftButton:hover
{
    background-color: rgb(48, 151, 137);
    cursor: pointer;
}

#topRightButton
{
    background-color: rgb(167, 226, 203);
    border-top-right-radius: 80px 80px;
    border-bottom-right-radius: 80px 80px;
}

#topRightButton:hover
{
    background-color: rgb(48, 151, 137);
    cursor: pointer;
}
</style>