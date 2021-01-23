<template>
	<div>
		<button
			@click="resetState"
			type="button"
			class="btn btn-info"
			data-toggle="modal"
			data-target="#createMishapModal"
		>
			<i class="fas fa-plus-circle" />
			Create a new mishap
		</button>

		<div
			class="modal fade"
			id="createMishapModal"
			tabindex="-1"
			aria-labelledby="createMishapModalLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5
							class="modal-title"
							id="createMishapModalLabel"
						>
							Add a new mishap
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
						<div class="w-50 d-block m-auto">
							<div
								v-if="result"
								class="alert-success p-2 mb-3"
							>
								{{ mishap.name }} successfully created
							</div>
							<input
								class="form-control mb-3"
								v-model="mishap.name"
								placeholder="Ex : Train stopped"
							>
							<div>
								<select
									class="form-control mb-3"
									v-model="mishap.type"
								>
									<option
										disabled
										value=""
									>
										Please select a type
									</option>
									<option>Accident</option>
									<option>Meteo</option>
									<option>Something else</option>
								</select>
							</div>
							<div>
								<select
									class="form-control mb-3"
									v-model="mishap.priority"
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
							<div>Desired date of resolution <span>(the date will appear in the bid for suppliers)</span> :</div>
							<input
								class="form-control mb-3"
								type="datetime-local"
								v-model="mishap.desiredDate"
							>
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
							@click="createMishap"
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
import MishapWSAPI from "../../../API/MishapWSAPI";

const mishapWSAPI = new MishapWSAPI();

export default {
	emits: ['created'],
	setup(props, {emit}) {
		const mishap = ref({name: '', type: '', priority: ''});
		const result = ref(false);

		const resetState = () => {
			mishap.value = {name: '', type: '', priority: ''};
		};

		const createMishap = () => {
			if (mishap.value.type !== "" &&
				mishap.value.name !== "" &&
				mishap.value.priority !== "") {
				mishapWSAPI
					.createMishap(mishap.value)
					.then(res => {
						mishap.value = res;
						if (mishap.value !== null) {
							result.value = true;
						}
						emit('created', res);
						setTimeout(() => {
							result.value = false;
						}, 2000);
					})
					.catch(error => {
						console.error(error);
					});
			}
		};

		return {mishap, resetState, createMishap, result};
	}
};
</script>

<style></style>