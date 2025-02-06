<template>
    <nav :id="navState.startOpen ? 'navOpen' : 'navClosed'">
        <div id="navWrapper">
            <div id="hamburgerContainer">
                <Vue3Lottie
                    ref="lottieAnimation"
                    animationLink="/hamburger.json"
                    id="hamburgerIcon"
                    :auto-play="false"
                    :loop="false"
                    @onComplete="onAnimationComplete"
                    @click="toggleAnimation"
                />
            </div>  

            <RouterLink to="/" class="navLinkContainer">
                <img src="/book.png" class="navLink" width="25" height="25"/> 
                <span class="navLink" v-if="navState.startOpen">Learn</span> 
            </RouterLink>
            
            <RouterLink to="/competence" class="navLinkContainer">
                <img src="/graph.png" class="navLink"width="25" height="25"/>
                <span class="navLink" v-if="navState.startOpen">Competence</span>
            </RouterLink>
            
            <RouterLink to="/login" class="navLinkContainer">
                <img src="/key.png" class="navLink" width="25" height="25" />
                <span class="navLink"v-if="navState.startOpen">Login</span>
            </RouterLink>
        </div>
    </nav>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import { Vue3Lottie } from 'vue3-lottie';
import { ref, reactive, onMounted } from 'vue';

const lottieAnimation = ref(null);

const navState = reactive({
    open: true,
    startOpen: true
});

onMounted(() => {
    if (lottieAnimation.value) {
        lottieAnimation.value.setSpeed(1); // Adjust speed if needed
    }
});

const toggleAnimation = () => {
    console.log("toggle animation run");

    if (!lottieAnimation.value) return;

    if (navState.open) {
        lottieAnimation.value.playSegments([0, 14], true); // Open animation
        navState.startOpen = false;
    } else {
        lottieAnimation.value.playSegments([14, 0], true); // Close animation
        navState.startOpen = true;
    }
};

const onAnimationComplete = () => {
    console.log("Animation complete");
    navState.open = !navState.open; // Flip state only after animation finishes
};
</script>


<style scoped>
#navOpen
{
    background-color: #f2faef;
    width: 170px;
    height: 100vh;
    transition: width 0.3s;
}

#navClosed
{
    background-color: #f2faef;
    width: 75px;
    height: 100vh;
    transition: width 0.3s;
}

#navWrapper
{
    display: flex;
    flex-direction: column;
    padding: 1em;
}

#hamburgerContainer
{
    width: 25px;
    height: 25px;
    margin-bottom: .5em;
}


#hamburgerIcon 
{
    width: 25px;
    height: 25px;
    margin-left: .5em;
}


.navLinkContainer
{
    display: flex;
    align-items: center;
    flex-direction: row;
    text-decoration: none;
    margin-top: .5em;
    border-radius: .5em;
}

.navLinkContainer:hover
{
    background-color: #a7dadc;
}

.navLink
{
    padding: .5em;
    color: black;
}
</style>