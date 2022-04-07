import { createStore } from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";
import router from "@/router/index.js";
import Web3 from "web3";
// import getWeb3 from "../utils/getWeb3;";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
// const SERVER_URL = http://localhost:8081;

const GANACHE_SERVER_URL = "http://20.196.209.2:8545";

let web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));

export default createStore({
  plugins: [createPersistedState()],
  state: {
    authToken: localStorage.getItem("jwt"),
    username: null,
    myAddress: null,
    userId: null,
    password: null,
    ExhibitionsCards: [
      {
        id: 1,
        dateDay: 1,
        dateMonth: "April",
        cardImage: "https://cdn.pixabay.com/photo/2020/06/18/09/10/city-5312660__340.jpg",
        title: "Let's Go To Mars!",
        content:
          "Curabitur aliquet quam id dui posuere blandit. Pellentesque in ipsum id orci porta dapibus. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Vivamus suscipit tortor eget felis porttitor volutpat.<br/>Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Cras ultricies ligula sed magna dictum porta. Sed porttitor lectus nibh.<br/>Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus.",
        creators: "Curabitur aliquet quam id dui posuere blandit. Donec rutrum congue leo eget malesuada.",
        curators: "Sunghyun",
        image: [
          "https://w.namu.la/s/2405a2a9d1d8f5f6ab689cb37ed1806ba4a8a0107b23242252f23f1e526c0ead19f9c2a1263a266538519a1a6de9c2becb62f7d20d8e4f7745a28b137c98a4d9dddb882ebbaeac0b4ca27300e9d4b81b512e46e34a0f309d6e14342cf9b49cb7",
          "https://imgnn.seoul.co.kr/img/upload/2016/10/11/SSI_20161011144833_V.jpg",
        ],
      },
      {
        id: 2,
        dateDay: 8,
        dateMonth: "Februrary",
        cardImage: "https://images.prismic.io/superrare/6d3a6dcc-f22d-4bad-ae66-7c16dc5d0c7f_LunaIkuta-SuperRare.png?auto=compress,format",
        title: "Vogue Singapore x Luna Ikuta",
        creators: "Praesent sapien massa, convallis a pellentesque nec.",
        curators: "Sunghyun",
        content:
          "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.",
        image: ["https://img.vogue.co.kr/vogue/2020/08/style_5f45be6804e86-1040x1400.jpg", "https://img.vogue.co.kr/vogue/2020/08/style_5f45bea99dec2-1049x1400.jpg"],
      },
      {
        id: 3,
        dateDay: 5,
        dateMonth: "February",
        cardImage: "https://cdn.pixabay.com/photo/2022/03/01/02/51/galaxy-7040416__480.png",
        title: "For Ukraine Artist Support the Cause",
        creators: "Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. ",
        curators: "Sunghyun",
        content:
          "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish.",
        image: ["https://t1.daumcdn.net/cfile/tistory/0153AF4F517F395F14", "http://kunsang1001.cafe24.com/main_images_set/x9788926387696.jpg"],
      },
      {
        id: 4,
        dateDay: 14,
        dateMonth: "January",
        cardImage: "https://upload.wikimedia.org/wikipedia/commons/a/a0/Picasso_-_P%C3%AAche_de_nuit_%C3%A0_Antibes_%28Q110152500%29.jpg",
        title: "Are you okay?",
        creators: "Vestibulum ante ipsum, luctus et ultrices ",
        curators: "Sunghyun",
        content:
          "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. ",
        image: ["https://t1.daumcdn.net/cfile/tistory/9975434D5BD3F11520", "https://newsimg.hankookilbo.com/cms/articlerelease/2021/06/29/078107fe-18fa-4b42-9234-acd3e35ed68d.jpg"],
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
    REMOVE_ID: function (state) {
      state.userId = "";
    },
    SET_USERNAME: function (state, credentials) {
      state.username = credentials.memberId;
    },
    // return newAddress.address;

    SET_ADDRESS: function (state, newAddress) {
      state.myAddress = newAddress;
      console.log(state.myAddress, typeof state.myAddress);
      // return newAddress.address;
    },
    SET_ID: function (state, newId) {
      state.userId = newId;
    },
    // EXHIBITION_CARDS: function (state, cards) {
    //   state.ExhibitionsCards = cards;
    // },
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
    // EXHIBITION_CARDS: function ({ commit }) {
    //   commit("EXHIBITION_CARDS");
    //   // router.push({ name: "Login" });
    // },
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
            commit("SET_ID", res.data.memberSeq);
            router.push({ name: "Home" });
          } else {
            this.dispatch("wallet");
            commit("SET_TOKEN", res.headers.authorization);
            commit("SET_USERNAME", credentials);
            commit("SET_ID", res.data.memberSeq);
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
      commit("REMOVE_ID");
      router.push({ name: "Home" });
      // router.push({ name: "Login" });
    },
    signup: function ({ commit }, credentials) {
      let data = new FormData();
      data.append("memberId", credentials.memberId);
      data.append("memberPassword", credentials.memberPassword);
      data.append("memberBio", credentials.memberBio);
      data.append("profileImage", credentials.file);
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
    myAddress: function ({ commit }, credentials) {
      let address = credentials;
      axios({
        method: "PUT",
        url: `${SERVER_URL}/api/members/wallet`,
        data: {
          wallet: address,
        },
        headers: {
          Authorization: this.state.authToken,
        },
      })
        .then((res) => {
          console.log("성공");
        })
        .catch(() => {
          alert("실패");
        });
      commit("SET_ADDRESS", address);
    },
    async wallet({ commit }) {
      console.log("실행은?");
      let newAddress = await web3.eth.accounts.create();
      let myAddress = await commit("SET_ADDRESS", newAddress.address);
      alert("Warning: keep your private key safely \n Private key : " + newAddress.privateKey);
      // let getbalance = await web3.eth.getBalance(newAddress.address);
      // await console.log(getbalance, "계좌 조회");
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
        .then((res) => {
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
