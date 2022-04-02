import { createStore } from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";
import router from "@/router/index.js";
import Web3 from "web3";
// import getWeb3 from "../utils/getWeb3;";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
let web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"));

export default createStore({
  plugins: [createPersistedState()],
  state: {
    authToken: localStorage.getItem("jwt"),
    username: null,
    myAddress: null,
  },
  mutations: {
    SET_TOKEN: function (state, token) {
      state.authToken = token;
      localStorage.setItem("jwt", token);
    },
    REMOVE_TOKEN: function (state) {
      localStorage.removeItem("jwt");
      state.authToken = "";
    },
    SET_USERNAME: function (state, credentials) {
      state.username = credentials.username;
      console.log(state.username);
    },
    SET_ADDRESS: function (state, newAddress) {
      state.myAddress = newAddress;
      console.log(state.myAddress.address, typeof state.myAddress.address);
      return newAddress.address;
    },
  },
  actions: {
    // async registerWeb3({ commit }) {
    //   console.log("registerWeb3 Action being executed");
    //   try {
    //     let result = await getWeb3;
    //     console.log("registerWeb3Instance", result);
    //     commit("registerWeb3Instance", result);
    //   } catch (err) {
    //     console.log("error in action registerWeb3", err);
    //   }
    // },
    login: function ({ commit }, credentials) {
      axios({
        method: "post",
        url: `${SERVER_URL}/api/members/login`,
        data: credentials,
      })
        .then((res) => {
          console.log(res);
          commit("SET_TOKEN", res.headers.authorization);
          commit("SET_USERNAME", credentials);
          console.log(this.getters.isLogin);
          router.push({ name: "Home" });
        })
        .catch(() => {
          alert("로그인 정보가 일치하지 않습니다.");
        });
    },
    logout: function ({ commit }) {
      commit("REMOVE_TOKEN");
      // router.push({ name: "Login" });
    },
    signup: function ({ commit }, credentials) {
      let data = new FormData();
      data.append("memberId", credentials.memberId);
      data.append("memberPassword", credentials.memberPassword);
      axios({
        method: "post",
        url: `${SERVER_URL}/api/members/register`,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: data,
      })
        .then(() => {
          console.log("계좌 생성 성공");
          this.dispatch("login", credentials);
        })
        .catch(() => {
          alert("비밀번호가 일치하지 않거나 이미 가입되어 있습니다.");
        });
    },
    async wallet({ commit }) {
      const newAddress = await web3.eth.accounts.create();
      const myAddress = await commit("SET_ADDRESS", newAddress);
      const getbalance = await web3.eth.getBalance(newAddress.address);
      await console.log(getbalance, "계좌 조회");
      await console.log(newAddress.address, "잘가나용?");
      await console.log(this.state.authToken, "토큰은요?");
      await axios({
        method: "PUT",
        url: `${SERVER_URL}/api/members/wallet`,
        data: {
          wallet: newAddress.address,
        },
        headers: {
          Authorization: this.state.authToken,
        },
      })
        .then(() => {
          console.log("성공");
        })
        .catch(() => {
          alert("실패");
        });
    },
  },
  getters: {
    isLogin: function (state) {
      return state.authToken ? true : false;
    },
    config: function (state) {
      return {
        Authorization: `JWT ${state.authToken}`,
      };
    },
    myUsername: function (state) {
      return state.username;
    },
  },
});
