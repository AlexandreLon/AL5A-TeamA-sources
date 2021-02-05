<template>
	<div>
		<table class="table mt-4">
			<thead>
				<tr>
					<th scope="col">
						#
					</th>
					<th scope="col">
						Name
					</th>
					<th scope="col">
						Desired Date
					</th>
					<th scope="col">
						Status
					</th>
					<th scope="col">
						Actions
					</th>
				</tr>
			</thead>
			<tbody v-if="bids && bids.length > 0">
				<Bid
					v-for="bid of bids"
					:key="bid.id"
					:bid="bid"
					:supplier="supplier"
					@accepted="acceptedOffer"
					:enable-accept-offer="enableAcceptOffer"
					:enable-create-proposal="enableCreateProposal"
				/>
			</tbody>
			<tr
				v-else
				style="colspan:all; text-align: center;"
			>
				There is no bid to display.
			</tr>
		</table>
	</div>
</template>

<script>
import { ref, onMounted, watch } from "vue";
import BidWSAPI from "../../API/BidWSAPI";
import Bid from "./Bid.vue";
import Supplier from '../../models/supplier/Supplier';
import SupplierWSAPI from "../../API/SupplierWSAPI";

const bidWSAPI = new BidWSAPI();
const supplierWSAPI = new SupplierWSAPI();


export default {
	components: { Bid },
	props: {
		enableAcceptOffer: Boolean,
		enableCreateProposal: Boolean,
		supplier:Supplier,

	},
	setup(props) {
		const bids = ref(null);

		watch(() => props.supplier, (supplier) => {
			supplierWSAPI.getBids(supplier.id)
				.then(res => {
					bids.value = res;
				})
				.catch(error => {
					console.error(error);
				});
		});
		onMounted(() => {
			if(props.supplier != null){
				supplierWSAPI.getBids(props.supplier.id)
					.then(res => {
						bids.value = res;
					})
					.catch(error => {
						console.error(error);
					});
			}
			else{
				bidWSAPI
					.getBids()
					.then(res => {
						bids.value = res;
					})
					.catch(error => {
						console.error(error);
					});
			}
			
		});

		const acceptedOffer = async () => {
			bidWSAPI
				.getBids()
				.then(res => {
					bids.value = res;
				})
				.catch(error => {
					console.error(error);
				});
		};

		

		return { bids, acceptedOffer };
	}
};
</script>

<style></style>
