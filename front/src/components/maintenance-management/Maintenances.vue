<template>
	<div>
		<CreationModal
			@created="createMaintenance($event)"
		/>

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
						Actions
					</th>
				</tr>
			</thead>
			<tbody v-if="maintenances && maintenances.length > 0">
				<Maintenance
					v-for="maintenance of maintenances"
					:key="maintenance.id"
					:maintenance="maintenance"
					@updated="update($event)"
					@deleted="remove"
				/>
			</tbody>
			<tr
				v-else
				style="colspan:all; text-align: center;"
			>
				There is no maintenance to display.
			</tr>
		</table>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";
import MaintenanceWSAPI from "../../API/MaintenanceWSAPI";
import Maintenance from "./Maintenance.vue";
import CreationModal from "./maintenance-modals/CreationModal.vue";

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	components: { Maintenance, CreationModal },
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

		function createMaintenance(newMaintenance) {
			maintenances.value.push(newMaintenance);
		}

		function update(newMaintenance) {
			console.log(newMaintenance);
			maintenances.value = maintenances.value.map(current =>
				current.id === newMaintenance.id ? newMaintenance : current
			);
		}

		function remove(deleteMaintenanceId) {
			maintenances.value = maintenances.value.filter(current =>
				current.id !== deleteMaintenanceId
			);
		}

		return { maintenances, createMaintenance, update, remove };
	}
};
</script>

<style></style>
