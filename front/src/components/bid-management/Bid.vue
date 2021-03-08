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
					<i class="fa fa-eye" />
					See Proposals
				</button>
				<button
					v-if="enableCreateProposal"
					type="button"
					class="btn btn-primary ml-2"
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
			:supplier="supplier"
		/>
		<ViewOffer
			:offers="offers"
			:bidid="bid.id"
			:enable-accept-offer="enableAcceptOffer"
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
import CreateOffer from "./offers/CreateOffer.vue";
import CheckAcceptedOffer from "./offers/AcceptedOffer.vue";
import ViewOffer from "./offers/ViewOffer.vue";
import BidWSAPI from '../../API/BidWSAPI';
import Supplier from "../../models/supplier/Supplier";

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
		enableAcceptOffer: Boolean,
		enableCreateProposal: Boolean,
		supplier : Supplier

	},
	emits: ['accepted'],
	setup(props, { emit }) {
		const offers = ref([]);

		const formatDate = (dateStr) => {
			return new Date(dateStr).toLocaleString();
		};

		const getOffers = () => {
			bidWSAPI.getOffers(props.bid.id).then(res => {offers.value = res;});
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