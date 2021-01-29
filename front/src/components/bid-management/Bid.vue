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
		<CreateOffer :bidid="bid.id" />
	</tr>
</template>

<script>
import Bid from "../../models/bid-management/Bid";
import CreateOffer from "./offers/CreateOffer2.vue";

export default {
	components: {
		CreateOffer
	},
	props: {
		bid: {
			type: Bid,
			default: null
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