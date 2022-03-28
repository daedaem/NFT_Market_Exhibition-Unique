import Vue from "vue";
import Vuex from "vuex";
// import axios from "axios";
import createPersistedState from "vuex-persistedstate";
import getWeb3 from "../utils/getWeb3;";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: { isLogin: false },
  mutations: {},
  actions: {
    async registerWeb3({ commit }) {
      console.log("registerWeb3 Action being executed");
      try {
        let result = await getWeb3;
        console.log("registerWeb3Instance", result);
        commit("registerWeb3Instance", result);
      } catch (err) {
        console.log("error in action registerWeb3", err);
      }
    },
  },
  getters: {},
});
