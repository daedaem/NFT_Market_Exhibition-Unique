import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../pages/Home.vue"),
  },
  {
    path: "/product-details-v1-:id",
    name: "ProductDetail",
    component: () => import("../pages/ProductDetail.vue"),
    props: true,
  },
  {
    path: "/explore",
    name: "explore",
    component: () => import("../pages/Explore.vue"),
  },
  {
    path: "/explore-v2",
    name: "exploreV2",
    component: () => import("../pages/Explore-v2.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return {
        left: 0,
        top: 0,
      };
    }
  },
});

export default router;
