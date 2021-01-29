<template>
	<div
		class="modal fade"
		tabindex="-1"
		:id="'viewOfferModal' + bidid"
	>
		<div class="modal-dialog">
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
								<td>{{ Math.round(offer.price)/100 }}€</td>
								<td>{{ new Date(offer.proposedDate).toISOString().replace(/T/, ' ').replace(/\..+/, '').split(' ')[0] }}</td>
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
					<button
						type="button"
						class="btn btn-primary"
						@click="submit"
					>
						Create
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: {
		bidid: {
			type: Number,
			default: 0
		},
		offers: {
			type: Array,
			default: () => []
		}
	}
};
</script>