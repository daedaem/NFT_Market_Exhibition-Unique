<template>
  <div class="page-wrap" v-if="isLogin === true">
    <!-- header  -->
    <header class="header-section has-header-main bg-pattern-3">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
    </header>
    <!-- create -->
    <section class="create-section section-space-b pt-4 pt-md-5 mt-md-4">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-8">
            <div class="section-head-sm">
              <!-- <router-link :to="SectionData.createData.path" class="btn-link fw-semibold"><em class="ni ni-arrow-left"></em> {{ SectionData.createData.btnText }}</router-link> -->
              <router-link :to="{ name: 'Home' }" class="btn-link fw-semibold"><em class="ni ni-arrow-left"></em> Home </router-link>
              <h1 class="mt-2">{{ username }}'s Wallet</h1>
            </div>
          </div>
          <!-- end col -->
          <div class="col-lg-8">
            <form action="#" class="form-create mb-5 mb-lg-0">
              <div class="form-item mb-4">
                <h5 class="mb-3">My account's address</h5>
                <input class="form-control form-control-s1 col-7" type="text" :value="`${myAddress}`" aria-label="Disabled input example" disabled readonly />
              </div>
              <!-- end form-item -->
              <!-- 작품 제목 및 설명 입력  -->
              <div class="mb-4">
                <h5 class="mb-2">Balance of SSF</h5>
                <div class="d-flex">
                  <input type="text" class="form-control form-control-s1 col-7" v-model="balanceOf" placeholder="&#8383;&ensp;SSF" />
                  <!-- end form-item -->
                  <button type="button" class="btn btn-dark d-block ms-1" @click="checkMyAccount">Inquiry</button>
                </div>
              </div>
              <div class="form-item mb-4">
                <div class="mb-4">
                  <h5 class="mb-1">Create New Wallet</h5>
                  <div class="d-flex">
                    <input class="form-control form-control-s1 col-7" placeholder="Your private key" disabled type="text" v-model="password" />
                    <!-- end form-item -->
                    <button type="button" @click="createWallet" class="btn btn-dark d-block ms-1">Create</button>
                  </div>
                </div>

                <div class="mb-4">
                  <h5 class="mb-2">
                    Get Coin
                    <div class="d-flex">
                      <input type="text" class="form-control form-control-s1 col-7" v-model="amountOfToken" placeholder="e. g. Redeemable T-Shirt with logo" /><button
                        @click="getToken"
                        type="button"
                        class="btn btn-dark d-block ms-1"
                      >
                        Amount
                      </button>
                    </div>
                  </h5>
                </div>
              </div>
              <!-- end form-item -->
            </form>
          </div>
          <!-- endn col -->
        </div>
        <!-- row-->
      </div>
      <!-- container -->
    </section>
    <!-- create-section -->
    <!-- Footer  -->
    <Footer classname="bg-black on-dark"></Footer>
    <!-- first Modal -->
    <!-- 등록하려는 아이템의 제목, 설명, 파일이 모두 있을 때 다음 모달로 -->
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import Web3 from "web3";
import SectionData from "@/store/store.js";
import getAddressFrom from "../utils/AddressExtractor";
// import ABI from "../../common/ABI";
// const abi = ABI.CONTRACT_ABI.NFT_ABI;
import SsafyNFT from "../../smart-contracts/build/contracts/SsafyNFT.json";
import { mapState } from "vuex";
import { mapActions, mapGetters } from "vuex";

const NFT_ABI = SsafyNFT.abi;
// const NFT_CA = "0x6c927304104cdaa5a8b3691e0ade8a3ded41a333";
const NFT_CA = SsafyNFT.networks["202112031219"].address;

// 네트워크 연결
const GANACHE_SERVER_URL = "http://20.196.209.2:8545";

let web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL)); // let Web3 = require("web3");
// let web3 = new Web3();
// web3.setProvider(new web3.providers.HttpProvider("http://127.0.0.1:8545"));
// let webs = new Web3("http://127.0.0.1:7545");

// let pollWeb3 = state => {

export default {
  name: "Wallet",
  data() {
    return {
      // myAccount: null,
      balanceOf: 0,
      SectionData,
      selected: "Select Collection",
      options: ["Select Collection", "Abstraction", "Patternlicious", "Skecthify", "Cartoonism", "Virtuland", "Papercut"],
      // d
      amountOfToken: 0,
      authorPrivateKey: null,
      password: null,
    };
  },
  methods: {
    checkMyAccount() {
      console.log(SERVER_URL);
      axios({
        method: "get",
        url: `${SERVER_URL}/api/web3j/getbalance`,
        headers: {
          // Authorization: token,
          Authorization: this.authToken,
        },
      }).then((res) => {
        // console.log(res, "내금액");
        this.balanceOf = res.data.ssf;
      });
    },
    // async addNewAddress() {},
    getToken() {
      if (this.amountOfToken <= 0) {
        alert("Please, requset more than 0 SSF");
      } else if (this.amountOfToken >= 100) {
        alert("Please, request less than 100 SSF");
      } else {
        axios({
          method: "get",
          url: `${SERVER_URL}/api/web3j/transfer/${this.amountOfToken}`,
          headers: {
            // Authorization: token,
            Authorization: this.authToken,
          },
          data: { ssf: this.amountOfToken },
        }).then((res) => {
          this.balanceOf = res.data.ssf;
          console.log(res, "내금액");
        });
      }
    },

    ...mapActions(["wallet"]),
    async createWallet() {
      // if ((this.password = "myaddress")) {
      let newAddress = await web3.eth.accounts.create();
      this.password = newAddress.privateKey;
      this.$store.dispatch("myAddress", newAddress.address);
      alert("Warning: keep your private key safely \n Private key : " + newAddress.privateKey);
      this.balanceOf = 0;
      this.amountOfToken = 0;
      // let myAddress = await commit("SET_ADDRESS", newAddress.address);
      // const results = await this.wallet().then((res) => console.log(res, "res"));
      // const ps = await results.privateKey;
      // console.log(results, "results");
      // console.log(ps, "private");
      // this.password =

      // privatekey;
      // alert("Warning : keep your private key safely", "`${private}`");
      // console.log(await results.privateKey);

      // let getbalance = await web3.eth.getBalance(newAddress.address);
      //     // await console.log(getbalance, "계좌 조회");
      //     // await console.log(newAddress.address, "잘가나용?");
      //     alert("Warning: keep your private key safely, Private key:" + this.myAddress);
      //     // await console.log(this.state.authToken, "토큰은요?");
      //   } else {
      //     alert("Please check your Password");
      //   }
      // },
    },
  },
  created() {
    // if (this.authToken == null) {
    //   alert("Please, login");
    //   this.$router.push({ name: "login" });
    // }
  },
  computed: {
    ...mapState(["myAddress", "username", "authToken"]),
    ...mapGetters(["isLogin"]),
  },
};
</script>
<style scoped>
.form-control-s1 {
  width: 60%;
}
</style>
