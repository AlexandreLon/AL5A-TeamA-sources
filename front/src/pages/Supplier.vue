<template>
	<div class="container">
		<h1>Supplier</h1>
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
import SelectSupplier from "../components/supplier/SelectSupplier.vue";

export default {
	components: {
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