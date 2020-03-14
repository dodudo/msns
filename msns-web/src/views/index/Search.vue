<template>
  <v-card style="margin-top:78px" flat width="1114">
    <News></News>
  </v-card>
</template>
<script>
import News from "../../components/News";
export default {
  components: {
    News
  },
  data: () => ({
    search: ""
  }),
  created() {
    if (this.$route.params.key) {
      this.search = this.$route.params.key;
    }
  }
};
</script>
