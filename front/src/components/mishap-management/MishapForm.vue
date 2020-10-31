<template>
	<div class="w-50 text-center d-block m-auto">
		<div
			v-if="result"
			class="alert-success p-2 mb-3"
		>
			{{ mishap.name }} successfully created
		</div>
		<input
			class="form-control mb-3"
			v-model="mishap.name"
			placeholder="Train derailed !!"
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
		<button
			class="btn btn-success"
			@click="createMishap"
		>
			Create
		</button>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";
import MishapWSAPI from "../../API/MishapWSAPI";

const mishapWSAPI = new MishapWSAPI();

export default {
	setup() {
		const mishap = ref({name:'',type:'', priority:''});
		const result = ref(false);

		onMounted(() => {});

		const createMishap = () => {
			if (
				mishap.value.type !== "" &&
                mishap.value.name !== ""
			)
				mishapWSAPI
					.postMishap(mishap.value)
					.then(res => {
						mishap.value = res;
						if(mishap.value !== null)
							result.value = true;
						setTimeout(()=>{result.value = false;},2000);
					})
					.catch(error => {
						console.error(error);
					});
		};

		return { mishap,createMishap,result };
	}
};
</script>

<style></style>
