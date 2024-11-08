import { defineNuxtPlugin } from '#app'
import vue3GoogleLogin from 'vue3-google-login'

export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.vueApp.use(vue3GoogleLogin, {
        clientId: '739762803537-8g93rg6i8ubsuhfjq78m6ks93e7uvmql.apps.googleusercontent.com'
    });
});