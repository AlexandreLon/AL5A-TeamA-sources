<template>
	<div>Supplier </div>
	<select
		class="form-control mb-3"
		v-model="supplierId"
		@change="onChange($event)"
	>
		<option
			v-for="supplier in suppliers"
			:value="supplier.id"
			:key="supplier.id"
		>
			{{ supplier.name }} - Type : {{ supplier.taskType }}
		</option>
	</select>
</template>

<script>
import {onMounted, ref} from "vue";
import SupplierWSAPI from "../../API/SupplierWSAPI";

const supplierWSAPI = new SupplierWSAPI();


export default {
	name: "SelectSupplier",
	emits: ["selected"],
	setup(props,{emit}) {
	    const suppliers = ref(null);
		const supplierId = ref(1);

		onMounted(() => {
			supplierWSAPI
				.getSuppliers()
				.then(res => {
					suppliers.value = res;
					emit('selected', suppliers.value[0]);
				})
				.catch(error => {
					console.error(error);
				});
		});

		function onChange(supplierSelected) {
			emit('selected', suppliers.value[supplierSelected.target.value-1]);
		}
        
		return {supplierId,suppliers,onChange};
	}
};
</script>

<style scoped>

</style>