<template>
  <v-row justify="center">
    <v-dialog v-model="loginForm" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span id="title">로그인</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="ID"
                  id="userid"
                  v-model="user.userid"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="Password"
                  type="password"
                  id="userpw"
                  v-model="user.userpw"
                  required
                ></v-text-field>

                <v-alert dense outlined type="error" v-if="loginResult">
                  {{ loginResult }}
                </v-alert>
              </v-col>

              <v-col cols="12" sm="6" md="4">
                <v-btn text small color="teal" @click="showPw()">
                  비밀번호 찾기
                </v-btn>
              </v-col>
              <v-col cols="12" sm="6" md="3">
                <v-btn text small color="teal" @click="showJoin()"
                  >회원가입</v-btn
                >
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="darkgrey" text outlined @click="closeLogin">
            Close
          </v-btn>
          <v-btn color="blue darken-1" outlined @click="login"> Login </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <pw-comp :pwFormData="pwForm" @closePw="closePw" />
  </v-row>
</template>

<script>
import PwComp from "./PwComp.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "LoginComp",
  components: {
    PwComp,
  },
  data() {
    return {
      loginForm: false,
      pwForm: false,
      user: {
        userid: null,
        userpw: null,
      },
      loginResult: "",
    };
  },
  props: ["loginFormData"],
  watch: {
    loginFormData() {
      //console.log("변경감지");
      this.loginForm = this.loginFormData;
    },
  },
  computed: {
    ...mapState(["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(["userConfirm", "getUserInfo", "getAttentionInfo"]),
    async login() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        // console.log("1 :", this.isLogin);
        // console.log("2 :", this.isLoginError);
        this.loginResult = "";
        await this.getUserInfo(token);
        await this.getAttentionInfo(token);
        this.closeLogin();
        if (this.$route.path != "/") this.$router.push({ name: "HomeComp" });
      } else {
        this.loginResult = "ID나 Password가 일치하지않습니다!";
        // console.log("3 : ", this.isLogin);
        // console.log("4 :", this.isLoginError);
      }
    },
    closeLogin() {
      this.user.userid = null;
      this.user.userpw = null;
      this.loginResult = null;
      this.loginForm = false;
      this.$emit("closeLogin");
    },
    showPw() {
      this.pwForm = true;
      this.closeLogin();
    },
    closePw(data) {
      if (data === "gologin") {
        this.pwForm = false;
        this.loginForm = true;
      } else {
        this.pwForm = false;
      }
    },
    showJoin() {
      this.closeLogin();
      this.$router.push({ name: "JoinComp" }).catch(() => {});
    },
  },
  created() {
    //console.log(this.loginFormData);
    this.loginForm = this.loginFormData;
  },
};
</script>

<style scope>
#title {
  font-family: "Alegreya Sans SC", sans-serif;
  font-family: "Gaegu", cursive;
  font-family: "Jua", sans-serif;
  font-size: 30px;
}
</style>
