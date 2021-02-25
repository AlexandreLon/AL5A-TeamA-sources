<template>
	<div>
		<button
			class="btn btn-primary"
			data-toggle="modal"
			data-target="#modalCreateSupplier"
		>
			Create supplier
		</button>
		<div
			class="modal fade"
			id="modalCreateSupplier"
			tabindex="-1"
			aria-labelledby="modalCreateSupplierLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5
							class="modal-title"
							id="modalCreateSupplierLabel"
						>
							Create supplier
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
						<div class="form-group">
							<label for="supplierName">Name</label>
							<input
								type="text"
								class="form-control"
								id="supplierName"
								v-model="name"
							>
						</div>
						<div class="form-group">
							<label for="taskType">Task type</label>
							<select
								class="form-control"
								id="taskType"
								v-model="type"
							>
								<option value="VERIFICATION">
									VERIFICATION
								</option>
								<option value="CLEANING">
									CLEANING
								</option>
								<option value="REPLACING">
									REPLACING
								</option>
							</select>
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
							type="button"
							class="btn btn-success"
							@click="submit"
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
import { ref } from 'vue';
import SupplierWSAPI from '../../API/SupplierWSAPI';

const supplierAPI = new SupplierWSAPI();

export default {
	setup() {
		const name = ref(null);
		const type = ref("VERIFICATION");
		const success = ref(false);

		const submit = async () => {
			await supplierAPI.createSupplier({
				name: name.value,
				taskType: type.value
			});
			name.value = null;
			type.value = "VERIFICATION";
			success.value = true;
			setTimeout(() => {success.value = false;}, 2000);
		};

		return {name, type, submit};
	}
};
</script>

<style>

</style>