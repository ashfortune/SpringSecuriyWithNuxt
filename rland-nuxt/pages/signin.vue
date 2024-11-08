<template>
  <form action="" @submit.prevent="localSigninHandler">
    <div>
      <label for="">
        사용자 아이디:
        <input v-model="userId" type="text">
      </label>
    </div>
    <div>
      <label for="">
        비밀번호:
        <input v-model="userPwd" type="text">
      </label>
    </div>
    <div>
      <input type="submit" value="로그인" class="n-btn n-btn-color:sub">
    </div>
<!--    <div>-->
<!--      <a @click.prevent="googleLoginHandler" class="margin-top:1 n-btn n-btn-color:main">구글 로그인</a>-->
<!--    </div>-->
<!--    <div>-->
<!--      <GoogleLogin :callback="callback"/>-->
<!--    </div>-->
  </form>
  <!--  <GoogleLogin :clientId="googleClientId"/>-->
</template>

<script setup>
import {decodeCredential, googleTokenLogin} from "vue3-google-login";
import {jwtDecode} from "jwt-decode";
import {useRoute} from "vue-router";
// import useDataFetch from "~/composables/useDataFetch.js";
// import axios from "axios";

const userDetails = useUserDetails();
const route = useRoute();
const userId = ref("");
const userPwd = ref("");

const localSigninHandler = async () => {
  console.log("로그인버튼 작동중");

  let response = await useDataFetch("auth/signin",{
    method: "POST",
    contentType: "application/json",
    body:{
      userId: userId.value,
      userPwd: userPwd.value
    }
  });
  let userInfo = jwtDecode(response.token)
  userDetails.login({
    id: userInfo.id,
    username: userInfo.username,
    email: userInfo.email,
    token: response.token,
    roles: userInfo.roles.map(role=>role.authority),
  });
  console.log(userInfo)

  const returnURL = route.query.returnURL || "/";
  console.log(returnURL);

  return navigateTo(returnURL);
}

// const googleLoginHandler = async () => {
//   let token;
//   {
//     let response = await googleTokenLogin();
//     console.log(response);
//     token = response.access_token;
//   }
//
//   {
//     let response = await fetch(`https://www.googleapis.com/oauth2/v3/userinfo?access_token=${token}`);
//     let userInfo = await response.json()
//
//     console.log(userInfo);
//   }
// }

// const callback = (response) => {
//   // This callback will be triggered when the user selects or login to
//   // his Google account from the popup
//
//   let user = decodeCredential(response.credential);
//
//
//   // auth.login(
//   //     user.name,
//   //     user.email, ['member','admin']
//   // );
//   const userDetails = useUserDetails();
//
//   userDetails.login({
//     id: 1,
//     username: user.name,
//     email: user.email,
//     roles: ['member', 'admin'],
//   })
//
//   console.log("userDetail:", user.name, user.email);
//
//   const returnURL = useRoute().query.returnURL || "/";
//   return navigateTo(returnURL);
// }

// const googleClientId = "739762803537-8g93rg6i8ubsuhfjq78m6ks93e7uvmql.apps.googleusercontent.com";
</script>