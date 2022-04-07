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
            <h1>{{ ExhibitionsCards[0].title }}</h1>
            <nav aria-label="breadcrumb">
              <ol class="breadcrumb breadcrumb-s1 justify-content-center mt-3 mb-0">
                <li class="breadcrumb-item">
                  <router-link to="/">Home</router-link>
                </li>
                <li class="breadcrumb-item">
                  <router-link to="/exhibition">Exhibition</router-link>
                </li>
                <li class="breadcrumb-item">
                  {{ ExhibitionsCards[0].title }}
                </li>
              </ol>
            </nav>
          </div>
          <img :src="`${ExhibitionsCards[0].cardImage}`" class="w-100 rounded mt-4" alt="" />
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
            <div class="single-entry mb-5 mb-lg-0">
              <h1 class="my-2">{{ ExhibitionsCards[0].title }}</h1>
              <p class="single-entry-text mb-2 text-black d-flex flex-wrap align-items-center">
                Published <span class="dot-separeted"></span> 30 August 2021 <span class="dot-separeted"></span> <a href="#" class="text-black">3 Comments</a> <span class="dot-separeted"></span> By
                Admin
              </p>
              <!-- 큐레이터 -->

              <hr />
              <!-- 내용 반복start-->
              <h2 class="mb-3">Description</h2>

              <p class="single-entry-text my-5">{{ ExhibitionsCards[0].content }}</p>
              <blockquote class="blockquote">
                <p>{{ SectionData.blogDetail.quoteText }}</p>
              </blockquote>

              <p class="single-entry-text mb-4">{{ SectionData.blogDetail.contentThree }}</p>
              <div class="row g-gs mb-4">
                <div class="col-lg-6 col-sm-6" v-for="img in SectionData.blogDetail.imgList" :key="img.id">
                  <img :src="img" class="w-100 rounded" alt="" />
                </div>
              </div>
              <!-- 내용 end-->
              <hr />

              <!-- 내용 end-->
              <!-- end row -->
              <hr />
              <!-- end comment-wrapper -->
            </div>
            <!-- end single-entry -->
          </div>
          <!-- end col-lg-8 -->
          <div class="col-lg-4 ps-lg-4">
            <!-- 큐레이터들 명단 시작 -->
            <div class="sidebar row">
              <div class="col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4>Curators</h4>
                    <hr />
                    <div class="d-flex flex-wrap justify-content-start">
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">akss</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">akss</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2018/10/29/21/46/human-3782189__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">hesdd</h5>
                      </div>
                    </div>
                  </div>
                  <!-- end card-body -->
                </div>
                <!-- end card -->
              </div>
              <!-- 큐레이터들 명단 끝 -->
              <!-- 아티스트 명단 시작 -->
              <div class="col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4>Artist</h4>
                    <hr />
                    <div class="d-flex flex-wrap justify-content-around">
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">akss</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2018/10/29/21/46/human-3782189__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">hesdd</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2018/10/29/21/46/human-3782189__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">hesdd</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">akss</h5>
                      </div>
                    </div>
                  </div>
                  <!-- end card-body -->
                </div>
                <!-- end card -->
              </div>
              <!-- 아티스트 명단 끝 -->

              <!-- end sidebar-widget -->
              <!-- <div class="col-md-6 col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4 class="mb-3">Categories</h4>
                    <ul class="list-item">
                      <li v-for="list in SectionData.sidebarData.sidebarWidgetThree.catList" :key="list.id">
                        <router-link :to="list.path"><em class="ni ni-chevron-right me-1"></em> {{ list.title }}</router-link>
                      </li>
                    </ul>
                  </div>
                </div>
              </div> -->
              <!-- end sidebar-widget -->
              <div class="col-md-6 col-lg-12 sidebar-widget">
                <div class="card">
                  <div class="card-body card-body-s1">
                    <h4 class="mb-3">Recent Post</h4>
                    <ul class="posts">
                      <li class="d-flex align-items-center" v-for="item in ExhibitionsCards" :key="item.id">
                        <router-link :to="{ name: ExhibitionDetail, params: { id: item.id } }" class="thumbnail flex-shrink-0 me-3">
                          <img :src="item.cardImage" alt="" />
                        </router-link>
                        <div class="flex-grow-1">
                          <h6 class="posts-title mb-1">
                            <router-link :to="{ name: ExhibitionDetail, params: { id: item.id } }">{{ item.title }}</router-link>
                          </h6>
                          <p class="posts-meta">{{ item.dateMonth }} {{ item.dateDay }}</p>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!-- end sidebar-widget -->
              <div class="col-md-6 col-lg-12 sidebar-widget">
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
              </div>
              <!-- end sidebar-widget -->
            </div>
            <!-- end sidebar -->
          </div>
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
import SectionData from "@/store/store.js";
import { mapState } from "vuex";

export default {
  name: "ExhibitionDetail",
  data() {
    return { DetailData: null, SectionData, id: this.$route.params.id, title: "", img: "", avatar: "", userName: "" };
  },
  methods: {
    getInfoDetail() {
      // axios.get(`${SERVER_URL}/exhibition/${this.id}`).then((res) => {
      //   console.log(res);
      // });
    },
  },
  computed: {
    ...mapState(["ExhibitionsCards"]),
  },
};
</script>
