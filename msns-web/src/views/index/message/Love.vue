<template>
  <v-card flat>
    <v-row>
      <v-col class="ml-2" cols="2">我收到的赞</v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row class="pb-4">
      <v-list
        v-for="(n, index) in 6"
        :key="index"
        class="mx-6"
        width="900"
        two-line
      >
        <v-list-item>
          <v-list-item-avatar>
            <v-avatar>
              <v-img src="https://cdn.vuetifyjs.com/images/john.jpg"></v-img>
            </v-avatar>
          </v-list-item-avatar>
          <v-list-item-content style="background-color:#F5F5F5" class="px-2">
            <v-list-item-title class="mx-4 d-flex justify-space-between">
              <v-card outlined color="rgba(0,0,0,0)" flat class="caption BLACK"
                >哈哈哈哈赞了你</v-card
              >
              <v-card
                outlined
                color="rgba(0,0,0,0)"
                flat
                class="caption"
                style="color:#BDBDBD"
                >2020年2月12日</v-card
              >
            </v-list-item-title>
            <v-list-item-subtitle class="mx-4"
              >Visit ten places on our planet that are underdsfad
              sadffffffffffsdfa asdfaaaaaaaaaaaaaaaa
              dsaffffffffffffffffffffffffffffffffffffffffffff
              sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
              dsaffffffffffffffffffffffffffffffffffffffffffff
              sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
              geeeeeeeeeeeeeeeeeeeeeeeeeeeeee
              eggggggggggggggggggggggg</v-list-item-subtitle
            >
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-row>
  </v-card>
</template>
<style scoped>
.subtitle {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
</style>
