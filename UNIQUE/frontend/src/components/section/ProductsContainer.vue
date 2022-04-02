<template>
  <div class="row g-gs">
    <div class="col-xl-3 col-lg-4 col-sm-6" v-for="product in nftMarketItems" :key="product.id">
      <Products :product="product"></Products>
    </div>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import SectionData from "@/store/store.js";
import Products from "@/components/section/Products";
import axios from "axios";

export default {
  name: "ProductsContainer",
  components: {
    Products,
  },
  data() {
    return {
      nftMarketItems: null,
      SectionData,
    };
  },
  methods: {
    async getItemsAll() {
      const newnftMarketItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/market`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0OTMxMjA0OX0.XlFGY8_2TU2KyQcju3n0qHOYOJvvt9jZ40ZLSlzgdCnHsSEsl63xh3NW-1M2Px6L3TQ5Z-gSpsVsA5qEf1an_A",
        },
        params: { limit: 8, offset: 0, type: "all", searchWord: "" },
      });
      this.nftMarketItems = newnftMarketItems.data.marketList;
      console.log(this.nftmarketItems);
    },
  },
  created: function () {
    this.getItemsAll();
  },
  computed: {
    // 마켓아이템스에 아이템이 담겨져 있지 않으면 담아오고
    // 마켓아이템스에 아이템이 담겨져 있으면 현재 페이지에 맞춰서 잘라서 보내기
    // else {
    //   const startIndex = this.perPage * (this.page - 1);
    //   const endIndex = startIndex + this.perPage;
    //   return this.nftMarketItems.slice(startIndex, endIndex);
  },
};
</script>
