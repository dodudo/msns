<template>
  <v-card flat>
    <v-row>
      <v-col class="ml-2" cols="2">回复我的</v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row class="pb-4">
      <v-list class="mx-6" width="900" height="30%" three-line>
        <v-list-item>
          <v-list-item-avatar>
            <v-avatar>
              <v-img src="https://cdn.vuetifyjs.com/images/john.jpg"></v-img>
            </v-avatar>
          </v-list-item-avatar>
          <v-list-item-content style="background-color:#F5F5F5" class="px-2">
            <v-list-item-subtitle class="d-flex justify-space-between">
              <v-card outlined color="rgba(0,0,0,0)" flat style="color:blue"
                >哈哈哈哈</v-card
              >
              <v-card outlined color="rgba(0,0,0,0)" flat style="color:#BDBDBD"
                >2020年2月12日</v-card
              >
            </v-list-item-subtitle>
            <v-list-item-content
              class="pa-2 caption"
              style="background-color:#EEEEEE;"
              >回复我：嗯 这两个小姐姐本来就是陈粒的粉丝
              在微博发了视频艾特了陈粒 然后粒粒看到了转发还说要翻唱
              很多粒粒的粉丝在微博听到很喜欢就鼓励两个小姐姐出歌
              再后来粒粒征得同意后放进了《玩》的专辑</v-list-item-content
            >
            <v-list-item-subtitle class="d-flex justify-space-between">
              <v-card
                outlined
                color="rgba(0,0,0,0)"
                class="caption"
                flat
                style="color:#757575"
                >我的评论：哇，粒哥新专《玩》里面就有这首歌</v-card
              >
              <a
                class="mr-4 caption"
                style="line-height:22px;text-decoration:underline"
                @click="show_input = !show_input"
                >回复</a
              >
            </v-list-item-subtitle>
            <v-divider></v-divider>
            <v-list-item-content class="pa-0 pb-1" v-show="show_input">
              <v-list-item-content class="pb-2">
                <v-textarea
                  clearable
                  clear-icon="mdi-close-circle-outline"
                  label="回复哈哈哈"
                  class="px-4"
                  outlined
                  flat
                  hide-details
                  rows="1"
                ></v-textarea>
              </v-list-item-content>
              <v-list-item-action class="ma-0 d-flex justify-end">
                <v-btn small width="50" class="mr-4">回复</v-btn>
              </v-list-item-action>
            </v-list-item-content>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-row>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    show_input: false
  })
};
</script>
