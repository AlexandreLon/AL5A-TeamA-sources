<template>
	<div>
		<button
			@click="resetState"
			type="button"
			class="btn btn-info"
			data-toggle="modal"
			data-target="#createMaintenanceModal"
		>
			<i class="fas fa-plus-circle" />
			Create a new maintenance
		</button>

		<div
			class="modal fade"
			id="createMaintenanceModal"
			tabindex="-1"
			aria-labelledby="createMaintenanceModalLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5
							class="modal-title"
							id="createMaintenanceModalLabel"
						>
							Add a new maintenance
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
								v-if="result"
								class="alert-success p-2 mb-3"
							>
								{{ maintenance.name }} successfully created
							</div>
							<input
								class="form-control mb-3"
								v-model="maintenance.name"
								placeholder="Ex.: Brakes checking"
							>
							<div>
								<select
									class="form-control mb-3"
									v-model="maintenance.type"
								>
									<option
										disabled
										value=""
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
							@click="createMaintenance"
						>
							Create
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>


<script>
import {ref} from "vue";
import MaintenanceWSAPI from "../../../API/MaintenanceWSAPI";

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	emits: ['maintenance-created'],
	setup(props, {emit}) {
		const maintenance = ref({name: '', type: ''});
		const result = ref(false);

		const resetState = () => {
			maintenance.value = {name: '', type: ''};
		};

		const createMaintenance = () => {
			if (maintenance.value.type !== "" &&
				maintenance.value.name !== "") {
				maintenanceWSAPI
					.createMaintenance(maintenance.value)
					.then(res => {
						maintenance.value = res;
						if (maintenance.value !== null) {
							result.value = true;
						}
						emit('maintenance-created', res);
						setTimeout(() => {
							result.value = false;
						}, 2000);
					})
					.catch(error => {
						console.error(error);
					});
			}
		};

		return {maintenance, resetState, createMaintenance, result};
	}
};
</script>

<style></style>