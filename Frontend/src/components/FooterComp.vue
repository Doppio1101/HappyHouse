<template>
  <div class="footer">
    <v-footer>
      <v-col class="text-center" style="font-size: 15px" cols="12">
        {{ new Date().getFullYear() }}_Happy House_JJH_HSJ
      </v-col>
    </v-footer>
  </div>
</template>

<script>
export default {};
</script>

<style scope>
.footer {
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>
