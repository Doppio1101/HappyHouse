<template>
  <form>
    <v-container>
      <v-row class="mx-auto" style="width: 50%">
        <v-col><h1>My Home</h1></v-col>
        <v-col cols="12">
          <v-text-field
            v-model="member.userid"
            :counter="16"
            label="ID"
            required
            d-sr-focusable
            readonly
          ></v-text-field>
        </v-col>
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
        <v-col cols="12">
          <v-text-field
            v-model="member.email"
            label="E-mail"
            required
            readonly
          ></v-text-field>
        </v-col>
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
            v-model="atten[0].totaladdress"
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
              v-model="atten[1].totaladdress"
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
              v-model="atten[2].totaladdress"
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
          <v-btn color="red darken-1" @click="deleteMember" class="mr-4">
            Delete
          </v-btn>
          <v-btn color="amber lighten-3" @click.prevent="updateMember">
            Update
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </form>
</template>

<script>
import { localhost } from "@/js/common.js";
import { mapState, mapActions, mapMutations } from "vuex";
export default {
  name: "MypageComp",
  data: () => ({
    member: {
      userid: "",
      userpw: "",
      pwchk: "",
      username: "",
      email: "",
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
        totaladdress: "",
      },
    ],
    showAtten: 1,
  }),
  computed: {
    ...mapState(["userInfo", "attentionInfo", "isLogin", "isLoginError"]),
  },
  created() {
    this.member = this.userInfo;
    if (this.attentionInfo.length !== 0) {
      this.atten = this.attentionInfo;
      this.showAtten = this.attentionInfo.length;
    }
  },
  methods: {
    ...mapActions(["getUserInfo", "getAttentionInfo"]),
    ...mapMutations(["SET_IS_LOGIN", "SET_USER_INFO", "SET_ATTENTION_INFO"]),
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
          this.atten[n].totaladdress = this.atten[n].address;
          if (this.atten[n].detailaddress !== "")
            this.atten[n].totaladdress += `(${this.atten[n].detailaddress})`;

          this.atten.push();
        },
      }).open();
    },
    async updateMember() {
      let msg = "";
      let err = true;

      if (!this.member.userpw) {
        msg = "PW를 입력해주세요";
        err = false;
      } else if (!this.member.pwchk) {
        msg = "PW 확인을 위해 한번 더 입력해주세요";
        err = false;
      } else if (!this.member.username) {
        msg = "Name을 입력해주세요";
        err = false;
      } else if (this.member.userpw != this.member.pwchk) {
        msg = "PW가 일치하지않습니다..!";
        err = false;
      }

      if (!err) alert(msg);
      else {
        await localhost({
          method: "put",
          url: "/member/update",
          data: this.member,
        }).then((data) => {
          console.log(data);
        });

        await localhost({
          method: "delete",
          url: "/attention/delete/" + this.member.userid,
        }).then((data) => {
          console.log(data);
        });

        if (this.atten[0].address) {
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

        let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getUserInfo(token);
          await this.getAttentionInfo(token);
        }

        this.$router.push({ name: "HomeComp" });
        alert("내 정보가 수정되었습니다.");
      }
    },
    async deleteMember() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        await localhost({
          method: "delete",
          url: "/member/delete/" + this.member.userid,
        }).then((data) => {
          console.log(data);
        });

        await localhost({
          method: "delete",
          url: "/attention/delete/" + this.member.userid,
        }).then((data) => {
          console.log(data);
        });

        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        this.SET_ATTENTION_INFO(null);
        sessionStorage.removeItem("access-token");
        alert("탈퇴처리되었습니다.");
        if (this.$route.path != "/") this.$router.push({ name: "HomeComp" });
      }
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
      if (this.atten[n].address) {
        if (confirm("관심 주소를 삭제하시겠습니까?")) {
          this.showAtten = n;
          this.atten.pop();
        }
      } else {
        this.showAtten = n;
        this.atten.pop();
      }
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
