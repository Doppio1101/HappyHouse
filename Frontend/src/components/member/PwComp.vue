<template>
  <v-row justify="center">
    <v-dialog v-model="pwForm" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span id="title">비밀번호 찾기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="ID"
                  v-model="member.userid"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="9" md="8">
                <v-text-field
                  label="E-mail"
                  v-model="member.email"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="3" md="3">
                <v-btn @click="chkMember"
                  >인증번호 받기
                  <v-overlay btn :value="overlay" :absolute="absolute">
                    <v-progress-circular
                      indeterminate
                      size="30"
                    ></v-progress-circular>
                  </v-overlay>
                </v-btn>
              </v-col>
              <template v-if="resultKey != 'SUCCESS'">
                <v-col cols="12" sm="9" md="8">
                  <v-text-field
                    v-model="member.authKey"
                    label="authKey"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="3" md="3">
                  <v-btn @click="checkAuth">확인</v-btn>
                </v-col>
              </template>
              <template v-if="resultKey == 'SUCCESS'">
                <v-col cols="12" sm="9" md="8">
                  <v-text-field
                    v-model="member.authKey"
                    label="인증번호"
                    required
                    disabled
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="3" md="3">
                  <v-btn color="green">인증 성공</v-btn>
                </v-col>
              </template>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="darkgrey" text outlined @click="closePw"> Close </v-btn>
          <v-btn color="blue darken-1" outlined @click="showNpw">
            Go Login
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <npw-comp
      :npwFormData="npwForm"
      :userId="member.send"
      @closeNpw="closeNpw"
    />
  </v-row>
</template>

<script>
import { localhost } from "@/js/common.js";
import NpwComp from "./NpwComp.vue";
export default {
  name: "PwComp",
  components: {
    NpwComp,
  },
  data() {
    return {
      pwForm: false,
      npwForm: false,
      member: {
        send: "",
        userid: "",
        email: "",
        authKey: "",
      },
      idChk: false,
      resultKey: "",
      overlay: false,
      absolute: true,
    };
  },
  props: ["pwFormData"],
  watch: {
    pwFormData() {
      this.pwForm = this.pwFormData;
    },
  },
  computed: {},
  methods: {
    async chkMember() {
      let msg = "";
      let err = true;

      if (!this.member.userid) {
        msg = "ID를 입력해주세요";
        err = false;
      } else if (!this.member.email) {
        msg = "E-mail를 입력해주세요";
        err = false;
      }

      if (!err) alert(msg);
      else {
        this.overlay = true;
        await localhost({
          method: "get",
          url: "/member/idchk/" + this.member.userid,
        }).then((data) => {
          this.idChk = data.data;
        });

        if (this.idChk === "") {
          await localhost({
            method: "post",
            url: "/email/mailConfirm/" + this.member.email,
          }).then((data) => {
            this.resultKey = data.data;
          });
          this.member.send = this.member.userid;
          alert("인증번호를 발송했습니다");
        } else {
          alert("회원이 아닙니다. 아이디를 다시 확인해주세요.");
        }
        this.overlay = false;
      }
    },
    checkAuth() {
      let msg = "";
      if (!this.member.authKey) {
        msg = "인증번호를 입력해주세요";
      } else {
        if (this.member.authKey === this.resultKey) {
          this.resultKey = "SUCCESS";
          msg = "인증에 성공하였습니다!";
        } else {
          msg = "인증번호가 틀렸습니다.";
        }
      }
      alert(msg);
    },
    closePw() {
      this.member.userid = "";
      this.member.email = "";
      this.member.authKey = "";
      this.resultKey = null;
      this.pwForm = false;
      this.$emit("closePw");
    },
    closeNpw() {
      this.npwForm = false;
    },
    showNpw() {
      if (this.resultKey === "SUCCESS") {
        this.npwForm = true;
        this.pwForm = false;
        this.$emit("closePw");
        this.closePw();
      } else {
        alert("이메일 인증을 먼저 해주시기바랍니다.");
      }
    },
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
