<template>
  <v-card style="margin-top:78px" flat width="1114">
    <News v-bind:dynamicSearch="dynamicSearch"></News>
  </v-card>
</template>
<script>
import News from "../../components/News";
export default {
  components: {
    News
  },
  data: () => ({
    dynamicSearch: {
      key: "",
      page: 1,
      sortBy: "publishDate",
      desc: true
    }
  }),
  watch: {
    $route() {
      this.dynamicSearch.key = this.$route.params.key;
      // console.log(this.dynamicSearch);
    }
  },
  created() {
    if (this.$route.params.key) {
      this.dynamicSearch.key = this.$route.params.key;
    }
  }
};
</script>
