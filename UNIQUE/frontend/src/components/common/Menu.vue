<template>
  <nav class="header-menu menu nav">
    <!-- menu list -->
    <MenuList></MenuList>
    <!-- header btn -->
    <ul class="menu-btns">
      <li><ButtonLink :text="SectionData.headerData.btnText" :link="`/profile/${userId}`" classname="btn" :class="classname"></ButtonLink></li>
      <li>
        <!-- 로그인 부분 해결되면 밑에꺼 사용 start-->
        <!-- <li v-if="this.$store.state.isLogin == false"><ButtonLink :text="SectionData.headerData.btnText" link="/wallet" classname="btn" :class="classname"></ButtonLink></li>
      <li v-else>
        <ButtonLink :text="SectionData.headerData.btnText" link="/create" classname="btn" :class="classname"></ButtonLink>
      </li> -->
        <!-- 로그인 부분 해결되면 사용할 end -->
        <!-- <ThemeSwitcher></ThemeSwitcher>/ -->
      </li>
    </ul>
  </nav>
  <!-- .header-menu -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";
import { mapState } from "vuex";

// @ is an alias to /src
import MenuList from "@/components/common/MenuList.vue";

export default {
  name: "Menu",
  props: ["classname"],
  components: {
    MenuList,
  },
  data() {
    return {
      SectionData,
    };
  },
  computed: {
    ...mapState([
      "authToken",
      "userId",
      ]),
  }
};
</script>
