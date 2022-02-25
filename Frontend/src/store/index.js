import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import { localhost } from "@/js/common.js";
import jwt_decode from "jwt-decode";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    qaNo: 0,
    selectQA: {},
    mode: "create",
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    attentionInfo: null,
  },
  actions: {
    SET_DETAIL_QANO({ commit }, qaNo) {
      localhost({
        url: "/QA/detail/" + qaNo,
        method: "get",
      }).then(({ data }) => {
        commit("SET_QA", data.detailData);
        // this.$router.push({ name: "Form" });
      });
    },
    async userConfirm({ commit }, user) {
      await localhost({
        method: "post",
        url: "/member/login",
        data: JSON.stringify(user),
      })
        .then((response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        })
        .catch(() => {});
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      localhost.defaults.headers["access-token"] =
        sessionStorage.getItem("access-token");
      await localhost({
        method: "get",
        url: "/member/detail/" + decode_token.userid,
      })
        .then((response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("사용자 정보 없음!!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getAttentionInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      localhost.defaults.headers["access-token"] =
        sessionStorage.getItem("access-token");
      await localhost({
        method: "get",
        url: "/attention/detail/" + decode_token.userid,
      })
        .then((response) => {
          if (response.data.message === "success") {
            commit("SET_ATTENTION_INFO", response.data.attentionInfo);
          } else {
            console.log("사용자 관심사 정보 없음!!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mutations: {
    SET_QA(state, data) {
      state.selectQA = data;
    },
    SET_MODE(state, mode) {
      state.mode = mode;
    },
    SET_QANO(state, qaNo) {
      state.qaNo = qaNo;
    },
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_ATTENTION_INFO: (state, attentionInfo) => {
      state.isLogin = true;
      state.attentionInfo = attentionInfo;
    },
  },
  modules: {},
  getters: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
