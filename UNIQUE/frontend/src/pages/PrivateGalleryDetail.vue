<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
      <!-- hero -->
      <div class="hero-wrap sub-header">
        <div class="container">
          <div class="hero-content text-center py-0">
            <h1>{{ exhibition.exhibitionTitle }}</h1>
            <nav aria-label="breadcrumb">
              <ol class="breadcrumb breadcrumb-s1 justify-content-center mt-3 mb-0">
                <li class="breadcrumb-item">
                  <router-link to="/">Home</router-link>
                </li>
                <li class="breadcrumb-item">
                  <router-link to="/PrivateGallery">PrivateGallery</router-link>
                </li>
                <li class="breadcrumb-item">
                  {{ exhibition.exhibitionTitle }}
                </li>
              </ol>
            </nav>
          </div>
          <!-- hero-content -->
        </div>
        <!-- .container-->
      </div>
      <!-- end hero-wrap -->
    </header>
    <!-- Blog  -->
    <section class="section-space-b blog-section">
      <div class="container">
        <!-- blog section -->
        <div class="row">
          <div class="col-lg-8">
            <div class="single-entry mb-5 mb-lg-0 mx-auto">
              <!-- <img :src="img" class="w-100 rounded mb-3" alt="" /> -->
              <!-- <img :src="reprentiveNft.fileUrl" class="w-100 rounded mb-3" alt="" /> -->
              <div class="text-center">
                <img :src="`https://j6e205.p.ssafy.io/${representiveNft.fileUrl}`" class="rounded mb-3" alt="representiveImage" />
              </div>
              <p class="single-entry-text mb-2 text-black d-flex flex-wrap align-items-center">
                Published <span class="dot-separeted"></span> {{ representiveNft.regDt }} <span class="dot-separeted"></span> By {{ representiveNft.nftAuthorName }}
              </p>
              <!-- <p class="single-entry-text mb-3">{{ SectionData.blogDetail.content }}</p>
              <p class="single-entry-text mb-3">{{ SectionData.blogDetail.contentTwo }}</p> -->
              <blockquote class="blockquote">
                <p>{{ exhibition.exhibitionDescription }}</p>
                <!-- <p>{{ test }}</p> -->
              </blockquote>

              <!-- <p class="single-entry-text mb-4">{{ SectionData.blogDetail.contentThree }}</p> -->
              <!-- <div class="row g-gs mb-4">
                <div class="col-lg-6 col-sm-6" v-for="img in SectionData.blogDetail.imgList" :key="img.id">
                  <img :src="img" class="w-100 rounded" alt="" />
                </div>
              </div>
              <p class="single-entry-text mb-3">{{ SectionData.blogDetail.contentFour }}</p>
              <h4 class="mb-2">{{ SectionData.blogDetail.title }}</h4>
              <p class="single-entry-text mb-2">{{ SectionData.blogDetail.contentFive }}</p>
              <div class="gap-2x"></div>
              <hr class="mt-0" />
              <div class="d-flex flex-wrap align-items-center justify-content-between">
                <ul class="tag-list my-1">
                  <li class="me-2 text-black fw-semibold">Tags:</li>
                  <li v-for="list in SectionData.blogDetail.tags" :key="list.id">
                    <router-link :to="list.path" class="tag-link">{{ list.title }}</router-link>
                  </li>
                </ul>
                <ul class="styled-icon my-1">
                  <li class="me-2 text-black fw-semibold">Share:</li>
                  <li v-for="list in SectionData.blogDetail.shares" :key="list.id">
                    <router-link :to="list.path"><em class="icon ni" :class="list.icon"></em></router-link>
                  </li>
                </ul>
              </div>
              <hr />
              <div class="comment-wrapper mt-5" id="comments">
                <h4 class="mb-4">{{ SectionData.commentData.title }}</h4>
                <Comments class="mb-5"></Comments>
                <div class="add-comment-wrap">
                  <h4 class="mb-1">{{ SectionData.formData.title }}</h4>
                  <p class="comment-desc">{{ SectionData.formData.content }}</p>
                  <Form class="mt-4"></Form>
                </div>
              </div> -->
              <!-- end comment-wrapper -->
            </div>
            <!-- end single-entry -->
          </div>
          <!-- end col-lg-8 -->
          <div class="col-lg-4 ps-lg-4">
            <div class="sidebar row">
              <!-- <div class="col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4 class="mb-3">{{ SectionData.sidebarData.sidebarWidget.title }}</h4>
                    <form action="#">
                      <div class="input-group">
                        <input type="text" class="form-control form-control-s1" :placeholder="SectionData.sidebarData.sidebarWidget.inputText" />
                        <button type="button" class="input-group-text"><em class="ni ni-search"></em></button>
                      </div>
                    </form>
                  </div>
                </div>
              </div> -->
              <!-- end sidebar-widget -->
              <div class="col-md-6 col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <div class="avatar avatar-1 mb-3 mx-auto">
                      <img :src="`https://j6e205.p.ssafy.io/${creator.profileImageUrl}`" alt="profile" />
                      <!-- <p>{{creator.profileImageUrl}}</p> -->
                    </div>
                    <!-- <h4 class="mb-2">{{ creator.memberId }}</h4> -->
                    <router-link :to="{ name: 'profile', params: { id: `${creator.memberSeq}` } }">
                      <h4 align="center" class="mb-2">{{ creator.memberId }}</h4>
                    </router-link>
                    <blockquote class="blockquote">
                      <p class="sidebar-text mb-3">{{ creator.memberBio }}</p>
                    </blockquote>
                    <!-- <ul class="styled-icon">
                      <li v-for="list in SectionData.sidebarData.sidebarWidgetTwo.icons" :key="list.id">
                        <router-link :to="list.path"><em class="icon ni" :class="list.icon"></em></router-link>
                      </li>
                    </ul> -->
                  </div>
                  <!-- end card-body -->
                </div>
              </div>
              <!-- end sidebar-widget -->
              <div class="col-md-6 col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4 align="center" class="mb-3">{{ SectionData.sidebarData.sidebarWidgetThree.title }}</h4>
                    <ul class="list-item">
                      <li v-for="list in SectionData.sidebarData.sidebarWidgetThree.catList" :key="list.id">
                        <p><em class="ni ni-chevron-right me-1"></em> {{ list.title }}</p>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!-- end sidebar-widget -->
              <!-- <div class="col-md-6 col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4 class="mb-3">{{ SectionData.sidebarData.sidebarWidgetFour.title }}</h4>
                    <ul class="posts">
                      <li class="d-flex align-items-center" v-for="item in SectionData.sidebarData.sidebarWidgetFour.postList" :key="item.id">
                        <router-link :to="item.path" class="thumbnail flex-shrink-0 me-3">
                          <img :src="item.img" alt="" />
                        </router-link>
                        <div class="flex-grow-1">
                          <h6 class="posts-title mb-1">
                            <router-link :to="item.path">{{ item.title }}</router-link>
                          </h6>
                          <p class="posts-meta">{{ item.date }}</p>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div> -->
              <!-- end sidebar-widget -->
              <!-- <div class="col-md-6 col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4 class="mb-3">{{ SectionData.sidebarData.sidebarWidgetFive.title }}</h4>
                    <ul class="tag-list">
                      <li v-for="tag in SectionData.sidebarData.sidebarWidgetFive.tagList" :key="tag.id">
                        <router-link :to="tag.path" class="tag-link">{{ tag.title }}</router-link>
                      </li>
                    </ul>
                  </div>
                </div>
              </div> -->
              <!-- end sidebar-widget -->
            </div>
            <!-- end sidebar -->
          </div>
          <!-- 리스트 띄우기 시작 -->
          <div class="container">
            <!-- filter -->
            <h3 class="my-4">All items in Gallery</h3>
            <hr style="height: 3px; background: black" />
            <!-- <div class="filter-box pb-5">
            <h3 class="mb-4">Filter by</h3>
            <div class="filter-btn-group">
              <a href="#" class="btn btn-sm filter-btn" :class="tab.class" v-for="tab in filterMenu" @click.prevent="setTab(tab, tab.id)" :key="tab.id">{{ tab.title }}</a>
            </div>
          </div> -->
            <!-- end filter-box -->
            <div class="row g-gs">
              <div class="col-xl-3 col-lg-4 col-sm-6" v-for="product in displayedRecords" :key="product.nft">
                <NftProducts :product="product.nft"></NftProducts>
              </div>
              <!-- end col -->
            </div>
            <!-- end end  -->
          </div>
          <div class="text-center mt-4 mt-md-5">
            <Pagination :records="this.total" v-model="page" :per-page="perPage"> 1</Pagination>
          </div>
          <!-- 리스트 띄우기 끝 -->
          <!-- end col-lg-4 -->
        </div>
        <!-- row -->
      </div>
      <!-- .container -->
    </section>
    <!-- end blog-section -->
    <Footer classname="bg-black on-dark"></Footer>
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import SectionData from "@/store/store.js";
import { mapState } from "vuex";
import Pagination from "v-pagination-3";
import NftProducts from "@/components/section/NftProducts";
export default {
  name: "PrivateGalleryDetail",
  components: {
    NftProducts,
    Pagination,
  },
  data() {
    return {
      SectionData,
      id: this.$route.params.id,
      title: "",
      img: "",
      avatar: "",
      userName: "",
      exhibition: null,
      exhibitionWorkList: null,
      creator: null,
      representiveNft: null,
      test: "Predictions for how secondary NFT sales will revolutionize creative industries. Elementum lacus, tempus aliquam turpis diam amet leo enim. Nisi enim condimentum tincidunt ornare nam adipiscing. Volutpat lacus, est hendrerit elit sed interdum. amet leo enimLorem ipsum dolor sit amet, consectetur adipiscing elit. Elementum lacus, tempus aliquam turpis diam amet leo enim. Nisi enim condimentum tincidunt ornare nam adipiscing. Volutpat lacus, est hendrerit elit sed interdum. amet leo enim.",
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
  mounted() {
    SectionData.blogData.blogListThree.forEach((element) => {
      if (this.id == element.id) {
        this.img = element.img;
        this.title = element.title;
        this.avatar = element.avatar;
        this.userName = element.userName;
      }
    });
  },
  methods: {
    async getExhibitionDetail() {
      const Exhibition = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/exhibition/detail/${this.$route.params.id}`,
        headers: {
          Authorization: this.authToken,
        },
      }).then((res) => {
        console.log(res.data);
        this.exhibition = res.data.exhibition;
        this.exhibitionWorkList = res.data.exhibitionWorkList;
        this.creator = res.data.memberList[0];
        this.representiveNft = res.data.exhibitionWorkList[0].nft;
        console.log(res.data.exhibitionWorkList[0].nft);
      });
      this.total = this.exhibitionWorkList.length;
    },
  },
  created: function () {
    this.getExhibitionDetail();
  },
  computed: {
    ...mapState(["authToken"]),
    displayedRecords() {
      if (!this.exhibitionWorkList) {
        this.getExhibitionDetail();
      }
      // 마켓아이템스에 아이템이 담겨져 있으면 현재 페이지에 맞춰서 잘라서 보내기
      else {
        const startIndex = this.perPage * (this.page - 1);
        const endIndex = startIndex + this.perPage;
        return this.exhibitionWorkList.slice(startIndex, endIndex);
      }
    },
  },
};
</script>
