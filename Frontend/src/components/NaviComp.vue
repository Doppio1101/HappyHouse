<template>
  <v-card>
    <v-toolbar flat>
      <img src="https://img.icons8.com/material/48/000000/cottage--v1.png" />

      <v-toolbar-title style="font-size: 35px; font-weight: bold">
        Happy House</v-toolbar-title
      >

      <v-spacer>
        <login-comp :loginFormData="loginForm" @closeLogin="closeLogin" />
      </v-spacer>

      <v-avatar color="grey" v-if="!userInfo">
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon large color="white"> mdi-account-circle </v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item link @click="showLogin()">
              <v-list-item-title>로그인</v-list-item-title>
            </v-list-item>

            <v-list-item link :to="{ name: 'JoinComp' }">
              <v-list-item-title>회원가입</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-avatar>
      <v-avatar color="indigo accent-3" v-if="userInfo">
        <v-menu offset-y>
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon large color="white"> mdi-account-circle </v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item>
              <v-list-item-title>
                {{ userInfo.username }}님:)
              </v-list-item-title>
            </v-list-item>

            <v-list-item @click="showMypage()">
              <v-list-item-title>내 정보</v-list-item-title>
            </v-list-item>

            <v-list-item @click="logout()">
              <v-list-item-title>로그아웃</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-avatar>

      <template v-slot:extension>
        <v-tabs v-model="tabs" fixed-tabs>
          <v-tabs-slider></v-tabs-slider>

          <v-tab
            class="primary--text"
            style="font-size: 20px"
            :to="{ name: 'HomeComp' }"
          >
            홈
            <v-icon>mdi-home</v-icon>
          </v-tab>

          <v-tab
            class="primary--text"
            style="font-size: 20px"
            :to="{ name: 'MapComp' }"
          >
            지도
            <v-icon>mdi-map-marker</v-icon>
          </v-tab>

          <v-tab
            class="primary--text"
            style="font-size: 20px"
            :to="{ name: 'List' }"
          >
            질의응답
            <v-icon>mdi-chat-question </v-icon>
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>
  </v-card>
</template>

<script>
import LoginComp from "./member/LoginComp.vue";
import { mapState, mapMutations } from "vuex";

export default {
  name: "Navibar",
  components: {
    LoginComp,
  },
  data() {
    return {
      tabs: null,
      loginForm: false,
    };
  },
  methods: {
    ...mapMutations(["SET_IS_LOGIN", "SET_USER_INFO", "SET_ATTENTION_INFO"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      this.SET_ATTENTION_INFO(null);
      sessionStorage.removeItem("access-token");
      alert("로그아웃되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "HomeComp" });
    },
    showMypage() {
      this.$router.push({ name: "MypageComp" });
    },
    showLogin() {
      this.loginForm = true;
    },
    closeLogin() {
      this.loginForm = false;
    },
  },
  computed: {
    ...mapState(["isLogin", "userInfo"]),
  },
};
</script>

<style></style>
