<template>
	<div
		class="modal fade"
		tabindex="-1"
		:id="'createOfferModal' + bidid"
	>
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						OutBid
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
					<div
						v-if="response.error"
						class="alert alert-danger"
						role="alert"
					>
						{{ error }}
					</div>
					<div
						v-if="response.success"
						class="alert alert-success"
						role="alert"
					>
						Offer registred
					</div>
					<form>
						<div class="form-group row">
							<label
								for="staticEmail"
								class="col-sm-2 col-form-label"
							>Price</label>
							<div class="col-sm-10">
								<input
									type="text"
									class="form-control"
									placeholder="price"
									v-model="price"
								>
							</div>
						</div>
						<div class="form-group row">
							<label
								for="inputPassword"
								class="col-sm-2 col-form-label"
							>Date</label>
							<div class="col-sm-10">
								<input
									class="form-control"
									type="date"
									v-model="date"
								>
							</div>
						</div>
						<div class="form-group row">
							<label
								for="inputPassword"
								class="col-sm-2 col-form-label"
							>Supplier</label>
							<div class="col-sm-10">
								<select
									class="form-control"
									v-model="supplier"
								>
									<option
										:value="sup.id"
										:key="sup.id"
										v-for="sup in suppliers"
									>
										{{ sup.name }}
									</option>
								</select>
							</div>
						</div>
					</form>
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
						class="btn btn-primary"
						@click="submit"
					>
						Create
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref } from 'vue';
import SupplierWSAPI from '../../../API/SupplierWSAPI';
import Supplier from "../../../models/supplier/Supplier";

const supplierAPI = new SupplierWSAPI();

export default {
	props: {
		bidid: {
			type: String,
			default: ''
		},
		suppliers: {
			type: [Supplier],
			default: []
		}
	},
	setup(props) {
		const price = ref(null);
		const date = ref(new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '').split(' ')[0]);
		const supplier = ref(null);

		const response = ref({error: null, success: false});

		const submit = async () => {
			supplierAPI.outbid({
				supplierId: supplier.value,
				price: parseInt(price.value, 10),
				proposedDate: new Date(date.value)
			}, props.bidid).then(() => {
				response.value.success = true;
				price.value = "";
				[date.value] = new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '').split(' ');
				supplier.value = null;
				setTimeout(() => {
					response.value = {error: null, success: false};
				}, 3000);
			}).catch(err => {
				response.value.error = err;
				price.value = "";
				[date.value] = new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '').split(' ');
				supplier.value = null;
				setTimeout(() => {
					response.value = {error: null, success: false};
				}, 3000);
			});
		};

		return {price, date, submit, supplier, response};
	}
};
</script>

<style>

</style>