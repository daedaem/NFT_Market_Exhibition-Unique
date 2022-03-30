<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
    </header>
    <!-- login section -->
    <loginSection></loginSection>
    <!-- Blog  -->
    <vue-metamask userMessage="msg" @onComplete="onComplete"></vue-metamask>
    <Footer classname="bg-dark on-dark"></Footer>
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";
import VueMetamask from "vue-metamask";
export default {
  name: "Login",
  components: {
    VueMetamask,
  },
  data() {
    return {
      SectionData,
      msg: "This is demo net work",
    };
  },
  methods:{
    onComplete(data){
        console.log('data:', data);
    }
  },
};
</script>
