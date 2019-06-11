import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import About from './views/About.vue'
import  GitlabInsight from './views/GitlabInsight.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'GitlabInsight',
            component: GitlabInsight
        },
        {
            path: '/home',
            name: 'home',
            component: Home
        },
        {
            path: '/info',
            name: 'info',
            component: () => import(/* webpackChunkName: "about" */ './views/Info.vue')
        },
        {
            path: '/about',
            name: 'about',
            component: About
        }, {
            path: '/gitlab-insight',
            name: 'gitlab-insight',
            component:GitlabInsight
        }
    ]
})
