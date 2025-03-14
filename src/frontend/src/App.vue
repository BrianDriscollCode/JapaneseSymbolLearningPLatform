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
import { useLearnSettingsStore } from '@/stores/LearnSettings';

const route = useRoute();
const navigablePage = ref(false);
const account = useAccountStore();
const learnSettings = useLearnSettingsStore();

// Remove NavBar from included pages
watchEffect(() => {
  navigablePage.value = !["signup"].includes(route.name);
});

// Automatically find session if available with retry
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

// Check that there is at least 1 valid row
const initializeKanaStore = () =>
{
    learnSettings.checkForActiveRow();
}

onMounted(() => {
  initSession();
  initializeKanaStore();
})

</script>

<style>
#app
{
  height: 100%;
  overflow: hidden;
}

html
{
  padding: 0;
  margin: 0;
  font-family: "Gill Sans", sans-serif;
  height: 100%;
  overflow: hidden;
}

body
{
  padding: 0;
  margin: 0;
  background-color: #f2faef;
  height: 100%;
  overflow: hidden;
}

#appContainer
{
  display: flex;
  flex-direction: row;
  height: 100%;
  overflow: hidden;
}
</style>
