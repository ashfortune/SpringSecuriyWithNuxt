// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-04-03',
  devtools: { enabled: true },
  runtimeConfig:{
    public: {
      apiBase: 'http://localhost:8080/api/v1/',
    }
  },
  // plugins: [
  //   { src: '~/plugins/google-login.client.js', mode: 'client' }
  // ]
  // css:[
  //   '@/assets/css/style.css'
  // ]
})
