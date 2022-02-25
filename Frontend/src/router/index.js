import Vue from "vue";
import VueRouter from "vue-router";
import List from "../views/List.vue";
import Form from "../views/Form.vue";
import HomeComp from "../views/HomeComp.vue";
import qaComp from "../views/qaComp.vue";
import MapComp from "../views/MapComp.vue";

import JoinComp from "../components/member/JoinComp.vue";
import MypageComp from "../components/member/MypageComp.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/qa",
    name: "qaComp",
    component: qaComp,
    children: [
      {
        path: "/list",
        name: "List",
        component: List,
      },
      {
        path: "/form",
        name: "Form",
        component: Form,
      },
    ],
  },
  {
    path: "/",
    name: "HomeComp",
    component: HomeComp,
  },
  {
    path: "/map",
    name: "MapComp",
    component: MapComp,
  },
  {
    path: "/join",
    name: "JoinComp",
    component: JoinComp,
  },
  {
    path: "/mypage",
    name: "MypageComp",
    component: MypageComp,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
