<template>
<div>
  <div>조성현123</div>
  <hr>
  <button @click="getItemsAll">전체 데이터 가져오기</button>
  <ul v-for="item in items" :key="item.token_id">
    <li>{{item.token_id}}</li>
  </ul>
  <hr>
  <input type="text" v-model="inputaddress">
  <div>{{inputaddress}}</div>
  <button @click="getItmesByAddress(inputaddress)">주소로 데이터 가져오기</button>
  <ul v-for="aitem in aitems" :key="aitem.token_id">
    <li>{{aitem.token_id}}</li>
  </ul>
  <hr>
  <div>{{exampleTokenId}}</div>
  <button @click="getDetail(exampleTokenId)">해당 작품 detail 가져오기</button>
  <div>{{exampleResult}}</div>

  

  
</div>
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import Web3 from "web3";
import SectionData from "@/store/store.js";
import getAddressFrom from "../utils/AddressExtractor";
import ABIS from "../../../smart-contracts/build/contracts/SsafyNFT.json";
import SsafyNFT from "../../../smart-contracts/build/contracts/SsafyNFT.json";
const abi = ABIS.abi;
const CA = SsafyNFT.networks["5777"].address;

let web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));

export default {
  name: "Test",
  data() {
    return {
      SectionData,
      items:null,
      inputaddress: "",
      aitems: null,
      exampleTokenId: "123185123156123156",
      exampleResult: ""
    };
  },
  methods: {
    async getItmesAll() {
      const getItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/items`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0ODc4MDgzMX0.CEFASNbzeHivF75lnL7B_1Nv3OivjJGhrkTRNAGJGEqbV7xv5XVMQFdWxvw4WPjLwRHZXWwIucBV69Um-f8_dw",
          "Content-Type": "multipart/form-data",
        },
      });
      this.items = getItems
    },
    async getItmesByAddress(address) {
      const getItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/items?address=${address}`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0ODc4MDgzMX0.CEFASNbzeHivF75lnL7B_1Nv3OivjJGhrkTRNAGJGEqbV7xv5XVMQFdWxvw4WPjLwRHZXWwIucBV69Um-f8_dw",
          "Content-Type": "multipart/form-data",
        },
      });
      this.aitems = getItems
    },
    async getDetail(token_id) {
      const getDetails = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/items/${token_id}`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0ODc4MDgzMX0.CEFASNbzeHivF75lnL7B_1Nv3OivjJGhrkTRNAGJGEqbV7xv5XVMQFdWxvw4WPjLwRHZXWwIucBV69Um-f8_dw",
          "Content-Type": "multipart/form-data",
        },
      });
      this.exampleResult = getDetails
    }
  }
};
</script>
<style scoped></style>
