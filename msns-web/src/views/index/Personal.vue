<template>
  <v-container class="mx-0 ml-2" style="margin-top:52px;max-width:1112px">
    <v-row>
      <v-content>
        <v-img
          class="white--text align-end"
          src="https://picsum.photos/1920/1080?random"
          height="200"
        >
          <v-row align="center" class="px-4">
            <v-col cols="1">
              <v-avatar size="80">
                <img
                  src="https://cdn.vuetifyjs.com/images/john.jpg"
                  alt="John"
                />
              </v-avatar>
            </v-col>
            <v-col cols="9" class="ml-4">
              <v-row align="center">
                <h3 class="ml-3">哈哈哈</h3>
              </v-row>
              <v-row justify="center">
                <v-text-field
                  dense
                  flat
                  :outlined="outlined"
                  @mouseenter="outlined = true"
                  @mouseleave="outlined = false"
                  dark
                  hide-details
                  background-color="rgba(255,255,255,0)"
                  color="#fff"
                  solo
                  label="编辑个性签名"
                ></v-text-field>
              </v-row>
            </v-col>
          </v-row>
        </v-img>
      </v-content>
    </v-row>
    <v-row>
      <v-tabs height="66" color="red" class="elevation-2">
        <v-tabs-slider></v-tabs-slider>

        <v-tab to="/index/personal/info">
          <v-row align="center" justify="center">
            <v-icon color="blue">mdi-home</v-icon>
            <span class="ml-1">主页</span>
          </v-row>
        </v-tab>
        <v-tab to="/index/personal/dynamic">
          <v-row align="center" justify="center">
            <v-icon color="green">mdi-star-face</v-icon>
            <span class="ml-1">动态</span>
          </v-row>
        </v-tab>
        <v-tab to="/index/personal/favor">
          <v-row align="center" justify="center">
            <v-icon color="#FFD740">mdi-star</v-icon>
            <span class="ml-1">收藏</span>
          </v-row>
        </v-tab>
        <v-tab to="/index/personal/music">
          <v-row align="center" justify="center">
            <v-icon color="red">mdi-music-note</v-icon>
            <span class="ml-1">音乐</span>
          </v-row>
        </v-tab>
        <v-tab to="/index/personal/attention">
          <v-row align="center" justify="center">
            <v-icon color="purple">mdi-account-multiple</v-icon>
            <span class="ml-1">关注</span>
          </v-row>
        </v-tab>
        <v-tab to="/index/personal/fan">
          <v-row align="center" justify="center">
            <v-icon color="orange">mdi-fan</v-icon>
            <span class="ml-1">粉丝</span>
          </v-row>
        </v-tab>
      </v-tabs>
    </v-row>
    <v-row>
      <router-view />
    </v-row>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      outlined: false
    };
  },
  methods: {}
};
</script>
