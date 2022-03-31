<template>
  <div class="row g-gs">
    <div class="col-xl-3 col-lg-4 col-sm-6" v-for="product in marketItems" :key="product">
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
      marketItems: null,
      SectionData,
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
      this.marketItems = getItems.data;
      // console.log(this.marketItems);
    },
  },
  created: function () {
    this.getItemsAll();
  },
};
</script>
