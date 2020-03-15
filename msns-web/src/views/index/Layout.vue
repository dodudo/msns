<template>
  <v-app>
    <TopBar v-on:topBarSearchVal="topBarSearchVal"></TopBar>
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
  data: () => ({
    search: ""
  }),
  methods: {
    topBarSearchVal(searchVal) {
      this.search = searchVal;
      // console.log(this.search);
      if (searchVal != null || searchVal != "") {
        this.$router.push({ path: `/index/search/${this.search}` });
      }
    }
  },
  components: {
    TopBar,
    RightSidebar
  }
};
</script>
<style scoped></style>
