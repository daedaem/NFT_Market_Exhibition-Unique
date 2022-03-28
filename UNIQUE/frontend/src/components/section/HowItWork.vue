<template>
  <section class="section-space how-it-work-section" style="padding-top: 60px; margin-bottom: 60px; background: #262626">
    <div class="container">
      <!-- section heading -->
      <MainSectionHeading style="color: #adadad" classname="text-center" :text="title" :content="subtitle" :isMargin="gutterBottom"></MainSectionHeading>
      <div class="row g-gs justify-content-center">
        <div class="col-10 col-sm-6 col-md-6" v-for="item in SectionData.howItWorkData.howItWorkList" :key="item.id" :class="classname">
          <div class="card-htw text-center" :class="classnameTwo">
            <span :class="item.icon"></span>
            <h4 style="color: #ffffff" class="mb-3">{{ item.title }}</h4>
            <p class="card-text-s1" v-html="item.content"></p>
          </div>
        </div>
        <!-- end col -->
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  </section>
  <!-- end how-it-work-section -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";

export default {
  name: "HowItWork",
  props: ["classname", "title", "classnameTwo", "subtitle", "gutterBottom"],
  data() {
    return {
      SectionData,
    };
  },
};
</script>
<style scoped>
/* .HowItWork .MainSectionHeading { */
/* color: white; */
/* } */
.card-text-s1 {
  color: #e8e8e8;
}
.card-text-s1 .a {
  color: #e8e8e8;
}
</style>
