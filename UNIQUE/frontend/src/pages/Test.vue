<template>
<div>
  <div>조성현123</div>
  <hr>
  <button @click="getItemsAll">전체 데이터 가져오기</button>
  <ul v-for="item in items" :key="item">
    <li>{{item.nftName}}</li>
    <li>{{item.nftDescription}}</li>
    <li>{{item.nftType}}</li>
    <li>{{item.nftAuthorName}}</li>
    <li>{{item.nftTokenId}}</li>
    <hr>
  </ul>
  <hr>
  <input type="text" v-model="inputaddress">
  <div>{{inputaddress}}</div>
  <button @click="getItmesByAddress(inputaddress)">주소로 데이터 가져오기</button>
  <ul v-for="aitem in aitems" :key="aitem">
    <li>{{aitem.nftName}}</li>
    <li>{{aitem.nftDescription}}</li>
    <li>{{aitem.nftType}}</li>
    <li>{{aitem.nftAuthorName}}</li>
    <li>{{aitem.nftTokenId}}</li>
    <hr>
  </ul>
  <hr>
  <div>{{exampleTokenId}}</div>
  <button @click="getDetail(exampleTokenId)">해당 작품 detail 가져오기</button>
  <div>{{exampleResult.nftName}}</div>
  <div>{{exampleResult.nftDescription}}</div>
  <div>{{exampleResult.nftType}}</div>
  <div>{{exampleResult.nftAuthorName}}</div>
  <div>{{exampleResult.nftTokenId}}</div>

</div>
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import Web3 from "web3";
import SectionData from "@/store/store.js";
import getAddressFrom from "../utils/AddressExtractor";
import ABIS from "../../smart-contracts/build/contracts/SsafyNFT.json";
import SsafyNFT from "../../smart-contracts/build/contracts/SsafyNFT.json";
const abi = ABIS.abi;
const CA = SsafyNFT.networks["1377"].address;

let web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"));

export default {
  name: "Test",
  data() {
    return {
      SectionData,
      items:null,
      inputaddress: "",
      aitems: null,
      exampleTokenId: 7,
      exampleResult: ""
    };
  },
  methods: {
    async getItemsAll() {
      const getItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/items`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0OTMxMjA0OX0.XlFGY8_2TU2KyQcju3n0qHOYOJvvt9jZ40ZLSlzgdCnHsSEsl63xh3NW-1M2Px6L3TQ5Z-gSpsVsA5qEf1an_A",
        },
      });
      this.items = getItems.data
      console.log(this.items);
    },
    async getItmesByAddress(address) {
      const getItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/items/${address}`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0OTMxMjA0OX0.XlFGY8_2TU2KyQcju3n0qHOYOJvvt9jZ40ZLSlzgdCnHsSEsl63xh3NW-1M2Px6L3TQ5Z-gSpsVsA5qEf1an_A",
        },
      });
      this.aitems = getItems.data
      console.log(this.aitems)
    },
    async getDetail(token_id) {
      const getDetails = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/details/${token_id}`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0OTMxMjA0OX0.XlFGY8_2TU2KyQcju3n0qHOYOJvvt9jZ40ZLSlzgdCnHsSEsl63xh3NW-1M2Px6L3TQ5Z-gSpsVsA5qEf1an_A",
        },
      });
      this.exampleResult = getDetails.data[0]
      console.log(this.exampleResult)
    }
  }
};
</script>
<style scoped></style>
