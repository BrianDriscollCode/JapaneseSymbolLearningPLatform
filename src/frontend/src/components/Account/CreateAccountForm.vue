<template>
    <section class="formContainer">
        <!-- <img id="xPNG" :src="xPNG" width="15" height="15"/> -->
        <div class="formWrapper">
            <div class="logoContainer">
                <img src="/icons8_cat.gif" alt="Animated GIF" class="title-gif" />
            </div>
            
            <h2 id="loginTitle"> Sign up to TomoTalk </h2>    

            <form @submit.prevent="login">
                <div class="formInnerWrapper">
                <div class="orSpacing">
                    <hr />
                </div>

                <input class="signUpInput" type="email" placeholder="Email" v-model="loginCredentials.email"/>
                <br />

                <input class="signUpInput" type="password" placeholder="Password"  v-model="loginCredentials.password"/> 
                <br />

                <input class="signUpInput" type="name" placeholder="Name"  v-model="loginCredentials.name"/> 

                <button id="loginButton" @click="login"> Create Account </button> 
                
                </div>
                
            </form>
        </div>
    </section> 
</template>

<script setup>

import { supabase } from '@/clients/supabase';
import { reactive } from "vue";

const loginCredentials = reactive({
    email: '',
    password: '',
    name: ''
});

const login = async () => 
{
    const { data, error } = await supabase.auth.signUp({
        email: loginCredentials.email,
        password: loginCredentials.password
    });
    
    if (error)
    {
        console.log("Authentication Error:" + error.message || error);
        return;
    }
    else 
    {
        console.log("Signup Data:", data);
        console.log("Signup Error:", error);


        if (data.user)
        {

            let submissions = 0;
            let isSubmitted = false;

            while (submissions < 1 && !isSubmitted)
            {
                try 
                {
                    const response = await fetch("/api/account/create", {
                        method: "POST",
                        headers: 
                        {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            uuid: data.user.id,
                            email: loginCredentials.email,
                            name: loginCredentials.name
                        })
                    });

                    const result = await response.text();

                    if (response.ok) 
                    {
                        console.log("User created successfully:", result);
                        console.log(response);
                        isSubmitted = true;
                    } 
                    else 
                    {
                        console.log("Failed to create user:", result);
                        console.log(response);
                    }

                    submissions += 1;
                }
                catch (error)
                {
                    console.log("User Table error: " + error);
                    submissions += 1;
                }
            }
            
        }
    }
}
</script>

<style scoped>
.error
{

color: red;
}

.formContainer 
{
display: flex;
flex-direction: column;
align-items: center;
padding: 1em;
background-color: rgb(255, 255, 255);
width: 500px;
margin-top: auto;
margin-bottom: auto;
margin-left: auto;
margin-right: auto;
border-radius: .5em;
color:#1d3568;
}


.formWrapper
{
    margin-left: auto;
    margin-right: auto;
}

.logoContainer
{
display: flex;
justify-content: center;
margin: 1em 0 2em 0;
}

#loginTitle 
{
padding: 0;
margin: 0 0 1em 0;
}

.formInnerWrapper
{
justify-content: center;
flex-direction: column;
}
.orSpacing 
{

margin: 0 0 1em 0;
}

.signUpInput
{
box-sizing: border-box;
width: 100%;
height: 50px;
margin-bottom: 1em;
padding: 0 0 0 1em;
margin: 0 0 1em 0;
border: 1px solid #ccc;
font-size: 1em;
}

#loginButton
{
    width: 100%;
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

#signUpLink
{
color: #e63746;
}

#signUpLink:hover
{
color: #0a21c0;
}

#signUpLink:active
{
color: #828dd4;
}
</style>