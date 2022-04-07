import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/Purchase",
    name: "Purchase",
    component: () => import("../components/common/Purchase.vue"),
  },
  {
    path: "/",
    name: "Home",
    component: () => import("../pages/Home.vue"),
  },
  {
    path: "/details/:id",
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

  {
    path: "/create",
    name: "create",
    component: () => import("../pages/Create.vue"),
  },
  {
    path: "/salecreate/:id",
    name: "SaleCreate",
    component: () => import("../pages/SaleCreate.vue"),
  },
  {
    path: "/privateGallery",
    name: "PrivateGallery",
    component: () => import("../pages/PrivateGallery.vue"),
  },
  {
    path: "/exhibition",
    name: "Exhibition",
    component: () => import("../pages/Exhibition.vue"),
  },
  {
    path: "/exhibition/:id",
    name: "ExhibitionDetail",
    component: () => import("../pages/ExhibitionDetail.vue"),
  },

  {
    path: "/privateGalleryDetail/:id",
    name: "PrivateGalleryDetail",
    component: () => import("../pages/PrivateGalleryDetail.vue"),
  },
  {
    path: "/profile/:id",
    name: "profile",
    component: () => import("../pages/Profile.vue"),
  },
  {
    path: "/account/:id",
    name: "account",
    component: () => import("../pages/Account.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../pages/Login.vue"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("../pages/Register.vue"),
  },
  {
    path: "/test",
    name: "Test",
    component: () => import("../pages/Test.vue"),
  },
  {
    path: "/wallet",
    name: "Wallet",
    component: () => import("../pages/Wallet.vue"),
  },
  {
    path: "/privateCreate",
    name: "PrivateCreate",
    component: () => import("../pages/PrivateCreate.vue"),
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
