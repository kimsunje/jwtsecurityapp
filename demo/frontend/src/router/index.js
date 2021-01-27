import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/login',
            component: () => import('../components/Login.vue'),
        },
        {
            path: '/',
            component: () => import('../components/HelloWorld.vue'),
        }
    ]
})

export default router