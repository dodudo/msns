<template>
  <v-container
    style="margin-top:60px;background-color:rgba(255,255,255,.2)"
    width="1114"
    class="ml-2 d-flex justify-left"
  >
    <div style="width:160px">
      <v-navigation-drawer
        permanent
        fixed
        light
        height="100vh"
        class="ml-4 sidebar"
        floating
        style="margin-top:78px"
        width="160"
      >
        <v-card flat class="mx-auto my-3" style="text-align:center">
          <v-icon color="red" class="mr-1">mdi-email</v-icon>消息中心
        </v-card>
        <v-divider></v-divider>
        <v-list nav two-line light>
          <v-list-item-group>
            <v-list-item color="red" to="/index/message/reply" link>
              <v-list-item-title style="text-align:center"
                >回复我的</v-list-item-title
              >
            </v-list-item>
            <v-list-item color="red" to="/index/message/love" link>
              <v-list-item-title style="text-align:center"
                >收到的赞</v-list-item-title
              >
            </v-list-item>
            <v-list-item color="red" to="/index/message/comment" link>
              <v-list-item-title style="text-align:center"
                >我的评论</v-list-item-title
              >
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>
    </div>
    <v-card width="930" class="ml-2 mt-2">
      <router-view />
    </v-card>
  </v-container>
</template>
<script>
export default {
  data: () => ({})
};
</script>
<style scoped>
.sidebar {
  margin-top: 78px;
  margin-right: 8px;
  z-index: 1;
  box-shadow: 0px 0px 7px rgba(224, 139, 139, 0.4);
}
</style>
