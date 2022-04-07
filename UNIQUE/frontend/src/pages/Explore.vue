<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
      <!-- hero -->
      <HeroFour classname="hero-title" :title="SectionData.breadcrumbData.breadcrumbList.title" :lists="SectionData.breadcrumbData.breadcrumbList.navList"></HeroFour>
    </header>
    <!-- Explore section  -->
    <ExploreSection></ExploreSection>
    <!-- top creators -->
    <!-- <TopCreators classname="section-space-t"></TopCreators> -->
    <br><br><br><br>
    <Featured></Featured>
    <!-- Collections -->
    <!-- <Collections></Collections> -->
    <!-- Footer  -->
    <Footer classname="bg-black on-dark"></Footer>
  </div>
  <!-- end page-wrap -->
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
// Import component data. You can change the data in the store to reflect in all component
// import axios from "axios";
// import Web3 from "web3";
import SectionData from "@/store/store.js";
import HeroFour from "@/components/section/HeroFour.vue";
// import ABIS from "../../smart-contracts/build/contracts/SsafyNFT.json";
// import SsafyNFT from "../../smart-contracts/build/contracts/SsafyNFT.json";

// const abi = ABIS.abi;
// console.log(abi);
// const CA = SsafyNFT.networks["202112031219"].address;
// console.log(CA);

// 네트워크 연결
// GANACHE_SERVER_URL="http://20.196.209.2:8545"

// let web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
export default {
  name: "Explore",
  components: {
    HeroFour,
  },
  data() {
    return {
      SectionData,
    };
  },
};
</script>
