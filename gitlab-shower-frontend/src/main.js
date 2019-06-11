import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import VueResource from 'vue-resource'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import echarts from 'echarts'
import iView from 'iview/dist/iview';
import 'iview/dist/styles/iview.css'

Vue.use(BootstrapVue);
Vue.use(echarts);
Vue.config.productionTip = false;
Vue.use(VueResource);
Vue.use(iView);
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
