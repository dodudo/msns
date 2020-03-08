<template>
  <v-card width="1112" class="mt-2">
    <v-card class="mx-2" height="54">
      <v-list-item style="height:54px" dens>
        <v-list-item-avatar
          style="border-radius:4px"
          tile
          class="my-0"
        ></v-list-item-avatar>
        <v-list-item-content style="height:40px" class="pa-0 mr-12">
          <v-list-item-title>歌曲名</v-list-item-title>
        </v-list-item-content>
        <v-list-item-content style="height:40px" class="pa-0">
          <v-list-item-title>时长</v-list-item-title>
        </v-list-item-content>
        <v-list-item-content style="height:40px" class="pa-0">
          <v-list-item-title>歌手名</v-list-item-title>
        </v-list-item-content>
        <v-list-item-content style="height:40px" class="pa-0">
          <v-list-item-title>专辑</v-list-item-title>
        </v-list-item-content>
        <v-list-item-action style="height:40px" class="pa-0">
          <v-list-item-title>收藏</v-list-item-title>
        </v-list-item-action>
      </v-list-item>
    </v-card>
    <v-card height style="overflow-y:auto" flat class="mt-2 pa-2">
      <v-list class="pa-0 ma-0" dense two-line>
        <template v-for="(item, index) in musicItems">
          <v-divider
            v-if="item.divider"
            :key="index"
            :inset="item.inset"
          ></v-divider>

          <v-list-item @click="playMusic()" dense v-else :key="item.title" link>
            <v-list-item-avatar style="border-radius:4px" tile class="my-0">
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content style="height:40px" class="pa-0 mr-12">
              <v-list-item-title v-html="item.title"></v-list-item-title>
            </v-list-item-content>
            <v-list-item-content style="height:40px" class="pa-0">
              <v-list-item-subtitle>04:23</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-content style="height:40px" class="pa-0">
              <v-list-item-title v-html="item.subtitle"></v-list-item-title>
            </v-list-item-content>
            <v-list-item-content style="height:40px" class="pa-0">
              <v-list-item-subtitle>《洗心革面》</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
              <v-btn @click.stop="favor(index)" icon>
                <v-icon :color="item.favor ? 'red' : 'gray'">mdi-heart</v-icon>
              </v-btn>
            </v-list-item-action>
          </v-list-item>
        </template>
      </v-list>
    </v-card>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    musicItems: [
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "你离开了南京，从此没有人和我说话",
        subtitle: "李志",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "关于郑州的记忆",
        subtitle: "李志",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "热河",
        subtitle: "李志",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "山阴路的夏天",
        subtitle: "李志",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "和你在一起",
        subtitle: "李志",
        favor: true
      }
    ],
    i: []
  }),
  methods: {
    favor(index) {
      if (this.musicItems[index].favor == true) {
        if (confirm("您确定要取消收藏？")) {
          this.musicItems[index].favor = !this.musicItems[index].favor;
        }
      } else {
        this.musicItems[index].favor = !this.musicItems[index].favor;
      }
    },
    playMusic() {}
  }
};
</script>
