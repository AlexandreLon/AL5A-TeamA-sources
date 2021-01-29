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
				@click="getOffers"
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
		<ViewOffer
			:offers="offers"
			:bidid="bid.id"
		/>
	</tr>
</template>

<script>
import { ref } from 'vue';
import Bid from "../../models/bid-management/Bid";
import CreateOffer from "./offers/CreateOffer2.vue";
import ViewOffer from "./offers/ViewOffer.vue";
import BidWSAPI from '../../API/BidWSAPI';

const bidWSAPI = new BidWSAPI();

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
			type: Array,
			default: () => []
		}
	},
	setup(props) {

		const offers = ref([]);

		const formatDate = (dateStr) => {
			return new Date(dateStr).toLocaleString();
		};

		const getOffers = () => {
			bidWSAPI.getOffers(props.bid.id).then(res => {offers.value = res; console.log(offers.value.proposedDate);});
		};

		return { formatDate, getOffers, offers };
	}
};
</script>

<style>

</style>