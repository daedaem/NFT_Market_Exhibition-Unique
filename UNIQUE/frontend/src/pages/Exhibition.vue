<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
      <!-- hero -->
      <HeroFour classname="hero-title" :title="SectionData.breadcrumbData.breadcrumbListExhibition.title" :lists="SectionData.breadcrumbData.breadcrumbListExhibition.navList"></HeroFour>
    </header>
    <section>
      <!-- <MainCarousel></MainCarousel> -->
      <ExhibitionCarousel></ExhibitionCarousel>
      <Exhibition-cards v-for="(card, index) in EXHIBITION_CARDS" :EXHIBITION_CARDS="card" @click="moveToDetail(card)" :key="index" class="mb-5"></Exhibition-cards>
    </section>
    <!-- Blog  -->
    <!-- 전시회 카드들 start -->
    <!-- 전시회 카드들 end -->
    <!-- 기사1 start-->

    <!--  -->
    <Footer classname="bg-black on-dark"></Footer>
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";
import HeroFour from "@/components/section/HeroFour.vue";
// import MainCarousel from "@/components/section/MainCarousel.vue";
import ExhibitionCards from "@/components/section/ExhibitionCards.vue";
import ExhibitionCarousel from "@/components/section/ExhibitionCarousel.vue";
import { mapState } from "vuex";

export default {
  name: "Exhibition",
  components: {
    HeroFour,
    // MainCarousel,
    ExhibitionCards,
    ExhibitionCarousel,
  },
  data() {
    return {
      SectionData,
      EXHIBITION_CARDS: this.$store.state.ExhibitionsCards,
    };
  },
  methods: {
    moveToDetail(card) {
      this.$router.push({
        name: "ExhibitionDetail",
        params: {
          // id:1
          id: card.id,
          //
        },
      });
    },
  },
  computed: {
    ...mapState(["ExhibitionsCards"]),
  },
};
</script>
