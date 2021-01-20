<template>
	<tr v-if="task !== null">
		<th scope="row">
			{{ task.id }}
		</th>
		<td>{{ task.name }}</td>
		<td>{{ task.type }}</td>
		<td>{{ task.status }}</td>
		<td>{{ task.priority }}</td>
		<td>{{ task.creationDate }}</td>
		<td>
			<div
				@click="taskDone"
				class="btn btn-danger"
			>
				Done
			</div>
		</td>
	</tr>
</template>

<script>
import TaskWSAPI from '../../API/TaskWSAPI';
import Task from '../../models/department/Task';

const taskWSAPI = new TaskWSAPI();

export default {
	props:{
		task: {
			type: Task,
			default: null
		}
	},
	emits: ['updated'],
	setup(props,{emit}) {
		
		const taskDone = () => {
			taskWSAPI.putTask(props.task.id).then(res => {
				emit('updated',res);
			}).catch(error => {
				console.error(error);
			});
		};

		return {taskDone};
	}

};
</script>

<style>

</style>