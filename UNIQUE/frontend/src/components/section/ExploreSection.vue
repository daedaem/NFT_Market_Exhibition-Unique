<template>
  <section class="explore-section pt-4">
    <div class="container">
      <!-- filter -->
      <div class="filter-box pb-5">
        <h3 class="mb-4">Filter by</h3>
        <div class="filter-btn-group">
          <a href="#" class="btn btn-sm filter-btn" :class="tab.class" v-for="tab in filterMenu" @click.prevent="setTab(tab, tab.id)" :key="tab.id">{{ tab.title }}</a>
        </div>
      </div>
      <!-- end filter-box -->
      <div class="row g-gs">
        <div class="col-xl-3 col-lg-4 col-sm-6" v-for="product in nftMarketItems" :key="product.id">
          <Products :product="product"></Products>
        </div>
        <!-- end col -->
      </div>
      <!-- end end  -->
    </div>
    <!-- .container -->
  </section>
  <!-- end explore-section -->
  <Pagination :records="this.total" v-model="page" :per-page="perPage"></Pagination>
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
// import SectionData from "@/store/store.js";
import Pagination from "v-pagination-3";

import Products from "@/components/section/Products";
import axios from "axios";

export default {
  name: "ExploreSection",
  components: {
    Products,
    Pagination,
  },
  data() {
    return {
      nftMarketItems: null,
      page: 0,
      perPage: 6,
      Total: 20,
      // SectionData,
      filterMenu: [
        { id: 1, title: "All", class: "active", options: [{ category: "image" }, { category: "video" }, { category: "audio" }] },
        { id: 2, title: "Image", class: "", options: [{ category: "image" }] },
        { id: 3, title: "Video", class: "", options: [{ category: "video" }] },
        { id: 4, title: "Audio", class: "", options: [{ category: "audio" }] },
      ],
      selectedTab: "",
      previous_active_id: 1,
    };
  },
  methods: {
    setTab(tab, id) {
      this.selectedTab = tab;
      if (this.previous_active_id === id) return;
      this.filterMenu.find((menu) => menu.id === this.previous_active_id).class = "";
      this.filterMenu.find((menu) => menu.id === id).class = "active";
      this.previous_active_id = id;
    },
    selectFilter(filterData) {
      this.filterData = filterData;
    },
    async getItemsAll() {
      const getItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/items`,
        headers: {
          // Authorization: token,
          Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0OTMxMjA0OX0.XlFGY8_2TU2KyQcju3n0qHOYOJvvt9jZ40ZLSlzgdCnHsSEsl63xh3NW-1M2Px6L3TQ5Z-gSpsVsA5qEf1an_A",
        },
        params: { limit: 6, offset: 0, nftType: this.selectedTab, searchWord: "" },
      });
      this.nftMarketItems = getItems.data;
      this.perPage = 6;
      this.page = 1;
      this.Total = 100;
      console.log(this.nftMarketItems);
    },
  },
  created: function () {
    this.getItemsAll();
  },
  computed: {
    pagedNumber() {
      return (this.nftMarketItems = s);
    },
    //   filteredData() {
    //     return this.SectionData.productData.products.filter((data) => {
    //       if (this.selectedTab === null) return true;
    //       const opts = this.selectedTab.options.map((opt) => opt.category);
    //       return opts.includes(data.category);
    //     });
    //   },
    // },
  },
};
</script>

<style lang="css" scoped>
.details {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}
.author-link {
  z-index: 2;
  position: relative;
}
</style>
