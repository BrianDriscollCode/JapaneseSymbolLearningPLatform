import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

import { createVuetify } from 'vuetify';
import 'vuetify/styles'; // Import Vuetify styles
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@fortawesome/fontawesome-free/css/all.css'
import '@mdi/font/css/materialdesignicons.css'
import { mdi } from 'vuetify/iconsets/mdi' // Import MDI icons

const app = createApp(App)
const vuetify = createVuetify({
    components,
    directives,
    icons: {
      defaultSet: 'mdi', // Set default to MDI
      sets: { mdi }, // Register MDI icons
    },
  })

app.use(createPinia())
app.use(router)
app.use(vuetify)


app.mount('#app')
