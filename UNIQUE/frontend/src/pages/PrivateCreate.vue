<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
    </header>
    <HeroFour classname="hero-title" :title="SectionData.breadcrumbData.breadcrumbListPrivateCreate.title" :lists="SectionData.breadcrumbData.breadcrumbListPrivateCreate.navList"></HeroFour>
    <!-- login section -->
    <section class="explore-section pt-4">
      <div class="container">
        <!-- filter -->
        <div class="filter-box pb-5">
          <h3 class="mb-4">Filter by</h3>
          <div class="filter-btn-group">
            <a href="#" class="btn btn-sm filter-btn" :class="tab.class" v-for="tab in filterMenu" @click.prevent="setTab(tab, tab.id)" :key="tab.id">{{ tab.title }}</a>
          </div>
        </div>
        <div>
          <h3>Select Items for Gallery</h3>
          <br />
        </div>
        <!-- end filter-box -->
        <div class="row g-gs">
          <div class="col-xl-3 col-lg-4 col-sm-6" v-for="product in displayedRecords" :key="product">
            <SelectedProducts :selectedIds="selectedIds" :product="product" v-on:removeId="removeId" v-on:insertId="insertId"></SelectedProducts>
          </div>
          <!-- end col -->
        </div>
        <!-- end end  -->
        <div class="text-center mt-4 mt-md-5">
          <Pagination :records="this.total" v-model="page" :per-page="perPage"> 1</Pagination>
        </div>
        <form action="#" class="form-create mb-5 mb-lg-0">
          <!-- <div class="form-item mb-4">
          <h3 class="mb-3">Upload representative Image</h3>
          <div class="file-upload-wrap">
            <p class="file-name mb-4" id="file-name">PNG, GIF, WEBP, MP4 or MP3. Max 100mb.</p>
            <input id="file-upload" class="file-upload-input" data-target="file-name" type="file" enctype="multipart/form-data" @change="selectFile" hidden />
            <label for="file-upload" class="input-label btn btn-dark">Choose File</label>
          </div>
        </div> -->
          <div class="form-item mb-4">
            <div class="mb-4">
              <h3 class="mb-2">Title</h3>
              <input type="text" class="form-control form-control-s1" v-model="galleryName" placeholder="Input your Private Gallery name" />
            </div>
            <div class="mb-4">
              <h3 class="mb-2">Description</h3>
              <textarea name="message" class="form-control form-control-s1" v-model="galleryDescription" placeholder="Explain your Private Gallery"></textarea>
            </div>
          </div>
          <button type="button" data-bs-toggle="modal" data-bs-target="#createNftModal" class="btn btn-dark d-block mb-5" @click="createGallery()">Create Item</button>
        </form>
      </div>
      <!-- .container -->
      <!-- <pagination v-model="currentPage" :total-rows="rows" :per-page="perPage"></pagination> -->
    </section>
    <Footer classname="bg-black on-dark"></Footer>
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import SectionData from "@/store/store.js";
import Pagination from "v-pagination-3";
import HeroFour from "@/components/section/HeroFour.vue";
import SelectedProducts from "@/components/section/SelectedProducts";
import { mapState } from "vuex";
import axios from "axios";
import router from "@/router/index.js";
export default {
  name: "PrivateCreate",
  components: {
    HeroFour,
    SelectedProducts,
    Pagination,
  },
  data() {
    // Pagination;
    return {
      SectionData,
      selectedIds: [],
      newnftItems: null,
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
      galleryName: null,
      galleryDescription: null,
    };
  },
  methods: {
    createGallery() {
      axios({
        method: "POST",
        url: `${SERVER_URL}/api/exhibition/register`,
        headers: {
          Authorization: this.authToken,
        },
        data: {
          title: this.galleryName,
          description: this.galleryDescription,
          type: "PRI",
          nftSeqList: this.selectedIds,
        },
      }).then((res) => {
        console.log("갤러리 성공");
        router.push({ name: "PrivateGallery" });
      });
    },
    selectFile(data) {
      this.form.galleryName = data.galleryName;
      this.form.file = data.target.files[0];
    },
    insertId(Id) {
      this.selectedIds.push(Id);
    },
    removeId(Id) {
      this.selectedIds.splice(this.selectedIds.indexOf(Id), 1);
    },
    setPage() {},
    setTab(tab, id) {
      // 선택된 category 즉 tab의 길이가 3개면 all
      if (tab.options.length > 1) {
        this.selectedTab = "all";
        // 아니면 카테고리만
      } else {
        this.selectedTab = tab.options[0].category;
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
      const newnftItems = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/items/${this.myAddress}`,
        headers: {
          // Authorization: token,
          Authorization: this.authToken,
        },
      });
      this.total = newnftItems.data.nftList.length;
      this.newnftItems = newnftItems.data.nftList;
      console.log(this.newnftItems);
    },
  },
  created: function () {
    // 맨 처음에 아이템들 가져오기
    this.getItemsAll();
  },
  watch: {},
  computed: {
    ...mapState(["authToken", "myAddress"]),
    // 마켓아이템스에 아이템이 담겨져 있지 않으면 담아오고
    displayedRecords() {
      if (!this.newnftItems) {
        this.getItemsAll();
      }
      // 마켓아이템스에 아이템이 담겨져 있으면 현재 페이지에 맞춰서 잘라서 보내기
      else {
        const startIndex = this.perPage * (this.page - 1);
        const endIndex = startIndex + this.perPage;
        return this.newnftItems.slice(startIndex, endIndex);
      }
    },
    // pagedNumber() {
    //   if (this.nftMarketItems) {
    //     return this.nftMarketItems.length;
    //   }
  },
  mounted() {
    // function fileUpload(selector) {
    //   let elem = document.querySelectorAll(selector);
    //   if (elem.length > 0) {
    //     elem.forEach((item) => {
    //       item.addEventListener("change", function () {
    //         var target = document.getElementById(item.dataset.target);
    //         var allowedExtensions = ["jpg", "png", "gif", "webp", "mp4", "mp3"];
    //         var fileExtension = this.value.split(".").pop();
    //         var lastDot = this.value.lastIndexOf(".");
    //         var ext = this.value.substring(lastDot + 1);
    //         var extTxt = (target.value = ext);
    //         if (!allowedExtensions.includes(fileExtension)) {
    //           alert(extTxt + " file type not allowed, Please upload jpg, png, gif, webp, mp4 or mp3 file");
    //           target.innerHTML = "Please upload jpg, png, gif, webp, mp4 or mp3 file";
    //         } else {
    //           target.innerHTML = item.files[0].name;
    //         }
    //       });
    //     });
    //   }
    // }
    // fileUpload(".file-upload-input");
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
