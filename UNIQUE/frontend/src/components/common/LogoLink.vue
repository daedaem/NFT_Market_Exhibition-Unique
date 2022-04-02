<template>
  <router-link to="/" class="logo-link" :class="classname">
    <h3 style="font-family: DM Serif Display, serif">Unique</h3>
    <!-- <img class="logo-dark logo-img" :src="require('@/images/logo-black.png')" alt="logo" /> -->
    <!-- <img class="logo-light logo-img" :src="require('@/images/logo-white.png')" alt="logo" /> -->
  </router-link>
</template>
<script>
export default {
  name: "LogoLink",
  props: ["classname"],
};
</script>
<style lang="scss" scoped>
.h3 {
  // @import url("https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap");
  // font-family: "DM Serif Display", serif;
  font-family: "DM Serif Display", serif;
  font-family: "Ruslan Display", cursive;
}
</style>
