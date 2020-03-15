import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

function route(path, file, name, children) {
  return {
    exact: true,
    path,
    name,
    children,
    component: () => import("../views" + file)
  };
}
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  route("/login", "/index/Login", "Login"),
  route("/test", "/index/test", "test"),
  route("/register", "/index/Register", "Register"),
  route("/admin/login", "/admin/AdminLogin", "AdminLogin"),
  {
    path: "/",
    component: () => import("../views/index/Layout.vue"),
    redirect: "/index/main",
    children: [
      route("/index/main", "/index/Main.vue", "Main"),
      route("/index/music", "/index/Music.vue", "Music"),
      route("/index/search/:key", "/index/Search.vue", "Search"),
      route("/index/dynamic", "/index/Dynamic", "Dynamic"),
      {
        path: "/index/message",
        name: "Message",
        redirect: "/index/message/reply",
        component: () => import("../views/index/Message.vue"),
        children: [
          route("/index/message/reply", "/index/message/Reply", "Reply"),
          route("/index/message/love", "/index/message/Love", "Love"),
          route("/index/message/comment", "/index/message/Comment", "Comment")
        ]
      },
      {
        path: "/index/personal",
        name: "Personal",
        redirect: "/index/personal/info",
        component: () => import("../views/index/Personal.vue"),
        children: [
          route(
            "/index/personal/info",
            "/index/personal/PersonalInfo",
            "PersonalInfo"
          ),
          route(
            "/index/personal/dynamic",
            "/index/personal/PersonalDynamic",
            "PersonalDynamic"
          ),
          route("/index/personal/favor", "/index/personal/Favor", "Favor"),
          route(
            "/index/Personal/music",
            "/index/personal/PersonalMusic",
            "PersonalMusic"
          ),
          route(
            "/index/Personal/attention",
            "/index/personal/Attention",
            "Attention"
          ),
          route("/index/Personal/fan", "/index/personal/Fan", "Fan")
        ]
      }
    ]
  },
  {
    path: "/admin",
    name: "Admin",
    redirect: "/admin/main",
    component: () => import("../views/admin/AdminLayout.vue"),
    children: [
      route("/admin/main", "/admin/AdminMain", "AdminMain"),
      route(
        "/admin/dynamic",
        "/admin/dynamic/DynamicCategory",
        "DynamicCategory"
      ),
      route(
        "/admin/dynamic/category",
        "/admin/dynamic/DynamicCategory",
        "DynamicCategory"
      ),
      route(
        "/admin/dynamic/CategoryDelete",
        "/admin/dynamic/DynamicCategoryDelete",
        "DynamicCategoryDelete"
      ),
      route("/admin/dynamic/list", "/admin/dynamic/DynamicList", "DynamicList"),
      route(
        "/admin/dynamic/delete",
        "/admin/dynamic/DynamicDelete",
        "DynamicDelete"
      ),
      route(
        "/admin/dynamic/ReportList",
        "/admin/dynamic/DynamicReportList",
        "DynamicReportList"
      ),
      route("/admin/music", "/admin/music/MusicList", "MusicList"),
      route("/admin/music/list", "/admin/music/MusicList", "MusicList"),
      route("/admin/music/delete", "/admin/music/MusicDelete", "MusicDelete"),
      route(
        "/admin/music/statistics",
        "/admin/music/MusicStatistics",
        "MusicStatistics"
      ),
      route("/admin/setting", "/admin/setting/AdminList", "Admin"),
      route("/admin/setting/list", "/admin/setting/AdminList", "AdminList"),
      route(
        "/admin/setting/delete",
        "/admin/setting/AdminDelete",
        "AdminDelete"
      ),
      route("/admin/user", "/admin/user/UserList", "User"),
      route("/admin/user/list", "/admin/user/UserList", "UserList"),
      route(
        "/admin/user/delete",
        "/admin/user/UserDelete",
        "UserDelete"
      )
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  routes
});

export default router;