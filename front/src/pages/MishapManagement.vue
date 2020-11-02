<template>
	<div class="container">
		<h1>Mishap management</h1>
		<Mishaps />
	</div>
</template>

<script>
import Mishaps from "../components/mishap-management/mishap-table/Mishaps.vue";

export default {
	components: {
		Mishaps
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>