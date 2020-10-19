<template>
	<div>
		<div
			class="card"
			style="width: 18rem;"
			v-if="task !== undefined"
		>
			<div class="card-body">
				<h5 class="card-title">
					{{ task.name }}
				</h5>
				<p class="card-text">
					Type : {{ task.type }}<br>
					Status : {{ task.status }}
				</p>
			</div>
		</div>
	</div>
</template>

<script>
import {ref, onMounted} from 'vue';
import TaskWSAPI from '../../API/TaskWSAPI';

const taskWSAPI = new TaskWSAPI();

export default {

	setup() {
		const task = ref(undefined);
        
		onMounted(() => {
			taskWSAPI.getTask().then(res => {
				task.value = res;
			}).catch(error => {
				console.error(error);
			});
		});
		return {task};
	}

};
</script>

<style>

</style>