import { createStore } from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";
import router from "@/router/index.js";
import Web3 from "web3";
// import getWeb3 from "../utils/getWeb3;";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const GANACHE_SERVER_URL = process.env.GANACHE_SERVER_URL;
let web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));

export default createStore({
  plugins: [createPersistedState()],
  state: {
    authToken: localStorage.getItem("jwt"),
    username: null,
    myAddress: null,
    ExhibitionsCards: [
      {
        id: 1,
        dateDay: 2,
        dateMonth: "April",
        cardImage: "https://cdn.pixabay.com/photo/2020/06/18/09/10/city-5312660__340.jpg",
        title: "Let's Go To Mars!",
        content:
          "Curabitur aliquet quam id dui posuere blandit. Pellentesque in ipsum id orci porta dapibus. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Vivamus suscipit tortor eget felis porttitor volutpat.<br/>Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Cras ultricies ligula sed magna dictum porta. Sed porttitor lectus nibh.<br/>Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus.",
        creators: "Curabitur aliquet quam id dui posuere blandit. Donec rutrum congue leo eget malesuada.",
        curators: "sapien massa, ziskp ejsep",
      },
      {
        id: 2,
        dateDay: 5,
        dateMonth: "February",
        cardImage: "https://cdn.pixabay.com/photo/2022/03/01/02/51/galaxy-7040416__480.png",
        title: "For Ukraine Artist Support the Cause",
        creators: "Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. ",
        curators: "Curabitur aliquet, quam id dui, posuere blandit.",
      },
      // {
      //   id: 3,
      //   dateDay: 31,
      //   dateMonth: "January",
      //   cardImage: "https://cdn.pixabay.com/photo/2020/03/08/15/25/cover-painting-4912734__340.jpg",
      //   title: "This Week's Unique Steals:",
      //   creators: "Praesent sapien massa, convallis a pellentesque nec.",
      //   curators: "Sed porttitor lectus nibh.",
      // },
      {
        id: 4,
        dateDay: 14,
        dateMonth: "January",
        cardImage: "https://upload.wikimedia.org/wikipedia/commons/a/a0/Picasso_-_P%C3%AAche_de_nuit_%C3%A0_Antibes_%28Q110152500%29.jpg",
        title: "Are you okay?",
        creators: "Vestibulum ante ipsum, luctus et ultrices ",
        curators: "primis faucibus",
      },
    ],
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
    REMOVE_ADDRESS: function (state) {
      state.myAddress = "";
    },
    REMOVE_USERNAME: function (state) {
      state.username = "";
    },
    SET_USERNAME: function (state, credentials) {
      state.username = credentials.memberId;
    },
    SET_ADDRESS: function (state, newAddress) {
      state.myAddress = newAddress;
      console.log(state.myAddress, typeof state.myAddress);
      // return newAddress.address;
    },
    EXHIBITION_CARDS: function (state, cards) {
      state.ExhibitionsCards = cards;
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
    EXHIBITION_CARDS: function ({ commit }) {
      commit("EXHIBITION_CARDS");
      // router.push({ name: "Login" });
    },
    login: function ({ commit }, credentials) {
      console.log(credentials, "credentials");
      // if (credentials)
      axios({
        method: "post",
        url: `${SERVER_URL}/api/members/login`,
        data: {
          memberId: credentials.memberId,
          memberPassword: credentials.memberPassword,
        },
      })
        .then((res) => {
          console.log(res, "여긴데여");
          if (res.data.memberAddress) {
            commit("SET_TOKEN", res.headers.authorization);
            commit("SET_USERNAME", credentials);
            // if (!res.data.memberAddress) {
            //   const result = wallet();
            //   console.log(result, " 나온건가");
            // }
            // 여기 없으니까 안됨
            commit("SET_ADDRESS", res.data.memberAddress);
            router.push({ name: "Home" });
          } else {
            this.dispatch("wallet");
            commit("SET_TOKEN", res.headers.authorization);
            commit("SET_USERNAME", credentials);
            // if (!res.data.memberAddress) {
            //   const result = wallet();
            //   console.log(result, " 나온건가");
            // }
            // 여기 없으니까 안됨
            // commit("SET_ADDRESS", res.data.memberAddress);
            router.push({ name: "Home" });
          }
          // wallet;

          // console.log(this.getters.isLogin);
          // console.log(res.data, " 요기요");
        })
        .catch(() => {
          alert("로그인 정보가 일치하지 않습니다.");
        });
    },
    logout: function ({ commit }) {
      commit("REMOVE_TOKEN");
      commit("REMOVE_ADDRESS");
      commit("REMOVE_USERNAME");

      // router.push({ name: "Login" });
    },
    signup: function ({ commit }, credentials) {
      let data = new FormData();
      data.append("memberId", credentials.memberId);
      data.append("memberPassword", credentials.memberPassword);
      // data.append("memberBio", credentials.memberBio);
      // data.append("file", credentials.file);
      // console.log(credentials, "credentials");
      axios({
        method: "post",
        url: `${SERVER_URL}/api/members/register`,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: data,
      })
        .then((res) => {
          console.log("계좌 생성 성공");
          this.dispatch("login", credentials);
        })
        .catch(() => {
          alert("비밀번호가 일치하지 않거나 이미 가입되어 있습니다.");
        });
    },
    async wallet({ commit }) {
      console.log("실행은?");
      let newAddress = await web3.eth.accounts.create();
      let myAddress = await commit("SET_ADDRESS", newAddress.address);
      let getbalance = await web3.eth.getBalance(newAddress.address);
      await console.log(getbalance, "계좌 조회");
      // await console.log(newAddress.address, "잘가나용?");
      await console.log(newAddress, "계좌정보");
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
