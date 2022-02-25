<template>
  <v-container>
    <div>
      <br />

      <v-card class="d-flex mb-6" flat>
        <div class="mb-1">
          <v-select
            v-model="select"
            class="pa-2"
            background-color="grey lighten-3"
            :items="items"
            item-text="state"
            item-value="abbr"
            label="Select"
            persistent-hint
            return-object
            single-line
          ></v-select>
        </div>
        <div class="mb-4">
          <v-text-field
            v-model="search"
            class="pa-2 mr-auto"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </div>
      </v-card>

      <v-simple-table>
        <thead>
          <tr>
            <th id="tbltitle" class="text-left">번호</th>
            <th id="tbltitle" class="text-center">제목</th>
            <th id="tbltitle" class="text-center">작성자</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in qa" :key="item.no" @click="detail(item)">
            <td id="tblcont" class="text-left">{{ item.no }}</td>
            <td id="tblcont" class="text">{{ item.title }}</td>
            <td id="tblcont" class="text-center">{{ item.userid }}</td>
          </tr>
        </tbody>
      </v-simple-table>
      <br />
      <div>
        <div class="text-right" v-if="userInfo">
          <v-fab-transition>
            <v-btn
              key="mdi - pencil"
              color="green"
              fab
              dark
              small
              class="v-btn--example"
              @click="regist"
            >
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
          </v-fab-transition>
        </div>
        <div class="text-center">
          <v-pagination
            v-model="page"
            :length="totalPage"
            :total-visible="5"
            circle
            @input="load"
          ></v-pagination>
        </div>
      </div>
    </div>
  </v-container>
</template>

<script>
import { localhost } from "@/js/common.js";
import { mapActions, mapMutations, mapState } from "vuex";
export default {
  data() {
    return {
      qa: [],
      totalPage: 1,
      page: 1,
    };
  },
  computed: {
    ...mapState(["mode", "userInfo"]),
  },
  created() {
    localhost({
      method: "get",
      url: "/QA/",
    }).then((data) => {
      //console.log("get:", data.data.page);
      let total = parseInt(data.data.page / 10);
      if (data.data.page % 10 != 0) {
        total++;
        //console.log("여기");
      }
      this.totalPage = total;
      //console.log("total", total);
    });
    this.load();
  },
  methods: {
    ...mapActions(["SET_DETAIL_QANO"]),
    ...mapMutations(["SET_MODE", "SET_QANO"]),
    load() {
      localhost({
        method: "get",
        url: "/QA/" + (this.page - 1) * 10,
      }).then((data) => {
        //console.log(data);
        this.qa = data.data;
      });
    },
    detail(item) {
      let qaNo = item.no;
      this.SET_QANO(qaNo);
      this.SET_MODE("detail");
      this.$router.push({ name: "Form" });
    },
    regist() {
      this.SET_MODE("create");
      this.$router.push({ name: "Form" });
    },
  },
};
</script>

<style scope>
#tbltitle {
  font-size: 20px;
}
#tblcont {
  font-size: 17px;
}
</style>
