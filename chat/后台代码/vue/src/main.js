import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './plugins/element.js'
//引入图标
import './assets/iconfont/iconfont.css'

Vue.prototype.axios = axios;
axios.defaults.withCredentials = true;

Vue.config.productionTip = false;
Vue.use(axios);
Vue.use(ElementUI);

// 导航守卫，判断是否登录

router.beforeEach((to, from, next) => {
    console.log(to)
    if (to.meta.requireAuth) {
        if (store.state.root !== null && store.state.root) {
            next()
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            },alert("未登录无法获取管理员权限，请登录！"))
        }
    }else {
      next()
    }
});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
