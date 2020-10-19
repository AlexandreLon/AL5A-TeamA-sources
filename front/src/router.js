import { createWebHistory, createRouter } from "vue-router";
import Home from "./pages/Home.vue";
import Hello from "./pages/Hello.vue";
import Department from "./pages/Department.vue";

const routes = [
	{
		path: "/",
		name: "Home",
		component: Home,
	},
	{
		path: "/hello",
		name: "Hello",
		component: Hello,
	},
	{
		path: "/department",
		name: "Department",
		component: Department,
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;