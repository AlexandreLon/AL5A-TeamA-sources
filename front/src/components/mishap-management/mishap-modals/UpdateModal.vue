<template>
	<div v-if="mishapToUpdate !== undefined">
		<button
			type="button"
			class="btn btn-warning"
			data-toggle="modal"
			:data-target="`#updateMishapModal${mishapToUpdate.id}`"
		>
			<i class="fas fa-pencil-alt" />
			Update
		</button>

		<div
			class="modal fade"
			:id="`updateMishapModal${mishapToUpdate.id}`"
			tabindex="-1"
			aria-labelledby="updateMishapModalLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5
							class="modal-title"
							id="updateMishapModalLabel"
						>
							Update mishap {{ mishapToUpdate.name }}
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
								v-if="displayMishapUpdateSuccess"
								class="alert-success p-2 mb-3"
							>
								{{ mishapToUpdate.name }} successfully updated to {{ updatedMishap.name }}
							</div>
							<input
								class="form-control mb-3"
								v-model="updatedMishap.name"
								placeholder="Update name"
							>
							<div>
								<select
									class="form-control mb-3"
									v-model="updatedMishap.type"
								>
									<option
										disabled
										:value="mishapToUpdate"
									>
										Please select a type
									</option>
									<option>Accident</option>
									<option>Weather</option>
									<option>Something else</option>
								</select>
							</div>
							<div>
								<select
									class="form-control mb-3"
									v-model="updatedMishap.priority"
								>
									<option
										disabled
										value=""
									>
										Please select a priority
									</option>
									<option>LOW</option>
									<option>MEDIUM</option>
									<option>HIGH</option>
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
							@click="updateMishap"
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
import MishapWSAPI from "../../../API/MishapWSAPI";
import Mishap from '../../../models/mishap-management/Mishap';

const mishapWSAPI = new MishapWSAPI();

export default {
	props:{
		mishapToUpdate: {
			type: Mishap,
			default: undefined
		}
	},
	emits: ['mishap-updated'],
	setup(props, {emit}) {

		const updatedMishap = ref({...props.mishapToUpdate});
		const displayMishapUpdateSuccess = ref(false);

		const updateMishap = () => {
			mishapWSAPI.updateMishap(props.mishapToUpdate.id, updatedMishap.value)
				.then(res => {
					if (updatedMishap.value !== null) {
						displayMishapUpdateSuccess.value = true;
					}
					emit("mishap-updated", res);
					setTimeout(() => {
						displayMishapUpdateSuccess.value = false;
					}, 2000); 		// TODO Close modal when displayMishapUpdateSuccess is false again

				}).catch(error => {
					console.error(error);
				}); 
		};

		return { updatedMishap, displayMishapUpdateSuccess, updateMishap };
	}
};
</script>

<style></style>