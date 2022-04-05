/* eslint-disable no-undef */
<template>
  <swiper :modules="modules" :slides-per-view="1" :effect="'fade'" :loop="true" :navigation="{ clickable: true }" class="swiper-button-s1 swiper-button-s2 on-dark animated">
    <!-- <swiper-slide v-for="item in SectionData.heroDataEight" :key="item.id" class="h-auto"> -->
    <swiper-slide v-for="item in ExhibitionsCards" :key="item.id" class="h-auto">
      <div class="slider-full-screen set-bg" :data-set-bg="item.cardImage">
        <div class="row d-flex col-7">
          <div class="slider-full-body col-lg-6 col-md-8 col-sm-9">
            <h2 class="mb-3 card-title">{{ item.title }}</h2>
            <p class="mb-4 card-text">{{ item.creators }}</p>
          </div>
          <!-- end col -->
        </div>
        <!-- end row -->
      </div>
      <!-- end card -->
    </swiper-slide>
  </swiper>
</template>
<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";

// core version + navigation, pagination modules:
import SwiperCore, { Navigation, EffectFade } from "swiper";

// configure Swiper to use modules
SwiperCore.use([Navigation, EffectFade]);

// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";
import { mapState } from "vuex";

export default {
  name: "CarouselScreenSlider",
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      SectionData,
    };
  },
  mounted() {
    /*  ================================================================
      Set background image
    ==================================================================== */
    function setBg(selector) {
      var list = document.querySelectorAll(selector);
      for (var i = 0; i < list.length; i++) {
        var src = list[i].getAttribute("data-set-bg");
        list[i].style.backgroundImage = "url('" + src + "')";
      }
    }

    setBg(".set-bg");
  },
  setup() {
    return {
      modules: [Navigation, EffectFade],
    };
  },
  computed: {
    ...mapState(["ExhibitionsCards"]),
  },
};
</script>
