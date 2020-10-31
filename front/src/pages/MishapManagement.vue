<template>
	<div class="container">
		<h1>Mishap management</h1>
		<MishapManagement />
	</div>
</template>

<script>

import MishapManagement from '../components/mishap-management/MishapForm.vue';

export default {
	components: {
		MishapManagement
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>