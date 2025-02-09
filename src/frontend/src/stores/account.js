import { defineStore } from "pinia";

export const useAccountStore = defineStore('account', {
    state: () => {
        return { 
            status: false,
            name: '',
            // Either get item or empty
            uuid: localStorage.getItem('uuid') || '',  
            isAdmin: false
        }
    },

    actions: {
        // Processes for login and logout to hydrate data for store.
        // Also uses localStorage for refresh persistence.
        // APP level will always use SupaBase, but platform level will use store
        logOut() {
            this.status = false;
            this.uuid = '';  
            this.name = '';
            localStorage.removeItem('uuid'); 
            this.isAdmin = false;
        },

        logIn(uuid, name) {
            this.status = true;
            this.name = name;
            this.uuid = uuid; 
            localStorage.setItem('uuid', uuid); 
            console.log(name + " is logged in");
        }
    }
});