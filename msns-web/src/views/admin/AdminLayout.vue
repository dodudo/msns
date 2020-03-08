<template>
  <v-app>
    <!-- 左侧边栏 -->
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant="miniVariant"
      persistent
      app
      fixed
      enable-resize-watcher
    >
      <v-list>
        <v-list-item class="px-2">
          <!-- 左侧导航栏头像 -->
          <v-list-item-avatar>
            <v-img src="https://randomuser.me/api/portraits/men/85.jpg"></v-img>
          </v-list-item-avatar>
        </v-list-item>
        <!-- 左侧导航栏昵称 -->
        <v-list-item link>
          <v-list-item-content>
            <v-list-item-title class="title">Sandra Adams</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>
      <!-- 左侧导航栏页面列表 -->
      <v-list>
        <v-list-group
          v-for="item in items"
          :key="item.title"
          v-model="item.active"
          :prepend-icon="item.action"
          no-action
        >
          <!-- 一级菜单 -->
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title v-text="item.title"></v-list-item-title>
            </v-list-item-content>
          </template>
          <!-- 二级菜单 -->
          <v-list-item
            :to="
              item.path == '/admin'
                ? item.path + subItem.path
                : '/admin' + item.path + subItem.path
            "
            link
            v-for="subItem in item.items"
            :key="subItem.title"
          >
            <v-list-item-content>
              <v-list-item-title v-text="subItem.title"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-group>
      </v-list>
    </v-navigation-drawer>
    <v-content>
      <v-toolbar color="red">
        <!-- 隐藏左侧菜单的按钮-->
        <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
        <!-- 收起左侧菜单的按钮-->
        <v-btn class="ml-2" icon @click.stop="miniVariant = !miniVariant">
          <v-icon
            v-html="miniVariant ? 'mdi-chevron-right' : 'mdi-chevron-left'"
          />
        </v-btn>
        <!-- 顶部导航标题 -->
        <v-flex xs3></v-flex>
        <v-toolbar-title v-text="title" />
        <v-spacer />

        <!-- 顶部导航用户菜单 -->
        <v-btn icon>
          <v-icon>mdi-account-box</v-icon>
        </v-btn>
      </v-toolbar>

      <v-breadcrumbs :items="items1"></v-breadcrumbs>

      <!--定义一个路由锚点，Layout的子组件内容将在这里展示-->
      <router-view />
    </v-content>
  </v-app>
</template>
<script>
import menus from "../../assets/menu";

export default {
  data() {
    return {
      drawer: true, // 左侧导航是否隐藏
      miniVariant: false, // 左侧导航是否收起
      title: "音乐社交网站后台管理", // 顶部导航条名称,
      menuMap: {}
    };
  },
  computed: {
    items() {
      return menus;
    },
    items1() {
      const arr = this.$route.path.split("/");
      var items = [];
      var href = "/admin";
      for (var i = 1; i < arr.length; i++) {
        var item = {};
        if (i == 1) {
          continue;
        }
        if (i == 2) {
          if (arr[i] == "main") {
            item.disabled = true;
            item.text = "统计";
          } else {
            item.disabled = false;
            item.text = this.menuMap[arr[i]].name;
          }
        }
        if (i == 3) {
          item.disabled = true;
          item.text = this.menuMap[arr[i - 1]][arr[i]];
        }
        href = href + "/" + arr[i];
        item.href = href;
        items.push(item);
      }

      return items;
    },
    item2() {
      const arr = this.$route.path.split("/");
      return this.menuMap[arr[1]][arr[2]];
    }
  },
  name: "App",
  watch: {},
  created() {
    menus.forEach(m => {
      const p1 = m.path.slice(1);
      this.menuMap[p1] = { name: m.title };
      m.items.forEach(i => {
        this.menuMap[p1][i.path.slice(1)] = i.title;
      });
    });
    // console.log(this.menuMap);
  }
};
</script>

<style scoped>
.box {
  width: 90%;
}
</style>
