import { createApp } from 'vue';
import { Datetime } from 'vue-datetime';
import App from './App.vue';
import router from './router';

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/";
import 'vue-datetime/dist/vue-datetime.css';

createApp(App).use(router).use(Datetime).mount('#app');