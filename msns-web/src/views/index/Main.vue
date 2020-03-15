<template>
  <v-app>
    <LeftSidebar></LeftSidebar>
    <Edit></Edit>
    <News v-bind:dynamicSearch="dynamicSearch" class="news"></News>
  </v-app>
</template>
<script>
import News from "../../components/News";
import LeftSidebar from "../../components/LeftSidebar";
import Edit from "../../components/Edit";
export default {
  data: () => ({
    screenWidth: "",
    dynamicSearch: {
      key: "",
      page: 1,
      sortBy: "publishDate",
      desc: true
    },
    dynamics: []
  }),
  components: {
    News,
    LeftSidebar,
    Edit
  },
  mounted() {
    // console.log("main-create");
  },
  methods: {}
};
</script>
<style scoped></style>
