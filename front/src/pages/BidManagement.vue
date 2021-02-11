<template>
	<div class="container">
		<h1>Bid management</h1>
		<Bids :enable-accept-offer="true" />
	</div>
</template>

<script>
import Bids from "../components/bid-management/Bids.vue";

export default {
	components: {
		Bids
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>