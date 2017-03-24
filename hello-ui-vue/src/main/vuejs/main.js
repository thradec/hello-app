import Vue from 'vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
import App from './App.vue';
import HelloCard from './HelloCard.vue';
import HelloAdmin from './HelloAdmin.vue';
import Login from './Login.vue';
import Dev from './Dev.vue';


Vue.use(VueRouter);
Vue.use(VueResource);


const router = new VueRouter({
    routes: [
        {path: '/', redirect: 'hello'},
        {path: '/hello', component: HelloCard},
        {path: '/hello/admin', component: HelloAdmin},
        {path: '/login', component: Login},
        {path: '/dev', component: Dev}
    ]
});


new Vue({
    el: '#app',
    router,
    render: h => h(App)
});