<template>
  <v-card class="mt-4 mx-5" max-width="1000">
    <v-sheet
      class="v-sheet--offset mx-auto"
      color="cyan"
      elevation="12"
      max-width="calc(100% - 32px)"
    >
      <v-sparkline
        :labels="labels"
        :value="value"
        color="white"
        line-width="2"
        padding="16"
      ></v-sparkline>
    </v-sheet>

    <v-card-text class="pt-0">
      <v-divider class="my-2"></v-divider>
      <v-icon class="mr-2">
        mdi-equalizer
      </v-icon>
      <span class=" grey--text headline">本站各分类音乐数量</span>
    </v-card-text>
  </v-card>
</template>
<style scoped>
.v-sheet--offset {
  top: -24px;
  position: relative;
}
</style>
<script>
export default {
  data: () => ({
    labels: [],
    value: []
  }),
  mounted() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.$http.get("/music/statistics").then(resp => {
        console.log(resp.data);
        for (let i = 0; i < resp.data.length; i++) {
          this.labels.push(resp.data[i].typeName);
          this.value.push(resp.data[i].musicCount);
        }
      });
    }
  }
};
</script>
