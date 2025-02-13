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

const initSession = async (retries = 3, delay = 2000) => 
{
  try 
  {
    console.log("run init session");

    const { data: { session }, error } = await supabase.auth.getSession();

    if (error) 
    {
      throw new Error("Error getting session: " + error.message);
    }

    if (!session || !session.user) 
    {
      console.log("No active session found.");
      account.logOut();
      return;
    }

    console.log("Found session:");
    //console.log(session);

    const { id } = session.user;

    const response = await fetch(`/api/account/getUser/${id}`);

    if (!response.ok) 
    {
      throw new Error(`Failed to fetch user data: ${response.statusText}`);
    }

    const userData = await response.json();
    account.logIn(userData.uuid, userData.name);
  } 
  catch (error) 
  {
    console.error("Error in initSession:", error);

    if (retries > 0) 
    {
      console.log(`Retrying in ${delay / 1000} seconds... (${retries} retries left)`);
      setTimeout(() => initSession(retries - 1, delay), delay);
    } 
    else 
    {
      console.error("All retries failed. Showing error message to user.");
      showErrorMessage("Unable to connect to the server. Please try again later.");
    }
  }
};


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
