<template>
	<tr v-if="bid !== null">
		<th scope="row">
			{{ bid.id }}
		</th>
		<td>{{ bid.name }}</td>
		<td>{{ this.formatDate(bid.desiredDate) }}</td>
		<td>
			<button
				type="button"
				class="btn btn-warning"
			>
				<i class="fas fa-pencil-alt" />
				See Proposals
			</button>
		</td>
	</tr>
</template>

<script>
import Bid from "../../models/bid-management/Bid";

export default {
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