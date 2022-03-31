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
    </section>
    <!-- Blog  -->
    <Footer classname="bg-dark on-dark"></Footer>
</div><!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from '@/store/store.js'
import HeroFour from '@/components/section/HeroFour.vue'
import MainCarousel from '@/components/section/MainCarousel.vue'

export default {
  name: 'Exhibition',
  components: {
    HeroFour,
    MainCarousel,
  },
  data () {
    return {
      SectionData,
    }
  },
}
</script>