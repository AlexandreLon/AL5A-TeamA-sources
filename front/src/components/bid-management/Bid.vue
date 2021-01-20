<template>
	<tr v-if="bid !== null">
		<th scope="row">
			{{ bid.id }}
		</th>
		<td>{{ bid.name }}</td>
		<td>{{ bid.type }}</td>
		<td>{{ bid.status }}</td>
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
	}
};
</script>

<style>

</style>