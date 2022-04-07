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
            <h1>{{ ExhibitionsCards[id - 1].title }}</h1>
            <nav aria-label="breadcrumb">
              <ol class="breadcrumb breadcrumb-s1 justify-content-center mt-3 mb-0">
                <li class="breadcrumb-item">
                  <router-link to="/">Home</router-link>
                </li>
                <li class="breadcrumb-item">
                  <router-link to="/exhibition">Exhibition</router-link>
                </li>
                <li class="breadcrumb-item">
                  {{ ExhibitionsCards[id - 1].title }}
                </li>
              </ol>
            </nav>
          </div>
          <img :src="`${ExhibitionsCards[id - 1].cardImage}`" class="w-100 rounded mt-4" alt="" />
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
              <h1 class="my-2">{{ ExhibitionsCards[id - 1].title }}</h1>
              <p class="single-entry-text mb-2 text-black d-flex flex-wrap align-items-center">
                Published <span class="dot-separeted"></span> {{ ExhibitionsCards[id - 1].dateDay }} {{ ExhibitionsCards[id - 1].dateMonth }} 2022 <span class="dot-separeted"></span> By Sunghyun
              </p>
              <!-- 큐레이터 -->

              <hr />
              <!-- 내용 반복start-->
              <h2 class="mb-3">Description</h2>

              <p class="single-entry-text my-5">{{ ExhibitionsCards[id - 1].content }}</p>
              <blockquote class="blockquote">
                <p>{{ SectionData.blogDetail.quoteText }}</p>
              </blockquote>

              <p class="single-entry-text mb-4">{{ SectionData.blogDetail.contentThree }}</p>
              <div class="row g-gs mb-4">
                <div class="col-lg-6 col-sm-6" v-for="img in ExhibitionsCards[id - 1].image" :key="img.id">
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
                    <div class="d-flex flex-wrap justify-content-center">
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://thumbs.gfycat.com/LonePowerfulGermanshorthairedpointer-max-1mb.gif" alt="" />
                        <h5 class="mt-3 text-center" style="font-weight: 900; font-size: 0.75rem">Sunghyun</h5>
                      </div>
                      <!-- <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">akss</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://cdn.pixabay.com/photo/2018/10/29/21/46/human-3782189__340.jpg" alt="" />
                        <h5 class="mt-3 text-center">hesdd</h5>
                      </div> -->
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
                  <div v-if="id == 1" class="card-body card-body-s1">
                    <h4>Artist</h4>
                    <hr />
                    <div class="d-flex flex-wrap justify-content-around">
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://w7.pngwing.com/pngs/873/457/png-transparent-alien-head-illustration-emoji-sticker-iphone-smiley-emoticon-alien-love-face-heart.png" alt="" />
                        <h5 class="mt-3 text-center">Alien</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://www.blockmedia.co.kr/wp-content/uploads/2021/02/%EC%9D%BC%EB%A1%A0%EB%A8%B8%EC%8A%A4%ED%81%AC_%EC%A0%95%EC%82%AC%EA%B2%A9%ED%98%95.jpg" alt="" />
                        <h5 class="mt-3 text-center">Musk</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://t1.daumcdn.net/cfile/tistory/99B0133E5ECD2F1E19" alt="" />
                        <h5 class="mt-3 text-center">Tesla</h5>
                      </div>
                      <div class="avatar avatar-1 mb-5 mx-4">
                        <img src="https://pds.joongang.co.kr/news/component/htmlphoto_mmdata/202104/18/297db777-9d31-450f-93bd-42909a4def5e.jpg" alt="" />
                        <h5 class="mt-3 text-center">Doge</h5>
                      </div>
                    </div>
                  </div>
                  <!-- 2222222222222222222222222222222222222222222222222222222222222222222222222222222 -->
                  <div v-if="id == 2" class="card-body card-body-s1">
                    <h4>Artist</h4>
                    <hr />
                    <div class="d-flex flex-wrap justify-content-around">
                      <div class="avatar avatar-1 my-4 mx-4">
                        <img src="https://media.gucci.com/style/DarkGray_Center_0_0_490x490/1636628417/644656_XJC3W_4440_011_100_0000_Light--Web.jpg" alt="" />
                        <h5 class="mt-3 text-center">Gucci</h5>
                      </div>
                      <div class="avatar avatar-1 my-4 mx-4">
                        <img src="http://buyingsoo.cafe24.com/wp-content/uploads/2019/03/no2_logoChanel.jpg" alt="" />
                        <h5 class="mt-3 text-center">CHANEL</h5>
                      </div>
                      <div class="avatar avatar-1 mt-5 my-5 mx-4">
                        <img src="https://contents.lotteon.com/search/brand/P4/53/3/P4533_320_320.jpg" alt="" />
                        <h5 class="mt-3 text-center">Polo</h5>
                      </div>
                    </div>
                  </div>
                  <!-- 33333333333333333333333333333333333333333333333333333333333333333333333333333333333 -->
                  <div v-if="id == 3" class="card-body card-body-s1">
                    <h4>Artist</h4>
                    <hr />
                    <div class="d-flex flex-wrap justify-content-around">
                      <div class="avatar avatar-1 my-4 mx-4">
                        <img src="https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/42iI/image/fgznKzwTj38hXdylF03JXH2Gv5E" alt="" />
                        <h5 class="mt-3 text-center">Kongz</h5>
                      </div>
                      <div class="avatar avatar-1 my-4 mx-4">
                        <img src="https://image.zdnet.co.kr/2021/09/21/4ee92191a32c45ca1b2d32c61f5939ab.png" alt="" />
                        <h5 class="mt-3 text-center">CryptoPunk</h5>
                      </div>
                      <div class="avatar avatar-1 mt-5 my-5 mx-4">
                        <img src="https://mblogthumb-phinf.pstatic.net/20160117_8/pinbank_1452999374914D0Uv5_PNG/%B8%DE%C0%CC%C7%C3%BD%BA%C5%E4%B8%AE%B3%D8%BD%BC%C4%AB%B5%E50.png?type=w2" alt="" />
                        <h5 class="mt-3 text-center">Maple Story</h5>
                      </div>
                    </div>
                  </div>
                  <!-- 44444444444444444444444444444444444444444444444444444444444444444444444444444444444444 -->
                  <div v-if="id == 4" class="card-body card-body-s1">
                    <h4>Artist</h4>
                    <hr />
                    <div class="d-flex flex-wrap justify-content-around">
                      <div class="avatar avatar-1 my-4 mx-4">
                        <img src="https://ppss.kr/wp-content/uploads/2017/10/1-110.jpg" alt="" />
                        <h5 class="mt-3 text-center">Picasso</h5>
                      </div>
                      <div class="avatar avatar-1 my-4 mx-4">
                        <img src="https://cdn.imweb.me/upload/S2017101359e025984d346/b9bb99cf52ce5.jpg" alt="" />
                        <h5 class="mt-3 text-center">Van Gogh</h5>
                      </div>
                      <div class="avatar avatar-1 mt-5 my-5 mx-4">
                        <img src="https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/bsnE/image/-sqP2NHQUrEtj1YIp-YR6D2CXHE.JPG" alt="" />
                        <h5 class="mt-3 text-center">Andy Warho</h5>
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
