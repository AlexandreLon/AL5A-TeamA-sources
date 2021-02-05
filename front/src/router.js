import { createWebHistory, createRouter } from "vue-router";
import Home from "./pages/Home.vue";
import Supplier from "./pages/Supplier.vue";
import BidSupplier from "./pages/BidSupplier.vue";
import MaintenanceManagement from "./pages/MaintenanceManagement.vue";
import MishapManagement from "./pages/MishapManagement.vue";
import BidManagement from "./pages/BidManagement.vue";

const routes = [
	{
		path: "/",
		name: "Home",
		component: Home,
	},
	{
		path: "/bid-supplier",
		name: "Bid supplier",
		component: BidSupplier,
	},
	{
		path: "/supplier",
		name: "Supplier",
		component: Supplier,
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
	},
	{
		path: "/bid",
		name: "Bid management",
		component: BidManagement,
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;