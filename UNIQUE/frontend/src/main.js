import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
// import store from './store'

// createApp(App).use(store).use(router).mount('#app')

// vue app
const app = createApp(App);

// bootstrap
import "bootstrap";

// vue select
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";

app.component("v-select", vSelect);

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

app.use(router).mount("#app");
