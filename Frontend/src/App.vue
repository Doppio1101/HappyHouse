<template>
  <v-app>
    <navi-comp />
    <router-view class="content"></router-view>
    <footer-comp></footer-comp>
  </v-app>
</template>

<script>
import NaviComp from "./components/NaviComp.vue";
import FooterComp from "./components/FooterComp.vue";

export default {
  name: "App",
  components: {
    NaviComp,
    FooterComp,
  },
  data: () => ({}),
  created() {},
  methods: {},
};
</script>

<style scope>
#app {
  min-height: 100%;
  position: relative;
  padding-bottom: 100px;
}
.content {
  padding-top: 10px;
}
* {
  font-family: "Alegreya Sans SC", sans-serif;
  font-family: "Gaegu", cursive;
  font-family: "Jua", sans-serif;
  font-size: 20px;
}
</style>
