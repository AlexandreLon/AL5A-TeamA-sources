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
						Actions
					</th>
				</tr>
			</thead>
			<tbody v-if="bids && bids.length > 0">
				<Bid
					v-for="bid of bids"
					:key="bid.id"
					:bid="bid"
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
import { ref, onMounted } from "vue";
import BidWSAPI from "../../API/BidWSAPI";
import Bid from "./Bid.vue";

const bidWSAPI = new BidWSAPI();

export default {
	components: { Bid },
	setup() {
		const bids = ref(null);

		onMounted(() => {
			bidWSAPI
				.getBids()
				.then(res => {
					bids.value = res;
				})
				.catch(error => {
					console.error(error);
				});
		});

		return { bids };
	}
};
</script>

<style></style>
