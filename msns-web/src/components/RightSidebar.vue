<template>
  <v-card class="mb-0" flat>
    <div style="width:380px"></div>
    <v-navigation-drawer
      v-model="rdrawer"
      height="630"
      width="380"
      right
      fixed
      class="right-sidebar"
      light
      stateless
      floating
    >
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title">我的音乐</v-list-item-title>
          <v-list-item-subtitle>哈哈哈</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>
      <v-content>
        <v-container fluid>
          <v-row justify="center" no-gutters>
            <v-col cols="7">
              <v-img
                src="https://picsum.photos/510/300?random"
                height="210"
                width="210"
                aspect-ratio="1"
              ></v-img>
            </v-col>
          </v-row>
          <v-row justify="center" style="height:50px" no-gutters>
            <v-col cols="4">
              <v-list class="pa-0">
                <v-list-item style="text-align:center" two-line>
                  <v-list-item-content class="pa-0">
                    <v-list-item-title>忽然</v-list-item-title>
                    <v-list-item-subtitle>李志</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-col>
          </v-row>
          <v-row no-gutters>
            <v-col cols="2" style="text-align:right">
              <div class="overline mt-2">00:00</div>
            </v-col>
            <v-col cols="8">
              <v-slider
                dense
                hide-details
                class="ma-0 pa-0"
                color="orange darken-3"
              ></v-slider>
            </v-col>
            <v-col cols="2">
              <div class="overline mt-2">04:10</div>
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-col class="pa-0" style="text-align:center" cols="2"></v-col>
            <v-col class="pa-0" style="text-align:center" cols="2">
              <v-btn icon>
                <v-icon>mdi-skip-previous</v-icon>
              </v-btn>
            </v-col>
            <v-col class="pa-0" style="text-align:center" cols="2">
              <v-btn icon @click="play()">
                <v-icon>{{ this.$store.state.playStatus }}</v-icon>
              </v-btn>
            </v-col>
            <v-col class="pa-0" style="text-align:center" cols="2">
              <v-btn icon class="mr-2">
                <v-icon>mdi-skip-next</v-icon>
              </v-btn>
            </v-col>
            <v-col class="pa-0" style="text-align:center" cols="2">
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
            </v-col>
          </v-row>
          <v-row>
            <v-subheader style="height:20px">歌曲列表</v-subheader>
            <v-card flat style="overflow-y:auto" width="360" height="186">
              <v-list class="pa-0 ma-0" dense two-line>
                <template v-for="(item, index) in musicItems">
                  <v-divider
                    v-if="item.divider"
                    :key="index"
                    :inset="item.inset"
                  ></v-divider>

                  <v-list-item dense v-else :key="item.title">
                    <v-list-item-avatar
                      style="border-radius:4px"
                      tile
                      class="my-0"
                    >
                      <v-img :src="item.avatar"></v-img>
                    </v-list-item-avatar>
                    <v-list-item-content style="height:40px" class="pa-0">
                      <v-list-item-title
                        v-html="item.title"
                      ></v-list-item-title>
                      <v-list-item-subtitle
                        v-html="item.subtitle"
                      ></v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-list>
            </v-card>
          </v-row>
        </v-container>
      </v-content>
    </v-navigation-drawer>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    items: [
      { title: "Home", icon: "mdi-home-city" },
      { title: "My Account", icon: "mdi-account" },
      { title: "Users", icon: "mdi-account-group-outline" }
    ],
    musicItems: [
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "你离开了南京，从此没有人和我说话",
        subtitle: "李志"
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "关于郑州的记忆",
        subtitle: "李志"
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "热河",
        subtitle: "李志"
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "山阴路的夏天",
        subtitle: "李志"
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "和你在一起",
        subtitle: "李志"
      }
    ],
    screenWidth: "",
    playStatus: "mdi-play",
    drawer: true
  }),
  methods: {
    play() {
      this.playBtnStatus = !this.playBtnStatus;
      if (this.playBtnStatus == true) {
        this.playStatus = "mdi-play";
      } else {
        this.playStatus = "mdi-pause";
      }
      this.$store.dispatch("changePalyBtnStatus", this.playStatus);
    }
  },
  computed: {
    rdrawer: {
      get() {
        return this.$store.state.drawer;
      },
      set(val) {
        this.$store.dispatch("changeDrawer", val);
      }
    }
  },
  components: {},
  mounted() {
    this.screenWidth = document.body.clientWidth;
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth;
        if (this.screenWidth < 1500) {
          this.drawer = false;
        } else {
          this.drawer = true;
        }
        this.$store.dispatch("changeDrawer", this.drawer);
      })();
    };
  }
};
</script>
<style scoped>
.right-sidebar {
  margin-top: 78px;
  margin-right: 8px;
  z-index: 1;
  box-shadow: 0px 0px 7px rgba(224, 139, 139, 0.4);
}
</style>
