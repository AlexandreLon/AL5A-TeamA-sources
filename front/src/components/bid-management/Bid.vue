<template>
	<tr v-if="bid !== null">
		<th scope="row">
			{{ bid.id }}
		</th>
		<td>{{ bid.name }}</td>
		<td>{{ this.formatDate(bid.desiredDate) }}</td>
		<td>{{ bid.status }}</td>
		<td>
			<button
				type="button"
				class="btn btn-warning"
				data-toggle="modal" 
				:data-target="'#viewOfferModal' + bid.id"
			>
				<i class="fas fa-pencil-alt" />
				See Proposals
			</button>
			<button
				type="button"
				class="btn btn-primary"
				data-toggle="modal" 
				:data-target="'#createOfferModal' + bid.id"
			>
				<i class="fas fa-plus" />
				Create Proposals
			</button>
		</td>
		<CreateOffer
			:bidid="bid.id"
			:suppliers="suppliers"
		/>
		<ViewOffer :bidid="bid.id" />
	</tr>
</template>

<script>
import Bid from "../../models/bid-management/Bid";
import Supplier from "../../models/supplier/Supplier";
import CreateOffer from "./offers/CreateOffer2.vue";
import ViewOffer from "./offers/ViewOffer.vue";

export default {
	components: {
		CreateOffer, ViewOffer
	},
	props: {
		bid: {
			type: Bid,
			default: null
		},
		suppliers: {
			type: [Supplier],
			default: []
		}
	},
	setup() {

		const formatDate = (dateStr) => {
			return new Date(dateStr).toLocaleString();
		};

		return { formatDate };
	}
};
</script>

<style>

</style>