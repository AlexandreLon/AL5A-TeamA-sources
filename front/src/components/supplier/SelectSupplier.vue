<template>
	<div>
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
	</div>
</template>

<script>
import {ref} from "vue";


export default {
	name: "SelectSupplier",
	emits: ["selected"],
	props:{
		suppliers: {
			type: Array,
			default: null
		}
	},
	setup(props,{emit}) {
		const supplierId = ref(1);

		function onChange(supplierSelected) {
			emit('selected', props.suppliers[supplierSelected.target.value-1]);
		}
        
		return {supplierId,onChange};
	}
};
</script>

<style scoped>

</style>