<template>
  <ul class="menu-list ms-lg-auto">
    <li class="menu-item has-sub">
      <a href="#" class="menu-link menu-toggle">{{ SectionData.headerData.menuList2.title }}</a>
      <div class="menu-sub">
        <ul class="menu-list">
          <li class="menu-item" v-for="nav in SectionData.headerData.menuList2.navList" :key="nav.id">
            <router-link :to="nav.path" class="menu-link"
              >{{ nav.title }} <span :class="nav.badgeClass" v-if="nav.badge">{{ nav.badge }}</span></router-link
            >
          </li>
        </ul>
      </div>
    </li>
    <li class="menu-item">
      <router-link :to="SectionData.headerData.menuList3.navList[0].path" class="menu-link">{{ SectionData.headerData.menuList3.title }}</router-link>
    </li>
    <li class="menu-item has-sub">
      <a href="#" class="menu-link menu-toggle">{{ SectionData.headerData.menuList2.title }}</a>
      <div class="menu-sub">
        <ul class="menu-list">
          <li class="menu-item" v-for="nav in SectionData.headerData.menuList2.navList" :key="nav.id">
            <router-link :to="nav.path" class="menu-link"
              >{{ nav.title }} <span :class="nav.badgeClass" v-if="nav.badge">{{ nav.badge }}</span></router-link
            >
          </li>
        </ul>
      </div>
    </li>  
    <li class="menu-item">
      <router-link :to="SectionData.headerData.menuList5.navList[0].path" class="menu-link">{{ SectionData.headerData.menuList5.title }}</router-link>
    </li>
  </ul>
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";

export default {
  name: "MenuList",
  data() {
    return {
      SectionData,
    };
  },
};
</script>
