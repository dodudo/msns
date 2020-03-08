import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    drawer: true,
    playStatus: "mdi-play"
  },
  mutations: {
    changeDrawer(state, drawer) {
      state.drawer = drawer;
    },
    changePalyBtnStatus(state, playStatus) {
      state.playStatus = playStatus;
    }
  },
  actions: {
    changeDrawer(context, drawer) {
      context.commit("changeDrawer", drawer);
    },
    changePalyBtnStatus(context, playStatus) {
      context.commit("changePalyBtnStatus", playStatus);
    }
  },
  modules: {}
});
