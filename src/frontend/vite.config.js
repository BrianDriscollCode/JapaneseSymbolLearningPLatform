import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig(({ mode }) => {
  console.log(`Running in mode: ${mode}`); // Debugging output

  return {
    plugins: [
      vue(),
      vueDevTools(),
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      },
    },
    server: {
      port: 3000,
      proxy: {
        '/api': {
          target: mode === 'development' ? 'http://localhost:8080' : process.env.VITE_API_URL,
          changeOrigin: true,
          secure: false,
        },
      },
    },
  };
});

