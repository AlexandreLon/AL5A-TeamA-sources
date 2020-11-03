import { createApp } from 'vue';
import App from './App.vue';

import router from './router';

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/";

createApp(App).use(router).mount('#app');