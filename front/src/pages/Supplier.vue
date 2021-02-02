<template>
	<div class="container">
		<h1>Supplier</h1>
		<Tasks />
	</div>
</template>

<script>

import Tasks from '../components/supplier/Tasks.vue';

export default {
	components: {
		Tasks
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>