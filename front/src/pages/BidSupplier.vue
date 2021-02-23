<template>
	<div class="container">
		<h1>Bid supplier</h1>
		<Notifications
			v-if="selectedSupplier"
			:supplier-id="selectedSupplier.id"
		/>
		<SelectSupplier @selected="select($event)" />
		<Bids 
			v-if="selectedSupplier"
			:enable-accept-offer="false"
			:enable-create-proposal="true"
			:supplier="selectedSupplier"
		/>
	</div>
</template>

<script>
import {ref} from "vue";
import Bids from "../components/bid-management/Bids.vue";
import Notifications from "../components/supplier/Notifications.vue";
import SelectSupplier from "../components/supplier/SelectSupplier.vue";


export default {
	name: "BidSupplier",
	components: {
		Bids,
		SelectSupplier,
		Notifications
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

</style>