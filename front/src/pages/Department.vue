<template>
	<div class="container">
		<h1>Department</h1>
		<Task />
	</div>
</template>

<script>

import Task from '../components/department/Task.vue';

export default {
	components: {
		Task
	}
};
</script>

<style scoped>
h1 {
    text-align: center;
}
</style>