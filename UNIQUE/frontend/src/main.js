import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";

// import Vue from 'vue'

// Vue.config.productionTip = false

// new Vue({
//   router,
//   store,
//   vuetify,
//   render: h => h(App)
// }).$mount('#app')

// createApp(App).use(store).use(router).mount('#app')

// vue app
const app = createApp(App);
app.use(store);
// bootstrap
import "bootstrap";

// vue select
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";

app.component("v-select", vSelect);
app.component("Datepicker", Datepicker);

// app.mount("#app");

// clipboard
import VueClipboard from "vue3-clipboard";
app.use(VueClipboard, {
  autoSetContainer: true,
  appendToBody: true,
});

// template custom css
import "./assets/scss/bundles.scss";
import "./assets/scss/style.scss";

import HeaderMain from "./components/common/HeaderMain.vue";
import LogoLink from "./components/common/LogoLink.vue";
import ButtonLink from "./components/common/ButtonLink.vue";
import ButtonGroup from "./components/common/ButtonGroup.vue";
import FullScreenSlider from "./components/common/FullScreenSlider.vue";
import Featured from "./components/section/Featured.vue";
import SectionHeading from "./components/common/SectionHeading.vue";
import MainSectionHeading from "./components/common/MainSectionHeading.vue";

import ProductsContainer from "./components/section/ProductsContainer.vue";
import RelatedProduct from "./components/section/RelatedProduct.vue";
import HowItWork from "./components/section/HowItWork.vue";

import Footer from "./pages/Footer.vue";
import FooterSection from "./components/section/FooterSection.vue";

// explore
import ExploreSectionTwo from "./components/section/ExploreSectionTwo.vue";
import TopCreators from "./components/section/TopCreators.vue";
import Creators from "./components/common/Creators.vue";
import Collections from "./components/section/Collections.vue";
import CollectionSlider from "./components/common/CollectionSlider.vue";

import ExploreSection from "./components/section/ExploreSection.vue";

//마켓상세페이지
// import RelatedProduct from "./components/section/RelatedProduct.vue";

//
import HeaderDashboard from "./components/common/HeaderDashboard.vue";
import AuthorHero from "./components/section/AuthorHero.vue";
import ProfileSection from "./components/section/ProfileSection.vue";
import AuthorSidebar from "./components/common/AuthorSidebar.vue";
import UserSidebar from "./components/common/UserSidebar.vue";
import AccountSection from "./components/section/AccountSection.vue";

import LoginSection from "./components/section/LoginSection.vue";
import RegisterSection from "./components/section/RegisterSection.vue";

import DetailPurchase from "./components/common/DetailPurchase.vue";
import Purchase from "./components/common/Purchase.vue";
import ExhibitionCards from "./components/section/ExhibitionCards.vue";
import ExhibitionCarousel from "./components/section/ExhibitionCarousel";
import CarouselScreenSlider from "./components/common/CarouselScreenSlider.vue";
import NftProducts from "./components/section/NftProducts.vue";

app.component("HeaderMain", HeaderMain);
app.component("LogoLink", LogoLink);
app.component("ButtonLink", ButtonLink);
app.component("ButtonGroup", ButtonGroup);

app.component("FullScreenSlider", FullScreenSlider);
app.component("Featured", Featured);
app.component("SectionHeading", SectionHeading);
app.component("ProductsContainer", ProductsContainer);
app.component("RelatedProduct", RelatedProduct);
app.component("MainSectionHeading", MainSectionHeading);

app.component("HowItWork", HowItWork);

app.component("Footer", Footer);
app.component("FooterSection", FooterSection);

// explore
app.component("ExploreSectionTwo", ExploreSectionTwo);
app.component("TopCreators", TopCreators);
app.component("Creators", Creators);
app.component("Collections", Collections);
app.component("CollectionSlider", CollectionSlider);
app.component("ExploreSection", ExploreSection);
// 마켓상세페이지
// app.component("RelatedProduct", RelatedProduct);

app.use(router).mount("#app");

app.component("HeaderDashboard", HeaderDashboard);
app.component("AuthorHero", AuthorHero);
app.component("ProfileSection", ProfileSection);
app.component("AuthorSidebar", AuthorSidebar);
app.component("UserSidebar", UserSidebar);
app.component("AccountSection", AccountSection);

app.component("LoginSection", LoginSection);
app.component("RegisterSection", RegisterSection);

app.component("DetailPurchase", DetailPurchase);
app.component("Purchase", Purchase);
app.component("ExhibitionCards", ExhibitionCards);

app.component("ExhibitionCarousel", ExhibitionCarousel);
app.component("CarouselScreenSlider", CarouselScreenSlider);
app.component("NftProducts", NftProducts);
