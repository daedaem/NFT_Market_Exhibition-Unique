import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// import store from './store'

// createApp(App).use(store).use(router).mount('#app')

// vue app
const app = createApp(App);

// bootstrap
import "bootstrap"

// vue select
import vSelect from 'vue-select'
import "vue-select/dist/vue-select.css";

app.component('v-select', vSelect)

// clipboard
import VueClipboard from 'vue3-clipboard'
app.use(VueClipboard, {
    autoSetContainer: true,
    appendToBody: true,
})

// template custom css
import './assets/scss/bundles.scss'
import './assets/scss/style.scss'

import HeaderMain from './components/common/HeaderMain.vue';
import LogoLink from './components/common/LogoLink.vue';
import ButtonLink from './components/common/ButtonLink.vue';
import ButtonGroup from './components/common/ButtonGroup.vue';

app.component('HeaderMain', HeaderMain);
app.component('LogoLink', LogoLink)
app.component('ButtonLink', ButtonLink)
app.component('ButtonGroup', ButtonGroup)
app.use(router).mount('#app')