<template>
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
			placeholder="Check brakes ..."
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
		<button
			class="btn btn-success"
			@click="createMaintenance"
		>
			Create
		</button>
	</div>
</template>

<script>
import {ref, onMounted} from "vue";
import MaintenanceWSAPI from "../../API/MaintenanceWSAPI";

const maintenanceWSAPI = new MaintenanceWSAPI();

export default {
	emits: ['maintenance-created'],
	setup(props, {emit}) {
		const maintenance = ref({name: '', type: ''});
		const result = ref(false);

		onMounted(() => {
		});

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

		return {maintenance, createMaintenance, result};
	}
};
</script>

<style></style>
