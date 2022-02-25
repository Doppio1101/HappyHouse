<template>
  <v-row justify="center">
    <v-dialog v-model="npwForm" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span id="title">비밀번호 설정</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="New Password"
                  type="password"
                  v-model="member.userpw"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="9" md="9">
                <v-text-field
                  label="New Password Check"
                  type="password"
                  v-model="member.pwchk"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="darkgrey" text outlined @click="closeNpw">
            Close
          </v-btn>
          <v-btn color="blue darken-1" outlined @click="changePw"> SET </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { localhost } from "@/js/common.js";
export default {
  name: "NpwComp",
  data() {
    return {
      npwForm: false,
      member: {
        userid: "",
        userpw: "",
        pwchk: "",
      },
    };
  },
  props: ["npwFormData", "userId"],
  watch: {
    npwFormData() {
      this.npwForm = this.npwFormData;
      this.member.userid = this.userId;
    },
  },
  computed: {},
  methods: {
    closeNpw() {
      this.npwForm = false;
      this.$emit("closeNpw");
      this.member.userid = null;
      this.member.userpw = null;
      this.member.pwchk = null;
    },
    async changePw() {
      let msg = "";
      let err = true;

      if (!this.member.userpw) {
        msg = "새로운 Password를 입력해주세요.";
        err = false;
      } else if (!this.member.pwchk) {
        msg = "Password 확인을 위해 한번 더 입력해주세요.";
        err = false;
      } else if (this.member.userpw !== this.member.pwchk) {
        msg = "Password가 일치하지않습니다..!";
        err = false;
      }
      //console.log("이거", this.member.userid);

      if (!err) alert(msg);
      else {
        await localhost({
          method: "put",
          url: "/member/updatepw",
          data: this.member,
        }).then((data) => {
          console.log(data);
        });
        this.closeNpw();
        alert("Password가 변경되었습니다. 로그인을 시도해보세요!");
        if (this.$route.path != "/") this.$router.push({ name: "HomeComp" });
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
