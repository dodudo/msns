import Vue from "vue";
import axios from "axios";
import config from "./config";

axios.defaults.baseURL = config.api; //设置axios的基本请求路径
axios.defaults.timeout = 2000; //设置axios请求时间

axios.loadData = async function(url) {
  const resp = await axios.get(url);
  return resp.data;
};
Vue.prototype.$http = axios;
