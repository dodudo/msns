<template>
  <v-card class="ml-2" style="margin-top:78px" flat width="1114">
    <v-carousel
      cycle
      height="200"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item
        :src="require(`../../assets/${slide.img}`)"
        v-for="(slide, i) in sliders"
        :key="i"
      >
        <v-sheet color="rgba(0,0,0,0)" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3">{{ slide.name }}</div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
    <v-tabs v-model="tab" light class="elevation-2" dark>
      <v-tabs-slider></v-tabs-slider>

      <v-tab v-for="i in classItems" :key="i">{{ i }}</v-tab>
      <v-spacer></v-spacer>
      <v-card width="300" class="d-flex align-center">
        <v-text-field
          background-color="rgba(255,255,255,.4)"
          class="d-flex md-3"
          dense
          hide-details
          label="搜索音乐"
          solo
          dark
        ></v-text-field>
        <v-btn dark class="mt-1 d-flex md-1" icon>
          <v-icon dark>mdi-magnify</v-icon>
        </v-btn>
      </v-card>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item v-for="i in classItems" :key="i">
        <v-card flat max-height="380" style="overflow-y:auto">
          <v-list class="pa-0 ma-0" dense two-line>
            <template v-for="(item, index) in musicItems">
              <v-divider
                v-if="item.divider"
                :key="index"
                :inset="item.inset"
              ></v-divider>

              <v-list-item
                @click="playMusic()"
                dense
                v-else
                :key="item.title"
                link
              >
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
                    <v-icon :color="item.favor ? 'red' : 'gray'"
                      >mdi-heart</v-icon
                    >
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </template>
          </v-list>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    tab: null,
    sliders: [
      { name: "Pop", img: "pop.jpg" },
      { name: "Rock", img: "rock.jpg" },
      { name: "Folk", img: "folk.jpg" },
      { name: "Electronic", img: "dj.jpeg" },
      { name: "Light", img: "gq.jpg" },
      { name: "ACG", img: "acg.jpg" }
    ],
    classItems: ["热歌榜", "流行", "摇滚", "民谣", "电子", "轻音乐", "ACG"],
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
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "定西",
        subtitle: "李志",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "杭州",
        subtitle: "李志",
        favor: true
      }
    ]
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
