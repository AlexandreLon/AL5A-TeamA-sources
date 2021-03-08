<template>
	<div
		class="modal fade"
		tabindex="-1"
		:id="'checkAcceptedOfferModal' + bidid"
	>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Accepted Offer
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
					<p>Price : {{ price }}</p>
					<p>Date : {{ date }}</p>
					<p>Priority : {{ taskPriority }}</p>
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
import { onMounted, ref } from 'vue';
import BidWSAPI from '../../../API/BidWSAPI';

const bidAPI = new BidWSAPI();

export default {
	props: {
		bidid: {
			type: Number,
			default: 0
		}
	},
	setup(props) {
		const price = ref(null);
		const date = ref(null);
		const taskPriority = ref(null);

		onMounted(() => {
			console.log("coucou");
			bidAPI.getAcceptedOffer(props.bidid).then(res => {
				price.value = res.price;
				date.value = res.proposedDate;
				taskPriority.value = res.bid.task.priority;
			}).catch(err => {
				console.err(err);
			});
		});

		return {price, date, taskPriority};
	}
};
</script>

<style>

</style>