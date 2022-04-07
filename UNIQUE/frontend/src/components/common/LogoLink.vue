<template>
  <router-link to="/" class="logo-link" :class="classname">
    <p class="logo">Unique</p>
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
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap');
.logo {
  font-family: "DM Serif Display", serif;
  letter-spacing: 0px;
  font-size: 25px;
  font-weight: 700;
  color: black;
}
.dark-mode .logo {
  font-family: "DM Serif Display", serif;
  letter-spacing: 0px;
  font-size: 25px;
  font-weight: 700;
  color: white;
}
</style>
