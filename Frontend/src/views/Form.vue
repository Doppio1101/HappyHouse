<template>
  <v-form ref="form" lazy-validation>
    <br />
    <v-text-field
      v-model="selectQA.userid"
      label="작성자"
      required
      :readonly="readonlyAuthor"
    ></v-text-field>

    <v-text-field
      v-model="selectQA.title"
      label="제목"
      required
      :readonly="readonlyTitle"
    ></v-text-field>

    <v-textarea
      background-color="grey lighten-3"
      color="cyan"
      :counter="500"
      v-model="selectQA.content"
      label="내용"
      :readonly="readonlyContent"
    ></v-textarea>
    <br />
    <v-btn color="success" class="mr-4" @click="mvList"> 목록으로 </v-btn>

    <template v-if="userInfo">
      <template>
        <v-btn color="warning" class="mr-4" @click="modePlay">
          {{ modeName }}
        </v-btn>

        <v-btn color="error" v-if="show" @click="delQA"> 삭제 </v-btn>
      </template>
    </template>
  </v-form>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import { localhost } from "@/js/common.js";
export default {
  data() {
    return {
      readonlyAuthor: false,
      readonlyTitle: false,
      readonlyContent: false,
      show: true,
      modeName: "create",
      author: "",
      title: "",
      content: "",
    };
  },
  created() {
    console.log(this.mode);
    if (this.mode == "detail" || this.mode == "modify") {
      this.SET_DETAIL_QANO(this.qaNo);
      // this.author = this.selectQA.userid;
      // this.title = this.selectQA.title;
      // this.content = this.selectQA.content;
    }
    this.load();
  },
  computed: {
    ...mapState([
      "qaNo",
      "selectQA",
      "mode",
      "userInfo",
      "isLogin",
      "isLoginError",
    ]),
  },
  methods: {
    ...mapActions(["SET_DETAIL_QANO"]),
    ...mapMutations(["SET_QA", "SET_MODE"]),
    load() {
      if (this.mode === "create") {
        this.readonlyAuthor = false;
        this.readonlyTitle = false;
        this.readonlyContent = false;
        this.modeName = "등록";
        this.show = false;
      } else if (this.mode === "modify") {
        this.readonlyAuthor = true;
        this.readonlyTitle = false;
        this.readonlyContent = false;
        this.modeName = "확인";
        this.show = true;
      } else if (this.mode === "detail") {
        this.readonlyAuthor = true;
        this.readonlyTitle = true;
        this.readonlyContent = true;
        this.modeName = "수정";
        this.show = true;
      }
    },
    modePlay() {
      if (this.mode === "create") {
        localhost({
          url: "/QA/",
          method: "post",
          data: this.selectQA,
        })
          .then((res) => {
            console.log("여기", res.data);
            this.mvList();
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (this.mode === "detail") {
        this.SET_MODE("modify");
        this.load();
      } else if (this.mode === "modify") {
        console.log(this.selectQA);
        let data = {};
        data.userid = this.selectQA.userid;
        data.no = this.qaNo;
        data.title = this.selectQA.title;
        data.content = this.selectQA.content;
        console.log(data);
        localhost({
          url: "/QA/update",
          method: "put",
          data: data,
        }).then((res) => {
          console.log(res.data);
          alert("게시글이 수정되었습니다.");
          this.mvList();
        });
      }
    },
    mvList() {
      this.SET_QA({});
      this.$router.push({ name: "List" });
    },
    delQA() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        localhost({
          url: "/QA/delete/" + this.qaNo,
          method: "delete",
        }).then((res) => {
          console.log(res.data);
          alert("게시글이 삭제되었습니다.");
          this.mvList();
        });
      }
    },
  },
};
</script>

<style></style>
