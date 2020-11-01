<template>
	<div>
		<MaintenanceForm />
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
						Creation date
					</th>
					<th scope="col">
						Actions
					</th>
				</tr>
			</thead>
			<tbody>
				<Maintenance
					v-for="maintenance of maintenances"
					:key="maintenance.id"
					:task="maintenance"
					@task-updated="update"
				/>
			</tbody>
		</table>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";
import MaintenanceWSAPI from "../../../API/MaintenanceWSAPI";
import Maintenance from "./Maintenance.vue";
import MaintenanceForm from "../MaintenanceForm.vue";

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	components: { Maintenance, MaintenanceForm },
	setup() {
		const maintenances = ref(null);

		onMounted(() => {
			maintenanceWSAPI
				.getMaintenances()
				.then(res => {
					maintenances.value = res;
				})
				.catch(error => {
					console.error(error);
				});
		});

		// function remove(deletedMaintenance) {
		// 	maintenances.value = maintenances.value.filter(current =>
		// 		current.id !== deletedMaintenance.id
		// 	);
		// }

		function update(newMaintenance) {
			maintenances.value = maintenances.value.map(current =>
				current.id === newMaintenance.id ? newMaintenance : current
			);
		}

		return { maintenances, update };
	}
};
</script>

<style></style>
