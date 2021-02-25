<template>
	<div class="container">
		<h1>Supplier</h1>
		<div class="d-flex flex-row bd-highlight align-items-center justify-content-between mb-3">
			<CreateSupplier />
			<div class="w-75 d-flex flex-column">
				<SelectSupplier @selected="select($event)" />
			</div>
		</div>
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
import CreateSupplier from "../components/supplier/CreateSupplier.vue";

export default {
	components: {
		Tasks,
		SelectSupplier,
		CreateSupplier
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