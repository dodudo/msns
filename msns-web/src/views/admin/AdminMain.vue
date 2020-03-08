<template>
  <v-card flat>
    <v-container fluid>
      <v-row>
        <v-col cols="12" md="6" xs="12">
          <v-card class="text-center" color="green" dark max-width="900">
            <v-card-text>
              <v-sheet color="rgba(0, 0, 0, .12)">
                <v-sparkline
                  :value="value"
                  color="rgba(255, 255, 255, .7)"
                  height="100"
                  padding="24"
                  stroke-linecap="round"
                  smooth
                >
                  <template v-slot:label="item">{{ item.value }}</template>
                </v-sparkline>
              </v-sheet>
            </v-card-text>

            <v-card-text class="pt-0">
              <div class="title font-weight-thin">最近一周用户增长量</div>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12" md="6" xs="12">
          <v-card class="text-center" color="blue" dark max-width="900">
            <v-card-text>
              <v-sheet color="rgba(0, 0, 0, .12)">
                <v-sparkline
                  :value="value"
                  color="rgba(255, 255, 255, .7)"
                  height="100"
                  padding="24"
                  stroke-linecap="round"
                  smooth
                >
                  <template v-slot:label="item">{{ item.value }}</template>
                </v-sparkline>
              </v-sheet>
            </v-card-text>

            <v-card-text class="pt-0">
              <div class="title font-weight-thin">每日动态发布数</div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    value: [0, 2, 5, 9, 5, 10, 3, 5, -4, -10, 1, 8, 2, 9, 0]
  })
};
</script>
