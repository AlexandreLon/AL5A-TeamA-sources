<template>
	<div class="container">
		<h1>Supplier</h1>
		<Notifications v-if="selectedSupplier" :supplier-id="selectedSupplier.id" />
		<SelectSupplier @selected="select($event)" />
		<Tasks
			v-if="selectedSupplier"
			:supplier="selectedSupplier"
		/>
	</div>
</template>

<script>

import { ref } from 'vue';
import Tasks from '../components/supplier/Tasks.vue';
import Notifications from "../components/supplier/Notifications.vue";
import SelectSupplier from "../components/supplier/SelectSupplier.vue";

export default {
	components: {
		Notifications,
		Tasks,
		SelectSupplier
	},
	setup(){
		const selectedSupplier = ref(null);
		function select(selectedSupplierArg) {
			selectedSupplier.value = selectedSupplierArg;
		}
		return {selectedSupplier,select};
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>