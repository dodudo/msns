<template>
  <v-app>
    <LeftSidebar></LeftSidebar>
    <Edit></Edit>
    <News class="news"></News>
  </v-app>
</template>
<script>
import News from "../../components/News";
import LeftSidebar from "../../components/LeftSidebar";
import Edit from "../../components/Edit";
export default {
  data: () => ({
    screenWidth: ""
  }),
  components: {
    News,
    LeftSidebar,
    Edit
  }
};
</script>
<style scoped>
</style>