<template>
	<tr v-if="bid !== null">
		<th scope="row">
			{{ bid.id }}
		</th>
		<td>{{ bid.name }}</td>
		<td>{{ this.formatDate(bid.desiredDate) }}</td>
		<td>{{ bid.status }}</td>
		<td>
			<span v-if="bid.status != 'CLOSED'">
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
			</span>
			<span v-else>
				<button
					type="button"
					class="btn btn-success"
					data-toggle="modal" 
					:data-target="'#checkAcceptedOfferModal' + bid.id"
				>
					<i class="fas fa-search" />
					Check accepted offer
				</button>
			</span>
		</td>
		<CreateOffer
			:bidid="bid.id"
			:suppliers="suppliers"
		/>
		<ViewOffer
			:offers="offers"
			:bidid="bid.id"
			@accepted="acceptedOffer"
		/>
		<CheckAcceptedOffer
			:bidid="bid.id"
			v-if="bid.status == 'CLOSED'"
		/>
	</tr>
</template>

<script>
import { ref } from 'vue';
import Bid from "../../models/bid-management/Bid";
import CreateOffer from "./offers/CreateOffer2.vue";
import CheckAcceptedOffer from "./offers/CheckAcceptedOffer.vue";
import ViewOffer from "./offers/ViewOffer.vue";
import BidWSAPI from '../../API/BidWSAPI';

const bidWSAPI = new BidWSAPI();

export default {
	components: {
		CreateOffer, ViewOffer, CheckAcceptedOffer
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
	emits: ['accepted'],
	setup(props, { emit }) {

		const offers = ref([]);

		const formatDate = (dateStr) => {
			return new Date(dateStr).toLocaleString();
		};

		const getOffers = () => {
			bidWSAPI.getOffers(props.bid.id).then(res => {offers.value = res; console.log(offers.value.proposedDate);});
		};

		const acceptedOffer = (offer) => {
			emit('accepted', offer);
		};

		return { formatDate, getOffers, offers, acceptedOffer };
	}
};
</script>

<style>

</style>