<template>
	<div class="container">
		<h1>Bid supplier</h1>
		<Notifications
			v-if="selectedSupplier"
			:supplier-id="selectedSupplier.id"
		/>
		<SelectSupplier
			@selected="select($event)"
			:suppliers="suppliers"
		/>
		<Bids 
			v-if="selectedSupplier"
			:enable-accept-offer="false"
			:enable-create-proposal="true"
			:supplier="selectedSupplier"
		/>
	</div>
</template>

<script>
import { onMounted, ref } from "vue";
import Bids from "../components/bid-management/Bids.vue";
import Notifications from "../components/supplier/Notifications.vue";
import SelectSupplier from "../components/supplier/SelectSupplier.vue";
import SupplierWSAPI from '../API/SupplierWSAPI';

const supplierWSAPI = new SupplierWSAPI();

export default {
	name: "BidSupplier",
	components: {
		Bids,
		SelectSupplier,
		Notifications
	},
	setup(){
		const selectedSupplier = ref(null);
		const suppliers = ref([]);

		function select(selectedSupplierArg) {
			selectedSupplier.value = selectedSupplierArg;
		}

		onMounted(() => {
			supplierWSAPI
				.getSuppliers()
				.then(res => {
					suppliers.value = res;
					select(suppliers.value[0]);
				})
				.catch(error => {
					console.error(error);
				});
		});

		return {selectedSupplier, select, suppliers};
	}


};
</script>

<style scoped>

</style>