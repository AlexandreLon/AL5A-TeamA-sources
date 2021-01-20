<template>
	<div v-if="maintenanceToUpdate !== undefined">
		<button
			type="button"
			class="btn btn-warning"
			data-toggle="modal"
			:data-target="`#updateMaintenanceModal${maintenanceToUpdate.id}`"
		>
			<i class="fas fa-pencil-alt" />
			Update
		</button>

		<div
			class="modal fade"
			:id="`updateMaintenanceModal${maintenanceToUpdate.id}`"
			tabindex="-1"
			aria-labelledby="updateMaintenanceModalLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5
							class="modal-title"
							id="updateMaintenanceModalLabel"
						>
							Update maintenance {{ maintenanceToUpdate.name }}
						</h5>
						<button
							type="button"
							class="close"
							data-dismiss="modal"
							aria-label="Close"
						>
							<span aria-hidden="true">&times;</span>
						</button>
					</div>


					<div class="modal-body">
						<div class="w-50 text-center d-block m-auto">
							<div
								v-if="displayMaintenanceUpdateSuccess"
								class="alert-success p-2 mb-3"
							>
								{{ maintenanceToUpdate.name }} successfully updated to {{ updatedMaintenance.name }}
							</div>
							<input
								class="form-control mb-3"
								v-model="updatedMaintenance.name"
								placeholder="Update name"
							>
							<div>
								<select
									class="form-control mb-3"
									v-model="updatedMaintenance.type"
								>
									<option
										disabled
										:value="maintenanceToUpdate"
									>
										Please select a type
									</option>
									<option>Check a train</option>
									<option>Check rails</option>
									<option>Something else</option>
								</select>
							</div>
						</div>
					</div>


					<div class="modal-footer">
						<button
							type="button"
							class="btn btn-secondary"
							data-dismiss="modal"
						>
							Close
						</button>
						<button
							class="btn btn-success"
							@click="updateMaintenance"
						>
							Update
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>


<script>
import { ref } from "vue";
import MaintenanceWSAPI from "../../../API/MaintenanceWSAPI";
import Maintenance from '../../../models/maintenance-management/Maintenance';

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	props:{
		maintenanceToUpdate: {
			type: Maintenance,
			default: undefined
		}
	},
	emits: ['updated'],
	setup(props, {emit}) {

		const updatedMaintenance = ref({...props.maintenanceToUpdate});
		const displayMaintenanceUpdateSuccess = ref(false);

		const updateMaintenance = () => {
			maintenanceWSAPI.updateMaintenance(props.maintenanceToUpdate.id, updatedMaintenance.value)
				.then(res => {
					if (updatedMaintenance.value !== null) {
						displayMaintenanceUpdateSuccess.value = true;
					}
					emit("updated", res);
					setTimeout(() => {
						displayMaintenanceUpdateSuccess.value = false;
					}, 2000); 		// TODO Close modal when displayMaintenanceUpdateSuccess is false again

				}).catch(error => {
					console.error(error);
				}); 
		};

		return { updatedMaintenance, displayMaintenanceUpdateSuccess, updateMaintenance };
	}
};
</script>

<style></style>