<template>
	<div class="container">
		<h1>Maintenance management</h1>
		<MaintenanceManagement />
	</div>
</template>

<script>

import MaintenanceManagement from '../components/maintenance-management/MaintenanceForm.vue';

export default {
	components: {
		MaintenanceManagement
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>