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
				@updated="updateMaintenance"
			/>

			<div
				@click="abortMaintenance"
				class="btn btn-danger"
			>
				<i class="fas fa-trash-alt" />
				Abort
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
	emits: ["updated", "aborted"],
	setup(props, { emit }) {
		
		const updateMaintenance = (newMaintenance) => {
			emit("updated", newMaintenance);
		};

		const abortMaintenance = () => {
			maintenanceWSAPI.abortMaintenance(props.maintenance.id).then(res => {
				emit("aborted", res);
			}).catch(error => {
				console.error(error);
			});
		};

		return { updateMaintenance, abortMaintenance };
	}

};
</script>

<style>

</style>