<template>
  <v-card flat>
    <v-navigation-drawer
      v-model="drawer"
      height="370"
      width="380"
      class="right-sidebar pa-4"
      fixed
      light
      floating
    >
      <v-avatar size="80">
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
      </v-avatar>
      <v-card class="title" flat>哈哈哈</v-card>
      <v-row justify="center">
        <v-col
          :style="{ color: color1, cursor: pointer1 }"
          @mouseover="(color1 = 'red'), (pointer1 = 'pointer')"
          @mouseleave="color1 = ''"
          class="btn"
          style="text-align:center"
          cols="4"
          @click="toAttention()"
        >
          <div>关注</div>
          <div>15</div>
        </v-col>
        <v-col
          :style="{ color: color2, cursor: pointer2 }"
          @mouseover="(color2 = 'red'), (pointer2 = 'pointer')"
          @mouseleave="color2 = ''"
          cols="4"
          style="text-align:center"
          @click="toFan()"
        >
          <div>粉丝</div>
          <div>25</div>
        </v-col>
        <v-col
          :style="{ color: color3, cursor: pointer3 }"
          @mouseover="(color3 = 'red'), (pointer3 = 'pointer')"
          @mouseleave="color3 = ''"
          cols="4"
          style="text-align:center"
          @click="toDynamic()"
        >
          <div>动态</div>
          <div>1</div>
        </v-col>
      </v-row>
      <v-list class="pa-0">
        <v-list-item-group color="red">
          <v-list-item @click="toPersonal()">
            <div>
              <v-icon class="mx-4">mdi-account</v-icon>
              <span>个人中心</span>
            </div>
          </v-list-item>
          <v-list-item @click="toMessage()">
            <div>
              <v-icon class="mx-4">mdi-message-text</v-icon>
              <span>消息</span>
            </div>
          </v-list-item>
          <v-list-item @click="toFavor">
            <div>
              <v-icon class="mx-4">mdi-star</v-icon>
              <span>收藏</span>
            </div>
          </v-list-item>
        </v-list-item-group>
      </v-list>
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
    color1: "",
    color2: "",
    color3: "",
    pointer1: "",
    pointer2: "",
    pointer3: ""
  }),
  methods: {
    toPersonal() {
      this.$router.push({ path: "/index/personal/info" });
    },
    toDynamic() {
      this.$router.push({ path: "/index/personal/dynamic" });
    },
    toFavor() {
      this.$router.push({ path: "/index/personal/favor" });
    },
    toMusic() {
      this.$router.push({ path: "/index/personal/music" });
    },
    toMessage() {
      this.$router.push({ path: "/index/message" });
    },
    toFan() {
      this.$router.push({ path: "/index/personal/fan" });
    },
    toAttention() {
      this.$router.push({ path: "/index/personal/attention" });
    }
  },
  computed: {
    drawer: {
      get() {
        return this.$store.state.drawer;
      },
      set(val) {
        this.$store.dispatch("changeDrawer", val);
      }
    }
  }
};
</script>
<style scoped>
.right-sidebar {
  margin-top: 78px;
  margin-left: 8px;
  z-index: 1;
  box-shadow: 0px 0px 7px rgba(224, 139, 139, 0.4);
  text-align: center;
}
</style>
