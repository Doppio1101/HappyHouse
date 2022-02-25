<template>
  <form>
    <v-container>
      <v-row class="mx-auto" style="width: 50%">
        <v-col><span id="title">회원가입</span></v-col>
        <v-col cols="12">
          <v-text-field
            v-model="member.userid"
            :counter="16"
            label="ID"
            required
            @keyup="idchk"
            d-sr-focusable
          ></v-text-field>
        </v-col>
        <small id="idresult">{{ idresult }}</small>
        <v-col cols="12">
          <v-text-field
            v-model="member.userpw"
            label="Password"
            type="password"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-text-field
            v-model="member.pwchk"
            label="Password Check"
            type="password"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-text-field
            v-model="member.username"
            label="Name"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="9" md="9">
          <v-text-field
            v-model="member.email"
            label="E-mail"
            required
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3" md="3">
          <v-btn @click="sendMail">인증번호 받기</v-btn>
        </v-col>

        <template v-if="resultKey != 'SUCCESS'">
          <v-col cols="12" sm="9" md="9">
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
          <v-col cols="12" sm="9" md="9">
            <v-text-field
              v-model="member.authKey"
              label="authKey"
              required
              disabled
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="3" md="3">
            <v-btn color="green">인증 성공</v-btn>
          </v-col>
        </template>
        <!-- <v-col cols="12">
          <div id="addChip" class="text-left">
            <v-chip
              v-if="atten1"
              class="ma-2"
              close
              color="orange"
              label
              @click:close="atten1 = false"
            >
              {{ atten.nickname1 }}
            </v-chip>
            <v-btn
              class="mx-2"
              dark
              small
              outlined
              color="grey"
              @click="addAtten"
            >
              <v-icon dark>mdi-plus</v-icon>
            </v-btn>
          </div>
        </v-col> -->
        <v-col cols="12" sm="3">
          <v-text-field
            label="Nickname1"
            v-model="atten[0].nickname"
            filled
            rounded
            dense
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="5">
          <v-text-field
            label="Address"
            v-model="atten[0].totalAddress"
            @click="execDaumPostcode(0)"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="4">
          <v-btn class="ma-3" @click="execDaumPostcode(0)">Search</v-btn>
          <template v-if="showAtten == 1">
            <v-btn
              class="ma-3"
              @click="addAtten(2)"
              small
              fab
              dark
              color="indigo"
            >
              <v-icon dark> mdi-plus </v-icon>
            </v-btn>
          </template>
        </v-col>
        <template v-if="showAtten == 2 || showAtten == 3">
          <v-col cols="12" sm="3">
            <v-text-field
              label="Nickname2"
              v-model="atten[1].nickname"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="5">
            <v-text-field
              label="Address"
              v-model="atten[1].totalAddress"
              @click="execDaumPostcode(1)"
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="4">
            <v-btn class="ma-3" @click="execDaumPostcode(1)">Search</v-btn>
            <v-btn
              v-if="showAtten == 2"
              class="ma-3"
              @click="addAtten(3)"
              small
              fab
              dark
              color="indigo"
            >
              <v-icon dark> mdi-plus </v-icon>
            </v-btn>
            <v-btn
              v-if="showAtten == 2"
              class="ma-3"
              @click="minusAtten(1)"
              small
              fab
              dark
              color="red"
            >
              <v-icon dark> mdi-minus </v-icon>
            </v-btn>
          </v-col>
        </template>
        <template v-if="showAtten == 3">
          <v-col cols="12" sm="3">
            <v-text-field
              label="Nickname3"
              v-model="atten[2].nickname"
              filled
              rounded
              dense
            ></v-text-field>
          </v-col>
          <v-col cols="12" sm="5">
            <v-text-field
              label="Address"
              v-model="atten[2].totalAddress"
              @click="execDaumPostcode(2)"
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="4">
            <v-btn class="ma-3" @click="execDaumPostcode(2)">Search</v-btn>
            <v-btn
              v-if="showAtten == 3"
              class="ma-3"
              @click="minusAtten(2)"
              small
              fab
              dark
              color="red"
            >
              <v-icon dark> mdi-minus </v-icon>
            </v-btn>
          </v-col>
        </template>

        <v-col cols="12">
          <v-btn class="mr-4"> clear </v-btn>
          <v-btn @click.prevent="registMember"> Join </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </form>
</template>

<script>
import { localhost } from "@/js/common.js";
export default {
  name: "JoinComp",
  data: () => ({
    member: {
      userid: "",
      userpw: "",
      pwchk: "",
      username: "",
      email: "",
      authKey: "",
    },
    atten: [
      {
        userid: "",
        bname: "",
        sigungucod: "",
        nickname: "",
        postnum: "",
        address: "",
        detailaddress: "",
        totalAddress: "",
        lat: "",
        lng: "",
      },
    ],
    idresult: "",
    resultKey: "FAIL",
    showAtten: 1,
  }),
  methods: {
    execDaumPostcode(n) {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.atten[n].detailaddress !== "") {
            this.atten[n].detailaddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.atten[n].address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.atten[n].address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.atten[n].detailaddress += data.bname;
              this.atten[n].bname = data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.atten[n].detailaddress +=
                this.atten[n].detailaddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            // if (this.atten[n].detailAddress !== "") {
            //   this.atten[n].detailAddress = `(${this.atten[n].detailAddress})`;
            // }
          } else {
            this.atten[n].detailaddress = "";
          }
          // 우편번호를 입력한다.
          this.atten[n].postnum = data.zonecode;

          //this.atten.bname[n] = data.bname;
          this.atten[n].sigungucode = data.sigunguCode;

          // 주소+상세주소
          this.atten[n].totalAddress = this.atten[n].address;
          if (this.atten[n].detailaddress !== "")
            this.atten[n].totalAddress += `(${this.atten[n].detailaddress})`;

          this.atten.push();
        },
      }).open();
    },
    async registMember() {
      let msg = "";
      let err = true;

      if (!this.member.userid) {
        msg = "ID를 입력해주세요";
        err = false;
      } else if (!this.member.userpw) {
        msg = "PW를 입력해주세요";
        err = false;
      } else if (!this.member.pwchk) {
        msg = "PW 확인을 위해 한번 더 입력해주세요";
        err = false;
      } else if (!this.member.username) {
        msg = "Name을 입력해주세요";
        err = false;
      } else if (!this.member.email) {
        msg = "E-mail을 입력해주세요";
        err = false;
      } else if (this.resultKey != "SUCCESS") {
        msg = "이메일 인증이 필요합니다";
        err = false;
      } else if (this.idresult != "SUCCESS") {
        err = false;
      } else if (this.member.userpw != this.member.pwchk) {
        msg = "PW가 일치하지않습니다..!";
        err = false;
      }

      if (!err) alert(msg);
      else {
        await localhost({
          method: "post",
          url: "/member/regist",
          data: this.member,
        }).then((data) => {
          console.log(data);
        });

        if (this.atten[0].address) {
          //console.log("여기", this.atten[0]);
          for (let i = 0; i < this.atten.length; i++) {
            this.atten[i].userid = this.member.userid;
            await localhost({
              method: "post",
              url: "/attention/regist",
              data: this.atten[i],
            }).then((data) => {
              console.log(data);
            });
          }
        }
        this.$router.push({ name: "HomeComp" });
        alert("회원가입에 성공하였습니다. 환영합니다 :)");
      }
    },
    idchk() {
      if (this.member.userid) {
        localhost({
          method: "get",
          url: "/member/idchk/" + this.member.userid,
        }).then((data) => {
          //console.log(data.data);
          if (data.data == "success") {
            this.idresult = "SUCCESS";
          } else {
            this.idresult = "이미 사용중인 아이디입니다..";
          }
        });
      }
    },
    sendMail() {
      let msg = "";
      if (!this.member.email) {
        msg = "E-mail을 먼저 입력해주세요";
      } else {
        localhost({
          method: "post",
          url: "/email/mailConfirm/" + this.member.email,
        }).then((data) => {
          //console.log(data.data);
          this.resultKey = data.data;
        });
        msg = "인증번호를 발송했습니다:)";
      }
      alert(msg);
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
    addAtten(n) {
      this.showAtten = n;
      this.atten[n - 1] = {
        userid: "",
        bname: "",
        sigungucod: "",
        nickname: "",
        postnum: "",
        address: "",
        detailaddress: "",
        totalAddress: "",
      };
    },
    minusAtten(n) {
      this.showAtten = n;
      this.atten.pop();
    },
  },
  mounted() {
    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    document.head.appendChild(script);
  },
};
</script>

<style></style>
