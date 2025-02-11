<template>
  <div id="appContainer">
    <WebNavigation v-show="navigablePage"/>
    <RouterView />
  </div>
</template>


<script setup>
import { ref, watchEffect, onMounted } from 'vue';
import { RouterView, useRoute } from 'vue-router'
import WebNavigation from './components/WebNavigation.vue';
import { supabase } from './clients/supabase';
import { useAccountStore } from './stores/account';

const route = useRoute();
const navigablePage = ref(false);
const account = useAccountStore();

watchEffect(() => {
  navigablePage.value = ["home", "learn", "login", "accountViewManager"].includes(route.name);
});

const initSession = async () => 
{
  console.log("run init session");
  const  { data: {session}, error } = await supabase.auth.getSession();

  if (session)
  {
    const data = await fetch(`/api/account/getUser/${session.user.id}`);
    const res = await data.json();
    
    console.log(res);

    account.logIn(session.user.id);
  }
}

onMounted(() => {
  initSession();
})

</script>

<style>
#app
{
  height: 100%;
}

html
{
  padding: 0;
  margin: 0;
  font-family: "Gill Sans", sans-serif;
  height: 100%;
}

body
{
  padding: 0;
  margin: 0;
  background-color: #f2faef;
  height: 100%;
}

#appContainer
{
  display: flex;
  flex-direction: row;
  height: 100%;
}
</style>
