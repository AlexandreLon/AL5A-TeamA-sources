import { createWebHistory, createRouter } from "vue-router";
import Home from "./pages/Home.vue";
import Department from "./pages/Department.vue";
import MaintenanceManagement from "./pages/MaintenanceManagement.vue";
import MishapManagement from "./pages/MishapManagement.vue";

const routes = [
	{
		path: "/",
		name: "Home",
		component: Home,
	},
	{
		path: "/department",
		name: "Department",
		component: Department,
	},
	{
		path: "/maintenance",
		name: "Maintenance management",
		component: MaintenanceManagement,
	},
	{
		path: "/mishap",
		name: "Mishap management",
		component: MishapManagement,
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;