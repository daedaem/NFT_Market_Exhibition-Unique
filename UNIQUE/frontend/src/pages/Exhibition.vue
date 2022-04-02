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
      <MainCarousel></MainCarousel>
      <!-- <Exhibition-cards v-for="(card, index) in Cards" :key="index" class="mb-5"></Exhibition-cards> -->
      <Exhibition-cards></Exhibition-cards>

      <Exhibition-cards></Exhibition-cards>
      <Exhibition-cards></Exhibition-cards>
      <Exhibition-cards></Exhibition-cards>
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
import MainCarousel from "@/components/section/MainCarousel.vue";
import ExhibitionCards from "@/components/section/ExhibitionCards.vue";

export default {
  name: "Exhibition",
  components: {
    HeroFour,
    MainCarousel,
    ExhibitionCards,
  },
  data() {
    return {
      SectionData,
    };
  },
};
</script>
