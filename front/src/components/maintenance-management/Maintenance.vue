<template>
	<tr v-if="maintenance !== null">
		<th scope="row">
			{{ maintenance.id }}
		</th>
		<td>{{ maintenance.name }}</td>
		<td>{{ maintenance.type }}</td>
		<td>{{ maintenance.status }}</td>
		<td>
			<UpdateModal
				:maintenance-to-update="maintenance"
				@maintenance-updated="updateMaintenance"
			/>

			<div
				@click="deleteMaintenance"
				class="btn btn-danger"
			>
				<i class="fas fa-trash-alt" />
				Delete
			</div>
		</td>
	</tr>
</template>

<script>
import MaintenanceWSAPI from "../../API/MaintenanceWSAPI";
import Maintenance from "../../models/maintenance-management/Maintenance";
import UpdateModal from "./maintenance-modals/UpdateModal.vue";

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	components: { UpdateModal },
	props: {
		maintenance: {
			type: Maintenance,
			default: null
		}
	},
	emits: ["maintenance-updated", "maintenance-deleted"],
	setup(props, { emit }) {
		
		const updateMaintenance = (newMaintenance) => {
			emit("maintenance-updated", newMaintenance);
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