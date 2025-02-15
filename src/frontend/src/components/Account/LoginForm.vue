<template>
    <section class="formContainer">
        <!-- <img id="xPNG" :src="xPNG" width="15" height="15"/> -->
        <div class="formWrapper">
            <div id="redirectContainer" v-if="state.redirect">
                <span> Redirecting in... </span> <span> {{ state.seconds }} </span>
            </div>

            <div class="logoContainer">
                <img id="logo" :src="logo" width="100" height="100"/>
            </div>
            
            <h2 id="loginTitle" v-if="!account.status"> Sign in to TomoTalk </h2>    
            <h2 id="loginTitle" v-else> Sign out of TomoTalk </h2>  

            <form @submit.prevent="login" v-if="!account.status">
                
                <div class="formInnerWrapper">
                <div class="orSpacing">
                    <hr />
                </div>

                <input id="emailInput" type="email" placeholder="Email" v-model="loginCredentials.email"/>
                <br />
                <input id="passwordInput" type="password" placeholder="Password"  v-model="loginCredentials.password"/> 
                <button id="loginButton" @click="login"> Login </button> 

                <!-- Leaving sign up off because would require different license for supabase to have unknown accounts to signup -->
                <p> Don't have an account? <RouterLink to="/signup" id="signUpLink"> Sign up </RouterLink> </p>
                
                </div>
                
            </form>

            <form @submit.prevent="logout" v-if="account.status">
                
                <div class="formInnerWrapper">
                <div class="orSpacing">
                    <hr />
                </div>
                <button id="loginButton" @click="logout"> Logout </button> 
                </div>
                
            </form>
        </div>
    </section> 
</template>

<script setup>
import logo from "/japan.png"
import { supabase } from '@/clients/supabase';
import { reactive } from "vue";
import { useAccountStore } from "@/stores/account";
import { useRouter } from "vue-router";

const loginCredentials = reactive({
    email: 'bdriscoll407@gmail.com',
    password: 'iiiiii777777'
});

const state = reactive({
    redirect: false,
    seconds: 3
})

const account = useAccountStore();
const router = useRouter();

const login = async () => 
{
    console.log("Email: " + loginCredentials.email);
    console.log("Password: " + loginCredentials.password);

    const { data, error } = await supabase.auth.signInWithPassword({
        email: loginCredentials.email,
        password: loginCredentials.password
    });

    if (error)
    {
        console.log("error: ", error.message);
    }
    else 
    {
        if (data.user)
        {
            console.log(data.user.id, data.user.user_metadata.first_name);
            account.logIn(data.user.id, data.user.user_metadata.first_name);
            state.redirect = true;
            redirect();
        }
    }
}

const logout = async () =>
{
    const { error } = await supabase.auth.signOut();

	if (error) {
		console.log(error);
	}
	else {
		console.log("Sign out success")
        account.logOut();
	}
}

const redirect = (number = 3) =>
{
    if (number <= 0) {
        router.push("/learn");
        return;
    }
    state.seconds = number;
    setTimeout(() => redirect(number - 1), 1000);
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
padding: 1em;
background-color: rgb(255, 255, 255);
box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1); /* Light shadow */
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
width: 55%;
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

#emailInput
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

#passwordInput
{
box-sizing: border-box;
width: 100%;
height: 50px;
margin-bottom: 2em;
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

#redirectContainer
{
    display: flex;
    justify-content: center;
    font-size: 2em;
}
</style>