<template>
	<div class="container">
		<h1>Supplier</h1>
		<div class="d-flex flex-row bd-highlight align-items-center justify-content-between mb-3">
			<CreateSupplier @create="updateList" />
			<div class="w-75 d-flex flex-column">
				<SelectSupplier
					:suppliers="suppliers"
					@selected="select($event)"
				/>
			</div>
		</div>
		<Tasks
			v-if="selectedSupplier"
			:supplier="selectedSupplier"
		/>
	</div>
</template>

<script>

import { onMounted, ref } from 'vue';
import Tasks from '../components/supplier/Tasks.vue';
import SelectSupplier from "../components/supplier/SelectSupplier.vue";
import CreateSupplier from "../components/supplier/CreateSupplier.vue";
import SupplierWSAPI from '../API/SupplierWSAPI';

const supplierWSAPI = new SupplierWSAPI();

export default {
	components: {
		Tasks,
		SelectSupplier,
		CreateSupplier
	},
	setup(){
		const selectedSupplier = ref(null);
		const suppliers = ref([]);

		function select(selectedSupplierArg) {
			selectedSupplier.value = selectedSupplierArg;
		}

		function updateList() {
			supplierWSAPI
				.getSuppliers()
				.then(res => {
					suppliers.value = res;
					select(suppliers.value[0]);
				})
				.catch(error => {
					console.error(error);
				});
		}

		onMounted(() => {
			updateList();
		});

		return {selectedSupplier,select, suppliers, updateList};
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>