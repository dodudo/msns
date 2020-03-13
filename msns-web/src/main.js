import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./config";
import "./http";
import qs from "qs";
import fs from "fs";
import vuetify from "./plugins/vuetify";
import "vuetify/dist/vuetify.min.css"; // Ensure you are using css-loader

Vue.use(vuetify);
Vue.prototype.$qs = qs; //全局引用qs
Vue.prototype.$fs = fs;
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");