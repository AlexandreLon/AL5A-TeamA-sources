<template>
	<tr v-if="maintenance !== null">
		<th scope="row">
			{{ maintenance.id }}
		</th>
		<td>{{ maintenance.name }}</td>
		<td>{{ maintenance.type }}</td>
		<td>{{ maintenance.status }}</td>
		<td>
			<div
				@click="updateMaintenance"
				class="btn btn-warning"
			>
				Update
				<i class="fas fa-pencil-alt" />
			</div>
			<div
				@click="deleteMaintenance"
				class="btn btn-danger"
			>
				Delete
			</div>
		</td>
	</tr>
</template>

<script>

import MaintenanceWSAPI from "../../API/MaintenanceWSAPI";
import Maintenance from "../../models/maintenance-management/Maintenance";

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	props: {
		maintenance: {
			type: Maintenance,
			default: null
		}
	},
	emits: ["maintenance-updated", "maintenance-deleted"],
	setup(props, { emit }) {

		const updateMaintenance = () => {
			maintenanceWSAPI.updateMaintenance(props.maintenance.id).then(res => {
				emit("maintenance-updated", res);
			}).catch(error => {
				console.error(error);
			});
		};

		const deleteMaintenance = () => {
			maintenanceWSAPI.deleteMaintenance(props.maintenance.id).then(res => {
				emit("maintenance-deleted", res);
			}).catch(error => {
				console.error(error);
			});
		};

		return { updateMaintenance, deleteMaintenance };
	}

};
</script>

<style>

</style>