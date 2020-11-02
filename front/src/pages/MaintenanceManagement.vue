<template>
	<div class="container">
		<h1>Maintenance management</h1>
		<Maintenances />
	</div>
</template>

<script>
import Maintenances from "../components/maintenance-management/Maintenances.vue";

export default {
	components: {
		Maintenances
	}
};
</script>

<style scoped>
h1 {
	text-align: center;
}
</style>