<template>
  <v-app>
    <TopBar></TopBar>

    <div id="content" style="max-width:1520px;min-width:880px" class="overflow-y-auto">
      <router-view />
      <RightSidebar class="right-sidebar my-0"></RightSidebar>
    </div>
  </v-app>
</template>
<script>
import TopBar from "../../components/TopBar";
import RightSidebar from "../../components/RightSidebar";
export default {
  data: () => ({}),
  methods: {},
  components: {
    TopBar,
    RightSidebar
  }
};
</script>
<style scoped>
</style>