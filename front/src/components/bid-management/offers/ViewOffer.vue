<template>
	<div
		class="modal fade"
		tabindex="-1"
		:id="'viewOfferModal' + bidid"
	>
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						OutBid
					</h5>
					<button
						type="button"
						class="close"
						data-dismiss="modal"
						aria-label="Close"
					>
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">
									#
								</th>
								<th scope="col">
									Name
								</th>
								<th scope="col">
									Price
								</th>
								<th scope="col">
									Date
								</th>
								<th
									scope="col"
									v-if="enableAcceptOffer"
								>
									Actions
								</th>
							</tr>
						</thead>
						<tbody>
							<tr
								v-for="offer in offers"
								:key="offer.id"
							>
								<th scope="row">
									{{ offer.id }}
								</th>
								<td>{{ offer.supplier.name }}</td>
								<td>{{ new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'EUR' }).format(offer.price) }}</td>
								<td>{{ new Date(offer.proposedDate).toISOString().replace(/T/, ' ').replace(/\..+/, '').split(' ')[0] }}</td>
								<td v-if="enableAcceptOffer">
									<button
										type="button"
										class="btn btn-success"
										@click="acceptOffer(offer.id)"
									>
										Accept offer
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button
						type="button"
						class="btn btn-secondary"
						data-dismiss="modal"
					>
						Close
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>

import BidWSAPI from '../../../API/BidWSAPI';

const bidAPI = new BidWSAPI();

export default {
	props: {
		bidid: {
			type: Number,
			default: 0
		},
		offers: {
			type: Array,
			default: () => []
		},
		enableAcceptOffer : Boolean
	},
	emits: ["accepted"],
	setup(props, { emit }){
		const acceptOffer = async (id) => {
			bidAPI.acceptOffer(id).then(res => {
				emit('accepted', res);
			}).catch(err => {
				console.log(err);
			});
		};

		return {acceptOffer};
	}
};
</script>