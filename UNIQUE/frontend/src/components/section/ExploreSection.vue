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
        <div class="col-xl-3 col-lg-4 col-sm-6" v-for="product in displayedRecords" :key="product.id">
          <Products class="button" :product="product"></Products>
        </div>
        <!-- end col -->
      </div>
      <!-- end end  -->
    </div>
    <div class="text-center mt-4 mt-md-5">
      <Pagination :records="this.total" v-model="page" :per-page="perPage"> 1</Pagination>
    </div>
    <!-- .container -->
    <!-- <pagination v-model="currentPage" :total-rows="rows" :per-page="perPage"></pagination> -->
  </section>
  <!-- end explore-section -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
// import SectionData from "@/store/store.js";
import Pagination from "v-pagination-3";
import Products from "@/components/section/Products";
import axios from "axios";
import { mapState } from "vuex";
// import Pagination from "../common/Pagination.vue";

export default {
  name: "ExploreSection",
  components: {
    Products,
    Pagination,
  },
  data() {
    // Pagination;
    return {
      nftMarketItems: null,
      page: 1,
      currentPage: 0,
      perPage: 8,
      total: 100,
      // SectionData,
      filterMenu: [
        //
        { id: 1, title: "All", class: "active", options: [{ category: "image" }, { category: "video" }, { category: "audio" }] },
        { id: 2, title: "Image", class: "", options: [{ category: "image" }] },
        { id: 3, title: "Video", class: "", options: [{ category: "video" }] },
        { id: 4, title: "Audio", class: "", options: [{ category: "audio" }] },
      ],
      selectedTab: "all",
      previous_active_id: 1,
    };
  },
  methods: {
    setTab(tab, id) {
      // 선택된 category 즉 tab의 길이가 3개면 all
      if (tab.options.length > 1) {
        this.selectedTab = "all";
        // console.log(this.selectedTab);
        // 아니면 카테고리만
      } else {
        this.selectedTab = tab.options[0].category;
        // console.log(this.selectedTab);
      }

      // 선택한 카테고리 필터 나오게
      if (this.previous_active_id === id) return;
      this.filterMenu.find((menu) => menu.id === this.previous_active_id).class = "";
      this.filterMenu.find((menu) => menu.id === id).class = "active";
      this.previous_active_id = id;
      this.getItemsAll();
    },
    // selectFilter(filterData) {
    //   this.filterData = filterData;
    // },
    async getItemsAll() {
      const newnftMarketItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/market`,
        headers: {
          // Authorization: token,
          Authorization: this.authToken,
        },
        // limit 부분 바꿔야할듯
        params: { limit: 100000, offset: 0, type: this.selectedTab, searchWord: "" },
        // params: { limit: this.perpage, offset: this.page * this.perpage, type: this.selectedTab, searchWord: "" },
      });
      this.total = newnftMarketItems.data.marketList.length;
      this.nftMarketItems = newnftMarketItems.data.marketList;
      console.log(newnftMarketItems.data);
      // console.log(this.nftMarketItems);
      // this.nftMarketItems = nftMarketItems.data;
      // this.perPage = 6;
      // this.page = 1;
      // this.Total = 100;
      // console.log(this.nftMarketItems);
    },
  },
  created: function () {
    // 맨 처음에 아이템들 가져오기
    this.getItemsAll();
  },
  // watch: {},
  computed: {
    ...mapState(["authToken"]),
    // 마켓아이템스에 아이템이 담겨져 있지 않으면 담아오고
    displayedRecords() {
      if (!this.nftMarketItems) {
        this.getItemsAll();
      }
      // 마켓아이템스에 아이템이 담겨져 있으면 현재 페이지에 맞춰서 잘라서 보내기
      else {
        const startIndex = this.perPage * (this.page - 1);
        const endIndex = startIndex + this.perPage;
        return this.nftMarketItems.slice(startIndex, endIndex);
      }
    },
    // pagedNumber() {
    //   if (this.nftMarketItems) {
    //     return this.nftMarketItems.length;
    //   }
  },
  //   filteredData() {
  //     return this.SectionData.productData.products.filter((data) => {
  //       if (this.selectedTab === null) return true;
  //       const opts = this.selectedTab.options.map((opt) => opt.category);
  //       return opts.includes(data.category);
  //     });
  //   },
  // }
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
