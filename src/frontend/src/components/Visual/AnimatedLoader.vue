<template>
    <div class="loader">
        <div class="circle"></div>
        <div class="circle"></div>
        <div class="circle"></div>
        <div class="circle"></div>
        <div class="circle"></div>
        <div class="circle"></div>
    </div>
</template>

<script setup>

</script>

<style scoped>

.loader 
{
    position: relative;
    width: 80px;
    height: 80px;
}
.circle {
    position: absolute;
    width: 15px;
    height: 15px;
    background-color: rgb(52, 52, 52);
    border-radius: 50%;
    animation: pulse 1.2s infinite ease-in-out;
}
@keyframes pulse {
    0%, 100% { transform: scale(1); opacity: 0.3; }
    50% { transform: scale(1.5); opacity: 1; }
}
.circle:nth-child(1) { top: 0; left: 50%; animation-delay: 0s; }
.circle:nth-child(2) { top: 20%; left: 85%; animation-delay: 0.2s; }
.circle:nth-child(3) { top: 60%; left: 85%; animation-delay: 0.4s; }
.circle:nth-child(4) { bottom: 0; left: 50%; animation-delay: 0.6s; }
.circle:nth-child(5) { top: 60%; left: 15%; animation-delay: 0.8s; }
.circle:nth-child(6) { top: 20%; left: 15%; animation-delay: 1s; }</style>