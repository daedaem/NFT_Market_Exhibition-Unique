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
    path: "/privateGallery",
    name: "PrivateGallery",
    component: () => import("../pages/PrivateGallery.vue"),
  },
  {
    path: '/blog-detail-:id',
    name:'PrivateGalleryDetail',
    component: () => import('../pages/PrivateGalleryDetail.vue'),
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('../pages/Profile.vue')
  },
  {
    path: '/account',
    name: 'account',
    component: () => import('../pages/Account.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../pages/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../pages/Register.vue')
  },
  // {
  //   path: "/privateGallery",
  //   name: "PrivateGallery",
  //   component: () => import("../pages/PrivateGallery.vue"),
  // },
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
