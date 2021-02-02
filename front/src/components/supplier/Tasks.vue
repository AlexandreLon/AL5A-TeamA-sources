<template>
	<div>
		<table class="table mt-4">
			<thead>
				<tr>
					<th scope="col">
						#
					</th>
					<th scope="col">
						Name
					</th>
					<th scope="col">
						Type
					</th>
					<th scope="col">
						Status
					</th>
					<th scope="col">
						Priority
					</th>
					<th scope="col">
						Creation date
					</th>
					<th scope="col">
						Actions
					</th>
				</tr>
			</thead>
			<tbody>
				<Task
					v-for="task of tasks"
					:key="task.id"
					:task="task"
					@updated="update($event)"
				/>
			</tbody>
		</table>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";
import TaskWSAPI from "../../API/TaskWSAPI";
import Task from "./Task.vue";
import SupplierWSAPI from "../../API/SupplierWSAPI";

const taskWSAPI = new TaskWSAPI();
const supplierWSAPI = new SupplierWSAPI();

export default {
	components: { Task },
	setup() {
		const tasks = ref(null);
		onMounted(() => {
			supplierWSAPI.getSuppliers().then(res => console.log(res.data));
			taskWSAPI
				.getTasks()
				.then(res => {
					tasks.value = res;
				})
				.catch(error => {
					console.error(error);
				});
		});

		function update(newTask) {
			tasks.value = tasks.value.map(currentTask =>
				currentTask.id === newTask.id ? newTask : currentTask
			);
		}

		return { tasks, update };
	}
};
</script>

<style></style>
